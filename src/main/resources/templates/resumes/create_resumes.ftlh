<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h3 class="mb-0">Создать резюме</h3>
            </div>
            <div class="card-body p-4">
                <form action="/resumes/create" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}">
                    </#if>


                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">Основная информация</h5>
                        <div class="mb-3">
                            <label class="form-label fw-bold">Название должности <span class="text-danger">*</span></label>
                            <input type="text" name="name" class="form-control" required placeholder="Например: Java Developer">
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Ожидаемая зарплата</label>
                                <div class="input-group">
                                    <input type="number" step="0.01" name="salary" class="form-control" placeholder="0.00">
                                    <span class="input-group-text">⃀</span>
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Категория <span class="text-danger">*</span></label>
                                <select name="categoryId" class="form-select" required>
                                    <option value="">-- Выберите категорию --</option>
                                    <#if categories??>
                                        <#list categories as category>
                                            <option value="${category.id}">${category.name}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                    </section>


                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">Опыт работы и навыки</h5>
                        <div class="mb-3">
                            <label class="form-label fw-bold">Подробное описание</label>
                            <textarea name="description" class="form-control" rows="6"
                                      placeholder="Расскажите о ваших проектах, технологиях и достижениях..."></textarea>
                        </div>
                    </section>


                    <section class="mb-4">
                        <h5 class="text-primary border-bottom pb-2 mb-3">Контактная информация</h5>
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label small text-muted">Номер телефона</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                                    <input type="text" name="phoneNumber" class="form-control" placeholder="+7 (___) ___-__-__">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label small text-muted">Электронная почта</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                                    <input type="email" name="applicantEmail" class="form-control" placeholder="example@mail.com">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label class="form-label small text-muted">Ссылка на профиль (LinkedIn / GitHub / Портфолио)</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-link-45deg"></i></span>
                                    <input type="url" name="externalLink" class="form-control" placeholder="https://github.com/your-profile">
                                </div>
                            </div>
                        </div>
                    </section>


                    <div class="d-flex justify-content-end gap-2 border-top pt-3">
                        <a href="/profile" class="btn btn-light border">Отмена</a>
                        <button type="submit" class="btn btn-primary px-5">Опубликовать резюме</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>