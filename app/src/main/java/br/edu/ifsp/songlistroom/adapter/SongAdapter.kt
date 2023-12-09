package br.edu.ifsp.songlistroom.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.databinding.SongCellBinding

class SongAdapter(): RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    private lateinit var binding: SongCellBinding
    private var listener: SongListener? = null
    var songList = ArrayList<Song>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Song>) {
        songList = newList
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
        songList[position].let {
            holder.name.text = it.name
            holder.artist.text = it.artist
        }
    }

    override fun getItemCount() = songList.size

    inner class SongViewHolder(view: SongCellBinding): RecyclerView.ViewHolder(view.root) {
        val name = view.nameTv
        val artist = view.artistTv

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