package com.otto.ottosdk.presenter

import app.beelabs.com.codebase.base.contract.IView
import com.otto.ottosdk.SdkActivity
import com.otto.ottosdk.callback.response.AccountBalanceResponse
import com.otto.ottosdk.callback.response.TokenResponse

interface IOttoCashView : IView {
    fun onSuccessGetToken(o: TokenResponse, activity: SdkActivity) {}
    fun onFailGetToken(message: String) {}

    fun onSuccessGetBalance(o: AccountBalanceResponse) {}
    fun onFailGetBalance(message: String) {}
}