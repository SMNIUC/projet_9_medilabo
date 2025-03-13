# MediLabo
## Description
**MediLabo** est un projet développé en Java avec Jakarta EE et Lombok. Ce projet utilise le SDK Java 22 et est conçu pour répondre aux besoins spécifiques des cliniques de santé et des cabinets privés sur le dépistage des risques de diabète.
## Prérequis
Avant de démarrer, assurez-vous d'avoir les prérequis suivants installés sur votre système :
- **Java SDK 22**
- Un IDE prenant en charge Jakarta EE (par exemple, IntelliJ IDEA Ultimate Edition)
- Maven ou Gradle (selon la configuration du projet)
- MySQL
- MongoDB
- Docker Desktop

## Installation
1. Clonez le dépôt du projet à l'aide de la commande suivante :
``` bash
    git clone https://github.com/SMNIUC/projet_9_medilabo.git
    cd projet_9_medilabo
```
1. Importez le projet dans votre IDE préféré.
2. Installez les dépendances en exécutant :
``` bash
    mvn install
```
ou si vous utilisez Gradle :
``` bash
    gradle build
```
1. Configurez le fichier `application.properties` ou équivalent pour fournir les variables d'environnement nécessaires. Par exemple :
``` properties
    server.port=8080
    database.url=jdbc:mysql://localhost:3306/medilabo
    database.username=nom_utilisateur
    database.password=mot_de_passe
```
## Lancement
Une fois le projet configuré, vous pouvez démarrer avec la commande suivante :
``` bash
mvn spring-boot:run
```
ou, si vous utilisez Gradle :
``` bash
gradle bootRun
```
les differents modules dans l'ordre suivant :
- medilabo-patient-api
- medilabo-doc-api
- medilabo-reporting-api
- medilabo-gateway
- medilabo-site

Accédez à l’application dans votre navigateur à l’adresse suivante : `http://localhost:8084`.
## Fonctionnalités principales
- medilabo-patient-api : Accès, mise à jour, et ajout des informations personnelles des patients.
- medilabo-doc-api : Accès à l'historique et ajout de notes d'observation du praticien sur le patient.
- medilabo-reporting-api : Génère des rapports sur le risque de diabète des patients.

## Technologies utilisées
- **Langage :** Java (Java SDK 22)
- **Framework principal :** Jakarta EE
- **Lombok** : Pour la réduction des bogues et la simplification du code.
- **Base de données :** MySQL (port 3306) + MongoDB (port 27017)

## Objectifs du Green Code 🌱
**Le Green Code vise à réduire l'empreinte environnementale des logiciels en optimisant leur consommation d'énergie et de ressources. Cela se traduit par :**

- Une meilleure efficacité énergétique (moins de CPU, RAM et stockage utilisés).
- Une réduction des émissions de CO₂ liées à l'hébergement et à l'exécution des applications.
- Une durabilité accrue du matériel, car une consommation optimisée réduit l'usure des serveurs et des appareils.


Voici quelques méthodes pour identifier les parties d'un code qui consomment trop de mémoire :

**1️⃣ Utilisation d'outils Profiler comme :**

- VisualVM (Java)
- Memory Profiler (Android)
- Valgrind (C/C++)
- Py-Spy (Python)
- Chrome DevTools (JavaScript) permettent d'analyser la consommation mémoire en temps réel.

**2️⃣ Analyse des allocations mémoire :**

- Les fuites mémoire (objets non libérés après usage).
- Les collections trop volumineuses (ex: listes, maps non nettoyées).
- L’utilisation excessive de variables globales qui restent en mémoire.

**3️⃣ Vérification des algorithmes :**

- Un algorithme inefficace peut consommer trop de mémoire. Vérifier la complexité en espace (O(n), O(n²)...).
- Remplacer des structures de données inappropriées (ex: utiliser un LinkedList au lieu d’un ArrayList sans raison).

**4️⃣ Surveillance des caches et buffers :**

- Un cache mal géré peut accumuler des données inutiles.
- Vérifier les objets stockés en mémoire et mettre en place une éviction efficace.

**5️⃣ Tests de charge et monitoring :**
- Simuler des utilisations intensives pour voir où la mémoire grimpe.
- Utiliser des outils comme Prometheus, Grafana, ou Datadog pour collecter des métriques.

## Pistes d'optimisation Green Code 🌱

- Alléger les controllers et les services en remplaçant les blocs `try-catch` par un gestionnaire global des exceptions via `@ControllerAdvice`.
- Certaines méthodes, comme `reportPatientStatus` ou `analyzePatientStatus` dans `ReportingService`, risquent de devenir longues si elles contiennent beaucoup de logique conditionnelle. Il faudrait les diviser en sous-méthodes qui respectent le principe de responsabilité unique.
- Certains blocs de logique, comme l'obtention des propriétés d'un patient ou la gestion des symptômes, sont répétés. Cela rend le code difficile à maintenir. Il serait mieux de centraliser les calculs fréquents ou la logique commune dans des services utilitaires réutilisables. Par exemple avec la gestion des symptômes : Créer `SymptomService` pour centraliser la capture et la manipulation de ces symptômes.
- Réduire les frameworks lourds comme Bootstrap et migrez vers des solutions plus légères comme TailwindCSS.
- Activer Lazy Loading dans les entités JPA pour réduire les lectures inutiles (charge de la mémoire).
- Utiliser des projections ou des requêtes optimisées avec un `select` spécifique pour limiter la consommation réseaux et I/O disque.
- Préférer des bases de données comme Postgres ou MariaDB, qui sont souvent plus efficaces en termes énergétiques que MySQL.