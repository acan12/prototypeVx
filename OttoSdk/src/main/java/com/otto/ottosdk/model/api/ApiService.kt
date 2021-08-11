package com.otto.ottosdk.model.api

import com.otto.ottosdk.callback.response.AccountBalanceResponse
import com.otto.ottosdk.callback.response.TokenResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET("accounts/info")
    fun callApiGetAccountBalance(
        @HeaderMap headers: Map<String, String>,
        @Query("mobile_phone_number") type: String
    ): Observable<AccountBalanceResponse>

    @POST("token")
    fun callApiGetToken(
        @Query("grant_type") grant_type: String,
        @Query("scope") scope: String
    ): Observable<TokenResponse>
}