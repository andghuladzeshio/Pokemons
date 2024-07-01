package com.example.presentation.ui.pages.pokemon_list

import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonsResponse
import com.example.domain.use_case.GetPokemonsUseCase
import com.example.shared.core.ui.base.BaseViewModel
import com.example.shared.core.ui.util.pagination.PaginationHelper
import com.example.shared.core.ui.util.state.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : BaseViewModel() {
    @Inject
    lateinit var paginationHelper: PaginationHelper<Pokemon>

    private val _pokemonsFlow = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonsFlow = _pokemonsFlow.asStateFlow()

    var nextPageAvailable = true
        private set

    init {
        getPokemons()
    }

    fun getPokemons() {
        networkExecutor {
            execute {
                getPokemonsUseCase(paginationHelper.getOffset(), paginationHelper.pageSize)
            }
            loading {
                updateScreenState(ScreenState.Loading)
            }

            success { response ->
                handlePokemonsResponse(response)
            }

            error { error ->
                updateScreenState(ScreenState.Error(error))
            }
        }
    }

    private suspend fun handlePokemonsResponse(response: PokemonsResponse) {
        updateScreenState(ScreenState.Idle)

        nextPageAvailable = response.hasNext
        paginationHelper.addPage(response.pokemons)
        _pokemonsFlow.emit(paginationHelper.getItems())
    }
}