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

    @Test //  Nom Buit
    fun register_nomBuit_mostraError() {
        viewModel.register("", "Password123!", "Password123!", "itzan@gmail.com")

        assertEquals("El nom d'usuari no pot estar buit", viewModel.usernameError.value)
        assertNull(viewModel.registerSuccess.value) // El registre no té èxit
    }

    @Test //  Email Incorrecte
    fun register_emailIncorrecte_mostraError() {
        viewModel.register("Itzan", "Password123!", "Password123!", "itzan.gmail.com") // Sense @

        assertEquals("El format del correu no és vàlid", viewModel.emailError.value)
    }

    @Test //  Contrasenya Curta i Insegura
    fun register_contrasenyaCurta_mostraError() {
        viewModel.register("Itzan", "12345", "12345", "itzan@gmail.com")

        // El primer error que salta és el de la longitud
        assertEquals("La contrasenya te menys de 8 caracters", viewModel.passwordError.value)
    }

    @Test //  Contrasenyes no coincideixen
    fun register_confirmacioNoCoincideix_mostraError() {
        viewModel.register("Itzan", "Password123!", "Password456!", "itzan@gmail.com")

        assertEquals("Les contrasenyes no coincideixen", viewModel.confirmPasswordError.value)
    }

    @Test //  Tot Correcte (Camí normal - Registre dades correctes)
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
    @Test //  Correu Buit
    fun register_correuBuit_mostraError() {
        viewModel.register("Itzan", "Password123!", "Password123!", "")

        // Comprovem que el ViewModel detecta que no hi ha correu i llença l'error de format
        assertEquals("El format del correu no és vàlid", viewModel.emailError.value)
    }

    @Test //  Contrasenya sense caràcter especial
    fun register_contrasenyaSenseEspecial_mostraError() {
        // La contrasenya té 8 caràcters, majúscules, minúscules i números, però NO especials
        viewModel.register("Itzan", "Password1234", "Password1234", "itzan@test.com")

        assertEquals("La contrasenya ha de tindre un careacter especial", viewModel.passwordError.value)
    }
}