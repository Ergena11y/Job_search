<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body text-center">
                        <#if user.avatar??>
                            <img src="${user.avatar}" class="img-fluid rounded-circle mb-2" style="max-width: 150px;">
                        <#else>
                            <div class="bg-light rounded-circle d-inline-flex" style="width: 150px; height: 150px; align-items: center; justify-content: center;">
                                <span class="text-muted">Нет фото</span>
                            </div>
                        </#if>
                        <h3>${user.name} ${user.surname}</h3>
                        <p class="text-muted">${user.email}</p>
                        <p>${user.phoneNumber}</p>
                        <p><strong>Тип аккаунта:</strong> ${user.accountType}</p>
                        <a href="/profile/edit?userId=${user.id!''}" class="btn btn-primary">Редактировать профиль</a>
                    </div>
                </div>
            </div>




            <div class="col-md-8">
                <h3>Мои резюме</h3>

                <#if resumes?has_content>
                    <div class="list-group">
                        <#list resumes as resume>
                            <div class="list-group-item">
                                <div class="d-flex justify-content-between align-items-start">
                                    <div>
                                        <h5 class="mb-1">${resume.name}</h5>
                                        <p class="mb-1">Желаемая зарплата: <strong>${resume.salary?string["0.00"]} ⃀</strong></p>
                                        <p class="text-muted mb-0">Создано: ${resume.createdDate}</p>
                                    </div>
                                    <div>
                                        <a href="/resumes/edit/${resume.id!''}" class="btn btn-sm btn-warning">Редактировать</a>
                                        <a href="/resumes/delete/${resume.id!''}" class="btn btn-sm btn-danger">Удалить</a>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>




                    <nav aria-label="Page navigation" class="mt-3">
                        <ul class="pagination">
                            <#if currentResumePage == 0>
                                <li class="page-item disabled"><a class="page-link" href="#">Предыдущая</a></li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="/profile?userId=${user.id!''}&resumePage=${currentResumePage - 1}">Предыдущая</a></li>
                            </#if>

                            <#list 0..totalResumePages - 1 as p>
                                <#if p == currentResumePage>
                                    <li class="page-item active"><span class="page-link">${p + 1}</span></li>
                                <#else>
                                    <li class="page-item"><a class="page-link" href="/profile?userId=${user.id!''}&resumePage=${p}">${p + 1}</a></li>
                                </#if>
                            </#list>

                            <#if currentResumePage == totalResumePages - 1>
                                <li class="page-item disabled"><a class="page-link" href="#">Следующая</a></li>
                            <#else>
                                <li class="page-item"><a class="page-link" href="/profile?userId=${user.id!''}&resumePage=${currentResumePage + 1}">Следующая</a></li>
                            </#if>
                        </ul>
                    </nav>
                <#else>
                    <div class="alert alert-info">У вас нет резюме. <a href="/resumes/create">Создать резюме</a></div>
                </#if>
            </div>
        </div>
    </div>
</@main.layout>