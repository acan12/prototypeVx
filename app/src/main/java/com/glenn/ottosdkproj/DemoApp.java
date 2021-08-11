package com.glenn.ottosdkproj;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.otto.ottosdk.App;

import app.beelabs.com.codebase.base.BaseApp;
import app.beelabs.com.codebase.di.component.AppComponent;
import app.beelabs.com.codebase.di.component.DaggerAppComponent;
import io.github.inflationx.viewpump.ViewPump;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class DemoApp extends BaseApp {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();


        context = getApplicationContext();
        setupBuilder(DaggerAppComponent.builder(), this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .disableCustomViewInflation()
                .build());

        App.Companion.setupComponent(getAppComponent());
    }

    public static AppComponent getAppComponent() {
        if (context == null) return null;
        return getComponent();
    }
}
