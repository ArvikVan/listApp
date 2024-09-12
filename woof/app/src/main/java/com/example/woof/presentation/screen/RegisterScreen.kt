package com.example.woof.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.woof.presentation.navigation.Screen

@Composable
fun RegisterScreen(
    onRegisterClicked: (String, String, String) -> Unit
) {
    val lastName = remember { mutableStateOf(TextFieldValue()) }
    val firstName = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Поля ввода для регистрации
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Фамилия") },
            isError = errorMessage != null && lastName.value.text.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("Имя") },
            isError = errorMessage != null && firstName.value.text.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email", color = Color.Blue) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            isError = errorMessage != null && !isValidEmail(email.value.text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Кнопка "Продолжить"
        Button(
            onClick = {
                val lastNameText = lastName.value.text
                val firstNameText = firstName.value.text
                val emailText = email.value.text

                when {
                    lastNameText.isEmpty() -> {
                        errorMessage = "Поле Фамилия, обязательно для заполнения"
                    }
                    firstNameText.isEmpty() -> {
                        errorMessage = "Поле Имя, обязательно для заполнения"
                    }
                    emailText.isEmpty() -> {
                        errorMessage = "Поле Email обязательно"
                    }
                    !isValidEmail(emailText) -> {
                        errorMessage = "Некорректный формат Email"
                    }
                    else -> {
                        errorMessage = null
                        onRegisterClicked(lastNameText, firstNameText, emailText)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Продолжить")
        }
    }
}
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
