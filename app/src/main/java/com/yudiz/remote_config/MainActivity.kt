package com.yudiz.remote_config

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var currentAppVersion: String
    private lateinit var playStoreAppVersion: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentAppVersion = BaseApp.getCurrentAppVersion(this)
        playStoreAppVersion = FirebaseRemoteConfig.getInstance().getString(BaseApp.parameterKey)

        tvVersion.text = "v: $currentAppVersion"

        if (isUpdateAvailable()) {
            //stuff for redirecting user to play store

            val alertDialog = AlertDialog.Builder(this).setTitle("App update is available v: $playStoreAppVersion")
                    .setCancelable(false)
                    .setPositiveButton("Ok") { p0, p1 -> finish() }.create()
            alertDialog.show()
        }
    }

    private fun isUpdateAvailable(): Boolean {
        return !playStoreAppVersion.equals(currentAppVersion, true)
    }
}
