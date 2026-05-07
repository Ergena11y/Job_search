<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">Вакансии</h2>
            <#if user?? && user.accountType == "EMPLOYER">
                <a href="/vacancies/create" class="btn btn-success">
                    <i class="bi bi-plus-lg me-1"></i>Создать вакансию
                </a>
            </#if>
        </div>


        <form method="get" class="card bg-light border-0 p-3 mb-4">
            <div class="row align-items-end g-2">
                <div class="col-md-4">
                    <label class="form-label small fw-bold mb-1">Сортировка</label>
                    <select name="sortBy" class="form-select">
                        <option value="date" <#if sortBy == "date">selected</#if>>По дате публикации</option>
                        <option value="responses" <#if sortBy == "responses">selected</#if>>По числу откликов</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100">Применить</button>
                </div>
            </div>
        </form>

        <#if vacancies?has_content>

            <#list vacancies as vacancy>
                <div class="card shadow-sm mb-3 w-100">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start">
                            <div class="flex-grow-1">
                                <h5 class="card-title mb-1">
                                    <a href="/vacancies/${vacancy.id!''}" class="text-decoration-none text-dark fw-bold">
                                        ${vacancy.name!'-'}
                                    </a>
                                </h5>
                                <p class="text-muted small mb-2">
                                    ${(vacancy.description!'')?truncate(120)}
                                </p>
                                <p class="mb-0 small">
                                    <span class="badge bg-light text-dark border me-2">
                                        <i class="bi bi-briefcase me-1"></i>Опыт: ${vacancy.expFrom!0} – ${vacancy.expTo!0} лет
                                    </span>
                                    <span class="text-muted">
                                        <i class="bi bi-calendar3 me-1"></i>${vacancy.createdDate!''}
                                    </span>
                                </p>
                            </div>
                            <div class="text-end ms-3 text-nowrap">
                                <span class="text-success fw-bold fs-5">${vacancy.salary?string["0.##"]!''} ⃀</span>
                            </div>
                        </div>

                        <div class="d-flex gap-2 mt-3">
                            <a href="/vacancies/${vacancy.id!''}" class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-eye me-1"></i>Подробнее
                            </a>
                            <#if user?? && user.accountType == "EMPLOYER">
                                <a href="/vacancies/edit/${vacancy.id!''}" class="btn btn-sm btn-outline-secondary">
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
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${currentPage - 1}&sortBy=${sortBy}">Предыдущая</a></li>
                    </#if>

                    <#list 0..totalPages - 1 as p>
                        <li class="page-item <#if p == currentPage>active</#if>">
                            <a class="page-link" href="/vacancies?page=${p}&sortBy=${sortBy}">${p + 1}</a>
                        </li>
                    </#list>

                    <#if currentPage == totalPages - 1>
                        <li class="page-item disabled"><a class="page-link" href="#">Следующая</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${currentPage + 1}&sortBy=${sortBy}">Следующая</a></li>
                    </#if>
                </ul>
            </nav>
        <#else>
            <div class="alert alert-info">Вакансии не найдены</div>
        </#if>
    </div>
</@main.layout>
