package dev.dschmidt.foodkmm.datasource.network

import io.ktor.client.*

expect class KtorClientFactory() {

    fun build(): HttpClient

}
//TOKEN 9c8b06d329136da358c2d00e76946b0111ce2c48