package ru.chani.flowstraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flowTraining()
    }

    private fun flowTraining() {
        GlobalScope.launch {
            printHelloWorldWithProgress().collect { progress ->
                Log.d("TAG_MAIN", progress.toString())
            }
        }
    }

    private fun printHelloWorldWithProgress(): Flow<Int> = flow<Int> {
        var progress = 0
        while (progress < 100) {
            progress++
            delay(40)
            emit(progress)
        }
        Log.d("TAG_MAIN", "HELLO WORLD!")
    }.flowOn(Dispatchers.IO)
}