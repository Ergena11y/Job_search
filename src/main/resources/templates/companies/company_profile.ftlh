<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-body text-center">
                        <#if company.avatar?? && company.avatar != "">
                            <img src="/${company.avatar}"
                                 class="rounded-circle img-thumbnail mb-3"
                                 style="width:130px;height:130px;object-fit:cover;" alt="Logo">
                        <#else>
                            <div class="rounded-circle bg-primary d-inline-flex align-items-center justify-content-center mb-3"
                                 style="width:130px;height:130px;">
                                <span class="text-white fw-bold" style="font-size:48px;">
                                    ${company.name?substring(0,1)?upper_case!'?'}
                                </span>
                            </div>
                        </#if>

                        <h4 class="mb-1">${company.name!''} ${company.surname!''}</h4>
                        <span class="badge bg-warning text-dark mb-3">Работодатель</span>

                        <ul class="list-group list-group-flush text-start">
                            <li class="list-group-item px-0">
                                <i class="bi bi-envelope me-2 text-muted"></i>${company.email!''}
                            </li>
                            <#if company.phoneNumber?? && company.phoneNumber != "">
                                <li class="list-group-item px-0">
                                    <i class="bi bi-telephone me-2 text-muted"></i>${company.phoneNumber}
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
                <a href="/companies" class="btn btn-outline-secondary w-100">
                    <i class="bi bi-arrow-left me-1"></i>Назад к списку
                </a>
            </div>

            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-light">
                        <h5 class="mb-0">Вакансии компании</h5>
                    </div>
                    <div class="card-body">
                        <div class="alert alert-info mb-0">
                            <i class="bi bi-info-circle me-2"></i>
                            Для просмотра вакансий компании перейдите в
                            <a href="/vacancies" class="alert-link">раздел вакансий</a>.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
