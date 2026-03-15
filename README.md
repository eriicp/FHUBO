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

  ---

  ## Guia de Desplegament Integral: API + MySQL a Oracle Cloud

  L'objectiu d'aquesta guia és documentar el procés pas a pas per allotjar una API REST i una base de dades en un servidor de producció (Oracle Cloud). Això donarà servei al CRUD de la nostra aplicació Android utilitzant Retrofit.

  1. Creació de la Instància a Oracle Cloud

  El primer pas és configurar la màquina virtual que farà de servidor:

  Navega al menú Compute -> Instances.
  Fes clic a "Create Instance" i assigna-li un nom al projecte.

  A l'apartat Image and Shape, selecciona Ubuntu com a sistema operatiu.

  Com a Shape, escull un recurs de tipus Ampere (arquitectura ARM).

  Revisa que la configuració de xarxa (VCN i Subnet) estigui correcta i assigni una IP pública.

  A l'apartat de Seguretat (SSH), necessitem unes claus per connectar-nos.
  Les generem des del nostre terminal local:

  ```sh
  ssh-keygen -t rsa -b 4096 -C "oracle-key" -f oracle.key
  ```

  Copiem el contingut de `oracle.key.pub` i l'enganxem a la configuració de la instància ("Paste public keys").

  Fem clic a "Create" i esperem que l'estat passi a "Running".
  Anotem la nostra IP Pública per a les futures connexions.


  2. Obertura de Ports (Firewall d'Oracle)

  Perquè Retrofit pugui comunicar-se amb l'API, cal obrir el port del nostre .jar (ex: 8080).
  Dins de la instància, clica sobre la Subnet assignada.


  Accedeix a la Default Security List.

  Fes clic a "Add Ingress Rules" i configura l'accés:
  Source CIDR: 0.0.0.0/0
  Protocol: TCP
  Destination Port: 8080

  Verifiquem que la regla s'ha afegit correctament a la llista. (Nota: No obrim el port 3306 de MySQL per seguretat).


  3. Connexió i Preparació del Servidor

  Ens connectem a la instància per SSH per instal·lar els paquets necessaris.

  Executem la connexió des del nostre terminal:

  ```sh
  ssh ubuntu@<LA_TEVA_IP> -i oracle.key
  ```

  Instal·lem Java per poder executar l'API:

  ```sh
  sudo apt update && sudo apt install openjdk-17-jdk -y
  ```


  Instal·lem Docker i Docker Compose per a la base de dades:

  ```sh
  sudo apt install docker.io docker-compose -y
  ```


  4. Desplegament de la Base de Dades (Docker Compose)

  Per persistir les dades del CRUD, aixecarem MySQL amb Docker.
  Creem i editem el fitxer de configuració: `nano docker-compose.yml`.

  Exemple mínim de `docker-compose.yml`:

  ```yaml
  version: '3.8'
  services:
    db:
      image: mysql:8.0
      environment:
        MYSQL_ROOT_PASSWORD: example_root_password
        MYSQL_DATABASE: fhubo_db
        MYSQL_USER: fhubo_user
        MYSQL_PASSWORD: fhubo_pass
      volumes:
        - db_data:/var/lib/mysql
      restart: unless-stopped
  volumes:
    db_data:
  ```

  Aixequem el contenidor en segon pla amb:

  ```sh
  docker-compose up -d
  ```

  Comprovem que la base de dades està funcionant amb:

  ```sh
  sudo docker ps
  ```


  5. Desplegament de l'API com a Daemon (Systemd)

  Finalment, pugem el `.jar` i el configurem com a servei perquè no caigui mai (solució tècnica avançada).

  Pugem l'arxiu des del nostre PC local:

  ```sh
  scp -i oracle.key api.jar ubuntu@<IP_PUBLICA>:/home/ubuntu/
  ```

  Creem l'arxiu del servei a `/etc/systemd/system/api-retrofit.service`.

  Exemple de unitat `systemd`:

  ```ini
  [Unit]
  Description=API Retrofit Service
  After=network.target

  [Service]
  User=ubuntu
  WorkingDirectory=/home/ubuntu
  ExecStart=/usr/bin/java -jar /home/ubuntu/api.jar --server.port=8080
  SuccessExitStatus=143
  Restart=always
  RestartSec=10

  [Install]
  WantedBy=multi-user.target
  ```

  Activem i iniciem el servei, i en comprovem l'estat:

  ```sh
  sudo systemctl enable api-retrofit.service
  sudo systemctl start api-retrofit.service
  sudo systemctl status api-retrofit.service
  ```

  Nota: Assegura't que la configuració de l'API apunta a la base de dades mitjançant la connexió interna (per exemple, utilitzant el nom del servei o `localhost` si s'utilitza `docker network`), i evita exposar directament el port 3306 al públic.


