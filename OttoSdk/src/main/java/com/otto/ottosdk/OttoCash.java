package com.otto.ottosdk;

import android.content.Context;

import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.support.util.CacheUtil;

public class OttoCash extends BaseActivity {
    public static String getBalance(Context context) {
        String saldo_ottocash = CacheUtil.getPreferenceString(IConfig.Companion.getSESSION_EMONEY_BALANCE(), context);
        return saldo_ottocash;
    }
}
