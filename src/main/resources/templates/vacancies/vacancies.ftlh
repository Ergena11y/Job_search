<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="row mb-3">
            <div class="col-md-8">
                <h1>Вакансии</h1>
            </div>
            <div class="col-md-4 text-end">
                <a href="/vacancies/create" class="btn btn-success">Новая вакансия</a>
            </div>
        </div>




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
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Зарплата</th>
                    <th>Опыт</th>
                    <th>Дата создания</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <#list vacancies as vacancy>
                    <tr>
                        <td>${vacancy.name}</td>
                        <td>${vacancy.salary?string["0.00"]} ⃀</td>
                        <td>${vacancy.expFrom} - ${vacancy.expTo} лет</td>
                        <td>${vacancy.createdDate}</td>
                        <td>
                            <a href="/vacancies/edit/${vacancy.id!''}" class="btn btn-warning btn-sm">Редактировать</a>
                            <a href="/vacancies/delete/${vacancy.id!''}" class="btn btn-danger btn-sm">Удалить</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>




            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <#if currentPage == 0>
                        <li class="page-item disabled"><a class="page-link" href="#">Предыдущая</a></li>
                    <#else>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${currentPage - 1}&sortBy=${sortBy}">Предыдущая</a></li>
                    </#if>

                    <#list 0..totalPages - 1 as p>
                        <#if p == currentPage>
                            <li class="page-item active"><span class="page-link">${p + 1}</span></li>
                        <#else>
                            <li class="page-item"><a class="page-link" href="/vacancies?page=${p}&sortBy=${sortBy}">${p + 1}</a></li>
                        </#if>
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
