package com.space.watch.domain.model.implementation

import android.util.Patterns
import com.space.watch.domain.model.EmailValidator

class AndroidEmailValidator : EmailValidator {
    override fun validate(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
