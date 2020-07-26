package adapter

import Data
import MainPage
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geideaassessment.R
import com.example.geideaassessment.SecondActivity
import com.example.geideaassessment.databinding.PageListRowBinding


class MainPageAdapter(val pageData:MainPage,val context: Context) : Adapter<MainPageAdapter.PageViewHolder>(){




   inner class PageViewHolder(binding:PageListRowBinding): ViewHolder(binding.root) {
        var binding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:PageListRowBinding = PageListRowBinding.inflate(inflater)

        return PageViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return pageData.data.size
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        var data:Data = pageData.data[position]
        holder.binding.firstName.text = data.first_name
        holder.binding.lastName.text = data.last_name
        holder.binding.title.text = data.id.toString()
        holder.binding.mainView.setOnClickListener(View.OnClickListener {
            startActivity(Intent(context, SecondActivity::class.java))

        })
    }
}