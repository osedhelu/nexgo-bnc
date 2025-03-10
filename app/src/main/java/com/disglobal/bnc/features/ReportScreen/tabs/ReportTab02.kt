package com.disglobal.bnc.features.ReportScreen.tabs


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.disglobal.bnc.data.local.database.Transaction
import com.disglobal.bnc.data.remote.dto.ApiResponseStatus
import com.disglobal.bnc.DigipayApi.domain.entities.GetInfoAffiliatesResp
import com.disglobal.bnc.DigipayApi.domain.entities.LotSummaryResponse
import com.disglobal.bnc.features.ReportScreen.view_models.LotSummaryViewModel


@Composable
fun ReportTab02(
    viewModel: LotSummaryViewModel = hiltViewModel(),
    onClick: (Transaction) -> Unit,
) {
    val commerce = GetInfoAffiliatesResp.getCommerce(LocalContext.current)
    val status by remember {
        viewModel.status
    }

    LaunchedEffect(true) {
        viewModel.getSummaryLog("${commerce?.id ?: 0}")
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        when (status) {
            is ApiResponseStatus.Loading -> {
                Column {
                    Spacer(modifier = Modifier.size(10.dp))
                    LoadingChatItem(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.size(10.dp))
                    LoadingChatItem(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.size(10.dp))
                    LoadingChatItem(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.size(10.dp))
                    LoadingChatItem(modifier = Modifier.fillMaxWidth())

                }
            }

            is ApiResponseStatus.Success -> {
                Column(
                    Modifier.fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "NEXGO")
                    }
                    val resp = (status as ApiResponseStatus.Success<LotSummaryResponse>).data
                    Text(text = "NOMBRE COMERCIO: ${commerce?.name ?: ""}")
                    RowItem("NEXGO:", "")
                    RowItem("AFIL:", commerce?.taxId ?: "")
                    RowItem("LOTE CRÉDITO:", commerce?.serial ?: "")
                    RowItem("LOTE DÉBITO:", "000001")
                    RowItem("LOTE MASTER/VISA:", "000001")
                    RowItem("LOTE EXTRAFINANCIAMIENTO:", "000001")
                    RowItem("FECHA/HORA", "2024/10/03 07:46:38")
                    Spacer(modifier = Modifier.size(10.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .height(1.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Row3Item("TIPO", "Suma", "Monto")
                    Row3Item("TARJETA CRÉDITO", "", "")
                    Row3Item("COMPRA", "0", "Bs. 0.00")
                    Row3Item("ANULACIÓN", "0", "Bs. 0.00")
                    Row3Item("TOTAL", "0", "Bs. 0.00")
                    Row3Item("TARJETA DÉBITO", "", "")
                    Row3Item("COMPRA", "0", "Bs. 0.00")
                    Row3Item("ANULACIÓN", "0", "Bs. 0.00")
                    Row3Item("TOTAL", "0", "Bs. 0.00")
                    Row3Item("DÉBITO MASTER/VISA", "", "")
                    Row3Item("COMPRA", "0", "Bs. 0.00")
                    Row3Item("ANULACIÓN", "0", "Bs. 0.00")
                    Row3Item("TOTAL", "0", "Bs. 0.00")
//            Row3Item("EXTRAFINANCIAMIENTO", "", "")
//            Row3Item("COMPRA", "0", "Bs. 0.00")
//            Row3Item("ANULACIÓN", "0", "Bs. 0.00")
//            Row3Item("TOTAL", "0", "Bs. 0.00")
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(2.dp) // Set corner radius to 0.dp for square edges
                    ) {
                        Text(text = "IMPRIMIR")
                    }
                }

            }

            is ApiResponseStatus.Error -> {

                Column(
                    Modifier.fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "NEXGO")
                    }
                    Text(text = "NOMBRE COMERCIO: ${commerce?.name ?: ""}")
                    RowItem("NEXGO:", "${commerce?.deviceId ?: 0}")
                    RowItem("AFIL:", commerce?.taxId ?: "")
                    RowItem("LOTE CRÉDITO:", commerce?.serial ?: "")
                    RowItem("LOTE DÉBITO:", commerce?.acquirerValue ?: "")
                    RowItem("LOTE MASTER/VISA:", commerce?.acquirerName ?: "")
                    RowItem("LOTE EXTRAFINANCIAMIENTO:", commerce?.acquirerTaxId ?: "")
                    RowItem("FECHA/HORA", "2024/10/03 07:46:38")
                    Spacer(modifier = Modifier.size(10.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .height(1.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Row3Item("TIPO", "Suma", "Monto")
                    Row3Item("TARJETA CRÉDITO", "", "")
                    Row3Item("COMPRA", "0", "Bs. 0.00")
                    Row3Item("ANULACIÓN", "0", "Bs. 0.00")
                    Row3Item("TOTAL", "0", "Bs. 0.00")
                    Row3Item("TARJETA DÉBITO", "", "")
                    Row3Item("COMPRA", "0", "Bs. 0.00")
                    Row3Item("ANULACIÓN", "0", "Bs. 0.00")
                    Row3Item("TOTAL", "0", "Bs. 0.00")
                    Row3Item("DÉBITO MASTER/VISA", "", "")
                    Row3Item("COMPRA", "0", "Bs. 0.00")
                    Row3Item("ANULACIÓN", "0", "Bs. 0.00")
                    Row3Item("TOTAL", "0", "Bs. 0.00")
//            Row3Item("EXTRAFINANCIAMIENTO", "", "")
//            Row3Item("COMPRA", "0", "Bs. 0.00")
//            Row3Item("ANULACIÓN", "0", "Bs. 0.00")
//            Row3Item("TOTAL", "0", "Bs. 0.00")
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Error ${(status as ApiResponseStatus.Error<LotSummaryResponse>).message}")

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(2.dp) // Set corner radius to 0.dp for square edges
                    ) {
                        Text(text = "IMPRIMIR")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun getViewReportTotal() {
    ReportTab02() {}
}