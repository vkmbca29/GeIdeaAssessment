package viewModel

import Connection.GetDataServiceInterface
import Connection.RetrofitInstance
import MainPage
import adapter.MainPageAdapter
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel :ViewModel(){
    val pageCount:Int = 20
    var listener:ConnectionListener? = null

    var serviceInterface:GetDataServiceInterface = RetrofitInstance.retrofitInstance!!.create(
        GetDataServiceInterface::class.java
    )
    interface ConnectionListener{
        fun onConnectionSuccess(mainPage: MainPage)
        fun onConnectionFailure()
    }
    fun registerListener(connectionListnere:ConnectionListener){
        listener = connectionListnere
    }
    fun unregisterListener(){
        listener = null
    }
    fun intializeConnection(){
        val searchQueryCall: Call<MainPage?>? =
            serviceInterface.getResult(pageCount)
        searchQueryCall?.enqueue(object : Callback<MainPage?> {
            override fun onResponse(
                call: Call<MainPage?>,
                response: Response<MainPage?>
            ) {
                listener?.onConnectionSuccess(response.body() as MainPage)
                /*Toast.makeText(applicationContext, "connection successful", Toast.LENGTH_LONG)
                    .show()
                mAdapter = MainPageAdapter(response.body() as MainPage)
                val mLayoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(applicationContext)

                binding.mainContent.recyclerview.layoutManager = mLayoutManager
                binding.mainContent.recyclerview.itemAnimator = DefaultItemAnimator()
                binding.mainContent.recyclerview.adapter = mAdapter
                Log.d("vikram",response.body().toString())*/
            }

            override fun onFailure(call: Call<MainPage?>, t: Throwable) {
//                Toast.makeText(applicationContext, "connection failed", Toast.LENGTH_LONG)
//                    .show()
                listener?.onConnectionFailure()
            }
        })
    }

}