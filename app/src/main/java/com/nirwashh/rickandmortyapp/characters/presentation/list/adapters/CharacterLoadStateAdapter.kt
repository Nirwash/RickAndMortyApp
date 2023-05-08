package com.nirwashh.rickandmortyapp.characters.presentation.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nirwashh.rickandmortyapp.databinding.DefaultLoadStateBinding

class CharacterLoadStateAdapter(
    private val adapter: CharactersAdapter
) : LoadStateAdapter<CharacterLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        Holder(
            DefaultLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            null
        ) { adapter.retry() }

    class Holder(
        private val binding: DefaultLoadStateBinding,
        private val swipeRefreshLayout: SwipeRefreshLayout?,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            messageTextView.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}