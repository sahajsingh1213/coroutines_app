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
//    lateinit var currentcont: TextView
//    lateinit var bigno: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var job = CoroutineScope(Dispatchers.IO).launch {
           Log.d("myTag","starting of long running task")

          for(i in 30..40){
           if(isActive) {
               Log.d("myTag", "fib($i) = ${fibonacci(i)}")
           }
          }

           Log.d("myTag","ending of long running task")
       }

        runBlocking {
            delay(500)
            job.cancel()


            Log.d("myTag","runBlocking block")
        }


    }
        fun fibonacci(n:Int):Long{
         return if(n==0)  0
         else if(n ==1 )  1
         else  fibonacci(n-1) + fibonacci(n-2)
        }

       }




