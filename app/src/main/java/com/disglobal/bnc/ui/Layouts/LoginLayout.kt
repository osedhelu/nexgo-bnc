package com.disglobal.bnc.ui.Layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.disglobal.bnc.R
import com.disglobal.bnc.data.remote.dto.GetInfoAffiliatesResp
import com.disglobal.bnc.utils.GoToActivity
import com.disglobal.bnc.utils.iNameActivity


@Composable
fun LoginLayout(children: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        val ctx = LocalContext.current
        val commerce = GetInfoAffiliatesResp.getCommerce(ctx)


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Spacer(modifier = Modifier.size(30.dp))
            Icon(
                painter = painterResource(id = R.drawable.logo_nexgo),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(height = 60.dp, width = 180.dp)

            )
            if (commerce == null) {
                Spacer(modifier = Modifier.size(30.dp))
            } else {
                IconButton(onClick = {
                    GetInfoAffiliatesResp.setCommerce(
                        ctx,
                        GetInfoAffiliatesResp()
                    )
                    GoToActivity(iNameActivity.MAIN, ctx)
                }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "")

                }
            }

        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Column(
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp),
//                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                children()

            }
        }
    }

}