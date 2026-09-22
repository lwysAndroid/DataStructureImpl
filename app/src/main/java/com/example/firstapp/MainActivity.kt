package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.firstapp.ui.theme.FirstAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.ContinuationInterceptor
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class MainActivity : ComponentActivity() {

    private val myOwnScope = CoroutineScope(context = Dispatchers.Default + SupervisorJob())

    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAppTheme {
                MainView()
            }
        }

//        testCoroutines()
        testFlows()
    }

    private fun testCoroutines() {
        /*with(lifecycleScope) {
            (0..10).forEach {
                launch {
                    firstSuspendFunction(name = "$it")
                }
            }
        }*/

        /*lifecycleScope.launch {
            coroutineSubtree()
        }*/

        /*lifecycleScope.launch {
            performBackgroundWork()
        }*/
        /*lifecycleScope.launch {
            testAsyncBuilder()
        }*/
        normalFunctionCallingSuspendFunctions(coroutineScope = lifecycleScope)
    }

    private fun testFlows() {
        val flowExample = flow {
            (0..100).forEach {
                emit(it)
            }
        }
        with(lifecycleScope) {
            launch {
                /* flowExample.collect {
                     println("CollectedForm Flow: $it")
                 }*/
            }
        }

        val mutableSharedFlow = MutableSharedFlow<Int>(replay = 2)
        lifecycleScope.launch {
            repeat(15) {
                mutableSharedFlow.emit(it)
                delay(duration = 1.seconds)
            }
        }

        val jobOfMutableSharedFlow = lifecycleScope.launch {
            delay(duration = 6.seconds)
            mutableSharedFlow.collect {
                println("CollectedForm MutableSharedFlow: $it")
            }
        }

        lifecycleScope.launch {
            delay(duration = 11.seconds)
            jobOfMutableSharedFlow.cancel()
            println("cancel jobOfMutableSharedFlow")
        }
    }
}

private fun normalFunctionCallingSuspendFunctions(coroutineScope: CoroutineScope) {
    coroutineScope.launch(context = Dispatchers.Main) {
        testAsyncBuilder()
        val dispatcher = coroutineContext[ContinuationInterceptor]
        println("Called from a no suspend function, Current Dispatcher: $dispatcher")
    }
}

@OptIn(ExperimentalTime::class)
private suspend fun testAsyncBuilder() {
    delay(duration = 2.seconds)
    /*
    * Once a coroutine is launched, its context (including its dispatcher,
    * Job, and name) is fixed for its entire lifecycle—unless that specific
    * coroutine explicitly changes its own context internally using withContext(...).
    */
    coroutineScope {
        launch(context = Dispatchers.Default) {
            (0..5).forEach {
                var dispatcher = coroutineContext[ContinuationInterceptor]
                if (it > 3) {
                    withContext(context = Dispatchers.Main) {
                        dispatcher = coroutineContext[ContinuationInterceptor]
                        println("seconds: $it, Current Dispatcher: $dispatcher")

                    }
                } else {
                    println("seconds: $it, Current Dispatcher: $dispatcher")
                }
                delay(duration = 1.seconds)
            }
        }
        val firstTask = async(context = Dispatchers.IO) {
            val dispatcher = coroutineContext[ContinuationInterceptor]
            delay(duration = 2.seconds)
            "Value of the first task, Current Dispatcher: $dispatcher"
        }
        val secondTask = async(context = Dispatchers.Main) {
            delay(duration = 2.seconds)
            val dispatcher = coroutineContext[ContinuationInterceptor]
            println("Current Dispatcher: $dispatcher")
            145.2F
        }

        // Awaits both results and use them
        val firstTaskValue = firstTask.await()
        val secondTaskValue = secondTask.await()

        println("$firstTaskValue. secondTaskValue: $secondTaskValue")

    }
}

@OptIn(ExperimentalTime::class)
private suspend fun performBackgroundWork() {
    delay(duration = 2.seconds)
    /*    Use CoroutineScope.launch() to run a task alongside other work when the result
    isn't needed, or you don't want to wait for it:*/
    coroutineScope {
        // Starts a coroutine that runs without blocking the scope
        val firstJob = launch {
            // Suspends to simulate background work
            delay(100.milliseconds)
            println("Sending notification in background")
        }
        launch { }
        // Main coroutine continues while a previous one suspends
        println("Scope continues, firstJob.isActive: ${firstJob.isActive}")
    }
}

suspend fun firstSuspendFunction(name: String) {
    withContext(Dispatchers.Default) {
        (0..20).forEach {
            println("$it, $name, ${Thread.currentThread().name}")
            delay(timeMillis = 500)
        }
    }

}

@OptIn(ExperimentalTime::class)
private suspend fun coroutineSubtree() {
    delay(duration = 2.seconds)
    // Root of the coroutine subtree
    coroutineScope { // this: CoroutineScope
        this.launch {
            this.launch {
                delay(duration = 2.seconds)
                println("Child of the enclosing coroutine completed")
            }
            println("Child coroutine 1 completed")
        }

        this.launch {
            delay(1.seconds)
            println("Child coroutine 2 completed")
        }
        // Runs only after all children in the coroutineScope have completed
        println("Coroutine scope completed")
    }
}
