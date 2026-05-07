<#import "/spring.ftl" as spring>
<#import "./layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h1><@spring.message "index.title"/></h1>

        <form method="get" class="mb-3">
            <div class="row">
                <div class="col-md-4">
                    <label><@spring.message "vacancy.sort"/>:</label>
                    <select name="sortBy" class="form-control">
                        <option value="date" <#if sortBy == "date">selected</#if>><@spring.message "vacancy.sort.date"/></option>
                        <option value="responses" <#if sortBy == "responses">selected</#if>><@spring.message "vacancy.sort.responses"/></option>
                    </select>
                </div>
                <div class="col-md-2">
                    <br>
                    <button type="submit" class="btn btn-primary"><@spring.message "vacancy.sort.apply"/></button>
                </div>
            </div>
        </form>

        <#if vacancies?has_content>
            <div class="row">
                <#list vacancies as vacancy>
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${vacancy.name}</h5>
                                <p class="card-text">${vacancy.description?truncate(100)}</p>
                                <p class="card-text"><strong><@spring.message "vacancy.salary"/>:</strong> ${vacancy.salary?string["0.00"]} ⃀</p>
                                <p class="card-text"><strong><@spring.message "vacancy.experience"/>:</strong> ${vacancy.expFrom} - ${vacancy.expTo} <@spring.message "vacancy.experience.years"/></p>
                                <p class="card-text"><strong><@spring.message "common.from"/>:</strong> ${vacancy.createdDate}</p>
                                <a href="/vacancies/${vacancy.id!''}" class="btn btn-info btn-sm"><@spring.message "vacancy.detail"/></a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>

            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <#if currentPage == 0>
                        <li class="page-item disabled"><a class="page-link" href="#"><@spring.message "page.prev"/></a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/?page=${currentPage - 1}&sortBy=${sortBy}"><@spring.message "page.prev"/></a></li>
                    </#if>

                    <#list 0..totalPages - 1 as p>
                        <#if p == currentPage>
                            <li class="page-item active"><span class="page-link">${p + 1}</span></li>
                        <#else>
                            <li class="page-item"><a class="page-link" href="/?page=${p}&sortBy=${sortBy}">${p + 1}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage == totalPages - 1>
                        <li class="page-item disabled"><a class="page-link" href="#"><@spring.message "page.next"/></a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/?page=${currentPage + 1}&sortBy=${sortBy}"><@spring.message "page.next"/></a></li>
                    </#if>
                </ul>
            </nav>
        <#else>
            <div class="alert alert-info"><@spring.message "vacancy.not.found"/></div>
        </#if>
    </div>
</@main.layout>