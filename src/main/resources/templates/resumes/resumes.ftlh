<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="display-6">База резюме</h2>
            <a href="/resumes/create" class="btn btn-primary">Разместить резюме</a>
        </div>

        <#list resumes as resume>
            <div class="col-12 mb-3">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title text-primary">${resume.name}</h5>
                            <span class="badge bg-success">${resume.salary} сом</span>
                        </div>
                        <p class="card-text">Категория: ${resume.categoryId!}</p>
                        <a href="/resumes/${resume.id!}" class="btn btn-sm btn-outline-primary">Посмотреть полностью</a>
                    </div>
                </div>
            </div>
        <#else>
            <p>Резюме пока нет.</p>
        </#list>
    </div>
</@main.layout>
