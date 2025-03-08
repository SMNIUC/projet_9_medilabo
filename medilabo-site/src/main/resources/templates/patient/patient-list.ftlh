<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>MediLabo - Patient List</title>
</head>

<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/patients/list">
                <button type="button" class="button-37" role="button">MediLabo</button>
            </a>
        </div>
    </nav>
</div>

<div class="container bg-light">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">Liste des Patients</li>
        </ol>
    </nav>
</div>

<div class="container">
    <div class="md-3 d-flex justify-content-center">
        <#if error?? >
            <div class="alert alert-danger" role="alert">${error}</div>
        </#if>
    </div>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Id Patient</th>
                <th scope="col">Nom</th>
                <th scope="col">Prénom</th>
                <th scope="col">Statut Patient</th>
                <th scope="col">Informations Patient</th>
                <th scope="col">Notes Patient</th>
                <th scope="col">Suppression Patient</th>
            </tr>
        </thead>
        <tbody>
            <#if patients?? && patients?size gt 0>
                <#list patients as patient>
                    <#assign idPatient>${patient.idPatient}</#assign>
                    <tr>
                        <th scope="row">${patient.idPatient}</th>
                        <td>${patient.nom}</td>
                        <td>${patient.prenom}</td>
                        <td>${(patient.statut)!"N/A"}</td>
                        <td><a href="/patients/${idPatient}">Fichier Patient</a></td>
                        <td><a href="/doc/newPatientNoteForm/${patient.nom}&${idPatient}">Nouvelle Note</a> - <a href="/doc/note-patient/${patient.nom}">Historique</a></td>
                        <td><a href="/patients/delete-patient/${idPatient}">Supprimer ce patient</a></td>
                    </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
<div class="d-grid gap-2 col-6 mx-auto">
    <a href="/patients/newPatientForm" class="btn btn-primary" role="button">Ajouter un nouveau patient</a>
</div>

</body>
</html>