<!DOCTYPE html>
<html lang="FR">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Pay My Buddy - Log In</title>
</head>
<body>
<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-3">
                <div id="login-box">
                    <div class="md-3 d-flex justify-content-center">
                        <#if error?? >
                            <div class="alert alert-danger" role="alert">${error}</div>
                        </#if>
                    </div>
                    <form id="login-form" name="f" class="form" method="POST">

                        <!-- Email input -->
                        <div class="form-floating mb-3 form-field">
                            <input type="text" name="username" class="form-control" id="username"/>
                            <label for="username">Username</label>
                        </div>

                        <!-- Password input -->
                        <div class="form-floating mb-3 form-field">
                            <input type="password" name="password" class="form-control" id="password"/>
                            <label for="password">Password</label>
                        </div>

                        <div class="row pt-2">
                            <div class="col text-center">
                                <!-- Submit button -->
                                <input type="submit" value="Submit">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>