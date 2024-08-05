package com.example.compassapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi

class SecoundActivity : AppCompatActivity(), SensorEventListener {

    var sensor: Sensor? = null
    var sensorManager : SensorManager? = null
    lateinit var  comapassimage : ImageView
    lateinit var  digreetv : TextView
    lateinit var  direction : TextView
    lateinit var  view :View
    var currentdigree = 0f

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secound)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as  SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION)

        comapassimage = findViewById(R.id.imageView2)
        digreetv = findViewById(R.id.textView)
        direction = findViewById(R.id.showdirections)
        view = findViewById(R.id.view)



    }

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n", "ResourceType")

    override fun onSensorChanged(event: SensorEvent?) {
        val digree = Math.round(event!!.values[0])
        digreetv.text =" $digree \u00B0 "
        val rotationanimation = RotateAnimation(currentdigree ,((-digree).toFloat()) ,

            Animation.RELATIVE_TO_SELF ,0.5f ,
            Animation.RELATIVE_TO_SELF , 0.5f)
        rotationanimation.duration =210
        rotationanimation.fillAfter =true
        comapassimage.startAnimation(rotationanimation)
        currentdigree = (-digree.toFloat())





        if (digree in 0..22)
        {
            direction.text = "North"
        }
        if (digree in 23..67)
        {
            direction.text = "NorthEast"
        }
        if (digree in 68..112)
        {
            direction.text = "East"
        }
        if (digree in 113..157)
        {
            direction.text = "Southeast"
        }
        if (digree in 158..202)
        {
            direction.text = "South"
        }
        if (digree in 203..247)
        {
            direction.text = "Southwest"
        }
        if (digree in 248..292)
        {
            direction.text = "West"
        }
        if (digree in 293..336)
        {
            direction.text = "Northwest"
        }
        if (digree >336)
        {
            direction.text = "North"
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, acuracy: Int) {

    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager?.unregisterListener(this)
    }


}