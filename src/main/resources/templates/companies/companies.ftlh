<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">Компании</h2>
            <span class="text-muted small">Всего: ${totalItems!0} компаний</span>
        </div>

        <#if companies?? && companies?has_content>

            <#list companies as company>
                <div class="card shadow-sm mb-3 w-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">


                            <div class="me-3">
                                <#if company.avatar?? && company.avatar != "">
                                    <img src="/${company.avatar}"
                                         class="rounded-circle"
                                         style="width:60px;height:60px;object-fit:cover;" alt="Logo">
                                <#else>
                                    <div class="rounded-circle bg-primary d-flex align-items-center justify-content-center"
                                         style="width:60px;height:60px;">
                                        <span class="text-white fw-bold fs-4">
                                            ${company.name?substring(0,1)?upper_case!'?'}
                                        </span>
                                    </div>
                                </#if>
                            </div>

                            <#-- Информация о компании -->
                            <div class="flex-grow-1">
                                <h5 class="card-title mb-1">
                                    <a href="/companies/${company.id!''}" class="text-decoration-none text-dark fw-bold">
                                        ${company.name!''} ${company.surname!''}
                                    </a>
                                </h5>
                                <p class="text-muted small mb-1">
                                    <i class="bi bi-envelope me-1"></i>${company.email!''}
                                    <#if company.phoneNumber?? && company.phoneNumber != "">
                                        <span class="mx-2">·</span>
                                        <i class="bi bi-telephone me-1"></i>${company.phoneNumber}
                                    </#if>
                                </p>
                                <span class="badge bg-warning text-dark">Работодатель</span>
                            </div>

                            <div class="text-end">
                                <a href="/companies/${company.id!''}" class="btn btn-sm btn-outline-primary">
                                    <i class="bi bi-building me-1"></i>Профиль компании
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>

        <#-- Пагинация -->
            <#if totalPages gt 1>
                <nav class="mt-4">
                    <ul class="pagination justify-content-center">
                        <#if currentPage == 0>
                            <li class="page-item disabled"><a class="page-link" href="#">Предыдущая</a></li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link" href="/companies?page=${currentPage - 1}">Предыдущая</a>
                            </li>
                        </#if>

                        <#list 0..totalPages - 1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link" href="/companies?page=${p}">${p + 1}</a>
                            </li>
                        </#list>

                        <#if currentPage == totalPages - 1>
                            <li class="page-item disabled"><a class="page-link" href="#">Следующая</a></li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link" href="/companies?page=${currentPage + 1}">Следующая</a>
                            </li>
                        </#if>
                    </ul>
                </nav>
            </#if>
        <#else>
            <div class="alert alert-info">
                <i class="bi bi-info-circle me-2"></i>Компании пока не зарегистрированы
            </div>
        </#if>
    </div>
</@main.layout>