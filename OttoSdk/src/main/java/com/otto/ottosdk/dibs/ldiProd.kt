package com.otto.ottosdk.dibs

import android.content.Context
import glenn.ca.webviewAuth.initiator.AuthWebviewLib
import glenn.ca.webviewAuth.interactor.WebviewAuthSessionManager

internal open class ldiProd : ldiZInterface {
    internal var lonk: String = "https://partner.ottocash.id:9443/ocsdk"

    var lib = AuthWebviewLib()

    override fun initiateSdk(
        mContext: Context,
        appid: String,
        deviceId: String,
        mbp: String,
        actId: String,
        addData: String,
        clientId: String,
        clientKey: String
    ) {
        lib.start(mContext).onProduction()
            .setupWebviewShortCut("", lonk, appid, deviceId, mbp, actId, addData)
            .setupSignature(clientId, clientKey)
    }

    override fun runOttosdk() {
        lib.initializeForCheckOutPayment()
    }

    override fun initiateSdkGetToken(ctx: Context): String {
        //        return lib.getUserToken(ctx)
        return WebviewAuthSessionManager(ctx).getAccessToken()
    }


}