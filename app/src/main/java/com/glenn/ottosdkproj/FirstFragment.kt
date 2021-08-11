package com.glenn.ottosdkproj

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.otto.ottosdk.OttoSdk
import org.json.JSONException
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
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

            OttoSdk.runAtDev().initSdk(
                activity as Context,
                "INDOLIFE",
                "deviceID",
                "085697366189",
                "",
                jsonStr,
                "9wT4ncv3Dpo5IEKGsMtzZ_ESbEEa", "7iUu0rGcTRZ0F5bqpaysMmNNL44a"
            ).start()
        }

    }
}