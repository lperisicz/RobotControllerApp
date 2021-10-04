package hr.perisic.luka.robotcontroller.app.ui.barrier

import hr.perisic.luka.robotcontroller.app.base.BaseFragment
import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.robotcontroller.databinding.FragmentMapBarriersBinding
import org.koin.android.viewmodel.ext.android.viewModel

internal class MapBarriersFragment : BaseFragment<MapBarriersViewState, FragmentMapBarriersBinding>(
    FragmentMapBarriersBinding::inflate
) {

    companion object {
        const val TAG = "MapBarriersFragment"
    }

    override val model: BaseViewModel<MapBarriersViewState> by viewModel<MapBarriersViewModel>()

    override fun FragmentMapBarriersBinding.render(viewState: MapBarriersViewState) {
        binding.mapBarrierView.setMapBarriers(viewState.barriers)
    }
}