<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <#include "css/patient-style.css">

    <title>MediLabo - Formulaire Nouveau Patient</title>
</head>

<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">
                <button type="button" class="button-37" role="button">MediLabo</button>
            </a>
            <div class="collapse navbar-collapse flex-row-reverse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link text-dark" href="/home">Accueil</a>
                    <a class="nav-link text-dark" href="/patient/list">Liste des Patients</a>
                    <a class="nav-link text-dark" href="/contact">Nous Contacter</a>
                    <a class="nav-link text-dark" href="/logout">Déconnection</a>
                </div>
            </div>
        </div>
    </nav>
</div>

<div class="container bg-light">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/home">Accueil</a></li>
            <li class="breadcrumb-item"><a href="/patient/list">Liste des Patients</a></li>
            <li class="breadcrumb-item active" aria-current="page">Formulaire Nouveau Patient</li>
        </ol>
    </nav>
</div>

<div class="container">
    <div class="container" id="patient-information-container">
        <div class="row">
            <div class="d-flex justify-content-start" id="transaction-title">
                <h5>Formulaire Nouveau Patient</h5>
            </div>
            <form method="post" action="/patient/addNewPatient" id="newPatientForm">
                <div class="mb-3">
                    <label for="prenom" class="form-label">Prénom</label>
                    <input type="text" name="prenom" class="form-control" id="prenom" aria-describedby="prenomHelp">
                </div>
                <div class="mb-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" name="nom" class="form-control" id="nom" aria-describedby="nomHelp">
                </div>
                <div class="mb-3">
                    <label for="dateNaissance" class="form-label">Date de Naissance</label>
                    <input type="text" name="dateNaissance" class="form-control" id="dateNaissance" aria-describedby="dateNaissanceHelp">
                </div>
                <div class="mb-3">
                    <label for="genre" class="form-label">Genre</label>
                    <input type="text" name="genre" class="form-control" id="genre" aria-describedby="genreHelp">
                </div>
                <div class="mb-3">
                    <label for="adresse" class="form-label">Adresse</label>
                    <input type="text" name="adresse" class="form-control" id="adresse" aria-describedby="adresseHelp">
                </div>
                <div class="mb-3">
                    <label for="telephone" class="form-label">Numéro de téléphone</label>
                    <input type="text" name="telephone" class="form-control" id="telephone" aria-describedby="telephoneHelp">
                </div>
                <button type="submit" class="btn btn-primary">Ajouter ce nouveau patient</button>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>