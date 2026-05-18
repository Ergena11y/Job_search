<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-10">


                <div class="d-flex justify-content-between align-items-center mb-4">
                    <a href="javascript:history.back()" class="btn btn-outline-secondary">
                        <i class="bi bi-chevron-left"></i> <@spring.message "common.back."/>
                    </a>
                    <#if user?? && user.accountType == "EMPLOYER">
                        <span class="badge bg-info text-dark p-2"><@spring.message "resume.employer.mode"/></span>
                    </#if>
                </div>

                <div class="card shadow-lg border-0">

                    <div class="card-header bg-white border-bottom py-4">
                        <div class="row align-items-center">
                            <div class="col-md-8">
                                <h1 class="display-6 fw-bold text-dark mb-1">${resume.name!'-'}</h1>
                                <p class="lead text-primary mb-0">
                                    <i class="bi bi-person-badge"></i> ${resume.applicantName!'ФИО не указано'}
                                </p>
                            </div>
                            <div class="col-md-4 text-md-end mt-3 mt-md-0">
                                <h3 class="text-success fw-bold">${resume.salary!''} ⃀</h3>
                                <p class="text-muted small"><@spring.message "resume.salary"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="card-body p-4">
                        <div class="row">

                            <div class="col-md-8">
                                <div class="mb-5">
                                    <h5 class="fw-bold mb-3"><i class="bi bi-file-earmark-text text-primary"></i><@spring.message "resume.about"/></h5>
                                    <div class="p-3 bg-light rounded shadow-sm" style="white-space: pre-wrap; line-height: 1.6;">
                                        ${resume.description!'Информация отсутствует'}
                                    </div>
                                </div>

                                <#if resume.categoryName??>
                                    <div class="mb-4">
                                        <h5 class="fw-bold mb-2 small text-uppercase text-muted">Специализация</h5>
                                        <span class="badge bg-secondary px-3 py-2">${resume.categoryName}</span>
                                    </div>
                                </#if>
                            </div>


                            <div class="col-md-4">
                                <div class="card border-0 bg-light p-3">
                                    <h5 class="fw-bold mb-4"><@spring.message "resume.contacts"/></h5>

                                    <div class="mb-3">
                                        <label class="text-muted small d-block"><@spring.message "resume.contact.email"/></label>
                                        <a href="mailto:${resume.applicantEmail!''}" class="fw-bold text-decoration-none">
                                            ${resume.applicantEmail!'Скрыто или не указано'}
                                        </a>
                                    </div>

                                    <div class="mb-3">
                                        <label class="text-muted small d-block"><@spring.message "resume.contact.phone"/></label>
                                        <span class="fw-bold">${resume.applicantPhone!'Не указан'}</span>
                                    </div>

                                    <hr>

                                    <#if user?? && user.accountType == "EMPLOYER">
                                        <a href="mailto:${resume.applicantEmail!''}" class="btn btn-primary w-100 mb-2 py-2">
                                            <@spring.message "resume.contact.candidate"/>
                                        </a>
                                        <button class="btn btn-outline-dark w-100 py-2">
                                            <@spring.message "resume.print"/>
                                        </button>
                                    <#else>
                                        <p class="text-muted small text-center italic">
                                            <@spring.message "resume.contacts.employer.only"/>
                                        </p>
                                    </#if>
                                </div>

                                <div class="mt-4 text-center">
                                    <small class="text-muted">
                                       <@spring.message "resume.publish"/>: <br>
                                        <strong>${resume.createdDate!''}</strong>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>