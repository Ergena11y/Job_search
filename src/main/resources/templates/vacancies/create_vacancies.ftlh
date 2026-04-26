<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2>Создать вакансию</h2>
        <form action="/vacancies/create" method="post">
            <#if _csrf??>
                <input
                        type="hidden"
                        name="${(_csrf.parameterName)!'csrf-param-name'}"
                        value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="mb-3">
                <label class="form-label">Название вакансии</label>
                <input type="text" name="name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Описание</label>
                <textarea name="description" class="form-control" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Зарплата</label>
                <input type="number" name="salary" class="form-control" required>
            </div>
            <div class="row">
                <div class="col mb-3">
                    <label class="form-label">Опыт от (лет)</label>
                    <input type="number" name="expFrom" class="form-control" value="0" required>
                </div>
                <div class="col mb-3">
                    <label class="form-label">Опыт до (лет)</label>
                    <input type="number" name="expTo" class="form-control" value="5" required>
                </div>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="isActive" value="true" class="form-check-input" checked>
                <label class="form-check-label">Опубликовать</label>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <a href="/vacancies" class="btn btn-link">Отмена</a>
        </form>
    </div>
</@main.layout>