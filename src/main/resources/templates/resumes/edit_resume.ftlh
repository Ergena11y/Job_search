<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<#assign today>${.now?string('yyyy-MM-dd')}</#assign>
<link rel="stylesheet" href="/static/css/resume.css">
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
        const RESUME_MSG = {
            workYears:`<@spring.message "resume.exp.years"/>`,
            workYearsPlaceholder:`<@spring.message "resume.exp.years.placeholder"/>`,
            workCompany:         `<@spring.message "resume.exp.company"/>`,
            workPosition:        `<@spring.message "resume.exp.position"/>`,
            workResp:            `<@spring.message "resume.exp.responsibilities"/>`,
            workRespPlaceholder: `<@spring.message "resume.exp.responsibilities.placeholder"/>`,
            eduInstitution:      `<@spring.message "resume.edu.institution"/>`,
            eduProgram:          `<@spring.message "resume.edu.program"/>`,
            eduStart:            `<@spring.message "resume.edu.start"/>`,
            eduEnd:              `<@spring.message "resume.edu.end"/>`,
            eduDegree:           `<@spring.message "resume.edu.degree"/>`,
            eduDegreeSelect:     `<@spring.message "resume.edu.degree.select"/>`,
            eduBachelor:         `<@spring.message "resume.edu.degree.bachelor"/>`,
            eduMaster:           `<@spring.message "resume.edu.degree.master"/>`,
            eduSpecialist:       `<@spring.message "resume.edu.degree.specialist"/>`,
            eduDoctor:           `<@spring.message "resume.edu.degree.doctor"/>`,
            eduSecondary:        `<@spring.message "resume.edu.degree.secondary"/>`,
        };
        const RESUME_TODAY = '${today}'
    </script>
    <script src="/static/js/resume.js"></script>
    <script> ResumeForm.init({ autoAddFirst: true})</script>
</@main.layout>