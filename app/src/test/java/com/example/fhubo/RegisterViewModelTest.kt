package com.example.fhubo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterViewModelTest {

    // Regla obligatòria per testejar LiveData
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        // Inicialitzem el ViewModel net abans de cada test
        viewModel = RegisterViewModel()
    }

    @Test // Prova 1: Nom Buit
    fun register_nomBuit_mostraError() {
        viewModel.register("", "Password123!", "Password123!", "itzan@gmail.com")

        assertEquals("El nom d'usuari no pot estar buit", viewModel.usernameError.value)
        assertNull(viewModel.registerSuccess.value) // El registre no té èxit
    }

    @Test // Prova 2: Email Incorrecte
    fun register_emailIncorrecte_mostraError() {
        viewModel.register("Itzan", "Password123!", "Password123!", "itzan.gmail.com") // Sense @

        assertEquals("El format del correu no és vàlid", viewModel.emailError.value)
    }

    @Test // Prova 3: Contrasenya Curta i Insegura
    fun register_contrasenyaCurta_mostraError() {
        viewModel.register("Itzan", "12345", "12345", "itzan@gmail.com")

        // El primer error que salta és el de la longitud
        assertEquals("La contrasenya te menys de 8 caracters", viewModel.passwordError.value)
    }

    @Test // Prova 4: Contrasenyes no coincideixen
    fun register_confirmacioNoCoincideix_mostraError() {
        viewModel.register("Itzan", "Password123!", "Password456!", "itzan@gmail.com")

        assertEquals("Les contrasenyes no coincideixen", viewModel.confirmPasswordError.value)
    }

    @Test // Prova 5: Tot Correcte (Camí normal - Registre dades correctes)
    fun register_dadesCorrectes_activaSuccess() {
        viewModel.register("Itzan", "Password123!", "Password123!", "itzan@test.com")

        // Verifiquem que els errors són nuls
        assertNull(viewModel.usernameError.value)
        assertNull(viewModel.emailError.value)
        assertNull(viewModel.passwordError.value)
        assertNull(viewModel.confirmPasswordError.value)

        // Verifiquem que el LiveData de success és true
        assertTrue(viewModel.registerSuccess.value!!)
    }
}