package com.digir.funwithsensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digir.funwithsensors.databinding.ActivityProximityBinding

class ProximityActivity : AppCompatActivity(), SensorEventListener{

    private lateinit var B : ActivityProximityBinding

    private var sensorManager : SensorManager? = null
    private var proximitySensor : Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)
        B = ActivityProximityBinding.bind(findViewById(R.id.rootProximity))

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        proximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    override fun onStart() {
        super.onStart()

        if (proximitySensor != null) {
            sensorManager!!.registerListener(
                this,
                proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )

        }
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