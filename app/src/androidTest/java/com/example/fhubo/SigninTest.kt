package com.example.fhubo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SigninTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(Signin::class.java)

    @Test // Botó activat i error per nom buit al clicar
    fun nom_buit_mostra_error_ui() {
        // A diferència d'Allercheck, a FHUBO el botó està actiu, comprovem que llença l'error al fer clic
        onView(withId(R.id.btnRegister)).perform(click())

        onView(withText("El nom d'usuari no pot estar buit"))
            .check(matches(isDisplayed()))
    }

    @Test // Error Email Incorrecte (UI)
    fun email_incorrecte_mostra_error_ui() {
        onView(withId(R.id.tietUsername)).perform(typeText("Itzan"), closeSoftKeyboard())
        onView(withId(R.id.tietEmail)).perform(typeText("itzan.com"), closeSoftKeyboard())

        onView(withId(R.id.btnRegister)).perform(click())

        onView(withText("El format del correu no és vàlid"))
            .check(matches(isDisplayed()))
    }

    @Test // Error Contrasenya (UI)
    fun password_insegur_mostra_error_ui() {
        onView(withId(R.id.tietUsername)).perform(typeText("Itzan"), closeSoftKeyboard())
        onView(withId(R.id.tietEmail)).perform(typeText("itzan@test.com"), closeSoftKeyboard())
        onView(withId(R.id.tietPassword)).perform(typeText("123"), closeSoftKeyboard())

        onView(withId(R.id.btnRegister)).perform(click())

        onView(withText("La contrasenya te menys de 8 caracters"))
            .check(matches(isDisplayed()))
    }

    @Test //  Registre amb èxit
    fun formulari_valid_fa_registre() {
        // Omplim dades vàlides
        onView(withId(R.id.tietUsername)).perform(typeText("Itzan"), closeSoftKeyboard())
        onView(withId(R.id.tietEmail)).perform(typeText("itzan@test.com"), closeSoftKeyboard())
        onView(withId(R.id.tietPassword)).perform(typeText("Password123!"), closeSoftKeyboard())
        onView(withId(R.id.tietConfirmPassword)).perform(typeText("Password123!"), closeSoftKeyboard())

        onView(withId(R.id.btnRegister)).perform(click())

        // Aquí l'activitat de Signin es tanca i s'obre MainActivity segons el teu codi
    }

    @Test // Navegació a la pantalla de Login
    fun click_anar_a_login() {
        // Simulem que l'usuari ja té compte i clica per anar al login
        onView(withId(R.id.tvGoToLogin)).perform(click())
    }

    @Test // Navegació "o continua sense compte"
    fun click_continuar_sense_compte() {
        // Clica l'opció inferior
        onView(withId(R.id.tvNoLogin)).perform(click())
    }
}