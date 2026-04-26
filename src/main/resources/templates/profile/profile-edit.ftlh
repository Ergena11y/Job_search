<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mt-4">
                <div class="card-header bg-primary text-white">
                    <h4>Редактирование профиля</h4>
                </div>
                <div class="card-body">
                    <form action="/profile/update" method="post" enctype="multipart/form-data">
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Имя</label>
                                <input type="text" name="name" class="form-control" value="${user.name!''}" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Фамилия</label>
                                <input type="text" name="surname" class="form-control" value="${user.surname!''}" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Телефон</label>
                            <input type="text" name="phoneNumber" class="form-control" value="${user.phoneNumber!''}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Сменить фото профиля</label>
                            <input type="file" name="avatar" class="form-control" accept="image/*">
                        </div>

                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-success">Сохранить изменения</button>
                            <a href="/profile" class="btn btn-secondary">Отмена</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>