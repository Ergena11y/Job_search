<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5 shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0 text-center"><@spring.message "auth.register.title"/></h3>
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
                            <label class="form-label fw-semibold">
                                <span id="nameLabel"><@spring.message "auth.register.name"/></span>
                                <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="name"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('name')>is-invalid</#if>"
                                   value="${(registerDto.name)!''}" required>
                            <#if bindingRes?? && bindingRes.hasFieldErrors('name')>
                                <#list bindingRes.getFieldErrors('name') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3" id="surnameBlock">
                            <label class="form-label fw-semibold">
                                <@spring.message "auth.register.surname"/> <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="surname"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('surname')>is-invalid</#if>"
                                   value="${(registerDto.surname)!''}">
                            <#if bindingRes?? && bindingRes.hasFieldErrors('surname')>
                                <#list bindingRes.getFieldErrors('surname') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3" id="ageBlock">
                            <label class="form-label fw-semibold">
                                <@spring.message "auth.register.age"/> <span class="text-danger">*</span>
                            </label>
                            <input type="number" name="age"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('age')>is-invalid</#if>"
                                   value="${(registerDto.age)!''}">
                            <#if bindingRes?? && bindingRes.hasFieldErrors('age')>
                                <#list bindingRes.getFieldErrors('age') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold"><@spring.message "auth.register.phone"/></label>
                            <input type="text" name="phoneNumber" class="form-control"
                                   value="${(registerDto.phoneNumber)!''}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">
                                <@spring.message "auth.register.email"/> <span class="text-danger">*</span>
                            </label>
                            <input type="email" name="email"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('email')>is-invalid</#if>"
                                   value="${(registerDto.email)!''}" required>
                            <#if bindingRes?? && bindingRes.hasFieldErrors('email')>
                                <#list bindingRes.getFieldErrors('email') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">
                                <@spring.message "auth.register.password"/> <span class="text-danger">*</span>
                            </label>
                            <input type="password" name="password"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('password')>is-invalid</#if>"
                                   required>
                            <div class="form-text text-muted"><@spring.message "auth.register.password.hint"/></div>
                            <#if bindingRes?? && bindingRes.hasFieldErrors('password')>
                                <#list bindingRes.getFieldErrors('password') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">
                                <@spring.message "auth.register.account.type"/> <span class="text-danger">*</span>
                            </label>
                            <select name="accountType" id="accountTypeSelect" class="form-select" required
                                    data-label-applicant="<@spring.message 'auth.register.name'/>"
                                    data-label-employer="<@spring.message 'auth.register.name.company'/>">
                                <option value="APPLICANT" <#if ((registerDto.accountType)!'APPLICANT') == 'APPLICANT'>selected</#if>>
                                    <@spring.message "auth.register.account.applicant"/>
                                </option>
                                <option value="EMPLOYER" <#if ((registerDto.accountType)!'') == 'EMPLOYER'>selected</#if>>
                                    <@spring.message "auth.register.account.employer"/>
                                </option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary w-100 mb-2">
                            <@spring.message "auth.register.submit"/>
                        </button>
                        <div class="text-center">
                            <a href="/auth/login" class="btn btn-link">
                                <@spring.message "auth.register.have.account"/>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="/static/js/register.js" defer></script>
</@main.layout>