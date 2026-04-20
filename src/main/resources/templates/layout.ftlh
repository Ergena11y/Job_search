<#macro layout>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Job Search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Job Search</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <#if user??>
                        <#if user.accountType == "EMPLOYER">

                            <li class="nav-item"><a class="nav-link" href="/profile?userId=${user.id}">Мои вакансии</a></li>
                        <#else>
                            <li class="nav-item"><a class="nav-link" href="/vacancies">Вакансии</a></li>
                            <li class="nav-item"><a class="nav-link" href="/profile?userId=${user.id}">Мои резюме</a></li>
                        </#if>
                        <li class="nav-item"><a class="nav-link" href="/auth/logout">Выход</a></li>
                    <#else>
                        <li class="nav-item"><a class="nav-link" href="/vacancies">Вакансии</a></li>
                        <li class="nav-item"><a class="nav-link" href="/resumes">Резюме</a></li>
                        <li class="nav-item"><a class="nav-link" href="/auth/login">Войти</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <#nested>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>