package com.example.presentation.ui.pages.pokemon_list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.Pokemon
import com.example.presentation.R
import com.example.presentation.ui.pages.pokemon_details.PokemonDetails
import com.example.shared.core.extensions.roundedCorners
import com.example.shared.core.extensions.shimmerEffect
import com.example.shared.core.ui.util.state.ScreenState
import com.example.shared.core.ui.view.error.ErrorView
import com.example.shared.core.ui.view.image.ImageLoader
import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Composable
fun PokemonListScreen(
    navController: NavHostController, viewModel: PokemonListViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    val pokemons by viewModel.pokemonsFlow.collectAsState()
    var fullScreenImageUrl by remember {
        mutableStateOf<String?>(null)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(24.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonView(pokemon, navController) {
                fullScreenImageUrl = pokemon.imageUrl
            }
        }

        if (screenState is ScreenState.Error) {
            item {
                ErrorView(exception = (screenState as ScreenState.Error).exception) {
                    viewModel.getPokemons()
                }
            }
        } else if (viewModel.nextPageAvailable) {
            item {
                LoadingView()

                LaunchedEffect(Unit) {
                    viewModel.getPokemons()
                }
            }
        }
    }

    fullScreenImageUrl?.let { url ->
        FullScreenImage(url) {
            fullScreenImageUrl = null
        }

        BackHandler {
            fullScreenImageUrl = null
        }
    }
}

@Composable
private fun PokemonView(
    pokemon: Pokemon, navController: NavHostController, onImageClick: () -> Unit
) {
    Row(modifier = Modifier
        .shadow(2.dp, RoundedCornerShape(24.dp))
        .fillMaxWidth()
        .height(150.dp)
        .roundedCorners(24.dp)
        .background(Color.White)
        .clickable {
            onImageClick()
        }) {
        ImageLoader(
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
                .padding(16.dp),
            url = pokemon.imageUrl
        ) {
            ImageShimmer(height = 150.dp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(24.dp), horizontalAlignment = Alignment.End
        ) {
            Text(
                text = pokemon.name,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate(PokemonDetails(pokemon.name, pokemon.imageUrl))
            }) {
                Text(text = stringResource(id = R.string.pokemons_details))
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun LoadingView() {
    Column(
        Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FullScreenImage(url: String, onClose: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable {
                onClose()
            }, contentAlignment = Alignment.Center
    ) {
        ImageLoader(
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .roundedCorners(24.dp),
            url = url,
        ) {
            ImageShimmer(height = 400.dp)
        }
    }
}

@Composable
fun ImageShimmer(height: Dp) {
    Box(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .roundedCorners(24.dp)
            .shimmerEffect()
    )
}
