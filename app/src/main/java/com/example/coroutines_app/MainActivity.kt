package com.example.coroutines_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var currentcont: TextView
    lateinit var bigno: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentcont = findViewById<TextView>(R.id.update_counter)
        var button = findViewById<Button>(R.id.update_button)
        var newb = findViewById<Button>(R.id.action)
        bigno = findViewById<TextView>(R.id.blocking_count)
        var specify = findViewById<TextView>(R.id.sp)

        button.setOnClickListener{
            currentcont.text = "${currentcont.text.toString().toInt()+1}"

        }
       var t = 0
       var job =  CoroutineScope(Dispatchers.IO).launch {
            newb.setOnClickListener {
                t += 1
                specify.text = "$t"

                CoroutineScope(Dispatchers.IO).launch {
                    execute()
                }

            }

        }
        CoroutineScope(Dispatchers.Default).launch {  job.join()}

    }

   suspend fun execute(){
       var job =  CoroutineScope(Dispatchers.IO).launch {
           for (i in 1..1000000000000) {
               //if (i == 1000000000000) {
               bigno.text = "${i}"


           }
       }
       job.join()
   }

}


