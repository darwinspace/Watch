package com.space.watch.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.domain.model.AccountService
import com.space.watch.domain.model.EmailValidator
import com.space.watch.domain.model.implementation.FirebaseAccountService
import com.space.watch.domain.model.implementation.AndroidEmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SignInViewModel(
    private val emailValidator: EmailValidator = AndroidEmailValidator(),
    private val accountService: AccountService = FirebaseAccountService()
) : ViewModel() {
    private var _email = MutableStateFlow(String())
    val email = _email.asStateFlow()

    private var _password = MutableStateFlow(String())
    val password = _password.asStateFlow()

    val isSignInButtonEnabled = combine(email, password) { email, password ->
        val isValidEmail = emailValidator.validate(email)
        val isValidPassword = password.length >= 6
        isValidEmail && isValidPassword
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun onSignInClick() {
        viewModelScope.launch {
            accountService.authenticate(email.value, password.value)
        }
    }
}
