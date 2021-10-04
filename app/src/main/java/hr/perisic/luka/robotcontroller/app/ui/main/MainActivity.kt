package hr.perisic.luka.robotcontroller.app.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ortiz.touchview.OnTouchImageViewListener
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.app.base.BaseActivity
import hr.perisic.luka.robotcontroller.app.position.RobotPositionFragment
import hr.perisic.luka.robotcontroller.app.ui.barrier.MapBarriersFragment
import hr.perisic.luka.robotcontroller.app.ui.login.LoginActivity
import hr.perisic.luka.robotcontroller.app.ui.login.LoginViewModel
import hr.perisic.luka.robotcontroller.app.ui.login.LoginViewState
import hr.perisic.luka.robotcontroller.app.ui.points.NavigationPointsFragment
import hr.perisic.luka.robotcontroller.app.ui.scale.MapScale
import hr.perisic.luka.robotcontroller.app.ui.widget.glide.GlideApp
import hr.perisic.luka.robotcontroller.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

internal class MainActivity : BaseActivity<MapViewState, ActivityMainBinding>(), OnTouchImageViewListener {

    companion object {
        fun start(context: Context) {
            Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this)
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getBinding(): (LayoutInflater) -> ActivityMainBinding {
        return ActivityMainBinding::inflate
    }

    override val viewModel by viewModel<MapViewModel>()

    private val authViewModel by viewModel<LoginViewModel>()

    override fun ActivityMainBinding.render(viewState: MapViewState) {
        if (viewState is MapViewState.Map) {
            displayImage(viewState.mapModel.url)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        CoroutineScope(Dispatchers.Main).launch {
            authViewModel.viewStates.collect {
                if (it is LoginViewState.LoggedState && !it.isLoggedIn) {
                    showLoginScreen()
                }
            }
        }
    }

    private fun showLoginScreen() {
        LoginActivity.start(this)
    }

    private fun setupUi() {
        loadRobotPositionFragment()

        binding.touchImageViewMap.setOnTouchImageViewListener(this)

        binding.layoutNavigation.buttonToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button1 -> loadRobotPositionFragment()
                    R.id.button2 -> loadNavigationPointsFragment()
                    else -> loadMapBarriersFragment()
                }
            }
        }
    }

    private fun loadRobotPositionFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayoutOverlay, RobotPositionFragment(), RobotPositionFragment.TAG
        ).commitNow()
    }

    private fun loadNavigationPointsFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayoutOverlay, NavigationPointsFragment(), NavigationPointsFragment.TAG
        ).commitNow()
    }

    private fun loadMapBarriersFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayoutOverlay, MapBarriersFragment(), MapBarriersFragment.TAG
        ).commitNow()
    }

    private fun displayImage(imageUrl: String) {
        GlideApp.with(binding.touchImageViewMap)
            .load(imageUrl)
            .error(R.drawable.ic_launcher_background)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    binding.touchImageViewMap.setImageDrawable(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit
            })
    }

    /*private fun displayImage(image: ByteArray?) {
        Glide.with(binding.touchImageViewMap)
            .load(image)
            .error(R.drawable.ic_launcher_background)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    binding.touchImageViewMap.setImageDrawable(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit
            })
    }*/

    override fun onMove() {
        println("NEW STATE: ${binding.touchImageViewMap.currentZoom}, ${binding.touchImageViewMap.scrollPosition.x}, ${binding.touchImageViewMap.scrollPosition.y},  ${binding.touchImageViewMap.zoomedRect}")
        viewModel.setScale(
            MapScale(
                scale = binding.touchImageViewMap.currentZoom,
                scrollPosition = binding.touchImageViewMap.scrollPosition
            )
        )
    }
}