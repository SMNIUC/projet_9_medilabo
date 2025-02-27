<!DOCTYPE html>
<html lang="FR">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>MediLabo - Patient Profile</title>
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
                <li class="breadcrumb-item"><a href="/patients/list">Liste des Patients</a></li>
                <li class="breadcrumb-item active" aria-current="page">Profile Patient</li>
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
                    <#if success?? >
                        <div class="alert alert-success" role="alert">${success}</div>
                    </#if>
                </div>
                <div class="d-flex justify-content-start" id="transaction-title">
                    <h5>Informations du Patient <span class="badge bg-info text-dark">Statut : ${patient.statut}</span></h5>
                </div>
                <form method="post" action="/patients/${patient.idPatient}" id="updatePatientInfoForm">
                    <input type="hidden" name="idpatient" value="${patient.idPatient}">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" name="nom" class="form-control" id="nom" aria-describedby="nomHelp" value="${patient.nom}">
                    </div>
                    <div class="mb-3">
                        <label for="prenom" class="form-label">Prénom</label>
                        <input type="text" name="prenom" class="form-control" id="prenom" aria-describedby="prenomHelp" value="${patient.prenom}">
                    </div>
                    <div class="mb-3">
                        <label for="dateNaissance" class="form-label">Date de Naissance</label>
                        <input type="text" name="dateNaissance" placeholder="YYYY-MM-DD" class="form-control" id="dateNaissance" aria-describedby="dateNaissanceHelp" value="${patient.dateNaissance}">
                    </div>
                    <div class="mb-3">
                        <label for="genre" class="form-label">Genre</label>
                        <input type="text" name="genre" class="form-control" id="genre" aria-describedby="genreHelp" value="${patient.genre}">
                    </div>
                    <div class="mb-3">
                        <label for="adresse" class="form-label">Adresse</label>
                        <input type="text" name="adresse" class="form-control" id="adresse" aria-describedby="adresseHelp" value="${patient.adresse!}">
                    </div>
                    <div class="mb-3">
                        <label for="telephone" class="form-label">Numéro de téléphone</label>
                        <input type="text" name="telephone" class="form-control" id="telephone" aria-describedby="telephoneHelp" value="${patient.telephone!}">
                    </div>
                    <button type="submit" class="btn btn-primary">Mettre à jour les informations</button>
                    <a class="btn btn-outline-primary" href="/doc/note-patient/${patient.nom}" role="button">Notes patient</a>
                </form>
            </div>
        </div>
    </div>

    </body>
</html>