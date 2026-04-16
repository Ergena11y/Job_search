<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Доступные вакансии</h2>
        <a href="/vacancies/create" class="btn btn-primary">Добавить вакансию</a>
    </div>

    <div class="row">
        <#list vacancies as vacancy>
        <div class="col-md-12 mb-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">${vacancy.name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        ${vacancy.salary} сом
                    </h6>
                    <p class="card-text">${vacancy.description}</p>
                    <a href="#" class="btn btn-sm btn-outline-success">Откликнуться</a>
                </div>
            </div>
        </div>
        <#else>
            <p>Вакансий пока нет.</p>
        </#list>
    </div>
</@main.layout>
