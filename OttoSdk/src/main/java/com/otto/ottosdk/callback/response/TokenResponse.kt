package com.otto.ottosdk.callback.response

import app.beelabs.com.codebase.base.response.BaseResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class TokenResponse : BaseResponse() {
    var access_token: String = ""
    var scope: String = ""
    var token_type: String = ""
}