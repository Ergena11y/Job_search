<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">Резюме соискателей</h2>
            <#if user?? && user.accountType == "APPLICANT">
                <a href="/resumes/create" class="btn btn-success">
                    <i class="bi bi-plus-lg me-1"></i>Создать резюме
                </a>
            </#if>
        </div>


        <#if error??>
            <div class="alert alert-danger">${error}</div>
        </#if>

        <#if resumes?has_content>

            <#list resumes as resume>
                <div class="card shadow-sm mb-3 w-100">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start">
                            <div class="flex-grow-1">
                                <h5 class="card-title mb-1">
                                    <a href="/resumes/${resume.id!''}" class="text-decoration-none text-dark fw-bold">
                                        ${resume.name!'-'}
                                    </a>
                                </h5>
                                <p class="text-muted small mb-2">
                                    <i class="bi bi-person me-1"></i>${resume.applicantName!'Аноним'}
                                    <span class="mx-2">·</span>
                                    <i class="bi bi-clock me-1"></i>Обновлено: ${resume.updateTime!''}
                                </p>
                            </div>
                            <div class="text-end ms-3">
                                <span class="badge bg-success fs-6 px-3 py-2">${resume.salary?string["0.##"]!''} ⃀</span>
                            </div>
                        </div>

                        <div class="d-flex gap-2 mt-2">
                            <a href="/resumes/${resume.id!''}" class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-eye me-1"></i>Подробнее
                            </a>
                            <#if user?? && user.accountType == "APPLICANT">
                                <a href="/resumes/edit/${resume.id!''}" class="btn btn-sm btn-outline-secondary">
                                    <i class="bi bi-pencil me-1"></i>Редактировать
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>

            <nav aria-label="Page navigation" class="mt-4">
                <ul class="pagination justify-content-center">
                    <#if currentPage == 0>
                        <li class="page-item disabled"><a class="page-link" href="#">Предыдущая</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/resumes?page=${currentPage - 1}">Предыдущая</a></li>
                    </#if>

                    <#list 0..totalPages - 1 as p>
                        <li class="page-item <#if p == currentPage>active</#if>">
                            <a class="page-link" href="/resumes?page=${p}">${p + 1}</a>
                        </li>
                    </#list>

                    <#if currentPage == totalPages - 1>
                        <li class="page-item disabled"><a class="page-link" href="#">Следующая</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/resumes?page=${currentPage + 1}">Следующая</a></li>
                    </#if>
                </ul>
            </nav>
        <#else>
            <div class="alert alert-info">Резюме не найдены</div>
        </#if>
    </div>
</@main.layout>

