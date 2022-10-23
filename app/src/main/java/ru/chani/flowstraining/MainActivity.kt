package ru.chani.flowstraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flowTraining()
    }

    fun flowTraining() {
        val flow: Flow<Int> = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        flow
            .filter { it % 2 == 0 }
            .map { it * 100 }


        GlobalScope.launch {
            flow.collect {
                Log.d("TAG_MAIN", it.toString())
            }
        }

        MainScope().launch {
            flow
                .filter { it == 6 }
                .collect {
                    Log.d("TAG_MAIN", it.toString())
                }
        }
    }
}