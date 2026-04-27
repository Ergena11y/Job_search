<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <#-- Информация о пользователе -->
        <div class="col-md-4">
            <div class="card shadow-sm mb-4">
                <div class="card-body text-center">
                    <#if user.avatar??>
                        <img src="/images/${user.avatar}" class="rounded-circle img-thumbnail mb-3"
                             style="width: 150px; height: 150px; object-fit: cover;" alt="Avatar">
                    <#else>
                        <img src="/images/tony-stark-relief.jpg" class="rounded-circle img-thumbnail mb-3"
                             style="width: 150px; height: 150px; object-fit: cover;" alt="Default Avatar">
                    </#if>
                    <h3 class="card-title">${user.name!''} ${user.surname!''}</h3>
                    <p class="text-muted">${(user.accountType == "EMPLOYER")?string("Работодатель", "Соискатель")}</p>
                    <ul class="list-group list-group-flush text-start">
                        <li class="list-group-item"><strong>Email:</strong> ${user.email!''}</li>
                        <li class="list-group-item"><strong>Телефон:</strong> ${user.phoneNumber!'Не указан'}</li>
                    </ul>
                    <div class="mt-3">
                        <a href="/profile/edit" class="btn btn-outline-primary btn-sm w-100">Редактировать профиль</a>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-md-8">


            <#if user.accountType == "EMPLOYER">

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h4 class="mb-0">Мои вакансии</h4>
                    <a href="/vacancies/create" class="btn btn-success btn-sm">Добавить вакансию</a>
                </div>

                <#if vacancies?? && vacancies?has_content>
                    <div class="list-group mb-4">
                        <#list vacancies as v>
                            <a href="/vacancies/${v.id!''}" class="list-group-item list-group-item-action shadow-sm mb-2">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1 text-primary">${v.name!'-'}</h5>
                                    <small class="text-muted">Откликов: ${v.responseCount!0}</small>
                                </div>
                                <p class="mb-1 text-truncate">${v.description!''}</p>
                            </a>
                        </#list>
                    </div>

                    <#if totalVacancyPages gt 1>
                        <nav>
                            <ul class="pagination pagination-sm justify-content-center">
                                <#list 0..totalVacancyPages-1 as i>
                                    <li class="page-item ${(i == currentVacancyPage)?string('active', '')}">
                                        <a class="page-link" href="/profile?vacancyPage=${i}">${i + 1}</a>
                                    </li>
                                </#list>
                            </ul>
                        </nav>
                    </#if>
                <#else>
                    <div class="alert alert-light border mb-4">У вас пока нет активных вакансий.</div>
                </#if>

                <hr class="my-4">


                <h4 class="mb-3">База резюме</h4>
                <#if resumes?? && resumes?has_content>
                    <div class="list-group">
                        <#list resumes as r>
                            <div class="list-group-item shadow-sm mb-2 border-start border-primary border-4">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${r.name!'-'}</h5>
                                    <span class="badge bg-success">${r.salary!''} ⃀</span>
                                </div>
                                <p class="mb-1 small"><strong>Соискатель:</strong> ${r.applicantName!'Аноним'}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">Обновлено: ${r.updateTime!''}</small>
                                    <a href="/resumes/${r.id!''}" class="btn btn-link btn-sm p-0">Посмотреть подробнее</a>
                                </div>
                            </div>
                        </#list>
                    </div>


                    <#if totalResPage gt 1>
                        <nav class="mt-3">
                            <ul class="pagination pagination-sm justify-content-center">
                                <#list 0..totalResPage-1 as i>
                                    <li class="page-item ${(i == currentResumesPage)?string('active', '')}">
                                        <a class="page-link" href="/profile?resumePage=${i}">${i + 1}</a>
                                    </li>
                                </#list>
                            </ul>
                        </nav>
                    </#if>
                <#else>
                    <div class="alert alert-info">В системе пока нет доступных резюме.</div>
                </#if>

            <#-- СЕКЦИЯ ДЛЯ СОИСКАТЕЛЯ -->
            <#else>
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="mb-0">Мои резюме</h4>
                    <a href="/resumes/create" class="btn btn-success btn-sm">Создать резюме</a>
                </div>

                <#if resumes?? && resumes?has_content>
                    <div class="list-group">
                        <#list resumes as r>
                            <div class="list-group-item list-group-item-action shadow-sm mb-2">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${r.name!'-'}</h5>
                                    <span class="text-success fw-bold">${r.salary!''} ⃀</span>
                                </div>
                                <div class="mt-2">
                                    <a href="/resumes/edit/${r.id!''}" class="btn btn-sm btn-outline-secondary">Редактировать</a>
                                    <a href="/resumes/${r.id!''}" class="btn btn-sm btn-outline-primary">Просмотр</a>
                                </div>
                            </div>
                        </#list>
                    </div>


                    <#if totalResPage gt 1>
                        <nav class="mt-3">
                            <ul class="pagination pagination-sm justify-content-center">
                                <#list 0..totalResPage-1 as i>
                                    <li class="page-item ${(i == currentResumesPage)?string('active', '')}">
                                        <a class="page-link" href="/profile?resumePage=${i}">${i + 1}</a>
                                    </li>
                                </#list>
                            </ul>
                        </nav>
                    </#if>
                <#else>
                    <div class="alert alert-light border">Вы еще не создали ни одного резюме.</div>
                </#if>
            </#if>

        </div>
    </div>
</@main.layout>