package com.glenn.ottosdkproj

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import app.beelabs.com.codebase.support.util.CacheUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.otto.ottosdk.IConfig
import com.otto.ottosdk.OttoCash
import com.otto.ottosdk.OttoSdk
import com.otto.ottosdk.SdkActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject


class MainActivity : SdkActivity() {

    var emoney: String? = null
    var isEmpty = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        swiperefresh.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                if (OttoSdk.iniSdkGetToken(this).equals("")) {
                } else {
                    OttoSdk.getBalancePhoneNumber("085697366189", this)
                    if (OttoCash.getBalance(this).equals("")) {
                        emoney_balance.text = "Aktivasi Akun"
                    } else {
                        emoney_balance.text = "Saldo " + OttoCash.getBalance(this)
                    }
                }
                swiperefresh.setRefreshing(false)
            }, 3000)

        }

        val check_out_payment = JSONObject()
        try {
            check_out_payment.put("description", "Bayar Tagihan dari Julo")
            check_out_payment.put("merchantId", "6346354")
            check_out_payment.put("merchantName", "Kantin Buah")
            check_out_payment.put("requestDate", "2021-02-16 09:47:37")
            check_out_payment.put("amount", 65000)
            check_out_payment.put("fee", 0)
            check_out_payment.put("total", 65000)
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        val jsonStr = check_out_payment.toString()


//        OttoSdk.runAtDev().initSdk(
//            this,
//            "PEDE",
//            "deviceID",
//            "087780496878",
//            "check_out_payment",
//            jsonStr,
//            "9wT4ncv3Dpo5IEKGsMtzZ_ESbEEa", "7iUu0rGcTRZ0F5bqpaysMmNNL44a"
//        ).start()

//        OttoSdk.runAtDev().initSdk(
//            this,
//            "PEDE",
//            "deviceID",
//            "087780496878",
//            "",
//            jsonStr,
//            "9wT4ncv3Dpo5IEKGsMtzZ_ESbEEa", "7iUu0rGcTRZ0F5bqpaysMmNNL44a"
//        ).start()


        if (OttoSdk.iniSdkGetToken(this).equals("")) {
        } else {
            OttoSdk.getBalancePhoneNumber("085697366189", this)
            if (OttoCash.getBalance(this).equals("")) {
                emoney_balance.text = "Aktivasi Akun"
            } else {
                emoney_balance.text = "Saldo " + OttoCash.getBalance(this)
            }
        }
    }

    override fun onResume() {
        OttoSdk.iniSdkGetToken(this)
        if (OttoSdk.iniSdkGetToken(this).equals("")) {
        } else {
            OttoSdk.getBalancePhoneNumber("085697366189", this)
            if (OttoCash.getBalance(this).equals("")) {
                emoney_balance.text = "Aktivasi Akun"
            } else {
                emoney_balance.text = "Saldo " + OttoCash.getBalance(this)
            }
        }
        OttoCash.getBalance(this)
        super.onResume()
    }


    override fun onStart() {
        OttoSdk.iniSdkGetToken(this)
        if (OttoSdk.iniSdkGetToken(this).equals("")) {
        } else {
            OttoSdk.getBalancePhoneNumber("085697366189", this)
            if (OttoCash.getBalance(this).equals("")) {
                emoney_balance.text = "Aktivasi Akun"
            } else {
                emoney_balance.text = "Saldo " + OttoCash.getBalance(this)
            }
        }
        OttoCash.getBalance(this)
        super.onStart()
    }

//    private fun onGetSaldoOttoCash() {
//        saldo_ottocash = OttoCash.getBalance(this)
//        if (saldo_ottocash.equals("")) {
//            emoney_balance.text = "Aktivasi Akun"
//        } else {
//            emoney_balance.text = saldo_ottocash
//        }
//    }

    fun getClientId(): String {
        return "cleintid"
    }

    fun getClientKey(): String {
        return "asdf"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}