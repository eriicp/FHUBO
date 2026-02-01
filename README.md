# Projecte **FHUBO**

Aquest document descriu les característiques principals i les decisions tècniques preses durant el desenvolupament de l'aplicació **FHUBO**, centrant-se en els requisits de la rúbrica d'avaluació.

---

## Índex

* [07. Menú - Llista - Filtre](#07-menú---llista---filtre)
* [07.1 ViewModel](#071-viewmodel)


---

## 07. Menú - Llista - Filtre

### 1. Pantalla Inicial Personalitzada (Splash Screen)

L'objectiu era crear una pantalla de benvinguda atractiva, personalitzada amb el logotip de l'app i amb una animació fluida.

#### Solució Implementada

**API Moderna de Splash Screen**
S'ha implementat l'API oficial **SplashScreen** d'Android, que permet:

* Una transició automàtica entre la pantalla inicial i la primera pantalla funcional.
* Evitar pantalles en blanc durant la càrrega.
* Millorar el temps d'arrencada de l'aplicació.

**Personalització del Logotip**
La Splash Screen utilitza un fons personalitzat amb el logotip centrat i una paleta de colors coherent amb la identitat visual de **FHUBO**.

**Animació**
S'han combinat dues animacions:

* Animació d'entrada gestionada per l'API.
* Animació de sortida (*fade-out* de 0,5 segons) implementada a la `LoginActivity` mitjançant un `OnExitAnimationListener`.

---

### 2. Menú de Navegació i Barres d'Eines Personalitzades

Es va implementar un sistema de navegació funcional i personalitzat.

#### Solució Implementada

**Menú de Navegació (BottomNavigationView)**

* Color de fons personalitzat.
* Icones en color blanc i de mida augmentada.
* Eliminació de les etiquetes de text per a un disseny més net.

**Barres d'Eines Personalitzades**

* `MaterialToolbar` en pantalles secundàries (*Settings*, *Language*).
* Barres de títol avançades amb `CardView` a `FilmsActivity` i `CityLocationsActivity`, permetent un control total del disseny visual.

---

### 3. Llistes Dinàmiques Personalitzades

S'han implementat diverses llistes dinàmiques utilitzant **RecyclerView**.

* **Pantalles amb llistes**:

  * `MainActivity`: graella de pel·lícules.
  * `CityActivity`: llista vertical de ciutats.
  * Pantalles de detall i preferits.
* **Disseny personalitzat**:

  * Cel·les XML específiques per a cada llista.
  * Ús de `CardView` per millorar l'aspecte visual.
* **Interactivitat**:

  * Elements clicables amb navegació a pantalles de detall mitjançant `onItemClick`.

---

### 4. Filtre Complex (Múltiples Criteris)

El filtre principal combina tres criteris simultanis:

1. **Categoria**: Totes, Pel·lícules, Llibres o Música.
2. **Cerca per text**: mitjançant `SearchView`.
3. **Ordenació per any**: ascendent o descendent.

La funció `performSearch` aplica els filtres de manera seqüencial:

1. Categoria.
2. Text.
3. Ordenació final.

Aquesta implementació compleix el requisit de **filtre complex amb múltiples criteris**.

---

## 07.1 ViewModel

### Descripció General

S'ha aplicat el patró d'arquitectura **MVVM** mitjançant la creació de classes **ViewModel**, que actuen com a controladors lògics. Aquesta separació garanteix:

* Desacoblament total entre UI i lògica de negoci.
* Persistència de dades davant canvis de configuració (rotació de pantalla).
* Facilitat per al testeig unitari.

---

### RegisterViewModel

Gestiona la lògica de negoci de la pantalla de registre (*Signin*), validant les dades abans de crear un usuari.

**Estructura de Dades (LiveData)**

S'utilitza l'encapsulament `MutableLiveData` (privat) i `LiveData` (públic):

```kotlin
private val _nameuser = MutableLiveData<String>()
private val _password = MutableLiveData<String>()

val nameuser: LiveData<String> = _nameuser
val password: LiveData<String> = _password
```

**Lògica de Validació (checkPassword)**

El mètode retorna un `String?` amb el missatge d'error o `null` si la validació és correcta.

Regles implementades:

* Longitud mínima de 8 caràcters.
* Almenys un dígit.
* Combinació de majúscules i minúscules.
* Almenys un caràcter especial (`!`, `+`, `^`).
* Coincidència entre contrasenya i confirmació.

---

### LoginViewModel

Gestiona l'autenticació de l'usuari (*Login*).

**Validació de Correu (checkEmail)**

Utilitza `android.util.Patterns.EMAIL_ADDRESS` per validar el format del correu electrònic:

```kotlin
fun checkEmail(email: String): String? {
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return "El formato del correo no es válido"
    }
    return null
}
```

**Autenticació (authenticate)**

Mètode booleà que comprova condicions bàsiques d'accés (actualment, que la contrasenya no estigui buida).

---

### Beneficis de la Implementació

* **Desacoblament**: les `Activity` només observen canvis i reaccionen.
* **Gestió centralitzada d'errors**: missatges definits exclusivament al ViewModel.
* **Observabilitat**: ús de `LiveData` per a una UI reactiva i preparada per escalar.

