package fr.appsolute.tp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import fr.appsolute.tp.R
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.ui.activity.MainActivity
import fr.appsolute.tp.ui.adapter.EpisodeAdapter
import fr.appsolute.tp.ui.viewmodel.CharacterViewModel
import fr.appsolute.tp.ui.viewmodel.EpisodeViewModel
import kotlinx.android.synthetic.main.fragment_character_details.view.*

class CharacterDetailsFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var episodeViewModel: EpisodeViewModel
    private lateinit var episodeAdapter: EpisodeAdapter
    private var characterId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            characterViewModel = ViewModelProvider(this, CharacterViewModel).get()
            episodeViewModel = ViewModelProvider(this, EpisodeViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        characterId =
            arguments?.getInt(ARG_CHARACTER_ID_KEY) ?: throw IllegalStateException("No ID found")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeAdapter = EpisodeAdapter()
        view.episode_recycler_view.apply {
            adapter = this@CharacterDetailsFragment.episodeAdapter
            // Add the offset for each item in the list
            if (itemDecorationCount == 0) addItemDecoration(EpisodeAdapter.OffsetDecoration())
        }
        loadCharacter(view)
    }

    private fun loadCharacter(view: View) {
        characterViewModel.getCharacterById(characterId) {
            (activity as? MainActivity)?.supportActionBar?.apply {
                this.title = it.name
                this.setDisplayHomeAsUpEnabled(true)
            }
            view.apply {
                this.character_details_species.text = it.species
                this.character_details_location.text = it.location.name
                Glide.with(this)
                    .load(it.image)
                    .into(this.character_details_image_view)
            }
            view.character_details_species.text = it.species
            view.character_details_location.text = it.location.name
            loadEpisodes(it)
        }
    }

    private fun loadEpisodes(it: Character) {
        episodeViewModel.getEpisodes(it.episode) {
            episodeAdapter.submitList(it)
        }
    }

    companion object {
        const val ARG_CHARACTER_ID_KEY = "arg_character_id_key"
    }

}