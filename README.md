# Unit-Test-Gen-App-CodeT5

**Unit-Test-Gen-App-CodeT5** est un projet basé sur une architecture microservices, conçu pour gérer, générer et tester des tests unitaires pour des projets Java. Ce projet utilise différentes technologies et frameworks pour garantir évolutivité, flexibilité et facilité d'utilisation.

## Structure du Projet

Le dépôt est organisé en plusieurs répertoires comme suit :

### 1. **EurekaServer**
- Serveur de découverte de services utilisant Netflix Eureka.
- Permet la gestion de l'enregistrement et de la communication entre les microservices.

### 2. **FlaskAPI**
- Microservice développé avec Flask (Python) pour générer des tests unitaires à partir de code Java.
- Intègre le modèle `CodeT5-Base Unit Test` pour générer des tests.

### 3. **GateWay**
- Passerelle API pour router les requêtes vers les microservices appropriés.
- Gère l'authentification, l'autorisation et la transmission des requêtes.

### 4. **UnitTest**
- Interface utilisateur pour scanner les fichiers Java et générer les tests unitaires.
- Contient la logique backend pour interagir avec FlaskAPI.

### 5. **UserAuth-BackEnd**
- Microservice backend pour la gestion de l'authentification et de l'autorisation des utilisateurs.
- Assure un accès sécurisé au système.

### 6. **ace-front**
- Application frontEnd du projet.
- Développée avec Angular pour offrir une expérience utilisateur intuitive.

---

## Importation et Utilisation du Modèle CodeT5-Base Unit Test

Le projet intègre un modèle d'intelligence artificielle, **CodeT5-Base**, pour générer des tests unitaires.

### Prérequis

- **Docker** installé sur votre système.
- **Python** (si le modèle est exécuté localement sans Docker).

### Étape 1 : Télécharger le Modèle Préentrainé

1. Téléchargez le modèle préentrainé `CodeT5-Base` à partir du lien suivant :  
   [Google Drive - CodeT5-Base Model](https://drive.google.com/drive/folders/1a-J1XfUvM9SR_jijnUE3IXoBlquuZ9xs?usp=sharing)

2. Placez les fichiers du modèle dans un répertoire nommé `Model` à côté du fichier `app.py`. La structure doit ressembler à ceci :

```
Model/
   codeT5-base/
   |-- app.py
   |-- model.safetensors
   |-- config.json
   |-- tokenizer_config.json
   |-- autres fichiers nécessaires...
```

### Étape 2 : Exécuter le Modèle avec Docker

1. Naviguez dans le répertoire du modèle :
   ```bash
   cd Model/codeT5-base
   ```

2. Construisez l'image Docker :
   ```bash
   docker build -t my_model_api .
   ```

3. Lancez le conteneur Docker :
   ```bash
   docker run -p 5000:5000 my_model_api
   ```

Le modèle sera alors accessible sur `http://localhost:5000`.

---

## Démarrage du Projet

### Cloner le Dépôt

```bash
git clone https://github.com/hossam1956/ACE-Project-Unit-Test.git
cd ACE-Project-Unit-Test
```

### Lancer le Projet avec Docker Compose

1. Vérifiez que le fichier `docker-compose.yml` est correctement configuré.
2. Exécutez la commande suivante :
   ```bash
   docker-compose up --build
   ```

### Lancer le Microservice Unit Test en Local

1. Naviguez dans le répertoire `UnitTest`.
2. Exécutez la commande suivante pour démarrer le service :
   ```bash
   ./mvnw spring-boot:run
   ```
### Lancer le Microservice UserAuth en Local

1. Naviguez dans le répertoire `UserAuth-BackEnd`.
2. Exécutez la commande suivante pour démarrer le service :
   ```bash
   ./mvnw spring-boot:run
   ```
### Lancer le Frontend en Local

1. Accédez au répertoire `ace-front` :
   ```bash
   cd ace-front
   ```
2. Installez les dépendances :
   ```bash
   npm install
   ```
3. Lancez le serveur Angular :
   ```bash
   ng serve
   ```
4. Accédez à l'application sur `http://localhost:4200`.

---

## Démo

### Vidéo de Démonstration


https://github.com/user-attachments/assets/c6449f0e-d4ee-4f05-a9bf-3798190e6441



---

## Licence

Ce projet est sous licence MIT.
