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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.woof.presentation.navigation.Screen

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit
) {
    val countryCode = "+7" // Код страны по умолчанию
    var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun isValidPhoneNumber(number: String): Boolean {
        // Проверяем, что номер состоит только из цифр и имеет нужное количество цифр
        val cleanedNumber = number.removePrefix(countryCode)
        return cleanedNumber.all { it.isDigit() } && cleanedNumber.length == 10
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Введите номер телефона",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "чтобы войти или стать клиентом",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                val inputText = it.text
                if (inputText.startsWith(countryCode)) {
                    phoneNumber = it.copy(text = inputText.removePrefix(countryCode))
                } else {
                    phoneNumber = it.copy(text = inputText)
                }
            },
            label = { Text("Номер телефона", color = Color.Blue) },
            leadingIcon = {
                Text(
                    text = countryCode,
                    style = TextStyle(fontSize = 16.sp, color = Color.Blue)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            ),
            isError = errorMessage != null,
            modifier = Modifier.fillMaxWidth()
        )

        // Показываем сообщение об ошибке, если номер не валиден
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val fullPhoneNumber = "$countryCode${phoneNumber.text}"
                if (isValidPhoneNumber(fullPhoneNumber)) {
                    onNavigateTo(Screen.Register)
                } else {
                    errorMessage = "Неверный формат номера телефона"
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