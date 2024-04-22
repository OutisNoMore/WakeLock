package com.example.wakelock

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager

import android.content.BroadcastReceiver
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create button to release/acquire
        val wakeLock: PowerManager.WakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
                    acquire()
                }
            }
    }

}

class startupOnBootUpReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {

            val activityIntent = Intent(context, MainActivity::class.java)

            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(activityIntent)
        }
    }
}