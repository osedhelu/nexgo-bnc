package com.osedhelu.bnc.ui.Screens.ReportScreen.tabs


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.osedhelu.bnc.data.local.database.Transaction


@Composable
fun ReportTab01(onClick: (Transaction) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Credit", "Debit", "Master/Visa Debit")
    var selectedOption by remember { mutableStateOf(options[0]) }


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            Modifier
                .fillMaxWidth()
        ) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                expanded = true
            }) {
                Text(
                    text = selectedOption,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedOption = option
                            expanded = false
                        })
                }
            }


        }
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            contentPadding = PaddingValues(
                start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp
            ),
        ) {

        }
    }


}