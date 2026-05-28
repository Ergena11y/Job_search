<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0"><@spring.message "resume.title"/></h2>
            <#if user?? && user.accountType == "APPLICANT">
                <a href="/resumes/create" class="btn btn-success">
                    <i class="bi bi-plus-lg me-1"></i><@spring.message "resume.create"/>
                </a>
            </#if>
        </div>


        <#if user?? && user.accountType == "EMPLOYER">
            <form method="get" action="/resumes" class="card bg-light border-0 p-3 mb-4">
                <div class="row align-items-end g-2">
                    <div class="col-md-4">
                        <label class="form-label small fw-bold mb-1"><@spring.message "resume.category"/></label>
                        <select name="categoryId" class="form-select">
                            <option value=""><@spring.message "resume.category.select"/></option>
                            <#if categories??>
                                <#list categories as cat>
                                    <option value="${cat.id}"
                                            <#if selectedCategory?? && selectedCategory == cat.id>selected</#if>>
                                        ${cat.name}
                                    </option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary w-100"><@spring.message "vacancy.sort.apply"/></button>
                    </div>
                    <div class="col-md-2">
                        <a href="/resumes" class="btn btn-outline-secondary w-100"><@spring.message "vacancy.filter.reset"/></a>
                    </div>
                </div>
            </form>
        </#if>

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
                                    <i class="bi bi-person me-1"></i>${resume.applicantName!'<@spring.message "common.anonymous"/>'}
                                    <span class="mx-2">·</span>
                                    <i class="bi bi-clock me-1"></i><@spring.message "resume.updated"/>: ${resume.formattedUpdateTime!''}
                                    <#if resume.categoryName??>
                                        <span class="mx-2">·</span>
                                        <span class="badge bg-secondary">${resume.categoryName}</span>
                                    </#if>
                                </p>
                            </div>
                            <div class="text-end ms-3">
                                <span class="badge bg-success fs-6 px-3 py-2">${resume.salary?string["0.##"]!''} ⃀</span>
                            </div>
                        </div>

                        <div class="d-flex gap-2 mt-2">
                            <a href="/resumes/${resume.id!''}" class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-eye me-1"></i><@spring.message "resume.detail"/>
                            </a>
                            <#if user?? && user.accountType == "APPLICANT">
                                <a href="/resumes/edit/${resume.id!''}" class="btn btn-sm btn-outline-secondary">
                                    <i class="bi bi-pencil me-1"></i><@spring.message "resume.edit"/>
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>

            <#if totalPages?? && totalPages gt 1>
                <nav aria-label="Page navigation" class="mt-4">
                    <ul class="pagination justify-content-center">
                        <#if currentPage == 0>
                            <li class="page-item disabled"><a class="page-link" href="#"><@spring.message "page.prev"/></a></li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link" href="/resumes?page=${currentPage - 1}<#if selectedCategory??>&categoryId=${selectedCategory}</#if>">
                                    <@spring.message "page.prev"/>
                                </a>
                            </li>
                        </#if>

                        <#list 0..totalPages - 1 as p>
                            <li class="page-item <#if p == currentPage>active</#if>">
                                <a class="page-link" href="/resumes?page=${p}<#if selectedCategory??>&categoryId=${selectedCategory}</#if>">${p + 1}</a>
                            </li>
                        </#list>

                        <#if currentPage == totalPages - 1>
                            <li class="page-item disabled"><a class="page-link" href="#"><@spring.message "page.next"/></a></li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link" href="/resumes?page=${currentPage + 1}<#if selectedCategory??>&categoryId=${selectedCategory}</#if>">
                                    <@spring.message "page.next"/>
                                </a>
                            </li>
                        </#if>
                    </ul>
                </nav>
            </#if>
        <#else>
            <div class="alert alert-info"><@spring.message "resume.not.found"/></div>
        </#if>
    </div>
</@main.layout>
