package com.example.shared.core.ui.view.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shared.R
import java.lang.Exception

@Composable
fun ErrorView(exception: Exception, onRefresh: ()-> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.pokemons_error_on_data_loading),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))

        exception.message?.let { errorMessage ->
            Text(text = errorMessage, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onRefresh() }) {
            Text(text = stringResource(id = R.string.pokemons_try_again))
        }
    }
}