<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="row mb-3">
            <div class="col-md-8">
                <h1>Резюме</h1>
            </div>
            <div class="col-md-4 text-end">
                <a href="/resumes/create" class="btn btn-success">Новое резюме</a>
            </div>
        </div>





        <#if resumes?has_content>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Желаемая зарплата</th>
                    <th>Дата создания</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <#list resumes as resume>
                    <tr>
                        <td>${resume.name}</td>
                        <td>${resume.salary?string["0.00"] } ⃀ </td>
                        <td>${resume.createdDate}</td>
                        <td>
                            <a href="/resumes/edit/${resume.id!''}" class="btn btn-warning btn-sm">Редактировать</a>
                            <a href="/resumes/delete/${resume.id!''}" class="btn btn-danger btn-sm">Удалить</a>
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
                        <li class="page-item"><a class="page-link" href="/resumes?page=${currentPage - 1}">Предыдущая</a></li>
                    </#if>

                    <#list 0..totalPages - 1 as p>
                        <#if p == currentPage>
                            <li class="page-item active"><span class="page-link">${p + 1}</span></li>
                        <#else>
                            <li class="page-item"><a class="page-link" href="/resumes?page=${p}">${p + 1}</a></li>
                        </#if>
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
