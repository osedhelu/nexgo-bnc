package com.osedhelu.bnc.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osedhelu.bnc.R
import com.osedhelu.bnc.ui.theme.primary


@Composable
fun InputPassword(title: String, value: String, onChange: (String) -> Unit) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        shape = RoundedCornerShape(19.dp), // Specify the desired corner radius
        modifier = Modifier
            .fillMaxWidth(.87f)
            .height(54.1.dp),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = painterResource(id = R.drawable.icons_eyes), contentDescription = null
                )

            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),

        onValueChange = onChange,
        label = { Text(title) },
    )
}

@Composable
fun InputGlobal(
    title: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable() (() -> Unit)? = null, //EXAMPLE: {  Icon(imageVector = Icons.Default.Clear, contentDescription = null)}
    leadingIcon: @Composable() (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    messageError: String = "",
    isError: Boolean = false,

    ) {
    Column(modifier.fillMaxWidth()) {
        OutlinedTextField(
            leadingIcon = leadingIcon,
            value = value,
            shape = RoundedCornerShape(17.dp), // Specify the desired corner radius
            modifier = Modifier
                .fillMaxWidth()
                .height(54.1.dp)
                .then(modifier),
            trailingIcon = trailingIcon,
            enabled = enabled,
            textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal, fontSize = 10.sp
            ),
            onValueChange = onChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = Color.Black, // Cambia el color del label cuando el campo está enfocado
                unfocusedLabelColor = Color.Gray, // Cambia el color del label cuando el campo no está enfocado
            ),
            keyboardOptions = keyboardOptions,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            suffix = suffix,
            keyboardActions = keyboardActions,
            label = { Text(title) },
            singleLine = true, // Set singleLine to true to prevent multiline input
            isError = isError,


            )
        if (isError) {
            Text(
                text = messageError, color = Color.Red,
//                    style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp), fontSize = 9.sp
            )
        } else {
            Spacer(modifier = Modifier.size(14.dp))
        }

    }

}


@Composable
fun ButtonPersonal(
    title: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    color: Color = primary,
    colorText: Color = Color(0xFFFFFFFF),
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = color,
        ),
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .then(modifier),
        enabled = enabled
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
//                fontFamily = FontFamily(Font(R.font.helvetica neue)),
                fontWeight = FontWeight(500),
                color = colorText,
                textAlign = TextAlign.Center,
            )
        )
    }
}
