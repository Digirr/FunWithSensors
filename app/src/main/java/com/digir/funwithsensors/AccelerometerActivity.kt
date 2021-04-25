package com.digir.funwithsensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digir.funwithsensors.databinding.ActivityAccelerometerBinding

class AccelerometerActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var B : ActivityAccelerometerBinding

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometer)
        B = ActivityAccelerometerBinding.bind(findViewById(R.id.rootAccelerometer))

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onStart() {
        super.onStart()
        if (accelerometer != null)
            sensorManager!!.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onStop() {
        super.onStop()
        sensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val current_xValue = event!!.values[0]
        val current_yValue = event!!.values[1]
        val current_zValue = event!!.values[2]

        B.accelerometrXText.text = resources.getString(R.string.accelerometer_x_value, current_xValue)
        B.accelerometrYText.text = resources.getString(R.string.accelerometer_y_value, current_yValue)
        B.accelerometrZText.text = resources.getString(R.string.accelerometer_z_value, current_zValue)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}