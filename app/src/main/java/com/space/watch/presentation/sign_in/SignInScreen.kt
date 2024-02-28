package com.space.watch.presentation.sign_in

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.space.watch.ui.theme.WatchTheme

@Preview
@Composable
fun SignInScreenPreview() {
    WatchTheme {
        SignInScreen()
    }
}

@Composable
fun SignInScreen() {
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
            SignInScreenContent()
        }
    }
}

@Composable
private fun SignInScreenContent() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        EmailTextField()

        PasswordTextField()

        SignInButton()
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
        text = "Sign In",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun HeaderBody() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Sign in to continue.",
        style = MaterialTheme.typography.bodyMedium
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
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = { },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Go
        ),
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Visibility, contentDescription = null)
            }
        },
        placeholder = {
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}

@Composable
private fun SignInButton() {
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
        Text(text = "Sign In")
    }
}
