<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Доступные вакансии</h2>
        <button class="btn btn-primary">Добавить вакансию</button>
    </div>

    <div class="row">

        <div class="col-md-12 mb-3">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Java Developer</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Google • 300 000 сом</h6>
                    <p class="card-text">Требуется знание Spring Boot и понимание MVC...</p>
                    <a href="#" class="btn btn-sm btn-outline-success">Откликнуться</a>
                </div>
            </div>
        </div>
    </div>
</@main.layout>