package com.example.myapplication.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myapplication.data.errors.NoAuthExp
import com.firebase.ui.auth.AuthUI


abstract class BaseFragment<T, S : BaseViewState<T>> : Fragment() {

    companion object {
        const val RC_SINGIN = 5252
    }

    abstract val layoutRes: Int?
    abstract val viewModel: BaseViewModel<T, S>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutRes?.let { inflater.inflate(it, container, false) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getViewState().observe(this, Observer { state ->
            state ?: return@Observer
            state.error?.let {
                renderError(it)
                return@Observer
            }
            renderData(state.data)
        })

    }

    abstract fun renderData(data: T)

    protected fun renderError(e: Throwable?) {
        when (e) {
            is NoAuthExp -> startLogin()
            else -> e?.message?.let {
                showError(it)
            }

        }
    }

    private fun startLogin() {
        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        startActivityForResult(intent, RC_SINGIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
if (requestCode == RC_SINGIN && resultCode != Activity.RESULT_OK) {

}
    }

    protected fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}