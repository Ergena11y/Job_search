<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body text-center">
                        <#if user.avatar?? && user.avatar?has_content>
                            <img src="${user.avatar}" class="img-fluid rounded-circle mb-2" style="max-width: 150px;">
                        <#else>
                            <div class="bg-light rounded-circle d-inline-flex mb-2" style="width: 150px; height: 150px; align-items: center; justify-content: center;">
                                <span class="text-muted">Нет фото</span>
                            </div>
                        </#if>
                        <h3>${user.name!'Имя'} ${user.surname!'Фамилия'}</h3>
                        <p class="text-muted">${user.email!''}</p>
                        <p>${user.phoneNumber!'Телефон не указан'}</p>
                        <p><strong>Тип:</strong> ${user.accountType!''}</p>

                        <a href="/profile/edit" class="btn btn-primary w-100">Редактировать профиль</a>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <#if user.accountType == "EMPLOYER">
                    <h4>Мои вакансии</h4>
                    <#if vacancies?? && vacancies?has_content>
                        <ul class="list-group">
                            <#list vacancies as v>
                                <li class="list-group-item">${v.name!'-'}</li>
                            </#list>
                        </ul>
                    <#else>
                        <p>У вас пока нет вакансий.</p>
                    </#if>
                <#else>
                    <h4>Мои резюме</h4>
                    <#if resumes?? && resumes?has_content>
                        <ul class="list-group">
                            <#list resumes as r>
                                <li class="list-group-item">
                                    <strong>${r.name!'-'}</strong>
                                    <br>
                                    <small>Зарплата: ${r.salary?string["0.00"]!'не указана'} ⃀</small>
                                    <#if r.createdDate??>
                                        <br>
                                        <small>Создано: ${r.createdDate}</small>
                                    </#if>
                                </li>
                            </#list>
                        </ul>
                    <#else>
                        <p>У вас пока нет резюме.</p>
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</@main.layout>