<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>

<#assign t_years><@spring.message "resume.exp.years"/></#assign>
<#assign t_years_ph><@spring.message "resume.exp.years.placeholder"/></#assign>
<#assign t_company><@spring.message "resume.exp.company"/></#assign>
<#assign t_position><@spring.message "resume.exp.position"/></#assign>
<#assign t_resp><@spring.message "resume.exp.responsibilities"/></#assign>
<#assign t_resp_ph><@spring.message "resume.exp.responsibilities.placeholder"/></#assign>
<#assign t_institution><@spring.message "resume.edu.institution"/></#assign>
<#assign t_program><@spring.message "resume.edu.program"/></#assign>
<#assign t_start><@spring.message "resume.edu.start"/></#assign>
<#assign t_end><@spring.message "resume.edu.end"/></#assign>
<#assign t_degree><@spring.message "resume.edu.degree"/></#assign>
<#assign t_degree_sel><@spring.message "resume.edu.degree.select"/></#assign>
<#assign t_bachelor><@spring.message "resume.edu.degree.bachelor"/></#assign>
<#assign t_master><@spring.message "resume.edu.degree.master"/></#assign>
<#assign t_specialist><@spring.message "resume.edu.degree.specialist"/></#assign>
<#assign t_doctor><@spring.message "resume.edu.degree.doctor"/></#assign>
<#assign t_secondary><@spring.message "resume.edu.degree.secondary"/></#assign>
<#assign t_name_ph><@spring.message "resume.name.placeholder"/></#assign>
<#assign today>${.now?string('yyyy-MM-dd')}</#assign>

<@main.layout>
    <div class="container mt-4" style="max-width: 700px;">
        <div class="card shadow-sm" style="border-radius: 16px; border: 2px solid #e0e0e0;">
            <div class="card-body p-4">
                <h3 class="text-center mb-4" style="color: var(--bs-primary);">
                    <@spring.message "resume.edit.title"/>
                </h3>

                <#if bindingRes?? && bindingRes.hasErrors()>
                    <div class="alert alert-danger">
                        <ul class="mb-0">
                            <#list bindingRes.allErrors as err>
                                <li>${err.defaultMessage!''}</li>
                            </#list>
                        </ul>
                    </div>
                </#if>

                <form action="/resumes/edit/${resume.id!''}" method="post" id="resumeForm">
                    <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>

                    <#-- Основные поля -->
                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.name"/>: <span class="text-danger">*</span></label>
                        <input type="text" name="name"
                               class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('name')>is-invalid</#if>"
                               placeholder="${t_name_ph}"
                               value="${resume.name!''}" required>
                        <#if bindingRes?? && bindingRes.hasFieldErrors('name')>
                            <#list bindingRes.getFieldErrors('name') as err>
                                <div class="invalid-feedback">${err.defaultMessage}</div>
                            </#list>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.category"/>:</label>
                        <select name="categoryId" class="form-select">
                            <option value=""><@spring.message "resume.category.select"/></option>
                            <#list categories as cat>
                                <option value="${cat.id}"
                                        <#if resume.categoryId?? && resume.categoryId == cat.id>selected</#if>>
                                    ${cat.name}
                                </option>
                            </#list>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.salary"/>:</label>
                        <input type="number" name="salary"
                               class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>is-invalid</#if>"
                               placeholder="0" min="0" step="0.01" value="${resume.salary!''}">
                        <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>
                            <#list bindingRes.getFieldErrors('salary') as err>
                                <div class="invalid-feedback">${err.defaultMessage}</div>
                            </#list>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.about"/>:</label>
                        <textarea name="description" class="form-control" rows="3">${resume.description!''}</textarea>
                    </div>

                    <#-- Блоки опыта работы -->
                    <div class="mb-3">
                        <label class="form-label fw-bold"><@spring.message "resume.section.experience"/>:</label>
                        <div id="workContainer"></div>
                        <button type="button" class="btn btn-outline-primary btn-sm mt-2"
                                onclick="addWork()" style="border-radius: 12px;">
                            <i class="bi bi-plus-lg"></i> <@spring.message "resume.section.experience"/>
                        </button>
                    </div>

                    <#-- Блоки образования -->
                    <div class="mb-3">
                        <label class="form-label fw-bold"><@spring.message "resume.section.education"/>:</label>
                        <div id="eduContainer"></div>
                        <button type="button" class="btn btn-outline-primary btn-sm mt-2"
                                onclick="addEdu()" style="border-radius: 12px;">
                            <i class="bi bi-plus-lg"></i> <@spring.message "resume.section.education"/>
                        </button>
                    </div>

                    <#-- Контакты -->
                    <div class="mb-3">
                        <label class="form-label fw-bold"><@spring.message "resume.contacts"/>:</label>
                        <div class="row g-2">
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input type="text" name="contactPhone" class="form-control"
                                           placeholder="+996 999 555 555"
                                           value="${user.phoneNumber!''}">
                                    <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input type="email" name="contactEmail" class="form-control"
                                           placeholder="example@mail.com"
                                           value="${user.email!''}">
                                    <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input type="url" name="telegram" class="form-control"
                                           placeholder="Telegram">
                                    <span class="input-group-text"><i class="bi bi-telegram"></i></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input type="url" name="facebook" class="form-control"
                                           placeholder="Facebook">
                                    <span class="input-group-text"><i class="bi bi-facebook"></i></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input type="url" name="linkedin" class="form-control"
                                           placeholder="LinkedIn">
                                    <span class="input-group-text"><i class="bi bi-linkedin"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="form-check">
                            <input type="checkbox" name="isActive" value="true"
                                   class="form-check-input"
                                   <#if resume.isActive?? && resume.isActive>checked</#if>
                                   id="isActiveCheck">
                            <label class="form-check-label" for="isActiveCheck">
                                <@spring.message "resume.publish"/>
                            </label>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <a href="/profile" class="btn btn-light border w-50" style="border-radius: 12px;">
                            <i class="bi bi-x me-1"></i><@spring.message "resume.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary w-50" style="border-radius: 12px;">
                            <i class="bi bi-check-lg me-1"></i><@spring.message "resume.save.edit"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        function addWork() {
            const container = document.getElementById('workContainer');
            const div = document.createElement('div');
            div.className = 'card p-3 mb-2 position-relative';
            div.style.borderRadius = '12px';

            div.innerHTML = `
            <button type="button" class="btn-close position-absolute top-0 end-0 m-2"
                    onclick="this.parentElement.remove()"></button>
            <div class="row g-2">
                <div class="col-md-4">
                    <label class="form-label small">${t_years}</label>
                    <input type="number" name="work_years[]" class="form-control form-control-sm"
                           placeholder="${t_years_ph}" min="0">
                </div>
                <div class="col-md-8">
                    <label class="form-label small">${t_company}</label>
                    <input type="text" name="work_company[]" class="form-control form-control-sm">
                </div>
                <div class="col-md-12">
                    <label class="form-label small">${t_position}</label>
                    <input type="text" name="work_position[]" class="form-control form-control-sm">
                </div>
                <div class="col-md-12">
                    <label class="form-label small">${t_resp}</label>
                    <textarea name="work_responsibilities[]" class="form-control form-control-sm"
                              rows="2" placeholder="${t_resp_ph}"></textarea>
                </div>
            </div>`;
            container.appendChild(div);
        }

        function addEdu() {
            const container = document.getElementById('eduContainer');
            const div = document.createElement('div');
            div.className = 'card p-3 mb-2 position-relative';
            div.style.borderRadius = '12px';
            const today = '${today}';

            div.innerHTML = `
            <button type="button" class="btn-close position-absolute top-0 end-0 m-2"
                    onclick="this.parentElement.remove()"></button>
            <div class="row g-2">
                <div class="col-md-6">
                    <label class="form-label small">${t_institution}</label>
                    <input type="text" name="edu_institution[]" class="form-control form-control-sm">
                </div>
                <div class="col-md-6">
                    <label class="form-label small">${t_program}</label>
                    <input type="text" name="edu_program[]" class="form-control form-control-sm">
                </div>
                <div class="col-md-4">
                    <label class="form-label small">${t_start}</label>
                    <input type="date" name="edu_start[]" class="form-control form-control-sm"
                    max="`+ today +`">
                </div>
                <div class="col-md-4">
                    <label class="form-label small">${t_end}</label>
                    <input type="date" name="edu_end[]" class="form-control form-control-sm"
                    max="`+ today +`">
                </div>
                <div class="col-md-4">
                    <label class="form-label small">${t_degree}</label>
                    <select name="edu_degree[]" class="form-select form-select-sm">
                        <option value="">${t_degree_sel}</option>
                        <option value="Бакалавр">${t_bachelor}</option>
                        <option value="Магистр">${t_master}</option>
                        <option value="Специалист">${t_specialist}</option>
                        <option value="Доктор">${t_doctor}</option>
                        <option value="Среднее специальное">${t_secondary}</option>
                    </select>
                </div>
            </div>`;
            container.appendChild(div);
        }
    </script>
</@main.layout>