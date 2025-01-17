<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <#include "css/patient-style.css">

    <title>MediLabo - Patient List</title>
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
                    <a class="nav-link text-dark" aria-current="page" href="/patients/list">Liste des Patients</a>
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
            <li class="breadcrumb-item active" aria-current="page">Liste des Patients</li>
        </ol>
    </nav>
</div>

<div class="container">
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Id Patient</th>
                <th scope="col">Nom</th>
                <th scope="col">Prénom</th>
                <th scope="col">Informations Patient</th>
                <th scope="col">Suppression Patient</th>
            </tr>
        </thead>
        <tbody>
            <#list patients as patient>
                <#assign idPatient>${patient.idPatient}</#assign>
                <tr>
                    <th scope="row">${patient.idPatient}</th>
                    <td>${patient.prenom}</td>
                    <td>${patient.nom}</td>
                    <td><a href="/patients/${idPatient}">Fichier Patient - ${patient.prenom} ${patient.nom}</a></td>
                    <td><a href="/patients/delete-patient/${idPatient}">Supprimer ce patient</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<div class="d-grid gap-2 col-6 mx-auto">
    <a href="/patients/newPatientForm" class="btn btn-primary" role="button">Ajouter un nouveau patient</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>