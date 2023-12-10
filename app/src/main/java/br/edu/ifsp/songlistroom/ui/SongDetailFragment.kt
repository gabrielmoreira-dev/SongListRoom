package br.edu.ifsp.songlistroom.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.songlistroom.R
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.databinding.FragmentSongDetailBinding
import br.edu.ifsp.songlistroom.viewmodel.SongViewModel
import com.google.android.material.snackbar.Snackbar

class SongDetailFragment : Fragment() {
    private lateinit var binding: FragmentSongDetailBinding
    private lateinit var viewModel: SongViewModel

    private val songId: Int by lazy {
        requireArguments().getInt("songId")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SongViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongDetailBinding.inflate(inflater, container, false)
        binding.apply {
            songLt.saveBt.setOnClickListener { saveSong() }
            songLt.cancelBt.setOnClickListener { cancel() }
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getContactById(songId)
        setEditMode(requireArguments().getBoolean("isEditMode", false))
        viewModel.song.observe(viewLifecycleOwner) {
            it?.let { setSong(it) }
        }
        configureMenu()
    }

    private fun setSong(song: Song) = binding.songLt.apply {
        nameEt.setText(song.name)
        artistEt.setText(song.artist)
        albumEt.setText(song.album)
        imageEt.setText(song.image)
        favoriteCb.isChecked = song.isFavorite
    }

    private fun configureMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.song_detail_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem) = when(menuItem.itemId) {
                R.id.actionEditSong -> {
                    setEditMode(true)
                    true
                }
                R.id.actionDeleteSong -> {
                    deleteSong()
                    true
                }
                else -> false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setEditMode(isEditMode: Boolean) {
        binding.songLt.apply {
            saveBt.visibility = if (isEditMode) View.VISIBLE else View.INVISIBLE
            cancelBt.visibility = if (isEditMode) View.VISIBLE else View.INVISIBLE
            nameEt.isEnabled = isEditMode
            artistEt.isEnabled = isEditMode
            albumEt.isEnabled = isEditMode
            imageEt.isEnabled = isEditMode
            favoriteCb.isEnabled = isEditMode
        }
    }

    private fun saveSong() {
        viewModel.update(getCurrentSong())
        Snackbar.make(binding.root, getString(R.string.song_updated), Snackbar.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun cancel() {
        viewModel.song.value?.let { setSong(it) }
        setEditMode(false)
    }

    private fun deleteSong() {
        viewModel.delete(getCurrentSong())
        Snackbar.make(binding.root, getString(R.string.song_deleted), Snackbar.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun getCurrentSong() = binding.songLt.let {
        Song(
            id = songId,
            name = it.nameEt.text.toString(),
            artist = it.artistEt.text.toString(),
            album = it.albumEt.text.toString(),
            image = it.imageEt.text.toString(),
            isFavorite = it.favoriteCb.isChecked
        )
    }
}
