package com.example.presentation.ui.pages.pokemon_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.shared.core.extensions.roundedCorners
import com.example.shared.core.extensions.shimmerEffect
import com.example.shared.core.ui.view.image.ImageLoader
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(
    val pokemonName: String, val pokemonImageUrl: String
)

@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetails) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        ImageLoader(
            modifier = Modifier
                .fillMaxWidth()
                .roundedCorners(24.dp),
            url = pokemonDetails.pokemonImageUrl,
            contentScale = ContentScale.FillWidth
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(400.dp)
                    .shimmerEffect()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = pokemonDetails.pokemonName,
            fontFamily = FontFamily.Monospace,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraLight
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.pokemons_about_pokemon, pokemonDetails.pokemonName),
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraLight
        )
        Text(
            text = stringResource(id = R.string.pokemons_about_pokemon_lorem),
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraLight
        )
    }
}
