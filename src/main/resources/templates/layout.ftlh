<#import "spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Job Search</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="stylesheet" href="../static/css/layout.css">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="/">
                <i class="bi bi-briefcase-fill me-1"></i>Job Search
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-center">
                    <#if user??>

                        <#if user.accountType == "EMPLOYER">
                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies">
                                    <i class="bi bi-list-ul me-1"></i>Вакансии
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/resumes">
                                    <i class="bi bi-file-person me-1"></i>Резюме
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/companies">
                                    <i class="bi bi-building me-1"></i>Компании
                                </a>
                            </li>
                        <#else>

                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies">
                                    <i class="bi bi-list-ul me-1"></i>Вакансии
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/companies">
                                    <i class="bi bi-building me-1"></i>Компании
                                </a>
                            </li>
                        </#if>

                        <li class="nav-item">
                            <a class="nav-link" href="/profile">
                                <i class="bi bi-person-circle me-1"></i>Профиль
                            </a>
                        </li>
                        <li class="nav-item">
                            <form action="/auth/logout" method="post" class="m-0 d-inline">
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </#if>
                                <button type="submit" class="btn btn-link nav-link text-white p-0 ms-2">
                                    <i class="bi bi-box-arrow-right me-1"></i>Выйти
                                </button>
                            </form>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/vacancies">
                                <i class="bi bi-list-ul me-1"></i>Вакансии
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/companies">
                                <i class="bi bi-building me-1"></i>Компании
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/register">
                                <i class="bi bi-person-plus me-1"></i>Регистрация
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">
                                <i class="bi bi-box-arrow-in-right me-1"></i>Войти
                            </a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container py-4">
        <#nested>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>
