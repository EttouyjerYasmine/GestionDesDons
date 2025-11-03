# GestionDesDons

## 1. Description du projet
- **Contexte fonctionnel**: Application de gestion des dons et des campagnes de collecte pour une organisation (association, ONG, fondation).
- **Objectif**: Suivre les dons, gérer les donateurs et piloter les campagnes avec un tableau de bord de statistiques.
- **Public cible / cas d'usage**: Équipes de collecte de fonds, responsables associatifs, administrateurs.
- **En une phrase**: Application web qui permet de créer des campagnes, enregistrer des dons, gérer les donateurs et visualiser des indicateurs clés.

## 2. Architecture technique
### 2.1 Stack technologique
- **Backend**: Spring Boot 3.5.7, Spring Data JPA (Hibernate)
- **Frontend**: Thymeleaf, HTML/CSS, Bootstrap (via templates)
- **Base de données**: MySQL
- **Build**: Maven
- **Java**: 21

### 2.2 Structure du code
- `src/main/java/com/example/GestionDesDons/entity/` : classes JPA (`Campagne`, `Don`, `Donateur`).
- `src/main/java/com/example/GestionDesDons/repository/` : interfaces d'accès aux données (Spring Data JPA).
- `src/main/java/com/example/GestionDesDons/service/` : logique métier (`CampagneService`, `DonService`, `DonateurService`).
- `src/main/java/com/example/GestionDesDons/controller/` : contrôleurs web MVC (CRUD + dashboard).
- `src/main/resources/templates/` : vues Thymeleaf (`campagnes/`, `dons/`, `donateurs/`, `dashboard/`, `fragments/`).
- `src/main/resources/static/` : ressources statiques (`css/styles.css`).

### 2.3 Diagramme d’architecture :
![Structure Projet gestiondon](https://github.com/user-attachments/assets/a56eb28c-a0b8-422c-8b13-323746e7090c)

## 3. Fonctionnalités principales
- **CRUD**:
  - Campagnes: liste, création, édition, suppression.
  - Dons: liste, création, édition, suppression (association à une campagne et un donateur).
  - Donateurs: liste, création, édition, suppression.
- **Recherche / filtrage**: Non implémenté nativement dans les contrôleurs au moment présent. À étendre (exemples possibles: filtrage par date/montant/moyen/campagne/donateur).
- **Tableau de bord / statistiques**:
  - Total des montants de dons.
  - Répartition par campagne.
  - Répartition par mois.
  - Répartition par moyen de don.
- **Gestion des statuts**: Non applicable directement (le domaine ne prévoit pas de statut particulier côté entités dans cette version).

## 4. Modèle de données
### 4.1 Entités
- **Campagne**: `id`, `titre`, `objectif` (BigDecimal), `debut` (LocalDate), `fin` (LocalDate).
- **Don**: `id`, `montant` (BigDecimal), `dateDon` (LocalDate), `moyen` (String), `campagne` (ManyToOne), `donateur` (ManyToOne).
- **Donateur**: `id`, `nom`, `email`, `type` (particulier/entreprise, etc.).

### 4.2 Relations
- `Campagne 1..* — Don *..1` via `@OneToMany(mappedBy="campagne")` côté `Campagne` et `@ManyToOne` côté `Don`.
- `Donateur 1..* — Don *..1` via `@OneToMany(mappedBy="donateur")` côté `Donateur` et `@ManyToOne` côté `Don`.
- Aucune relation `@ManyToMany` ni `@OneToOne` dans cette version.

Schéma ER (texte):
- Campagne(id) 1..* — Don(campagne_id)
- Donateur(id) 1..* — Don(donateur_id)

### 4.3 Configuration base de données
- **URL**: `jdbc:mysql://localhost:3306/gestion_dons_db?useSSL=false&serverTimezone=UTC`
- **Utilisateur**: `root`
- **Mot de passe**: `""`
- **Dialecte**: `org.hibernate.dialect.MySQL8Dialect`
- **Stratégie DDL**: `spring.jpa.hibernate.ddl-auto=update`
- **Port serveur**: `8081`

## 5. Lancer le projet
### 5.1 Prérequis
- Java 21
- Maven 3.9+
- MySQL installé et base accessible (`gestion_dons_db`)

### 5.2 Installation
1. Cloner le dépôt.
2. Configurer `src/main/resources/application.properties` 
3. Créer la base `gestion_dons_db` dans MySQL si elle n'existe pas.
4. Lancer l'application:
   - `mvn spring-boot:run`
   - ou exécuter la classe main depuis l'IDE.

### 5.3 Accès
- **Accueil**: `http://localhost:8081/` (redirige vers `/dashboard`).
- **Dashboard**: `http://localhost:8081/dashboard`.
- **APIs stats**:
  - Total: `GET /api/stats/total`
  - Par campagne: `GET /api/stats/par-campagne`
  - Par mois: `GET /api/stats/par-mois`
  - Par moyen: `GET /api/stats/par-moyen`
- **CRUD Dons**:
  - Liste: `GET /dons` ou `/dons/`
  - Nouveau: `GET /dons/new`
  - Éditer: `GET /dons/edit/{id}`
  - Sauvegarder: `POST /dons/save`
  - Supprimer: `GET /dons/delete/{id}`
- **CRUD Campagnes**:
  - Liste: `GET /campagnes`
  - Nouveau: `GET /campagnes/new`
  - Éditer: `GET /campagnes/edit/{id}`
  - Sauvegarder: `POST /campagnes/save`
  - Supprimer: `GET /campagnes/delete/{id}`
- **CRUD Donateurs**:
  - Liste: `GET /donateurs` ou `/donateurs/`
  - Nouveau: `GET /donateurs/new`
  - Éditer: `GET /donateurs/edit/{id}`
  - Sauvegarder: `POST /donateurs/save`
  - Supprimer: `GET /donateurs/delete/{id}`

## 6. Jeu de données initial (optionnel)
- Aucun chargement automatique détecté (pas de `data.sql` ni de `CommandLineRunner`).
- Pour tester rapidement, créer quelques enregistrements via les formulaires `New` des sections Dons, Campagnes et Donateurs.

## 7. Démonstration (Vidéo)
- Lien: à compléter.
- Contenu attendu:
  - Navigation dans les pages (Campagnes, Dons, Donateurs, Dashboard).
  - Création d’un enregistrement (ex: Don).
  - Recherche / filtrage (si ajouté ultérieurement).
  - Dashboard / statistiques (total, par campagne, par mois, par moyen).

## 8. Auteurs / Encadrement
- Étudiant: Ettouyjer Yasmine
- Encadrant : Mohammed Lachgar
- Établissement: ENS-Marrakech
- Projet : Gestion des Dons 
