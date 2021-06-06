package dev.dschmidt.foodkmm.datasource.network

import io.ktor.client.*

expect class KtorClientFactory() {

    fun build(): HttpClient

}