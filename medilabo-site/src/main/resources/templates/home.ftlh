<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <#include "css/home-style.css">

    <title>MediLabo - Accueil</title>
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
                    <a class="nav-link text-dark" aria-current="page" href="/home">Accueil</a>
                    <a class="nav-link text-dark" href="/patients/list">Liste des Patients</a>
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
            <li class="breadcrumb-item" aria-current="page"><a href="/home">Accueil</a></li>
        </ol>
    </nav>
</div>

<div class="container">
    <h1> Bienvenue sur votre espace MediLabo </h1>

    <div class="container">
        <div class="list-group">
            <a href="/patients/list" class="list-group-item list-group-item-action">Liste des patients</a>
            <a href="#" class="list-group-item list-group-item-action">Notes practicien</a>
            <a href="#" class="list-group-item list-group-item-action">Informations du compte</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>