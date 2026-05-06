<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Job Search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="/">Job Search</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-center">
                    <#if user??>


                        <#if user.accountType == "EMPLOYER">


                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies">Вакансии</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/resumes">Резюме соискателей</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/companies">Компании</a>
                            </li>
                        <#else>


                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies">Вакансии</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/companies">Компании</a>
                            </li>
                        </#if>

                        <li class="nav-item">
                            <a class="nav-link" href="/profile">
                                <i class="bi bi-person-circle"></i> Профиль
                            </a>
                        </li>
                        <li class="nav-item">
                            <form action="/auth/logout" method="post" class="m-0">
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </#if>
                                <button type="submit" class="nav-link btn btn-link text-white">Выйти</button>
                            </form>
                        </li>
                    <#else>


                        <li class="nav-item">
                            <a class="nav-link" href="/vacancies">Вакансии</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/companies">Компании</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/register">Регистрация</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">Войти</a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container py-3">
        <#nested>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>