<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<#assign today>${.now?string('yyyy-MM-dd')}</#assign>
<@main.layout>
    <div class="container mt-4" style="max-width: 700px;">
        <div class="card shadow-sm" style="border-radius: 16px; border: 2px solid #e0e0e0;">
            <div class="card-body p-4">
                <h3 class="text-center mb-4" style="color: var(--bs-primary);">
                    <@spring.message "resume.create.title"/>
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

                <form action="/resumes/create" method="post" id="resumeForm">
                    <#if _csrf??><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></#if>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.name"/>: <span class="text-danger">*</span></label>
                        <input type="text" name="name" class="form-control"
                               placeholder="<@spring.message "resume.name.placeholder"/>"
                               value="${resumeDto.name!''}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.category"/>:</label>
                        <select name="categoryId" class="form-select">
                            <option value=""><@spring.message "resume.category.select"/></option>
                            <#list categories as cat>
                                <option value="${cat.id}">${cat.name}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.salary"/>:</label>
                        <input type="number" name="salary" class="form-control"
                               placeholder="<@spring.message "resume.salary"/>" min="0" step="0.01">
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message "resume.about"/>:</label>
                        <textarea name="description" class="form-control" rows="3"
                                  placeholder="<@spring.message "resume.about"/>">${resumeDto.description!''}</textarea>
                    </div>

                    <!-- Work Experience blocks -->
                    <div class="mb-3">
                        <label class="form-label fw-bold"><@spring.message "resume.section.experience"/>:</label>
                        <div id="workContainer"></div>
                        <button type="button" class="btn btn-outline-primary btn-sm mt-2"
                                onclick="addWork()" style="border-radius: 12px;">
                            <i class="bi bi-plus-lg"></i> <@spring.message "resume.section.experience"/>
                        </button>
                    </div>

                    <!-- Education blocks -->
                    <div class="mb-3">
                        <label class="form-label fw-bold"><@spring.message "resume.section.education"/>:</label>
                        <div id="eduContainer"></div>
                        <button type="button" class="btn btn-outline-primary btn-sm mt-2"
                                onclick="addEdu()" style="border-radius: 12px;">
                            <i class="bi bi-plus-lg"></i> <@spring.message "resume.section.education"/>
                        </button>
                    </div>

                    <!-- Contacts -->
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
                                   class="form-check-input" checked id="isActiveCheck">
                            <label class="form-check-label" for="isActiveCheck"><@spring.message "vacancy.published"/></label>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary w-100" style="border-radius: 12px;">
                        <@spring.message "resume.publish"/>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <script>
        const msg = {
            workYears:           `<@spring.message "resume.exp.years"/>`,
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

        <#noparse>
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
                    <label class="form-label small">${msg.workYears}</label>
                    <input type="number" name="work_years[]" class="form-control form-control-sm"
                           placeholder="${msg.workYearsPlaceholder}" min="0">
                </div>
                <div class="col-md-8">
                    <label class="form-label small">${msg.workCompany}</label>
                    <input type="text" name="work_company[]" class="form-control form-control-sm"
                           placeholder="${msg.workCompany}">
                </div>
                <div class="col-md-12">
                    <label class="form-label small">${msg.workPosition}</label>
                    <input type="text" name="work_position[]" class="form-control form-control-sm"
                           placeholder="${msg.workPosition}">
                </div>
                <div class="col-md-12">
                    <label class="form-label small">${msg.workResp}</label>
                    <textarea name="work_responsibilities[]" class="form-control form-control-sm"
                              rows="2" placeholder="${msg.workRespPlaceholder}"></textarea>
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
                    <label class="form-label small">${msg.eduInstitution}</label>
                    <input type="text" name="edu_institution[]" class="form-control form-control-sm"
                           placeholder="${msg.eduInstitution}">
                </div>
                <div class="col-md-6">
                    <label class="form-label small">${msg.eduProgram}</label>
                    <input type="text" name="edu_program[]" class="form-control form-control-sm"
                           placeholder="${msg.eduProgram}">
                </div>
                <div class="col-md-4">
                    <label class="form-label small">${msg.eduStart}</label>
                    <input type="date" name="edu_start[]" class="form-control form-control-sm"
                    max="${today}">
                </div>
                <div class="col-md-4">
                    <label class="form-label small">${msg.eduEnd}</label>
                    <input type="date" name="edu_end[]" class="form-control form-control-sm"
                    max="${today}">
                </div>
                <div class="col-md-4">
                    <label class="form-label small">${msg.eduDegree}</label>
                    <select name="edu_degree[]" class="form-select form-select-sm">
                        <option value="">${msg.eduDegreeSelect}</option>
                        <option value="Bachelor">${msg.eduBachelor}</option>
                        <option value="Master">${msg.eduMaster}</option>
                        <option value="Specialist">${msg.eduSpecialist}</option>
                        <option value="Doctor">${msg.eduDoctor}</option>
                        <option value="Secondary">${msg.eduSecondary}</option>
                    </select>
                </div>
            </div>`;
            container.appendChild(div);
        }

        addWork();
        addEdu();
    </script>
</#noparse>
</@main.layout>