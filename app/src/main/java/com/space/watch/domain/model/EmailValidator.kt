package com.space.watch.domain.model

interface EmailValidator {
    fun validate(email: String): Boolean
}
