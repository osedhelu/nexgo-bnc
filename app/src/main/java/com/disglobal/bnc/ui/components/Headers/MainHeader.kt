package com.disglobal.bnc.ui.components.Headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.disglobal.bnc.R.drawable.undraw_profile_data_re_v81r
import com.disglobal.bnc.R.string.NAME_APP
import com.disglobal.bnc.ui.theme.primary


@Composable
fun MainHeader(btnVolver: Boolean, navController: NavController, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (btnVolver) {
                IconButton(onClick = {
                    navController.popBackStack()
                    HeaderHelper.reset()
                }, modifier = Modifier.size(30.dp)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = primary,
                        contentDescription = "Setting"
                    )
                }

            } else {
                Text(
                    text = stringResource(id = NAME_APP),
                    color = primary,
                    modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.size(30.dp))
            if (!btnVolver) {
                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = onClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        tint = primary,
                        contentDescription = "Setting"
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(30.dp, 30.dp))
            }

        }
        if (!btnVolver) {
            Icon(
                painter = painterResource(id = undraw_profile_data_re_v81r),
                contentDescription = "Profile",
                modifier = Modifier.size(240.dp),
                tint = Color.Unspecified
            )

        }
    }

}
