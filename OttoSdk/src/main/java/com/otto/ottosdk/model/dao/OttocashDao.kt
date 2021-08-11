package com.otto.ottosdk.model.dao

import android.content.Context
import app.beelabs.com.codebase.base.BaseDao
import app.beelabs.com.codebase.base.contract.IDaoPresenter
import com.otto.ottosdk.callback.response.AccountBalanceResponse
import com.otto.ottosdk.callback.response.TokenResponse
import com.otto.ottosdk.model.api.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class OttocashDao(dao: IDaoPresenter?) : BaseDao() {
    constructor() : this(null)

    interface IAuthDao {
        fun onToken(
            grant_type: String,
            scope: String
        ) {
        }

        fun onAccountBalance(phone_number: String, context: Context) {}
    }

    fun onToken(
        grant_type: String,
        scope: String
    ): Observable<TokenResponse> {
        return Api.onToken(grant_type, scope).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun onAccountBalance(
        phone_number: String,
        context: Context
    ): Observable<AccountBalanceResponse> {
        return Api.onAccountBalance(phone_number, context).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}