package id.reza.rijks.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.reza.rijks.databinding.ItemListBinding
import id.reza.rijks.model.ArtObjects

class ListMovieAdapter(private val list: List<ArtObjects>) :
    RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {

    lateinit var context: Context

    var mInterface: Interface? = null

    fun setInterface(mInterface: Interface?) {
        this.mInterface = mInterface
    }

    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = list[position]

        Log.e("Adapter", "Data ${data.id}")
        holder.binding.txtTitle.text = data.title

        Glide.with(context).load(data.webImage!!.headerIurlmage).into(holder.binding.imgThumbnail)

        holder.binding.container.setOnClickListener {
            mInterface?.onClickDetail(data, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface Interface {
        fun onClickDetail(item: ArtObjects?, position: Int)
    }
}