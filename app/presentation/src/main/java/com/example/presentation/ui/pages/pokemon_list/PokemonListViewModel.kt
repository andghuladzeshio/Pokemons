package com.example.presentation.ui.pages.pokemon_list

import com.example.domain.use_case.GetPokemonsUseCase
import com.example.shared.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
): BaseViewModel() {



}