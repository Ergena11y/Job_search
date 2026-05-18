<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <#assign isOwner = (user?? && user.id?? && resume.applicantId?? && user.id == resume.applicantId)>
    <#assign isEmployer = (user?? && user.accountType == "EMPLOYER")>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <a href="javascript:history.back()" class="btn btn-outline-secondary">
                        <i class="bi bi-chevron-left"></i> <@spring.message "common.back"/>
                    </a>
                    <#if isEmployer>
                        <span class="badge bg-info text-dark p-2"><@spring.message "resume.employer.mode"/></span>
                    </#if>
                    <#if isOwner>
                        <span class="badge bg-success p-2">Ваше резюме</span>
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
                                    <h5 class="fw-bold mb-3">
                                        <i class="bi bi-file-earmark-text text-primary"></i>
                                        <@spring.message "resume.about"/>
                                    </h5>
                                    <div class="p-3 bg-light rounded shadow-sm"
                                         style="white-space: pre-wrap; line-height: 1.6;">
                                        ${resume.description!'Информация отсутствует'}
                                    </div>
                                </div>

                                <#if resume.categoryName??>
                                    <div class="mb-4">
                                        <h5 class="fw-bold mb-2 small text-uppercase text-muted">
                                            <@spring.message "resume.specialization"/>
                                        </h5>
                                        <span class="badge bg-secondary px-3 py-2">
                                            ${resume.categoryName}
                                        </span>
                                    </div>
                                </#if>
                            </div>

                            <div class="col-md-4">
                                <div class="card border-0 bg-light p-3">
                                    <h5 class="fw-bold mb-4"><@spring.message "resume.contacts"/></h5>

                                    <div class="mb-3">
                                        <label class="text-muted small d-block">
                                            <@spring.message "resume.contact.email"/>
                                        </label>
                                        <#if isEmployer || isOwner>
                                            <a href="mailto:${resume.applicantEmail!''}"
                                               class="fw-bold text-decoration-none">
                                                ${resume.applicantEmail!'Не указан'}
                                            </a>
                                        <#else>
                                            <span class="fw-bold text-muted">
                                                <@spring.message "resume.contact.hidden"/>
                                            </span>
                                        </#if>
                                    </div>

                                    <div class="mb-3">
                                        <label class="text-muted small d-block">
                                            <@spring.message "resume.contact.phone"/>
                                        </label>
                                        <#if isEmployer || isOwner>
                                            <span class="fw-bold">
                                                ${resume.applicantPhone!'Не указан'}
                                            </span>
                                        <#else>
                                            <span class="fw-bold text-muted">
                                                <@spring.message "resume.contact.hidden"/>
                                            </span>
                                        </#if>
                                    </div>

                                    <hr>

                                    <#if isEmployer>
                                        <a href="mailto:${resume.applicantEmail!''}"
                                           class="btn btn-primary w-100 mb-2 py-2">
                                            <@spring.message "resume.contact.candidate"/>
                                        </a>
                                        <#if respondedId??>
                                            <a href="/chat/${respondedId}"
                                               class="btn btn-success w-100 mb-2 py-2">
                                                <i class="bi bi-chat-dots-fill me-1"></i>
                                                <@spring.message "chat.open"/>
                                            </a>
                                        </#if>
                                        <button class="btn btn-outline-dark w-100 py-2"
                                                onclick="window.print()">
                                            <@spring.message "resume.print"/>
                                        </button>

                                    <#elseif isOwner>
                                        <a href="/resumes/edit/${resume.id!''}"
                                           class="btn btn-outline-primary w-100 mb-2 py-2">
                                            <i class="bi bi-pencil me-1"></i>
                                            <@spring.message "resume.edit"/>
                                        </a>
                                        <button class="btn btn-outline-dark w-100 py-2"
                                                onclick="window.print()">
                                            <@spring.message "resume.print"/>
                                        </button>

                                        <#if myResponds?? && myResponds?has_content>
                                            <div class="mt-3">
                                                <h6 class="fw-bold text-muted small text-uppercase">
                                                    <@spring.message "chat.my.responds"/>
                                                </h6>
                                                <#list myResponds as ra>
                                                    <div class="d-flex justify-content-between align-items-center
                                                                border rounded p-2 mb-2 bg-white">
                                                        <span class="small">${ra.vacancy.name!''}</span>
                                                        <a href="/chat/${ra.id}"
                                                           class="btn btn-sm btn-success"
                                                           title="<@spring.message "chat.open"/>">
                                                            <i class="bi bi-chat-dots-fill"></i>
                                                        </a>
                                                    </div>
                                                </#list>
                                            </div>
                                        </#if>

                                    <#else>
                                        <p class="text-muted small text-center">
                                            <@spring.message "resume.contacts.employer.only"/>
                                        </p>
                                    </#if>
                                </div>

                                <div class="mt-4 text-center">
                                    <small class="text-muted">
                                        <@spring.message "resume.published.date"/>: <br>
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
