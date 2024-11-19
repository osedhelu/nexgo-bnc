package com.disglobal.bnc.ui.components.sidebar

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.disglobal.bnc.R

@Composable
fun SidebarMain() {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(16.dp)
    ) {
        AnimatedImage()
        Spacer(modifier = Modifier.height(16.dp))
        SidebarMenu()
    }
}

@Composable
fun AnimatedImage() {
    var startAnimation by remember { mutableStateOf(false) }
    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 300.dp,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Image(
        painter = painterResource(id = R.drawable.logo_nexgo),
        contentDescription = "Animated Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .offset(x = offsetX)
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    )
}

@Composable
fun SidebarMenu() {
    Column {
        SidebarMenuItem(icon = Icons.Default.Settings, text = "Configuración")
        SidebarMenuItem(icon = Icons.Default.Person, text = "Administrador")
        Divider(color = Color.Gray, thickness = 1.dp)
        SidebarMenuItem(icon = Icons.Default.Call, text = "Ayuda y Soporte")
        SidebarMenuItem(icon = Icons.Default.Info, text = "Sobre")
        SidebarMenuItem(icon = Icons.Default.Menu, text = "Lenguaje")
    }
}

@Composable
fun SidebarMenuItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon, contentDescription = text, tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.titleSmall)
    }
}