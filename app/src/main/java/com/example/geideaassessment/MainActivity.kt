package com.example.geideaassessment

import Connection.GetDataServiceInterface
import Connection.RetrofitInstance
import MainPage
import adapter.MainPageAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geideaassessment.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity(),MainActivityViewModel.ConnectionListener {
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter:MainPageAdapter
     lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.registerListener(this)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onConnectionSuccess(mainPage: MainPage) {
        Toast.makeText(applicationContext, "connection successful", Toast.LENGTH_LONG)
            .show()
        mAdapter = MainPageAdapter( mainPage)
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(applicationContext)
        binding.mainContent.progressCircular.hide()

        binding.mainContent.recyclerview.layoutManager = mLayoutManager
        binding.mainContent.recyclerview.itemAnimator = DefaultItemAnimator()
        binding.mainContent.recyclerview.adapter = mAdapter
        Log.d("vikram",mainPage.toString())
    }

    override fun onConnectionFailure() {
        binding.mainContent.progressCircular.hide()

    }

    override fun onStart() {
        super.onStart()
        viewModel.intializeConnection()
        binding.mainContent.recyclerview.setOnClickListener(View.OnClickListener {
            startActivity(Intent(applicationContext,SecondActivity::class.java))
        })
    }
}