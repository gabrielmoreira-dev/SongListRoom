package br.edu.ifsp.songlistroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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
    private lateinit var viewModel: SongViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
                val searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?) = false

                    override fun onQueryTextChange(p0: String?): Boolean {
                        songAdapter.filter.filter(p0)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem) = false
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
        setOnItemClickedListener()
    }

    private fun setOnItemClickedListener() = object : SongAdapter.SongListener {
        override fun onItemClicked(pos: Int) = Bundle().let {
            it.putInt("songId", songAdapter.songListFiltered[pos].id)
            findNavController().navigate(R.id.action_songListFragment_to_songDetailFragment, it)
        }
    }.let {
        songAdapter.setClickListener(it)
    }
}