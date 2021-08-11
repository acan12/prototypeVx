package com.otto.ottosdk.presenter

import android.content.Context
import app.beelabs.com.codebase.base.BasePresenter
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse
import app.beelabs.com.codebase.support.rx.RxObserver
import com.otto.ottosdk.R
import com.otto.ottosdk.SdkActivity
import com.otto.ottosdk.callback.response.AccountBalanceResponse
import com.otto.ottosdk.callback.response.TokenResponse
import com.otto.ottosdk.model.dao.OttocashDao

class OttoCashPresenter(var iview: IView) : BasePresenter(), OttocashDao.IAuthDao {
    override fun onToken(grant_type: String, scope: String) {
        OttocashDao().onToken(grant_type, scope)
            .subscribe(object : RxObserver<BaseResponse>(
                iview, "Loading"
            ) {
                override fun onNext(o: Any) {
                    super.onNext(o)
                    val model = o as TokenResponse
                    (iview as SdkActivity).onSuccessGetToken(
                        o
                        , iview as SdkActivity
                    )
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    (iview as SdkActivity).onFailGetToken(e!!.message!!)
                }
            })
    }


    override fun onAccountBalance(phone_number: String, context: Context) {
        OttocashDao().onAccountBalance(phone_number, context)
            .subscribe(object : RxObserver<BaseResponse>(
                iview
            ) {
                override fun onNext(o: Any) {
                    super.onNext(o)
                    val model = o as AccountBalanceResponse
                    if (model.code == 200) {
                        (iview as SdkActivity).onSuccessGetBalance(
                            o
                        )
                    } else if (model.code == 500) {
                        (iview as SdkActivity).onFailGetBalance(
                            o.message!!
                        )
                    } else {
                        (iview as SdkActivity).onFailGetBalance(
                            o.message!!
                        )
                    }
                }

                override fun onError(e: Throwable?) {
                    super.onError(e)
                    (iview as SdkActivity).onFailGetBalance(e!!.message!!)
                }
            })
    }
}