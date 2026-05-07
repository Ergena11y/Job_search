<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h3 class="mb-0"><i class="bi bi-file-earmark-person me-2"></i><@spring.message "resume.create.title"/></h3>
            </div>
            <div class="card-body p-4">
                <form action="/resumes/create" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}">
                    </#if>

                    <#if bindingRes?? && bindingRes.hasErrors()>
                        <div class="alert alert-danger">
                            <strong><@spring.message "common.errors"/></strong>
                            <ul class="mb-0 mt-2">
                                <#list bindingRes.allErrors as err>
                                    <li>${err.defaultMessage!''}</li>
                                </#list>
                            </ul>
                        </div>
                    </#if>

                    <#-- 1. Основная информация -->
                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">
                            <i class="bi bi-person-badge me-1"></i><@spring.message "resume.section.basic"/>
                        </h5>

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                <@spring.message "resume.name"/> <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="name"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('name')>is-invalid</#if>"
                                   value="${resumeDto.name!''}"
                                   placeholder="<@spring.message "resume.name.placeholder"/>" required>
                            <#if bindingRes?? && bindingRes.hasFieldErrors('name')>
                                <#list bindingRes.getFieldErrors('name') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold"><@spring.message "resume.salary"/></label>
                                <div class="input-group">
                                    <input type="number" step="0.01" name="salary"
                                           class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>is-invalid</#if>"
                                           value="${resumeDto.salary!''}"
                                           placeholder="0.00" min="0">
                                    <span class="input-group-text">⃀</span>
                                    <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>
                                        <#list bindingRes.getFieldErrors('salary') as err>
                                            <div class="invalid-feedback">${err.defaultMessage}</div>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">
                                    <@spring.message "resume.category"/> <span class="text-danger">*</span>
                                </label>
                                <select name="categoryId"
                                        class="form-select <#if bindingRes?? && bindingRes.hasFieldErrors('categoryId')>is-invalid</#if>"
                                        required>
                                    <option value=""><@spring.message "resume.category.select"/></option>
                                    <#if categories??>
                                        <#list categories as category>
                                            <option value="${category.id}"
                                                    <#if resumeDto.categoryId?? && resumeDto.categoryId == category.id>selected</#if>>
                                                ${category.name}
                                            </option>
                                        </#list>
                                    </#if>
                                </select>
                                <#if bindingRes?? && bindingRes.hasFieldErrors('categoryId')>
                                    <#list bindingRes.getFieldErrors('categoryId') as err>
                                        <div class="invalid-feedback">${err.defaultMessage}</div>
                                    </#list>
                                </#if>
                            </div>
                        </div>

                        <div class="mb-3">
                            <div class="form-check">
                                <input type="checkbox" name="isActive" value="true"
                                       class="form-check-input" checked id="isActiveCheck">
                                <label class="form-check-label" for="isActiveCheck">
                                    <@spring.message "resume.publish"/>
                                </label>
                            </div>
                        </div>
                    </section>

                    <#-- 2. Опыт работы -->
                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">
                            <i class="bi bi-briefcase me-1"></i><@spring.message "resume.section.experience"/>
                        </h5>

                        <div class="row g-3 mb-3">
                            <div class="col-md-4">
                                <label class="form-label small text-muted"><@spring.message "resume.exp.years"/></label>
                                <input type="number" name="workYears" class="form-control"
                                       placeholder="<@spring.message "resume.exp.years.placeholder"/>" min="0">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted"><@spring.message "resume.exp.company"/></label>
                                <input type="text" name="companyName" class="form-control"
                                       placeholder="<@spring.message "resume.exp.company"/>">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted"><@spring.message "resume.exp.position"/></label>
                                <input type="text" name="position" class="form-control"
                                       placeholder="<@spring.message "resume.exp.position"/>">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small text-muted"><@spring.message "resume.exp.responsibilities"/></label>
                            <textarea name="responsibilities" class="form-control" rows="4"
                                      placeholder="<@spring.message "resume.exp.responsibilities.placeholder"/>"></textarea>
                        </div>
                    </section>

                    <#-- 3. Образование -->
                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">
                            <i class="bi bi-mortarboard me-1"></i><@spring.message "resume.section.education"/>
                        </h5>

                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label class="form-label small text-muted"><@spring.message "resume.edu.institution"/></label>
                                <input type="text" name="institution" class="form-control"
                                       placeholder="<@spring.message "resume.edu.institution"/>">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label small text-muted"><@spring.message "resume.edu.program"/></label>
                                <input type="text" name="program" class="form-control"
                                       placeholder="<@spring.message "resume.edu.program"/>">
                            </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col-md-4">
                                <label class="form-label small text-muted"><@spring.message "resume.edu.start"/></label>
                                <input type="date" name="eduStartDate" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted"><@spring.message "resume.edu.end"/></label>
                                <input type="date" name="eduEndDate" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted"><@spring.message "resume.edu.degree"/></label>
                                <select name="degree" class="form-select">
                                    <option value=""><@spring.message "resume.edu.degree.select"/></option>
                                    <option value="Бакалавр"><@spring.message "resume.edu.degree.bachelor"/></option>
                                    <option value="Магистр"><@spring.message "resume.edu.degree.master"/></option>
                                    <option value="Специалист"><@spring.message "resume.edu.degree.specialist"/></option>
                                    <option value="Доктор"><@spring.message "resume.edu.degree.doctor"/></option>
                                    <option value="Среднее специальное"><@spring.message "resume.edu.degree.secondary"/></option>
                                </select>
                            </div>
                        </div>
                    </section>

                    <#-- Кнопки -->
                    <div class="d-flex justify-content-end gap-2 border-top pt-3">
                        <a href="/profile" class="btn btn-light border">
                            <i class="bi bi-x me-1"></i><@spring.message "resume.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary px-5">
                            <i class="bi bi-check-lg me-1"></i><@spring.message "resume.save"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>