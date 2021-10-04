package hr.perisic.luka.robotcontroller.app.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.android.scope.currentScope

internal abstract class BaseActivity<ViewState: Any, Binding : ViewBinding> : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected lateinit var binding: Binding

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    abstract val viewModel: BaseViewModel<ViewState>

    protected abstract fun Binding.render(viewState: ViewState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding()(layoutInflater)
        setContentView(binding.root)

        setup()
        binding.initializeView()
    }

    private fun setup() {
        scope.launch {
            viewModel.viewStates.collect {
                binding.render(it)
            }
        }
    }

    abstract fun getBinding(): (LayoutInflater) -> Binding

    protected open fun Binding.initializeView() {

    }

}
