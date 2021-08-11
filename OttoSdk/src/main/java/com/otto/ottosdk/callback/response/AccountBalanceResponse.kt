package com.otto.ottosdk.callback.response

import app.beelabs.com.codebase.base.response.BaseResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class AccountBalanceResponse : BaseResponse() {
    var code = 0
    var message: String? = null
    var data: DataResponse? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class DataResponse {
        var trace_id: String = ""
        var mobile_phone_number: String = ""
        var account: Account? = null
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Account {
        var account_id: Int = 0
        var account_status: String = ""
        var kyc_status: String = ""
        var name: String = ""
        var balance_amount: Int = 0
    }
}