package com.android.rickandmortyapp.ui.character_detail.item

import com.android.rickandmortyapp.R
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponseItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_episode.*


class EpisodeItem(
    val episodeResponseItem: EpisodeResponseItem
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_episode_name.text = "${episodeResponseItem.episode} - ${episodeResponseItem.name}"
            item_episode_date.text = episodeResponseItem.airDate
        }
    }

    override fun getLayout() = R.layout.item_episode
}
