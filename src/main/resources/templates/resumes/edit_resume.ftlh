<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h3 class="mb-0"><i class="bi bi-pencil-square me-2"></i>Редактировать резюме</h3>
            </div>
            <div class="card-body p-4">
                <form action="/resumes/edit/${resume.id!''}" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}">
                    </#if>


                    <#if bindingRes?? && bindingRes.hasErrors()>
                        <div class="alert alert-danger">
                            <ul class="mb-0">
                                <#list bindingRes.allErrors as err>
                                    <li>${err.defaultMessage!''}</li>
                                </#list>
                            </ul>
                        </div>
                    </#if>

                    <#-- 1. Основная информация -->
                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">
                            <i class="bi bi-person-badge me-1"></i>Основная информация
                        </h5>

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                Название должности <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="name"
                                   class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('name')>is-invalid</#if>"
                                   value="${resume.name!''}" required>
                            <#if bindingRes?? && bindingRes.hasFieldErrors('name')>
                                <#list bindingRes.getFieldErrors('name') as err>
                                    <div class="invalid-feedback">${err.defaultMessage}</div>
                                </#list>
                            </#if>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Ожидаемая зарплата</label>
                                <div class="input-group">
                                    <input type="number" step="0.01" name="salary"
                                           class="form-control <#if bindingRes?? && bindingRes.hasFieldErrors('salary')>is-invalid</#if>"
                                           value="${resume.salary!''}" min="0">
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
                                    Категория <span class="text-danger">*</span>
                                </label>
                                <select name="categoryId" class="form-select" required>
                                    <option value=""><---| Выберите категорию |---></option>
                                    <#if categories??>
                                        <#list categories as category>
                                            <option value="${category.id}"
                                                    <#if resume.categoryId?? && resume.categoryId == category.id>selected</#if>>
                                                ${category.name}
                                            </option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <div class="form-check">
                                <input type="checkbox" name="isActive" value="true"
                                       class="form-check-input"
                                       <#if resume.isActive?? && resume.isActive>checked</#if>
                                       id="isActiveCheck">
                                <label class="form-check-label" for="isActiveCheck">
                                    Опубликовать резюме
                                </label>
                            </div>
                        </div>
                    </section>

                    <#-- 2. Опыт работы -->
                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">
                            <i class="bi bi-briefcase me-1"></i>Опыт работы
                        </h5>
                        <p class="text-muted small mb-3">
                            <i class="bi bi-info-circle me-1"></i>
                            Для добавления нескольких записей опыта используйте страницу детального просмотра резюме.
                        </p>

                        <div class="row g-3 mb-3">
                            <div class="col-md-4">
                                <label class="form-label small text-muted">Количество лет</label>
                                <input type="number" name="workYears" class="form-control"
                                       placeholder="Например: 3" min="0">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted">Название компании</label>
                                <input type="text" name="companyName" class="form-control"
                                       placeholder="Название компании">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted">Должность</label>
                                <input type="text" name="position" class="form-control"
                                       placeholder="Ваша должность">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small text-muted">Обязанности и достижения</label>
                            <textarea name="responsibilities" class="form-control" rows="4"
                                      placeholder="Опишите ваши обязанности и ключевые достижения..."></textarea>
                        </div>
                    </section>

                    <#-- 3. Образование -->
                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">
                            <i class="bi bi-mortarboard me-1"></i>Образование
                        </h5>

                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label class="form-label small text-muted">Учебное заведение</label>
                                <input type="text" name="institution" class="form-control"
                                       placeholder="Название университета / колледжа">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label small text-muted">Специальность / программа</label>
                                <input type="text" name="program" class="form-control"
                                       placeholder="Например: Информационные технологии">
                            </div>
                        </div>
                        <div class="row g-3 mb-3">
                            <div class="col-md-4">
                                <label class="form-label small text-muted">Дата начала</label>
                                <input type="date" name="eduStartDate" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted">Дата окончания</label>
                                <input type="date" name="eduEndDate" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label small text-muted">Степень</label>
                                <select name="degree" class="form-select">
                                    <option value=""><----| Степень |----></option>
                                    <option value="Бакалавр">Бакалавр</option>
                                    <option value="Магистр">Магистр</option>
                                    <option value="Специалист">Специалист</option>
                                    <option value="Доктор">Доктор</option>
                                    <option value="Среднее специальное">Среднее специальное</option>
                                </select>
                            </div>
                        </div>
                    </section>

                    <#-- Кнопки -->
                    <div class="d-flex justify-content-end gap-2 border-top pt-3">
                        <a href="/resumes" class="btn btn-light border">
                            <i class="bi bi-x me-1"></i>Отмена
                        </a>
                        <button type="submit" class="btn btn-primary px-5">
                            <i class="bi bi-check-lg me-1"></i>Сохранить изменения
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>