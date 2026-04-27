<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4">Новый пароль</h3>

                    <#if error??>
                        <div class="alert alert-danger">${error}</div>
                    </#if>

                    <form action="/auth/reset-password" method="post">
                        <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>
                        <input type="hidden" name="token" value="${token!''}">
                        <div class="mb-3">
                            <label class="form-label">Новый пароль</label>
                            <input type="password" name="password" class="form-control" required
                                   minlength="4" placeholder="Минимум 4 символа">
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Сохранить пароль</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>