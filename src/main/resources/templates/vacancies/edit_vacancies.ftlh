<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h3 class="mb-0"><i class="bi bi-pencil-square me-2"></i><@spring.message "vacancy.edit.title"/></h3>
            </div>
            <div class="card-body p-4">
                <form action="/vacancies/edit/${vacancyDto.id!''}" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}">
                    </#if>

                    <#if bindingRes?? && bindingRes.hasErrors()>
                        <div class="alert alert-danger">
                            <strong><@spring.message "common.errors"/>:</strong>
                            <ul class="mb-0 mt-2">
                                <#list bindingRes.allErrors as err>
                                    <li>${err.defaultMessage!''}</li>
                                </#list>
                            </ul>
                        </div>
                    </#if>

                    <div class="mb-3">
                        <label class="form-label fw-bold">
                            <@spring.message "vacancy.name"/> <span class="text-danger">*</span>
                        </label>
                        <input type="text" name="name"
                               class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('name')>is-invalid</#if>"
                               value="${vacancyDto.name!''}" required>
                        <#if bindingRes?? && bindingRes.hasFieldErrors('name')>
                            <#list bindingRes.getFieldErrors('name') as err>
                                <div class="invalid-feedback">${err.defaultMessage}</div>
                            </#list>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">
                            <@spring.message "vacancy.description"/> <span class="text-danger">*</span>
                        </label>
                        <textarea name="description" rows="5"
                                  class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('description')>is-invalid</#if>"
                                  required>${vacancyDto.description!''}</textarea>
                        <#if bindingRes?? && bindingRes.hasFieldErrors('description')>
                            <#list bindingRes.getFieldErrors('description') as err>
                                <div class="invalid-feedback">${err.defaultMessage}</div>
                            </#list>
                        </#if>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold">
                                <@spring.message "vacancy.category"/> <span class="text-danger">*</span>
                            </label>
                            <select name="categoryId" class="form-select" required>
                                <option value=""><@spring.message "vacancy.category.select"/></option>
                                <#if categories??>
                                    <#list categories as category>
                                        <option value="${category.id}"
                                                <#if vacancyDto.categoryId?? && vacancyDto.categoryId == category.id>selected</#if>>
                                            ${category.name}
                                        </option>
                                    </#list>
                                </#if>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold">
                                <@spring.message "vacancy.salary"/> <span class="text-danger">*</span>
                            </label>
                            <div class="input-group">
                                <input type="number" name="salary" step="0.01" min="0"
                                       class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>is-invalid</#if>"
                                       value="${vacancyDto.salary!''}" required>
                                <span class="input-group-text">⃀</span>
                                <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>
                                    <#list bindingRes.getFieldErrors('salary') as err>
                                        <div class="invalid-feedback">${err.defaultMessage}</div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold"><@spring.message "vacancy.exp.from"/></label>
                            <input type="number" name="expFrom" min="0"
                                   class="form-control"
                                   value="${vacancyDto.expFrom!0}" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label fw-bold"><@spring.message "vacancy.exp.to"/></label>
                            <input type="number" name="expTo" min="0"
                                   class="form-control"
                                   value="${vacancyDto.expTo!5}" required>
                        </div>
                    </div>

                    <div class="mb-4">
                        <div class="form-check">
                            <input type="checkbox" name="isActive" value="true"
                                   class="form-check-input"
                                   <#if vacancyDto.isActive?? && vacancyDto.isActive>checked</#if>
                                   id="isActiveCheck">
                            <label class="form-check-label" for="isActiveCheck">
                                <@spring.message "vacancy.publish"/>
                            </label>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-2 border-top pt-3">
                        <a href="/vacancies" class="btn btn-light border">
                            <i class="bi bi-x me-1"></i><@spring.message "vacancy.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary px-5">
                            <i class="bi bi-check-lg me-1"></i><@spring.message "vacancy.save.edit"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>