package com.digir.funwithsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digir.funwithsensors.databinding.ActivityLightBinding

class LightActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var B: ActivityLightBinding

    private var sensorManager : SensorManager? = null
    private var lightSensor : Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light)
        B = ActivityLightBinding.bind(findViewById(R.id.rootLight))

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        lightSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onStart() {
        super.onStart()
        if (lightSensor != null)
            sensorManager!!.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onStop() {
        super.onStop()
        sensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val currentValue = event?.values?.get(0)
        B.lightSensorText.text = resources.getString(R.string.light_text, currentValue)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}