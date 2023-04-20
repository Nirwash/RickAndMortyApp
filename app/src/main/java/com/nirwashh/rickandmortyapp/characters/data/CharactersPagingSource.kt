package com.nirwashh.rickandmortyapp.characters.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService

class CharactersPagingSource(
    private val characterService: CharactersService,
    private val filters: CharacterFilters
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: 0
        return try {
            val response = characterService.fetchCharacters(
                filters.name,
                filters.status,
                filters.species,
                filters.type,
                filters.gender,
                pageIndex
            )
            val data = checkNotNull(response.body()).results
            val prevKey = if (pageIndex == 0) null else pageIndex - 1
            val nextKey = pageParser(response.body()?.info?.next)
            return LoadResult.Page(data, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun pageParser(uri: String?): Int? {
        val uri = Uri.parse(uri)
        val pageQuery = uri.getQueryParameter("page")
        return pageQuery?.toInt()
    }

}