package com.otto.ottosdk.dibs

import android.content.Context

internal interface ldiZInterface {
    fun initiateSdk(
        mContext: Context,
        appid: String,
        deviceId: String,
        mbp: String,
        actid: String,
        addData: String,
        clientId: String,
        clientKey: String
    )

    fun runOttosdk()

    fun initiateSdkGetToken(ctx: Context): String
}