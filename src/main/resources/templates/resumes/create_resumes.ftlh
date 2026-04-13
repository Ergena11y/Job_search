<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2>Создать резюме</h2>
        <form action="/resumes/create" method="post">
            <div class="mb-3">
                <label class="form-label">Название должности</label>
                <input type="text" name="name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Ожидаемая зарплата</label>
                <input type="number" name="salary" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">ID категории</label>
                <input type="number" name="categoryId" class="form-control">
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="isActive" value="true" class="form-check-input" checked>
                <label class="form-check-label">Опубликовать</label>
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
            <a href="/resumes" class="btn btn-link">Отмена</a>
        </form>
    </div>
</@main.layout>