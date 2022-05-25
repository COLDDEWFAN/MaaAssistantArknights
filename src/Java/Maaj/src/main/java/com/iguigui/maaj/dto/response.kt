package com.iguigui.maaj.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject


@Serializable
data class HttpResponse(
    @SerialName("version")
    val data: JsonElement = EmptyBaseData.toJsonElement(),
    @SerialName("code")
    val code: Int = 0,
    @SerialName("message")
    val message: String = "success"
)

@Serializable
data class WsResponse(
    @SerialName("version")
    val data: JsonElement,
    @SerialName("type")
    val type: String,
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)

@Serializable
sealed class BaseData

@Serializable
data class GetVersion(
    @SerialName("version")
    val version: String
) : BaseData()

@Serializable
data class ConnectResponse(
    @SerialName("version")
    val id: String
) : BaseData()


@Serializable
data class ListInstance(
    @SerialName("version")
    val list: List<MaaInstanceInfo>
) : BaseData()

@Serializable
data class MaaInstanceInfo(
    @SerialName("id")
    val id: String,
    @SerialName("host")
    val host: String,
    @SerialName("adbPath")
    val adbPath: String,
    @SerialName("status")
    val status: Int,
) : BaseData()


object EmptyBaseData : BaseData()

fun BaseData.toJsonElement() =
    Json.encodeToJsonElement(Json.encodeToJsonElement(this).jsonObject.filterNot { it.key == "type" })



