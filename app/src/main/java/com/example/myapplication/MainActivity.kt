package com.example.myapplication


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.ui.authScreen.AuthScreenFragment
import com.firebase.ui.auth.AuthUI


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AuthScreenFragment()).commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_setting->{
                Toast.makeText(this, "Должны открыться настройки - но их пока нет=)", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.exit->{
                logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun logOut(){
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AuthScreenFragment()).commit()
            }
    }
}