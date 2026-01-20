# Projecte **FHUBO**

Aquest document descriu les característiques principals i les decisions tècniques preses durant el desenvolupament de l'aplicació **FHUBO**, centrant-se en els requisits de la rúbrica d'avaluació.

---

## Índex

1. [Pantalla Inicial Personalitzada (Splash Screen)](#1-pantalla-inicial-personalitzada-splash-screen)  
2. [Menú de Navegació i Barres d'Eines Personalitzades](#2-menú-de-navegació-i-barres-deines-personalitzades)  
3. [Llistes Dinàmiques Personalitzades](#3-llistes-dinàmiques-personalitzades)  
4. [Filtre Complex (Múltiples Criteris)](#4-filtre-complex-múltiples-criteris)

---

## 1. Pantalla Inicial Personalitzada (Splash Screen)

L'objectiu era crear una pantalla de benvinguda atractiva, personalitzada amb el logotip de l'app i amb una animació fluida.

### Solució Implementada

#### 1. API Moderna de Splash Screen
En lloc de fer servir una `Activity` manualment, hem implementat l'API oficial **SplashScreen** d'Android. Aquesta és la pràctica recomanada per Google, ja que:
- Gestiona automàticament la transició entre la pantalla de benvinguda i la primera pantalla de l'app.
- Evita pantalles en blanc.
- Millora el temps d'arrencada.

#### 2. Personalització del Logotip
La Splash Screen utilitza un fons personalitzat amb el logotip de l'aplicació centrat i una paleta de colors coherent amb la identitat visual de **FHUBO**, garantint una presentació neta i professional.

#### 3. Animació
La pantalla inicial compta amb dues animacions que treballen conjuntament:
- **Animació d'entrada**: gestionada automàticament per l'API de SplashScreen.
- **Animació de sortida**: a la `LoginActivity` hem afegit un `OnExitAnimationListener` que executa una animació de *fade-out* de 0,5 segons, aconseguint una transició suau i elegant cap a la pantalla de login.

---

## 2. Menú de Navegació i Barres d'Eines Personalitzades

Es demanava un sistema de navegació funcional i personalitzat, demostrant el domini de les barres de navegació (`BottomNavigationView`) o de les barres d'eines (`Toolbar` / `AppBar`).

### Solució Implementada

#### 1. Menú de Navegació Personalitzat (BottomNavigationView)
- El menú inferior és el pilar de la navegació de l'app.
- S'ha personalitzat:
  - Color de fons (`@color/granate`).
  - Color dels icones (`@color/white`).
  - Mida dels icones augmentada per a més claredat visual.
  - Eliminació de les etiquetes de text per aconseguir un disseny més net i minimalista.

#### 2. Barres d'Eines Personalitzades (Toolbar i CardView)
- **Toolbars estàndard**:  
  En pantalles secundàries com *Settings* o *Language*, hem utilitzat `MaterialToolbar` per mostrar un títol i un botó de *tornar enrere*, mantenint una coherència visual amb la resta de l'aplicació.
- **Toolbars avançades**:  
  A les pantalles `FilmsActivity` i `CityLocationsActivity` hem creat barres de títol totalment personalitzades utilitzant un `CardView`.  
  Aquest component inclou el títol i el botó de navegació enrere, permetent un control total sobre el disseny (colors, tipografia i alineació) i demostrant un nivell avançat de personalització de la interfície.

---

## 3. Llistes Dinàmiques Personalitzades

El requisit era implementar diverses llistes dinàmiques, cadascuna amb un disseny propi i elements clicables.

### Solució Implementada

Hem utilitzat **RecyclerView** de manera extensiva a tota l'aplicació, ja que és la forma més eficient de mostrar grans quantitats de dades.

- **Diverses llistes dinàmiques**:
  - `MainActivity`: mostra les pel·lícules en una graella (`GridLayoutManager`).
  - `CityActivity`: mostra les ciutats en una llista vertical.
  - Pantalles de detall (`FilmsActivity`, `CityLocationsActivity`): mostren les localitzacions.
  - `FavoritesActivity`: mostra els elements preferits de l'usuari.
- **Disseny personalitzat**:
  - Cada llista disposa del seu propi fitxer XML de cel·la (ex.: `recyclerfilm.xml`, `recyclercity.xml`).
  - S'ha utilitzat `CardView` per afegir ombres, cantonades arrodonides i superposicions de text sobre imatges.
- **Interactivitat**:
  - Tots els elements són clicables.
  - Cada `Adapter` rep una funció `onItemClick`, que permet navegar cap a la pantalla de detall corresponent quan l'usuari selecciona un element.

---

## 4. Filtre Complex (Múltiples Criteris)

Es demanava un filtre que combinés més d'un criteri per refinar els resultats d'una llista.

### Solució Implementada

El filtre de la pantalla principal (`MainActivity`) és una de les funcionalitats més avançades de l'aplicació. Combina **tres criteris simultanis**:

1. **Filtre per categoria**  
   - L'usuari pot seleccionar una categoria (Totes, Pel·lícules, Llibres, Música).
   - La selecció es desa a la variable `currentCategory`.

2. **Filtre per text**  
   - Cerca dinàmica mitjançant una barra de cerca (`SearchView`).

3. **Ordenació per any**  
   - Possibilitat d'ordenar per any d'estrena, tant ascendent com descendent.
   - El criteri seleccionat es guarda a la variable `currentSortOrder`.

La funció `performSearch` aplica els criteris en l'ordre següent:
1. Filtrat per categoria.
2. Filtrat per text sobre el resultat anterior.
3. Ordenació final segons l'any.

Aquest sistema ofereix un filtratge potent i flexible que compleix plenament el requisit de **filtre complex amb múltiples criteris**.
