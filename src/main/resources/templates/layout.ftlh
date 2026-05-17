<#import "/spring.ftl" as spring>
<#macro layout>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title><@spring.message "common.site.title"/></title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <link rel="stylesheet" href="../static/css/layout.css">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="/">
                <i class="bi bi-briefcase-fill me-1"></i><@spring.message "nav.brand"/>
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
                                    <i class="bi bi-list-ul me-1"></i><@spring.message "nav.vacancies"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/resumes">
                                    <i class="bi bi-file-person me-1"></i><@spring.message "nav.resumes"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/companies">
                                    <i class="bi bi-building me-1"></i><@spring.message "nav.companies"/>
                                </a>
                            </li>
                        <#else>

                            <li class="nav-item">
                                <a class="nav-link" href="/vacancies">
                                    <i class="bi bi-list-ul me-1"></i><@spring.message "nav.vacancies"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/companies">
                                    <i class="bi bi-building me-1"></i><@spring.message "nav.companies"/>
                                </a>
                            </li>
                        </#if>

                        <li class="nav-item">
                            <a class="nav-link" href="/profile">
                                <i class="bi bi-person-circle me-1"></i><@spring.message "nav.profile"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <form action="/auth/logout" method="post" class="m-0 d-inline">
                                <#if _csrf??>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </#if>
                                <button type="submit" class="btn btn-link nav-link text-white p-0 ms-2">
                                    <i class="bi bi-box-arrow-right me-1"></i><@spring.message "nav.logout"/>
                                </button>
                            </form>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/vacancies">
                                <i class="bi bi-list-ul me-1"></i><@spring.message "nav.vacancies"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/register">
                                <i class="bi bi-person-plus me-1"></i><@spring.message "nav.register"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">
                                <i class="bi bi-box-arrow-in-right me-1"></i><@spring.message "nav.login"/>
                            </a>
                        </li>
                    </#if>


                    <li class="nav-item dropdown ms-3">
                        <a class="nav-link dropdown-toggle" href="#" id="langDropdown" role="button" data-bs-toggle="dropdown">
                            <i class="bi bi-globe me-1"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="langDropdown">
                            <li><a class="dropdown-item" href="?lang=ru"><@spring.message "nav.lang.ru"/></a></li>
                            <li><a class="dropdown-item" href="?lang=en"><@spring.message "nav.lang.en"/></a></li>
                        </ul>
                    </li>
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
