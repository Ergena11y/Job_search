<#import "./layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h1>Последние вакансии</h1>


        <form method="get" class="mb-3">
            <div class="row">
                <div class="col-md-4">
                    <label>Сортировка:</label>
                    <select name="sortBy" class="form-control">
                        <option value="date" <#if sortBy == "date">selected</#if>>По дате</option>
                        <option value="responses" <#if sortBy == "responses">selected</#if>>По откликам</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <br>
                    <button type="submit" class="btn btn-primary">Применить</button>
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
                                <p class="card-text"><strong>Зарплата:</strong> ${vacancy.salary?string["0.00"]} ⃀</p>
                                <p class="card-text"><strong>Опыт:</strong> ${vacancy.expFrom} - ${vacancy.expTo} лет</p>
                                <p class="card-text"><strong>Дата:</strong> ${vacancy.createdDate}</p>
                                <a href="/vacancies/${vacancy.id!''}" class="btn btn-info btn-sm">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>





            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <#if currentPage == 0>
                        <li class="page-item disabled"><a class="page-link" href="#">Предыдущая</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/?page=${currentPage - 1}&sortBy=${sortBy}">Предыдущая</a></li>
                    </#if>

                    <#list 0..totalPages - 1 as p>
                        <#if p == currentPage>
                            <li class="page-item active"><span class="page-link">${p + 1}</span></li>
                        <#else>
                            <li class="page-item"><a class="page-link" href="/?page=${p}&sortBy=${sortBy}">${p + 1}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage == totalPages - 1>
                        <li class="page-item disabled"><a class="page-link" href="#">Следующая</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/?page=${currentPage + 1}&sortBy=${sortBy}">Следующая</a></li>
                    </#if>
                </ul>
            </nav>
        <#else>
            <div class="alert alert-info">Вакансии не найдены</div>
        </#if>
    </div>
</@main.layout>