<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-10">

                <div class="d-flex justify-content-between align-items-center mb-4">
                    <a href="javascript:history.back()" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left"></i> <@spring.message "common.back"/>
                    </a>
                </div>

                <div class="card shadow-lg border-0">

                    <div class="card-header bg-white border-bottom py-4">
                        <div class="row align-items-center">
                            <div class="col-md-8">
                                <h1 class="display-6 fw-bold text-dark mb-1">${vacancy.name!'-'}</h1>
                                <p class="lead text-muted mb-0">
                                    <i class="bi bi-building"></i> <@spring.message "vacancy.company"/>: ${vacancy.authorName!''}
                                </p>
                            </div>
                            <div class="col-md-4 text-md-end mt-3 mt-md-0">
                                <h3 class="text-success fw-bold"><@spring.message "common.from"/> ${vacancy.salary!''} ⃀</h3>
                                <p class="text-muted small text-uppercase"><@spring.message "vacancy.income"/></p>
                            </div>
                        </div>
                    </div>

                    <div class="card-body p-4">
                        <div class="row">

                            <div class="col-md-8">
                                <div class="mb-5">
                                    <h5 class="fw-bold mb-3"><i class="bi bi-info-circle text-primary"></i> <@spring.message "vacancy.description.text"/></h5>
                                    <div class="p-3 bg-light rounded" style="white-space: pre-wrap; line-height: 1.6;">
                                        ${vacancy.description!''}
                                        <#if !vacancy.description??><@spring.message "vacancy.description.empty"/></#if>
                                    </div>
                                </div>

                                <#if vacancy.categoryName??>
                                    <div class="mb-4">
                                        <h5 class="fw-bold mb-2 small text-uppercase text-muted"><@spring.message "vacancy.field"/></h5>
                                        <span class="badge bg-secondary px-3 py-2">${vacancy.categoryName}</span>
                                    </div>
                                </#if>
                            </div>

                            <div class="col-md-4">
                                <div class="card border-0 bg-light p-3 shadow-sm">
                                    <h5 class="fw-bold mb-4"><@spring.message "vacancy.respond.block"/></h5>

                                    <#if user?? && user.accountType == "APPLICANT">
                                        <form action="/responses/apply/${vacancy.id}" method="post">
                                            <button type="submit" class="btn btn-primary w-100 py-2 mb-3">
                                                <@spring.message "vacancy.respond"/>
                                            </button>
                                        </form>
                                    <#elseif user?? && user.accountType == "EMPLOYER">
                                        <p class="text-muted small"><@spring.message "vacancy.employer.view"/></p>
                                        <a href="/vacancies/edit/${vacancy.id}" class="btn btn-outline-primary w-100"><@spring.message "vacancy.edit"/></a>
                                    <#else>
                                        <p class="text-danger small"><@spring.message "vacancy.login.to.respond"/></p>
                                    </#if>

                                    <hr>
                                    <div class="mb-3">
                                        <label class="text-muted small d-block"><@spring.message "vacancy.contact.person"/>:</label>
                                        <span class="fw-bold">${vacancy.authorName!''}</span>
                                    </div>
                                    <div class="mb-3">
                                        <label class="text-muted small d-block"><@spring.message "vacancy.contact.email"/>:</label>
                                        <a href="mailto:${vacancy.authorEmail!''}" class="text-decoration-none">${vacancy.authorEmail!''}</a>
                                    </div>
                                </div>

                                <div class="mt-4 text-center">
                                    <small class="text-muted">
                                        <@spring.message "vacancy.published"/>: <br>
                                        <strong>${vacancy.updateTime!''}</strong>
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