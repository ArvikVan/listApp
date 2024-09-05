package com.salesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "phoneAuth") {
        composable("phoneAuth") {
            PhoneAuthScreen(onPhoneSubmitted = { phoneNumber ->
                if (phoneNumber.isNotEmpty()) {
                    // Если номер зарегистрирован
                    if (phoneNumberRegistered(phoneNumber)) {
                        navController.navigate("codeVerification")
                    } else {
                        navController.navigate("registration")
                    }
                }
            })
        }
        composable("registration") {
            RegistrationScreen(onRegisterClicked = { lastName, firstName, email ->
                // TODO: Логика регистрации
                navController.navigate("codeVerification")
            })
        }
        composable("codeVerification") {
            CodeVerificationScreen(onCodeEntered = { code ->
                // TODO: Логика проверки кода
            })
        }
    }
}

fun phoneNumberRegistered(phoneNumber: String): Boolean {
    // TODO: Реализовать проверку через API
    return false
}