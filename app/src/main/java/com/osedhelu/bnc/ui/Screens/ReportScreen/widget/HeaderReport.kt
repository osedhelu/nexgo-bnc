package com.osedhelu.bnc.ui.Screens.ReportScreen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.osedhelu.bnc.ui.components.Headers.HeaderHelper
import com.osedhelu.bnc.ui.theme.primary

@Composable
fun HeaderReport(navController: NavController) {
    val searchText = remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            navController.popBackStack()
            HeaderHelper.reset()
        }, modifier = Modifier.size(30.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack, tint = primary, contentDescription = "Volver"
            )
        }

        // Campo de búsqueda
        BasicTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            )
        )

        // Icono de búsqueda (debes importar el icono correspondiente)
        IconButton(onClick = { /* Acción de búsqueda */ }) {
            Icon(
                imageVector = Icons.Default.Search, // Asegúrate de importar el icono de búsqueda
                contentDescription = "Buscar"
            )
        }

        // Botón "Total"
        Button(onClick = { navController.navigate("reportes/1") }) {
            Text("Total")
        }

        // Botón "Imprimir Detalles"
        Button(onClick = { /* Acción de Imprimir Detalles */ }) {
            Text("Imprimir Detalles")
        }
    }
}