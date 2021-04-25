package com.digir.funwithsensors

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.digir.funwithsensors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var B : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        B = ActivityMainBinding.bind(findViewById(R.id.root))

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //Light button
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            B.lightButton.setOnClickListener {
                val lightIntent = Intent(applicationContext, LightActivity::class.java)
                startActivity(lightIntent)
            }

        } else
            B.lightButton.isEnabled = false

        //Proximity button
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {

            B.proximityButton.setOnClickListener {
                val proximityIntent = Intent(applicationContext, ProximityActivity::class.java)
                startActivity(proximityIntent)
            }
        } else
            B.proximityButton.isEnabled = false

        //Accelerometr button
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            B.accelerometerButton.setOnClickListener {
                val accelerometerIntent = Intent(applicationContext, AccelerometerActivity::class.java)
                startActivity(accelerometerIntent)
            }
        } else
            B.accelerometerButton.isEnabled = false

    }
}