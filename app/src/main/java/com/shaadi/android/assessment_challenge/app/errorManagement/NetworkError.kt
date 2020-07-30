package com.shaadi.android.assessment_challenge.app.errorManagement

import android.util.Log
import com.shaadi.android.assessment_challenge.app.utils.MConstants
import com.shaadi.android.assessment_challenge.app.utils.NoConnectivityException
import com.google.gson.JsonParseException
import okhttp3.internal.http2.ConnectionShutdownException
import okhttp3.internal.http2.StreamResetException
import retrofit2.HttpException
import java.net.*
import javax.net.ssl.SSLHandshakeException

class NetworkError(private val throwable: Throwable) : Throwable() {
    val TAG = "NetworkError"

    fun getAppErrorMessage(): String {
        when (throwable) {
            is UnknownHostException -> {
                Log.e(TAG, "throwable is of type UnknownHostException")
                return MConstants.SERVER_NOT_RESPONDING
            }
            is NoConnectivityException -> {
                Log.e(TAG, "throwable is of type NoConnectivityException")
                return MConstants.NOT_CONNECTED_TO_INTERNET
            }
            is ConnectException -> {
                Log.e(TAG, "throwable is of type ConnectException")
                return MConstants.NO_INTERNET
            }
            is SocketTimeoutException -> {
                Log.e(TAG, "throwable is of type SocketTimeoutException")
                return MConstants.SERVER_NOT_RESPONDING
            }
            is ConnectionShutdownException -> {
                Log.e(TAG, "throwable is of type ConnectionShutdownException")
                return MConstants.NO_INTERNET
            }
            is SSLHandshakeException -> {
                Log.e(TAG, "throwable is of type SSLHandshakeException")
                return MConstants.TRY_AGAIN
            }
            is StreamResetException -> {
                Log.e(TAG, "throwable is of type StreamResetException")
                return MConstants.TRY_AGAIN
            }
            is ProtocolException -> {
                Log.e(TAG, "throwable is of type ProtocolException")
                return MConstants.TRY_AGAIN
            }is UnknownServiceException -> {
                Log.e(TAG, "throwable is of type UnknownServiceException")
                return MConstants.TRY_AGAIN
            }
            is IllegalStateException -> {
                Log.e(TAG, "throwable is of type IllegalStateException")
                return MConstants.INVALID_SERVER_RESPONSE
            }
            is JsonParseException -> {
                Log.e(TAG, "throwable is of type JsonParseException")
                return MConstants.INVALID_SERVER_RESPONSE
            }is HttpException -> {
                Log.e(TAG, "throwable is of type HttpException")
                return MConstants.INVALID_SERVER_RESPONSE
            }
            else -> {
                Log.d(TAG, "throwable is of type Default one")
                return MConstants.TRY_AGAIN
            }
        }
    }
}