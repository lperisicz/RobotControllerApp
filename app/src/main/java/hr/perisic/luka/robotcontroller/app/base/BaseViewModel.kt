package hr.perisic.luka.robotcontroller.app.base

import androidx.lifecycle.ViewModel
import hr.perisic.luka.domain.base.extensions.mutableSharedFlowWithLatest
import hr.perisic.luka.domain.base.extensions.safeCoroutineExceptionHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

internal abstract class BaseViewModel<BaseViewState : Any> : ViewModel() {

    private val bgScope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    val viewStates = mutableSharedFlowWithLatest<BaseViewState>()

    protected inline fun <reified T: BaseViewState> query(flow: Flow<T>) = queryNotInline(flow)

    protected fun queryNotInline(flow: Flow<BaseViewState>) {
        bgScope.launch(
            safeCoroutineExceptionHandler { _ ,throwable ->
                println("Executing ${this@BaseViewModel::class.simpleName} failed: $throwable")
            }
        ) {
            flow
                .onEach { println("New emission of ${this@BaseViewModel::class.simpleName} {${it.javaClass.simpleName} $it}") }
                .collect { viewStates.emit(it) }
        }
    }

    protected fun runCommand(command: suspend () -> Unit): Job = bgScope.launch(
        safeCoroutineExceptionHandler { _ ,throwable ->
            println("Executing ${this@BaseViewModel::class.simpleName} failed: $throwable")
        }
    ) {
        command()
    }
}