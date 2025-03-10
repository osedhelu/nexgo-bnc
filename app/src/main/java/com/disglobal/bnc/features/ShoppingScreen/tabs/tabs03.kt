package com.disglobal.bnc.ui.Screens.ShoppingScreen.tabs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.ui.Screens.ShoppingScreen.providers.PaymentProvider
import com.disglobal.bnc.ui.theme.secondary

@Composable
fun PagoTab03(navController: NavController, viewModel: PaymentProvider = hiltViewModel()) {
    val state by remember {
        viewModel.status
    }
    LaunchedEffect(true) {
        viewModel.RegisterTransaction(
            """DF8330020200C28201A0F7CC36152E50AE1A67BC7C5A60F751CB1FC1453F67232CB5D78B466770D687A206E22717BBBF6F4C918885FEE5D022B901393878FB8AA94ADBA2D6BF532CFABA8C6AB7838EFF0C7135FA6169021EE1948E072431A1005E554BB1BA71A00ED9124C5EE1AE264ACF4C56531FB64CD5CDC69764B670C66D5024147640AC31D5D202D43458D25B63A9F58BA3F4F00D7192C5CC5FCFD9DF41BAA698E52D1826AFCB4562DDDAA170FCD0AE5B3B139AA97784FE0B4B17A5505EAC8E414CAB3D66718CD56C8991D103E1C11A955B11DDE1C5648177C0A4944B710C9170271C9F190C7234A20DA95651460204AAF4F7B03B1DA9FC5B3E845A64241E544A3F2B838BF3D0025C69487E3D98C7E25B859FD95807AA596C3F68B972BBAA397DEF01920E447129D7A281525BCF55FAB77939E65DD81CE67852C56F80C10AA7611F11C2F634A150739B4ED1B3439B98F05FBF9E871EC5B7ADEBF6BBAB07EE7F364AFCD9B80E224DAB03422A882ED73286C6D8B75A1A9722BC5E3A6B11CF6A3386DD101EC67EB16CB70E2A8BDD75F3E60E8FD0D88E38863F5619FBDE749A325988B890F5BEE57C93C00AFFFF9876543210E00004C4075327FFFFFF77399F530152DF834F0D50424A37323241583331343836DF83440A56313333383439393130DF836B050000258963DF834C053030303035DF834B143030303030303030303030303030303030303034""".trimIndent()
        )

    }
    when (state) {
        is ApiResponseStatus.Error -> {
            CardResponse(false) {
                Text((state as ApiResponseStatus.Error<String>).message)
            }
        }

        is ApiResponseStatus.Success -> {
            Text(text = (state as ApiResponseStatus.Success<String>).data)
            CardResponse(true) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.shadow(
                        elevation = 8.dp,
                        spotColor = Color(0xB906A4B4),
                        ambientColor = Color(0xBE06A4B4)
                    ),
                    shape = RoundedCornerShape(12.dp) // Set corner radius to 0.dp for square edges

                ) {
                    Text(text = "IMPRIMIR")
                }
                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    onClick = { navController.navigate("main") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            spotColor = secondary,
                            ambientColor = Color(0xBE06A4B4)
                        ),
                    shape = RoundedCornerShape(12.dp) // Set corner radius to 0.dp for square edges
                ) {
                    Text(text = "Inicio")
                }
            }
        }

        is ApiResponseStatus.Loading -> {
            Text(text = "Cargando...")
        }
    }


}
