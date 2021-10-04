package hr.perisic.luka.robotcontroller.app.ui.points

import hr.perisic.luka.robotcontroller.app.base.BaseFragment
import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.robotcontroller.databinding.FragmentNavigationPointsBinding
import org.koin.android.viewmodel.ext.android.viewModel

internal class NavigationPointsFragment: BaseFragment<NavigationPointsViewState, FragmentNavigationPointsBinding>(
    FragmentNavigationPointsBinding::inflate
) {

    companion object {
        const val TAG = "NavigationPointsFragment"
    }

    override val model: BaseViewModel<NavigationPointsViewState> by viewModel<NavigationPointsViewModel>()

    override fun FragmentNavigationPointsBinding.render(viewState: NavigationPointsViewState) {
        binding.navigationPointsView.setNavigationPoints(viewState.navigationPoints)
    }
}