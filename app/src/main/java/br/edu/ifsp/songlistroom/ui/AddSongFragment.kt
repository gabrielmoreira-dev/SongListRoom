package br.edu.ifsp.songlistroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.songlistroom.R
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.databinding.FragmentAddSongBinding
import br.edu.ifsp.songlistroom.viewmodel.SongViewModel
import com.google.android.material.snackbar.Snackbar

class AddSongFragment : Fragment() {
    private lateinit var binding: FragmentAddSongBinding
    private lateinit var  viewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SongViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddSongBinding.inflate(inflater, container, false)
        binding.apply {
            songLt.saveBt.setOnClickListener { saveSong() }
            songLt.cancelBt.setOnClickListener { cancel() }
            return root
        }
    }

    private fun saveSong() {
        binding.songLt.apply {
            val song = Song(
                id = 0,
                name = nameEt.text.toString(),
                artist = artistEt.text.toString(),
                album = albumEt.text.toString(),
                image = imageEt.text.toString(),
                isFavorite = false
            )
            viewModel.insert(song)
        }
        Snackbar.make(binding.root, getString(R.string.song_added), Snackbar.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun cancel() {
        findNavController().popBackStack()
    }
}