package com.otto.ottosdk

import android.app.Activity
import android.content.Context
import android.util.Log
import com.otto.ottosdk.dibs.ldiDev
import com.otto.ottosdk.dibs.ldiProd
import com.otto.ottosdk.dibs.ldiStaging
import com.otto.ottosdk.dibs.ldiZInterface


object OttoSdk {

    private lateinit var c1: ldiZInterface
    private val TAG = "OTTOSDK"
    private var isInit = false

    private var variant = 0;

    private fun iniSdkDev(
        ctx: Context,
        appid: String,
        deviceId: String,
        mbp: String,
        actId: String,
        addData: String,
        clientId: String,
        clientKey: String
    ) {
        c1 = ldiDev() as ldiZInterface
        c1.initiateSdk(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
        isInit = true
    }

    private fun iniSdkStaging(
        ctx: Context,
        appid: String,
        deviceId: String,
        mbp: String,
        actId: String,
        addData: String,
        clientId: String,
        clientKey: String
    ) {
        c1 = ldiStaging() as ldiZInterface
        c1.initiateSdk(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
        isInit = true
    }

    private fun initSdkProd(
        ctx: Context,
        appid: String,
        deviceId: String,
        mbp: String,
        actId: String,
        addData: String,
        clientId: String,
        clientKey: String
    ) {
        c1 = ldiProd() as ldiZInterface
        c1.initiateSdk(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
        isInit = true
    }


    fun initSdk(
        ctx: Context,
        appid: String,
        deviceId: String,
        mbp: String,
        actId: String,
        addData: String,
        clientId: String,
        clientKey: String
    ): OttoSdk {
        when (variant) {
            0 -> iniSdkDev(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
            1 -> iniSdkStaging(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
            2 -> initSdkProd(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
            else -> iniSdkDev(ctx, appid, deviceId, mbp, actId, addData, clientId, clientKey)
        }
        return this
    }

    fun runAtDev(): OttoSdk {
        variant = 0
        isInit = false
        return this
    }

    fun runAtStaging(): OttoSdk {
        variant = 1
        isInit = false
        return this
    }

    fun runAtProduction(): OttoSdk {
        variant = 2
        isInit = false
        return this
    }

    fun start() {
        if (isInit)
            c1.runOttosdk()
        else
            Log.e(TAG, "Ottosdk cant start cause it hasnt been initiated")
    }

    fun getBalancePhoneNumber(phoneNumber: String, activity: SdkActivity) {
        SdkActivity().initializeSDKForBalance(phoneNumber, activity)
    }

    fun iniSdkGetToken(
        ctx: Context
    ) : String {
        c1 = ldiDev() as ldiZInterface
        return c1.initiateSdkGetToken(ctx)
    }
}