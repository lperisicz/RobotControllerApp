package hr.perisic.luka.robotcontroller.app.ui.login

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.app.base.BaseActivity
import hr.perisic.luka.robotcontroller.app.ui.main.MainActivity
import hr.perisic.luka.robotcontroller.databinding.ActivityLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel

internal class LoginActivity : BaseActivity<LoginViewState, ActivityLoginBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override val layoutId: Int = R.layout.activity_login

    override val viewModel by viewModel<LoginViewModel>()

    override fun ActivityLoginBinding.render(viewState: LoginViewState) {
        if (viewState is LoginViewState.LoggedState) {
            if (viewState.isLoggedIn) {
                showMainScreen()
            }
        }
    }

    override fun getBinding(): (LayoutInflater) -> ActivityLoginBinding = ActivityLoginBinding::inflate

    override fun ActivityLoginBinding.initializeView() {
        buttonLogin.setOnClickListener {
            getLoginParams()?.let {
                viewModel.loginUser(
                    email = it.first,
                    password = it.second
                )
            }
        }
    }

    private fun showMainScreen() {
        MainActivity.start(this)
    }

    private fun ActivityLoginBinding.getLoginParams(): Pair<String, String>? {
        val email = editTextEmail.text?.toString() ?: return null.also {
            inputLayoutEmail.error = "Required"
        }
        val password = editTextPassword.text?.toString() ?: return null.also {
            inputLayoutPassword.error = "Required"
        }
        return email to password
    }
}