<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>MediLabo - Patient Notes</title>
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
            <li class="breadcrumb-item active" aria-current="page">Historique des Notes du Patient</li>
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
            <th scope="col">Id Note</th>
            <th scope="col">Note</th>
        </tr>
        </thead>
        <tbody>
        <#if notes?? && notes?size gt 0>
            <#list notes as note>
                <tr>
                    <th scope="row">${note._id}</th>
                    <td>${note.note}</td>
                </tr>
            </#list>
        <#else>
            <tr>
                <td colspan="2">Aucune note pour ce patient</td>
            </tr>
        </#if>
        </tbody>
    </table>
    <a class="btn btn-primary" href="/doc/newPatientNoteForm/${patientName!}&${patientId!}" role="button">Ajouter une nouvelle note</a>
</div>

</body>
</html>