package com.space.watch.presentation.sign_up

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.watch.presentation.`interface`.theme.WatchTheme

@Preview
@Composable
fun SignUpScreenPreview() {
    WatchTheme {
        SignUpScreen()
    }
}

@Composable
fun SignUpScreen() {
    val scrollState = rememberScrollState()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpScreenContent()
        }
    }
}

@Composable
private fun SignUpScreenContent() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        NameTextField()

        EmailTextField()

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PasswordTextField()
            PasswordTextFieldRestriction()
        }

        SignUpButton()
    }
}

@Composable
private fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderTitle()

        HeaderBody()
    }
}

@Composable
private fun HeaderTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Sign Up",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun HeaderBody() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Get started by setting up your account.",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun NameTextField() {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = { },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        placeholder = {
            Text(
                text = "Name",
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

@Composable
private fun EmailTextField() {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = { },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        placeholder = {
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

@Composable
private fun PasswordTextField() {
    var passwordVisibility by remember { mutableStateOf(false) }
    val passwordVisualTransformation = if (passwordVisibility) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "password",
        onValueChange = { },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.small,
        keyboardActions = KeyboardActions { /*TODO*/ },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility }
            ) {
                AnimatedVisibility(
                    visible = passwordVisibility,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = null)
                }

                AnimatedVisibility(
                    visible = !passwordVisibility,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Icon(imageVector = Icons.Outlined.Visibility, contentDescription = null)
                }
            }
        },
        placeholder = {
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodySmall
            )
        },
        visualTransformation = passwordVisualTransformation
    )
}

@Composable
private fun PasswordTextFieldRestriction() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Password must contain 6 characters.",
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
private fun SignUpButton() {
    Button(
        modifier = Modifier
            .heightIn(48.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
        ),
        onClick = { }
    ) {
        Text(text = "Sign Up")
    }
}
