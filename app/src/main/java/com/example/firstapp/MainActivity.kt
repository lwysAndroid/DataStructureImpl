package com.example.firstapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.firstapp.ui.theme.FirstAppTheme
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.java.canonicalName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }

        /* Log.d(TAG, "onCreate: ${Thread.currentThread().name}")
         lifecycleScope.launch {
             Log.d(TAG, "lifecycleScope.launch: ${Thread.currentThread().name}")
         }
         lifecycleScope.launch(Dispatchers.Main) {
             Log.d(TAG, "Dispatchers.Main ${Thread.currentThread().name}")
         }*/

        /* lifecycleScope.launch {
             val time = measureTimeMillis {
                 val response1 = async { network1() }
                 val response2 = async { network2() }
                 Log.d(TAG, "response1: ${response1.await()}")
                 Log.d(TAG, "response2: ${response2.await()}")
             }
             Log.d(TAG, "Total tme: ${time}")

         }*/

        lifecycleScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                launch {
                    delay(15000)
                }
                val response1 = network1()
                val response2 = network2()
                Log.d(TAG, "response1: ${response1}")
                Log.d(TAG, "response2: ${response2}")
            }
            Log.d(TAG, "Total tme: ${time}")

        }


    }
}


suspend fun network1(): String = coroutineScope {
    delay(2000)
    "Response 1"
}

suspend fun network2(): String {
    var someVar: String
    coroutineScope {
        /*
         it is launch in the same coroutineScope so the code runs sequentially
         */
        delay(1000)
        someVar = "Response 3"
        launch {
            delay(1000)
            "Response 5"
        }
    }
    CoroutineScope(coroutineContext).launch {/*
    it is launch in a new CoroutineScope so the code runs independently
    */
        delay(13000)
        "Response 4"
    }
    delay(2000)
    return "Response 2"
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.subtitle2,
        fontSize = 24.sp
    )
}

/*@Preview(
    showBackground = true,
    name = "Light Mode"
)*/
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    FirstAppTheme {
        Surface() {
            Greeting("Android")
        }
    }
}