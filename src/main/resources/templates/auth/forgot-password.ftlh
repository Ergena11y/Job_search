<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4"><@spring.message "auth.forgot.title"/></h3>

                    <#if message??>
                        <div class="alert alert-success">${message}</div>
                    </#if>
                    <#if error??>
                        <div class="alert alert-danger">${error}</div>
                    </#if>

                    <form action="/auth/forgot-password" method="post">
                        <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required
                                   placeholder="<@spring.message "auth.forgot.add.email"/>">
                        </div>
                        <button type="submit" class="btn btn-primary w-100"><@spring.message "auth.forgot.submit"/></button>
                        <div class="text-center mt-3">
                            <a href="/auth/login"><@spring.message "auth.forgot.back"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>