package dev.dschmidt.foodkmm.domain.util

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
) {
    companion object {
        fun <T> error(
            throwable: Throwable
        ): DataState<T> {
            return DataState(message = throwable.message ?: "Unknown Error", error = throwable)
        }

        fun <T> data(
            message: String? = null,
            data: T? = null,
        ): DataState<T> {
            return DataState(message = message, data = data)
        }

        fun <T> loading() = DataState<T>(isLoading = true)
    }
}