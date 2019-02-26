package jey.co.`in`.kotlin.first.network

data class NetworkResult<T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data)
        }

        fun <T> error(msg: String, data: T?): NetworkResult<T> {
            return NetworkResult(Status.ERROR, data)
        }

    }


}