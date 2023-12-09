package br.edu.ifsp.songlistroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.songlistroom.R
import br.edu.ifsp.songlistroom.adapter.SongAdapter
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.databinding.FragmentSongListBinding
import br.edu.ifsp.songlistroom.viewmodel.SongViewModel

class SongListFragment : Fragment() {
    private lateinit var binding: FragmentSongListBinding
    private lateinit var  viewModel: SongViewModel
    private lateinit var songAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SongViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongListBinding.inflate(inflater, container, false)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_songListFragment_to_addSongFragment)
        }
        configureRecyclerView()
        return binding.root
    }

    private fun configureRecyclerView() {
        songAdapter = SongAdapter()
        viewModel.songList.observe(viewLifecycleOwner) {
            it?.let { songAdapter.updateList(it as ArrayList<Song>) }
        }
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = songAdapter
        }
    }
}