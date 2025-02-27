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
                <h5>Formulaire Nouveau Patient</h5>
            </div>
            <form method="post" action="/patients/addNewPatient" id="newPatientForm">
                <div class="mb-3">
                    <label for="nom" class="form-label">Nom</label>
                    <input type="text" name="nom" class="form-control" id="nom" aria-describedby="nomHelp">
                    <#if errors?? && errors["nom"]?? >
                        <div class="alert alert-danger" role="alert">${errors["nom"]}</div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="prenom" class="form-label">Prénom</label>
                    <input type="text" name="prenom" class="form-control" id="prenom" aria-describedby="prenomHelp">
                    <#if errors?? && errors["prenom"]?? >
                        <div class="alert alert-danger" role="alert">${errors["prenom"]}</div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="dateNaissance" class="form-label">Date de Naissance</label>
                    <input type="text" name="dateNaissance" class="form-control" id="dateNaissance" aria-describedby="dateNaissanceHelp">
                    <#if errors?? && errors["dateNaissance"]?? >
                        <div class="alert alert-danger" role="alert">${errors["dateNaissance"]}</div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="genre" class="form-label">Genre</label>
                    <input type="text" name="genre" class="form-control" id="genre" aria-describedby="genreHelp">
                    <#if errors?? && errors["genre"]?? >
                        <div class="alert alert-danger" role="alert">${errors["genre"]}</div>
                    </#if>
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

</body>
</html>