package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.core.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class CharactersViewModel(
    private val app: Application,
    private val interactor: CharactersInteractor
) : ViewModel() {
    private var charactersPage = 1
    private var charactersResponse: CharactersResponse? = null
    val characters: MutableLiveData<Resource<CharactersResponse>> = MutableLiveData()
    val isFocusedSearchView: MutableLiveData<Boolean> = MutableLiveData()
    var filters: MutableLiveData<CharacterFilters> = MutableLiveData()

    /*
    * Сделать приватную лайвдату и паблик метод по обновлению фильтров и сделать еще один
    * паблик метод по обновлению UI
    * */


    init {
        filters.value = CharacterFilters()
        isFocusedSearchView.value = false
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            filters.value?.let { safeCharactersCall(it) }
        }
    }

    fun searchQueryListener(searchQuery: String) {
        viewModelScope.launch {
            delay(1000L)
            filters.value?.name = searchQuery
        }
    }

    private suspend fun safeCharactersCall(filters: CharacterFilters) {
        characters.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = interactor.getCharacters(filters)
                characters.value = handleCharactersResponse(response)
            } else {
                characters.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> characters.postValue(Resource.Error("Network Failure"))
                else -> characters.postValue(Resource.Error("Conversion error"))
            }
        }
    }

    private fun handleCharactersResponse(response: Response<CharactersResponse>): Resource<CharactersResponse> {
        if (response.isSuccessful)
            response.body()?.let { resultResponse ->
                charactersPage++
                if (charactersResponse == null) {
                    charactersResponse = resultResponse
                } else {
                    val oldCharacters: MutableList<Character> =
                        charactersResponse?.results as MutableList<Character>
                    val newCharacters = resultResponse.results
                    oldCharacters.clear()
                    oldCharacters.addAll(newCharacters)
                }
                return Resource.Success(charactersResponse ?: resultResponse)
            }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        val connectionManager = app.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val network = connectionManager.activeNetwork ?: return false
        val activeNetwork = connectionManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_USB) -> true
            else -> false
        }
    }
}