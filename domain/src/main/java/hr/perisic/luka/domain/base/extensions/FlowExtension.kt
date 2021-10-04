package hr.perisic.luka.domain.base.extensions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/** Factory method for MutableSharedFlow with BehaviorSubject-without-initial-value like behaviour */
fun <T> mutableSharedFlowWithLatest() = MutableSharedFlow<T>(replay = 1, extraBufferCapacity = 1)

inline fun safeCoroutineExceptionHandler(crossinline handler: (CoroutineContext, Throwable) -> Unit): CoroutineExceptionHandler =
    object : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            if (context.isActive) handler.invoke(context, exception)
            else println("Error occurred but the consumer is no longer active: $exception")
        }
    }