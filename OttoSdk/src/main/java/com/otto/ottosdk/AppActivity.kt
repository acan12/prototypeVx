package com.otto.ottosdk

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.beelabs.com.codebase.base.BaseActivity


private const val RESTART_DELAY = 200

open class AppActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun restartApp(context: Context) {
        // Application needs to restart when user declined some permissions at runtime
        val restartIntent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        val intent =
            PendingIntent.getActivity(context, 0, restartIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + RESTART_DELAY, intent)
        System.exit(0)
    }

    open fun fullScreencall() {
        if (Build.VERSION.SDK_INT in 12..18) { // lower api
            val v: View = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView: View = window.decorView
            val uiOptions: Int =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }
    }

    /**
     * Show Fragment Allowing State Loss
     *
     * @param fragment
     * @param fragmentResourceID
     */
    open fun showFragmentAllowingStateLoss(
        fragment: Fragment?,
        fragmentResourceID: Int
    ) {
        if (fragment != null) {
            val fragmentManager =
                this.supportFragmentManager
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(fragmentResourceID, fragment)
            fragmentTransaction.detach(fragment)
            fragmentTransaction.attach(fragment)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

}