<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mt-4">
                <div class="card-header"><h4>Редактирование профиля</h4></div>
                <div class="card-body">
                    <form action="/profile/update" method="post">
                        <input type="hidden" name="id" value="${edit.id}">
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Имя</label>
                                <input type="text" name="name" class="form-control" value="${edit.name}">
                            </div>
                            <div class="col">
                                <label class="form-label">Фамилия</label>
                                <input type="text" name="surname" class="form-control" value="${edit.surname!''}">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Телефон</label>
                            <input type="text" name="phoneNumber" class="form-control" value="${edit.phoneNumber!''}">
                        </div>
                        <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                        <a href="/profile?userId=${edit.id}" class="btn btn-link">Отмена</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>