package com.space.watch.presentation.sign_in

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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.watch.presentation.`interface`.theme.WatchTheme

@Preview
@Composable
fun SignInScreenPreview() {
    WatchTheme {
        SignInScreen()
    }
}

@Composable
fun SignInScreen(
    email: () -> String = { String() },
    onEmailChange: (String) -> Unit = { },
    password: () -> String = { String() },
    onPasswordChange: (String) -> Unit = { },
    isSignInButtonEnabled: () -> Boolean = { true },
    onSignInClick: () -> Unit = { }
) {
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
            SignInScreenContent(
                email = email,
                onEmailChange = onEmailChange,
                password = password,
                onPasswordChange = onPasswordChange,
                isSignInButtonEnabled = isSignInButtonEnabled,
                onSignInClick = onSignInClick
            )
        }
    }
}

@Composable
private fun SignInScreenContent(
    email: () -> String,
    onEmailChange: (String) -> Unit,
    password: () -> String,
    onPasswordChange: (String) -> Unit,
    isSignInButtonEnabled: () -> Boolean,
    onSignInClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        EmailTextField(
            email = email,
            onEmailChange = onEmailChange
        )

        PasswordTextField(
            password = password,
            onPasswordChange = onPasswordChange
        )

        SignInButton(
            enabled = isSignInButtonEnabled(),
            onClick = onSignInClick
        )

        CreateAccountButton()
    }
}

@Composable
private fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderTitle()

        HeaderBody()
    }
}

@Composable
private fun HeaderTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Sign In",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun HeaderBody() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Let's get you in! Please sign in.",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun EmailTextField(email: () -> String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email(),
        onValueChange = onEmailChange,
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
private fun PasswordTextField(password: () -> String, onPasswordChange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val passwordVisualTransformation = if (passwordVisibility) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password(),
        onValueChange = onPasswordChange,
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
private fun SignInButton(enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .heightIn(48.dp)
            .fillMaxWidth(),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
        ),
        onClick = onClick
    ) {
        Text(text = "Sign In")
    }
}

@Composable
private fun CreateAccountButton() {
    Button(
        modifier = Modifier
            .heightIn(48.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        ),
        onClick = { }
    ) {
        Text(text = "Create account")
    }
}
