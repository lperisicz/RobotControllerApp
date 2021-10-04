package hr.perisic.luka.robotcontroller.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal abstract class BaseFragment<ViewState : Any, Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> Binding
) : Fragment() {

    abstract val model: BaseViewModel<ViewState>

    private var _binding: Binding? = null

    protected val binding: Binding
        get() = _binding!!

    protected abstract fun Binding.render(viewState: ViewState)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.root.isClickable = true // To avoid clicks passing through
        binding.initializeView(view, savedInstanceState)
    }

    /** Override to initialise view */
    protected open fun Binding.initializeView(view: View, savedInstanceState: Bundle?) {
        // Template
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        model.viewStates.apply {
            scope().launch {
                collect {
                    binding.render(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    fun viewBoundJob(job: suspend () -> Unit) {
        scope().launch {
            job()
        }
    }

    fun scope() = viewLifecycleOwner.lifecycleScope
}