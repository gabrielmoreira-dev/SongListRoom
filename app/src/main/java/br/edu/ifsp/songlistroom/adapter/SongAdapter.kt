package br.edu.ifsp.songlistroom.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.databinding.SongCellBinding
import com.bumptech.glide.Glide

class SongAdapter(): RecyclerView.Adapter<SongAdapter.SongViewHolder>(), Filterable {
    private lateinit var binding: SongCellBinding
    private var listener: SongListener? = null
    var songList = ArrayList<Song>()
    var songListFiltered = ArrayList<Song>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Song>) {
        songList = newList
        songListFiltered = songList
        notifyDataSetChanged()
    }

    fun setClickListener(listener: SongListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        binding = SongCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        songListFiltered[position].let {
            holder.name.text = it.name
            holder.artist.text = it.artist
            Glide.with(holder.itemView).load(it.image).into(holder.logo)
        }
    }

    override fun getItemCount() = songListFiltered.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            songListFiltered = if (p0.toString().isEmpty()) songList else {
                ArrayList(songList.filterIndexed { _, song ->
                    song.name.lowercase().contains(p0.toString())
                })
            }
            FilterResults().let {
                it.values = songListFiltered
                return it
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            songListFiltered = p1?.values as ArrayList<Song>
            notifyDataSetChanged()
        }
    }

    inner class SongViewHolder(view: SongCellBinding): RecyclerView.ViewHolder(view.root) {
        val name = view.nameTv
        val artist = view.artistTv
        val logo = view.logoIv

        init {
            view.root.setOnClickListener {
                listener?.onItemClicked(adapterPosition)
            }
        }
    }

    interface SongListener {
        fun onItemClicked(pos: Int)
    }
}