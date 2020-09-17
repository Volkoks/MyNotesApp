package com.example.myapplication.ui.authScreen

import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.main.MainFragment


class AuthScreenFragment : BaseFragment<Boolean?, AuthViewState>() {
    override val layoutRes = R.layout.fragment_main
    override val viewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    override fun renderData(data: Boolean?) {
        if (data == true) {
            startMainFragment()
        }
    }

    fun startMainFragment() {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, MainFragment()).commit()
    }
}