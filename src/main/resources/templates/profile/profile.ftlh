<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row mt-4">

        <div class="col-md-4">
            <div class="card shadow-sm mb-4">
                <div class="card-body text-center">

                    <#if user.avatar?? && user.avatar != "">
                        <img src="/${user.avatar}"
                             class="rounded-circle img-thumbnail mb-3"
                             style="width:150px;height:150px;object-fit:cover;" alt="Avatar">
                    <#else>
                        <div class="rounded-circle bg-primary d-inline-flex align-items-center
                                    justify-content-center mb-3"
                             style="width:150px;height:150px;">
                            <span class="text-white fw-bold" style="font-size:48px;">
                                ${user.name?substring(0,1)?upper_case!'?'}
                            </span>
                        </div>
                    </#if>

                    <h4 class="card-title mb-1">${user.name!''} ${user.surname!''}</h4>
                    <span class="badge mb-3
                        <#if user.accountType == 'EMPLOYER'>bg-warning text-dark<#else>bg-success</#if>">
                        <#if user.accountType == "EMPLOYER">
                            <@spring.message "profile.employer"/>
                        <#else>
                            <@spring.message "profile.applicant"/>
                        </#if>
                    </span>

                    <ul class="list-group list-group-flush text-start mb-3">
                        <li class="list-group-item px-0">
                            <i class="bi bi-envelope me-2 text-muted"></i>${user.email!''}
                        </li>
                        <li class="list-group-item px-0">
                            <i class="bi bi-telephone me-2 text-muted"></i>
                            <#if user.phoneNumber?? && user.phoneNumber != "">
                                ${user.phoneNumber}
                            <#else>
                                <@spring.message "profile.phone.not.specified"/>
                            </#if>
                        </li>
                    </ul>

                    <a href="/profile/edit" class="btn btn-outline-primary btn-sm w-100">
                        <i class="bi bi-pencil me-1"></i><@spring.message "profile.edit"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-8">

            <#if user.accountType == "EMPLOYER">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="mb-0"><@spring.message "profile.my.vacancies"/></h4>
                    <a href="/vacancies/create" class="btn btn-success btn-sm">
                        <i class="bi bi-plus-lg me-1"></i><@spring.message "vacancy.create"/>
                    </a>
                </div>

                <#if vacancies?? && vacancies?has_content>
                    <#list vacancies as v>
                        <div class="card shadow-sm mb-3 w-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start">
                                    <div>
                                        <h5 class="card-title mb-1">
                                            <a href="/vacancies/${v.id!''}"
                                               class="text-decoration-none text-dark">
                                                ${v.name!'-'}
                                            </a>
                                        </h5>
                                        <p class="card-text text-muted small mb-2">
                                            ${(v.description!'')?truncate(120)}
                                        </p>
                                    </div>
                                    <span class="text-success fw-bold ms-3 text-nowrap">
                                        ${v.salary!''} ⃀
                                    </span>
                                </div>
                                <div class="d-flex gap-2">
                                    <a href="/vacancies/${v.id!''}"
                                       class="btn btn-sm btn-outline-primary">
                                        <@spring.message "profile.view"/>
                                    </a>
                                    <a href="/vacancies/edit/${v.id!''}"
                                       class="btn btn-sm btn-outline-secondary">
                                        <@spring.message "vacancy.edit"/>
                                    </a>
                                    <a href="/vacancies/${v.id!!''}/responses"
                                       class="btn btn-sm btn-outline-info">
                                        <i class="bi bi-people me-1"></i><@spring.message "vacancy.view.responses"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </#list>

                    <#if totalVacancyPages gt 1>
                        <nav>
                            <ul class="pagination pagination-sm justify-content-center">
                                <#list 0..totalVacancyPages-1 as i>
                                    <li class="page-item ${(i==currentVacancyPage)?string('active','')}">
                                        <a class="page-link" href="/profile?vacancyPage=${i}">
                                            ${i+1}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </nav>
                    </#if>
                <#else>
                    <div class="alert alert-light border mb-4">
                        <@spring.message "profile.no.vacancies"/>
                    </div>
                </#if>

                <hr class="my-4">

                <h4 class="mb-3"><@spring.message "profile.resume.base"/></h4>
                <#if resumes?? && resumes?has_content>
                    <#list resumes as r>
                        <div class="card shadow-sm mb-3 w-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start">
                                    <div>
                                        <h5 class="card-title mb-1">${r.name!'-'}</h5>
                                        <p class="card-text text-muted small mb-2">
                                            <i class="bi bi-person me-1"></i>
                                            <#if r.applicantName?? && r.applicantName != "">
                                                ${r.applicantName}
                                            <#else>
                                                <@spring.message "common.anonymous"/>
                                            </#if>
                                        </p>
                                    </div>
                                    <span class="badge bg-success ms-3">${r.salary!''} ⃀</span>
                                </div>
                                <a href="/resumes/${r.id!''}"
                                   class="btn btn-sm btn-outline-primary">
                                    <@spring.message "profile.see.resume"/>
                                </a>
                            </div>
                        </div>
                    </#list>

                    <#if totalResPage gt 1>
                        <nav>
                            <ul class="pagination pagination-sm justify-content-center">
                                <#list 0..totalResPage-1 as i>
                                    <li class="page-item ${(i==currentResumesPage)?string('active','')}">
                                        <a class="page-link" href="/profile?resumePage=${i}">
                                            ${i+1}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </nav>
                    </#if>
                <#else>
                    <div class="alert alert-info">
                        <@spring.message "profile.no.resumes.system"/>
                    </div>
                </#if>

            <#else>
            <#-- APPLICANT -->
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="mb-0"><@spring.message "profile.my.resumes"/></h4>
                    <a href="/resumes/create" class="btn btn-success btn-sm">
                        <i class="bi bi-plus-lg me-1"></i><@spring.message "resume.create"/>
                    </a>
                </div>

                <#if resumes?? && resumes?has_content>
                    <#list resumes as r>
                        <div class="card shadow-sm mb-3 w-100">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start">
                                    <h5 class="card-title mb-1">${r.name!'-'}</h5>
                                    <span class="text-success fw-bold">${r.salary!''} ⃀</span>
                                </div>
                                <p class="text-muted small mb-2">
                                    <@spring.message "resume.updated"/>: ${r.formattedUpdateTime!''}
                                </p>
                                <div class="d-flex gap-2">
                                    <a href="/resumes/${r.id!''}"
                                       class="btn btn-sm btn-outline-primary">
                                        <@spring.message "profile.view"/>
                                    </a>
                                    <a href="/resumes/edit/${r.id!''}"
                                       class="btn btn-sm btn-outline-secondary">
                                        <@spring.message "resume.edit"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </#list>

                    <#if totalResPage gt 1>
                        <nav>
                            <ul class="pagination pagination-sm justify-content-center">
                                <#list 0..totalResPage-1 as i>
                                    <li class="page-item ${(i==currentResumesPage)?string('active','')}">
                                        <a class="page-link" href="/profile?resumePage=${i}">
                                            ${i+1}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </nav>
                    </#if>
                <#else>
                    <div class="alert alert-light border">
                        <@spring.message "profile.no.resumes"/>
                    </div>
                </#if>

            <#-- Applicant responses with statuses -->
                <#if myResponds?? && myResponds?has_content>
                    <hr class="my-4">
                    <h4 class="mb-3"><@spring.message "chat.my.responds"/></h4>
                    <#list myResponds as ra>
                        <div class="card shadow-sm mb-2">
                            <div class="card-body py-2">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <span class="fw-bold small">${ra.vacancy.name!''}</span>
                                        <br>
                                        <span class="text-muted small">
                                            <@spring.message "chat.respond.resume"/>:
                                            ${ra.resume.name!''}
                                        </span>
                                        <br>
                                        <#if ra.status == "APPROVED">
                                            <span class="badge bg-success mt-1">
                                                <@spring.message "respond.status.approved"/>
                                            </span>
                                        <#elseif ra.status == "REJECTED">
                                            <span class="badge bg-danger mt-1">
                                                <@spring.message "respond.status.rejected"/>
                                            </span>
                                        <#else>
                                            <span class="badge bg-warning text-dark mt-1">
                                                <@spring.message "respond.status.pending"/>
                                            </span>
                                        </#if>
                                    </div>
                                    <#if ra.status == "APPROVED">
                                        <a href="/chat/${ra.id}"
                                           class="btn btn-sm btn-success"
                                           title="<@spring.message "chat.open"/>">
                                            <i class="bi bi-chat-dots-fill me-1"></i>
                                            <@spring.message "chat.open"/>
                                        </a>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>

            </#if>

        </div>
    </div>
</@main.layout>
