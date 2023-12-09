package br.edu.ifsp.songlistroom.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.songlistroom.databinding.FragmentSongDetailBinding
import br.edu.ifsp.songlistroom.viewmodel.SongViewModel

class SongDetailFragment : Fragment() {
    private lateinit var binding: FragmentSongDetailBinding
    private lateinit var viewModel: SongViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songId = requireArguments().getInt("songId")
        viewModel.getContactById(songId)
        viewModel.song.observe(viewLifecycleOwner) {
            it?.let {
                binding.songLt.apply {
                    nameEt.setText(it.name)
                    artistEt.setText(it.artist)
                    albumEt.setText(it.album)
                    imageEt.setText(it.image)
                }
            }
        }
    }
}
