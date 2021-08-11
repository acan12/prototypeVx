package com.otto.ottosdk

import android.content.Context
import android.os.Bundle
import app.beelabs.com.codebase.support.rx.RxCompositeDisposableManager
import app.beelabs.com.codebase.support.util.CacheUtil
import com.otto.ottosdk.callback.response.AccountBalanceResponse
import com.otto.ottosdk.callback.response.TokenResponse
import com.otto.ottosdk.presenter.IOttoCashView
import com.otto.ottosdk.presenter.OttoCashPresenter

open class SdkActivity : AppActivity(), IOttoCashView {
    companion object {
        var accessToken: String? = null
        var phoneNumberSend: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sdk)
    }

    fun initializeSDKForBalance(phoneNumber: String, activity: SdkActivity) {
        OttoCashPresenter(activity).onToken("client_credentials", "sdk-client")

        phoneNumberSend = phoneNumber
    }

    override fun onSuccessGetBalance(o: AccountBalanceResponse) {
        CacheUtil.putPreferenceString(
            IConfig.SESSION_EMONEY_BALANCE,
            o.data?.account?.balance_amount.toString(),
            this
        )
    }

    override fun onFailGetBalance(message: String) {
    }

    override fun onSuccessGetToken(o: TokenResponse, activity: SdkActivity) {
        CacheUtil.putPreferenceString(
            IConfig.SESSION_ACCESS_TOKEN,
            o.access_token,
            this@SdkActivity
        )

        //then
        accessToken = o.access_token
        if (accessToken.equals("")) {
        } else {
            RxCompositeDisposableManager.doAction(object : RxCompositeDisposableManager.OnProcess() {
                override fun onCall() {
                    // called when on process
                    OttoCashPresenter(activity).onAccountBalance(phoneNumberSend!!, activity)

                }
            }, object : RxCompositeDisposableManager.RxCallback() {
                override fun onComplete() {}
            })

        }
    }

    override fun onFailGetToken(message: String) {
    }
}