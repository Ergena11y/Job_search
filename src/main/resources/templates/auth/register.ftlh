<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5 shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0 text-center">Регистрация</h3>
                </div>
                <div class="card-body p-4">


                    <#if error??>
                        <div class="alert alert-danger">${error}</div>
                    </#if>

                    <form action="/auth/register" method="post" novalidate>
                        <#if _csrf??>
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!''}">
                        </#if>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Имя <span class="text-danger">*</span></label>
                            <input type="text" name="name" class="form-control <#if bindingResult?? && bindingRes.hasFieldErrors('name')>is-invalid</#if>"
                                   value="${userDto.name!''}" required>
                            <#if bindingResult?? && bindingRes.hasFieldErrors('name')>
                                <#list bindingRes.getFieldErrors('name') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Фамилия <span class="text-danger">*</span></label>
                            <input type="text" name="surname" class="form-control <#if bindingResult?? && bindingRes.hasFieldErrors('surname')>is-invalid</#if>"
                                   value="${userDto.surname!''}" required>
                            <#if bindingResult?? && bindingRes.hasFieldErrors('surname')>
                                <#list bindingRes.getFieldErrors('surname') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Возраст <span class="text-danger">*</span></label>
                            <input type="number" name="age" class="form-control <#if bindingResult?? && bindingRes.hasFieldErrors('age')>is-invalid</#if>"
                                   value="${userDto.age!''}" required>
                            <#if bindingResult?? && bindingRes.hasFieldErrors('age')>
                                <#list bindingRes.getFieldErrors('age') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Номер телефона</label>
                            <input type="text" name="phoneNumber" class="form-control"
                                   value="${userDto.phoneNumber!''}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Email <span class="text-danger">*</span></label>
                            <input type="email" name="email" class="form-control <#if bindingResult?? && bindingRes.hasFieldErrors('email')>is-invalid</#if>"
                                   value="${userDto.email!''}" required>
                            <#if bindingResult?? && bindingRes.hasFieldErrors('email')>
                                <#list bindingRes.getFieldErrors('email') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Пароль <span class="text-danger">*</span></label>
                            <input type="password" name="password" class="form-control <#if bindingResult?? && bindingRes.hasFieldErrors('password')>is-invalid</#if>"
                                   required>
                            <div class="form-text text-muted">Минимум 4 символа, строчные и заглавные буквы, цифра</div>
                            <#if bindingResult?? && bindingRes.hasFieldErrors('password')>
                                <#list bindingRes.getFieldErrors('password') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Тип аккаунта <span class="text-danger">*</span></label>
                            <select name="accountType" class="form-select" required>
                                <option value="APPLICANT" <#if (userDto.accountType!'') == 'APPLICANT'>selected</#if>>Соискатель</option>
                                <option value="EMPLOYER" <#if (userDto.accountType!'') == 'EMPLOYER'>selected</#if>>Работодатель</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 mb-2">Зарегистрироваться</button>
                        <div class="text-center">
                            <a href="/auth/login" class="btn btn-link">Уже есть аккаунт? Войти</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
