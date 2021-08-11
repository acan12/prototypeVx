package com.otto.ottosdk.dibs

import android.content.Context
import glenn.ca.webviewAuth.interactor.WebviewAuthSessionManager

internal class ldiDev : ldiProd() {
    init {
        lonk = "https://antares.pede.id/ocdev/"
    }

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
        lib.start(mContext).onDebug()
            .setupWebviewShortCut("", lonk, appid, deviceId, mbp, actId, addData)
            .setupSignature(clientId, clientKey)
    }

    override fun initiateSdkGetToken(ctx: Context): String {
//        return lib.getUserToken(ctx)
        return WebviewAuthSessionManager(ctx).getAccessToken()
    }
}