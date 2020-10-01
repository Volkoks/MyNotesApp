package com.example.myapplication.ui.authScreen

import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.main.MainFragment
import org.koin.android.viewmodel.ext.android.viewModel


class AuthScreenFragment : BaseFragment<Boolean?, AuthViewState>() {
    override val layoutRes = R.layout.fragment_auth_screen
    override val model: AuthViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        model.requestUser()
    }

    override fun renderData(data: Boolean?) {
        if (data == true) {
            startMainFragment()
        }else{
            Toast.makeText(activity,"ОШИБКА ОШИБКА!", Toast.LENGTH_SHORT).show()
        }
    }

    fun startMainFragment() {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, MainFragment()).commit()
    }
}