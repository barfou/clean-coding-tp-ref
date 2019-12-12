package fr.appsolute.tp.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.appsolute.tp.R
import fr.appsolute.tp.data.model.Episode
import kotlinx.android.synthetic.main.holder_episode.view.*

class EpisodeViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(episode: Episode) {
        itemView.apply {
            this.holder_episode_name.text = episode.name
            this.holder_episode_label.text = episode.episode
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup): EpisodeViewHolder {
            return EpisodeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.holder_episode,
                    parent,
                    false
                )
            )
        }
    }

}
