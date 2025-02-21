<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

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
            <li class="breadcrumb-item"><a href="/home">Accueil</a></li>
            <li class="breadcrumb-item"><a href="/patients/list">Liste des Patients</a></li>
            <li class="breadcrumb-item active" aria-current="page">Formulaire Nouveau Patient</li>
        </ol>
    </nav>
</div>

<div class="container">
    <div class="container" id="patient-information-container">
        <div class="row">
            <div class="md-3 d-flex justify-content-center">
                <#if error?? >
                    <div class="alert alert-danger" role="alert">${error}</div>
                </#if>
            </div>
            <div class="d-flex justify-content-start" id="transaction-title">
                <h5>Nouvelle Note Patient</h5>
            </div>
            <form method="post" action="/doc/write-note" id="newPatientNoteForm">
                <input type="hidden" name="patientName" value="${patientName}">
                <input type="hidden" name="patientId" value="${patientId}">
                <div class="mb-3">
                    <label for="nom" class="form-label">Nom du patient</label>
                    <input type="text" name="nom" class="form-control" id="nom" aria-describedby="nomHelp" value="${patientName}" disabled>
                </div>
                <div class="mb-3">
                    <label for="note" class="form-label">Note</label>
                    <input type="text" name="note" class="form-control" id="note" aria-describedby="noteHelp">
                    <!-- <#if errors?? && errors["note"]?? >
                        <div class="alert alert-danger" role="alert">${errors["note"]}</div>
                    </#if> -->
                </div>
                <button type="submit" class="btn btn-primary">Sauvegarder cette note</button>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>