<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-body">
                    <h3 class="text-center">Регистрация</h3>
                    <form action="/auth/register" method="post">
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label">Имя</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div>
                            <label class="form-label">Фамилия</label>
                            <input type="text" name="surname" class="form-control" required>
                        </div>
                        <div>
                            <label class="form-label">Возраст</label>
                            <input type="text" name="age" class="form-control" required>
                        </div>
                        <div>
                            <label class="form-label">Номер телефона</label>
                            <input type="text" name="phone_number" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Пароль</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Тип учетной записи</label>
                            <select name="accountType" class="form-select" required>
                                <option value="APPLICANT">Соискатель</option>
                                <option value="EMPLOYER">Работодатель</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Зарегистрироваться</button>
                        <a href="auth/login" class="btn btn-link">У меня уже есть аккаунт</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>