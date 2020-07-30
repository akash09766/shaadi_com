package com.shaadi.android.assessment_challenge.app.utils

object MConstants {

    const val CONNECTION_TIME_OUT = 25L

    // server response type
    const val SUCCESS = "SUCCESS"
    const val FAILED = "FAILED"
    const val ERROR = "ERROR"

    // network error message
    const val NOT_CONNECTED_TO_INTERNET = "You are not connected to internet."
    const val SERVER_NOT_RESPONDING =
        "Somethings wrong with our servers at the moment. Our best minds are on it. Please try again after some time."
    const val INVALID_SERVER_RESPONSE =
        "Somethings wrong with our servers at this movement.Our best minds are on it.\n Please try after some time."

    const val NO_INTERNET =
        "The internet connection you are connected to is not working please check."

    const val TRY_AGAIN = "Something went wrong please try again!"
    const val SNACK_BAR_OKAY_BTN_TEXT = "Okay"


    const val WENT_WRONG_ID = 1
    const val BACKEND_ERROR__ID = 2
    const val NETWORK_ERROR__ID = 3

    const val USER_ACCEPTANCE_STATUS_REJECTED = 0
    const val USER_ACCEPTANCE_STATUS_ACCEPTED = 1
    const val USER_ACCEPTANCE_STATUS_YET_TO_TAKE_DECISION = 2

    const val USER_AGE_UNIT = "yrs"

}