package hr.perisic.luka.robotcontroller.app.position

import android.os.Bundle
import android.view.View
import android.widget.Toast
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.app.base.BaseFragment
import hr.perisic.luka.robotcontroller.app.ui.info.InfoFragment
import hr.perisic.luka.robotcontroller.app.ui.position.RobotPositionViewModel
import hr.perisic.luka.robotcontroller.app.ui.position.RobotPositionViewState
import hr.perisic.luka.robotcontroller.app.ui.widget.destination.DestinationRequestView
import hr.perisic.luka.robotcontroller.app.ui.widget.position.RobotPositionView
import hr.perisic.luka.robotcontroller.databinding.FragmentRobotPositionBinding
import org.koin.android.viewmodel.ext.android.viewModel

internal class RobotPositionFragment :
    BaseFragment<RobotPositionViewState, FragmentRobotPositionBinding>(FragmentRobotPositionBinding::inflate),
    DestinationRequestView.DestinationRequestListener, RobotPositionView.Listener {

    companion object {
        const val TAG = "RobotPositionFragment"
    }

    override val model by viewModel<RobotPositionViewModel>()

    override fun FragmentRobotPositionBinding.initializeView(view: View, savedInstanceState: Bundle?) {
        robotPositionView.listener = this@RobotPositionFragment
    }

    override fun FragmentRobotPositionBinding.render(viewState: RobotPositionViewState) {
        when (viewState) {
            is RobotPositionViewState.Position -> {
                binding.robotPositionView.setPosition(viewState.x, viewState.y)
            }
            is RobotPositionViewState.Scale -> {
                binding.robotPositionView.setScale(viewState.mapScale)
            }
            is RobotPositionViewState.Info -> {
                renderInfoFragment(viewState.visible)
            }
        }
    }

    private fun renderInfoFragment(visible: Boolean) {
        if (visible) {
            showInfoFragment()
        } else {
            hideInfoFragment()
        }
    }

    private fun showInfoFragment() {
        childFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(
                R.id.frameLayoutInfoContainer, InfoFragment.newInstance(), InfoFragment.TAG
            ).commitNow()
    }

    private fun hideInfoFragment() {
        childFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .remove(
                childFragmentManager.findFragmentByTag(InfoFragment.TAG) ?: return
            ).commitNow()
    }

    override fun onRequestDestination(x: Float, y: Float) {
        Toast.makeText(context, "Position: $x, $y", Toast.LENGTH_SHORT).show()
    }

    override fun onRobotClicked() {
        Toast.makeText(requireContext(), "Robot Clicked", Toast.LENGTH_SHORT).show()
        model.onRobotClicked()
    }

    override fun onOutsideClicked() {
        model.onOutsideClicked()
    }
}