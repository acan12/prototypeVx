package com.otto.ottosdk.model.api

import android.content.Context
import app.beelabs.com.codebase.base.BaseApi
import app.beelabs.com.codebase.support.util.CacheUtil
import com.otto.ottosdk.App
import com.otto.ottosdk.IConfig
import com.otto.ottosdk.callback.response.AccountBalanceResponse
import com.otto.ottosdk.callback.response.TokenResponse
import com.otto.ottosdk.model.interceptor.RSAInterceptor
import io.reactivex.Observable
import okhttp3.Interceptor
import java.util.*

class Api : BaseApi() {
    companion object {
        private fun initHeader(context: Context): Map<String, String> {
            var map: MutableMap<String, String> = HashMap()
            map["Authorization"] = "${"Bearer " + CacheUtil.getPreferenceString(
                IConfig.SESSION_ACCESS_TOKEN,
                context
            )}"
            map["accept"] = "application/json"
            map["Content-Type"] = "application/json"
            return map
        }

        @Synchronized
        private fun initApiDomain(): ApiService {
            return getInstance()
                .setupApiDomain(
                    "https://gateway-dev.ottodigital.id/ottocash-sdk/v1/",
                    App.getAppComponent(),
                    ApiService::class.java,
                    false,
                    60,
                    true
                ) as ApiService
        }

        @Synchronized
        private fun initApiDomainWithInterceptor(): ApiService {
            return getInstance()
                .setupApiDomain(
                    "https://gateway-dev.ottodigital.id/",
                    App.getAppComponent(),
                    ApiService::class.java,
                    false,
                    60,
                    true,
                    arrayOf<Interceptor>(
                        RSAInterceptor(
                            "9wT4ncv3Dpo5IEKGsMtzZ_ESbEEa",
                            "7iUu0rGcTRZ0F5bqpaysMmNNL44a"
                        )
                    ), null
                ) as ApiService
        }

        @Synchronized
        fun onAccountBalance(
            phone_number: String,
            context: Context
        ): Observable<AccountBalanceResponse> {
            return initApiDomain().callApiGetAccountBalance(
                initHeader(context),
                phone_number
            )
        }

        @Synchronized
        fun onToken(
            grant_type: String,
            scope: String
        ): Observable<TokenResponse> {
            return initApiDomainWithInterceptor().callApiGetToken(
                grant_type,
                scope
            )
        }
    }
}