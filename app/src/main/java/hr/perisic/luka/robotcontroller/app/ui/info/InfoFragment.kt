package hr.perisic.luka.robotcontroller.app.ui.info

import androidx.fragment.app.Fragment
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.app.base.BaseFragment
import hr.perisic.luka.robotcontroller.databinding.FragmentInfoBinding
import org.koin.android.viewmodel.ext.android.viewModel

internal class InfoFragment private constructor()
    : BaseFragment<InfoViewState, FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    companion object {
        const val TAG = "InfoFragment"

        fun newInstance(): Fragment = InfoFragment()
    }

    override val model by viewModel<InfoViewModel>()

    override fun FragmentInfoBinding.render(viewState: InfoViewState) {
        if (viewState is InfoViewState.Info) {
            textViewSpeed.text = getString(R.string.speed_format, viewState.infoModel.speed)
            textViewBattery.text = getString(R.string.battery_format, viewState.infoModel.battery.toInt())
            textViewTime.text = getString(R.string.time_on_format, viewState.infoModel.timeOn.toInt())
        }
    }
}