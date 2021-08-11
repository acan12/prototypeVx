package com.otto.ottosdk.model

import android.content.Context
import app.beelabs.com.codebase.support.util.CacheUtil

interface ApiConfigs {
    companion object {
        fun getCredential(context: Context): String {
            return "3345d719-a068-33b8-ab28-f6e9d7b4bf2c"
        }
    }
}