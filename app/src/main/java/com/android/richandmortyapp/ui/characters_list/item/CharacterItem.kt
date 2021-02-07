package com.android.richandmortyapp.ui.characters_list.item

import com.android.richandmortyapp.R
import com.android.richandmortyapp.data.api.response.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_charaters.*

class CharacterItem(
    val resultItem: Result
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_character_name.text = resultItem.name
            item_character_species.text = resultItem.species
            Glide.with(item_character_image)
                .load(resultItem.image)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(item_character_image)
        }
    }

    override fun getLayout() = R.layout.item_charaters
}
