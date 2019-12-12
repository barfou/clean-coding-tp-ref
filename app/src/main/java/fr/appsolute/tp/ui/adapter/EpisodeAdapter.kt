package fr.appsolute.tp.ui.adapter

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.ui.utils.dp
import fr.appsolute.tp.ui.widget.holder.EpisodeViewHolder

class EpisodeAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {

    private var _data = emptyList<Episode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.newInstance(parent)
    }

    override fun getItemCount(): Int = _data.count()

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    /**
     * Set new data in the list and refresh it
     */
    fun submitList(data: List<Episode>) {
        _data = data
        notifyDataSetChanged()
    }

    /**
     * Define how decorate an item
     */
    class OffsetDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            // Set 8 dp for each vertex
            parent.run {
                outRect.set(
                    dp(8),
                    dp(8),
                    dp(8),
                    dp(8)
                )
            }

        }
    }

}