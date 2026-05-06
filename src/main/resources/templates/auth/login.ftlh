<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white text-center">
                    <h3 class="mb-0">Вход в систему</h3>
                </div>
                <div class="card-body p-4">


                    <#if RequestParameters.error?? || errorMsg??>
                        <div class="alert alert-danger" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            Неверный email или пароль. Попробуйте ещё раз.
                        </div>
                    </#if>


                    <#if RequestParameters.logout??>
                        <div class="alert alert-success" role="alert">
                            <i class="bi bi-check-circle me-2"></i>
                            Вы успешно вышли из системы.
                        </div>
                    </#if>


                    <#if RequestParameters.registered??>
                        <div class="alert alert-info" role="alert">
                            <i class="bi bi-info-circle me-2"></i>
                            Вы успешно зарегистрировались! Пожалуйста, войдите.
                        </div>
                    </#if>

                    <form action="/auth/login" method="post" novalidate>
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!''}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Email</label>
                            <input type="email" name="username" class="form-control"
                                   placeholder="Введите email" required autofocus>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Пароль</label>
                            <input type="password" name="password" class="form-control"
                                   placeholder="Введите пароль" required>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 mb-3">Войти</button>

                        <div class="text-center">
                            <a href="/auth/forgot-password" class="text-muted small">Забыли пароль?</a>
                            <span class="text-muted small mx-2">|</span>
                            <a href="/auth/register" class="small">Зарегистрироваться</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>