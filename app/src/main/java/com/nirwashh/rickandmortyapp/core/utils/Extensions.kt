package com.nirwashh.rickandmortyapp.core.utils

import android.content.res.Resources
import android.graphics.Rect
import android.net.Uri
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailsRecyclerViewItem
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import kotlinx.coroutines.flow.MutableStateFlow

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

inline fun <T> MutableStateFlow<T>.update(function: (T) -> T) {
    while (true) {
        val prevValue = value
        val nextValue = function(prevValue)
        if (compareAndSet(prevValue, nextValue)) {
            return
        }
    }
}

fun Episode.mapToEpisodeViewType() =
    DetailsRecyclerViewItem.EpisodeViewItem(
        name = this.name,
        episode = this.episode,
        id = this.id
    )

fun String.pageParser(): Int? {
    val uri = Uri.parse(this)
    val pageQuery = uri.getQueryParameter("page")
    return pageQuery?.toInt()
}

fun List<String>.idsParser(): String {
    val list = mutableListOf<Int>()
    this.forEach {
        val uri = Uri.parse(it)
        val parts = uri.toString().split("/")
        val id = parts[parts.size - 1]
        list.add(id.toInt())
    }
    return list.toList().toString()
}



