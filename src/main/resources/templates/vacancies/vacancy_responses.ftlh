<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h3>Отклики на: ${vacancy.name!''}</h3>
        <a href="/vacancies/${vacancy.id}" class="btn btn-outline-secondary btn-sm mb-3">
            <i class="bi bi-arrow-left"></i> Назад к вакансии
        </a>

        <#if responses?? && responses?has_content>
            <#list responses as ra>
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5>${ra.resume.applicant.name!''} ${ra.resume.applicant.surname!''}</h5>
                                <p class="text-muted small mb-1">
                                    <i class="bi bi-envelope me-1"></i>${ra.resume.applicant.email!''}
                                </p>
                                <p class="text-muted small mb-1">Резюме: <a href="/resumes/${ra.resume.id}">${ra.resume.name!''}</a></p>
                                <span class="badge <#if ra.status == 'APPROVED'>bg-success<#elseif ra.status == 'REJECTED'>bg-danger<#else>bg-secondary</#if>">
                                ${ra.status!'PENDING'}
                            </span>
                            </div>
                            <div class="d-flex flex-column gap-2">
                                <#if ra.status == 'PENDING'>
                                    <form action="/responses/approve/${ra.id}" method="post">
                                        <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>
                                        <button class="btn btn-success btn-sm">
                                            <i class="bi bi-check-lg"></i> Одобрить
                                        </button>
                                    </form>
                                    <form action="/responses/reject/${ra.id}" method="post">
                                        <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>
                                        <button class="btn btn-danger btn-sm">
                                            <i class="bi bi-x-lg"></i> Отклонить
                                        </button>
                                    </form>
                                <#elseif ra.status == 'APPROVED'>
                                    <a href="/chat/${ra.id}" class="btn btn-primary btn-sm">
                                        <i class="bi bi-chat-dots-fill"></i> Чат
                                    </a>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="alert alert-info">Откликов пока нет</div>
        </#if>
    </div>
</@main.layout>