<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3 class="mb-0">Создать резюме</h3>
            </div>
            <div class="card-body">
                <form action="/resumes/create" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}">
                    </#if>




                    <!-- Основная информация -->
                    <div class="mb-4">
                        <h5 class="border-bottom pb-2 mb-3">Основная информация</h5>

                        <div class="mb-3">
                            <label class="form-label">Название должности <span class="text-danger">*</span></label>
                            <input type="text" name="name" class="form-control" required placeholder="Например: Java Developer">
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label class="form-label">Ожидаемая зарплата <span class="text-danger">*</span></label>
                                    <input type="number" step="0.01" name="salary" class="form-control" required placeholder="50000">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label class="form-label">Категория</label>
                                    <input type="number" name="categoryId" class="form-control" placeholder="ID категории">
                                </div>
                            </div>
                        </div>

                        <div class="form-check mb-3">
                            <input type="checkbox" name="isActive" value="true" class="form-check-input" id="isActive" checked>
                            <label class="form-check-label" for="isActive">
                                Опубликовать резюме
                            </label>
                        </div>
                    </div>

                    <!-- Образование  -->

                    <div class="mb-4">
                        <h5 class="border-bottom pb-2 mb-3">Образование</h5>

                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="mb-3">
                                    <label class="form-label">Учреждение</label>
                                    <input type="text" name="education1_institution" class="form-control" placeholder="Название учреждения">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Специальность</label>
                                    <input type="text" name="education1_program" class="form-control" placeholder="Программа обучения">
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label">Начало обучения</label>
                                            <input type="date" name="education1_startDate" class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label">Завершение обучения</label>
                                            <input type="date" name="education1_endDate" class="form-control">
                                        </div>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Степень</label>
                                    <input type="text" name="education1_degree" class="form-control" placeholder="Например: Бакалавр, Магистр">
                                </div>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <h6>Образование 2 (опционально)</h6>
                                <div class="mb-3">
                                    <label class="form-label">Учреждение</label>
                                    <input type="text" name="education2_institution" class="form-control" placeholder="Название учреждения">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Специальность</label>
                                    <input type="text" name="education2_program" class="form-control" placeholder="Программа обучения">
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label">Начало обучения</label>
                                            <input type="date" name="education2_startDate" class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="mb-3">
                                            <label class="form-label">Завершение обучения</label>
                                            <input type="date" name="education2_endDate" class="form-control">
                                        </div>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Степень</label>
                                    <input type="text" name="education2_degree" class="form-control" placeholder="Например: Бакалавр, Магистр">
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- Опыт работы -->
                    <div class="mb-4">
                        <h5 class="border-bottom pb-2 mb-3">Опыт работы</h5>

                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="mb-3">
                                    <label class="form-label">Компания</label>
                                    <input type="text" name="workExp1_companyName" class="form-control" placeholder="Название компании">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Должность</label>
                                    <input type="text" name="workExp1_position" class="form-control" placeholder="Ваша должность">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Стаж (в годах)</label>
                                    <input type="number" name="workExp1_years" class="form-control" min="0" placeholder="0">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Обязанности</label>
                                    <textarea name="workExp1_responsibilities" class="form-control" rows="3" placeholder="Описание ваших обязанностей"></textarea>
                                </div>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <h6>Опыт работы 2(опционально)</h6>
                                <div class="mb-3">
                                    <label class="form-label">Компания</label>
                                    <input type="text" name="workExp2_companyName" class="form-control" placeholder="Название компании">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Должность</label>
                                    <input type="text" name="workExp2_position" class="form-control" placeholder="Ваша должность">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Стаж (в годах)</label>
                                    <input type="number" name="workExp2_years" class="form-control" min="0" placeholder="0">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Обязанности</label>
                                    <textarea name="workExp2_responsibilities" class="form-control" rows="3" placeholder="Описание ваших обязанностей"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- Контакты -->
                    <div class="mb-4">
                        <h5 class="border-bottom pb-2 mb-3">Контакты</h5>

                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="mb-3">
                                    <label class="form-label">Тип контакта</label>
                                    <select name="contact1_typeId" class="form-control">
                                        <option value="">Выберите тип</option>
                                        <option value="1">Телефон</option>
                                        <option value="2">Email</option>
                                        <option value="3">LinkedIn</option>
                                        <option value="4">GitHub</option>
                                        <option value="5">Сайт</option>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Значение</label>
                                    <input type="text" name="contact1_value" class="form-control" placeholder="Введите контактные данные">
                                </div>
                            </div>
                        </div>

                        <div class="card mb-3">
                            <div class="card-body">
                                <h6>Контакт 2 (опционально)</h6>
                                <div class="mb-3">
                                    <label class="form-label">Тип контакта</label>
                                    <select name="contact2_typeId" class="form-control">
                                        <option value="">Выберите тип</option>
                                        <option value="1">Телефон</option>
                                        <option value="2">Email</option>
                                        <option value="3">LinkedIn</option>
                                        <option value="4">GitHub</option>
                                        <option value="5">Сайт</option>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Значение</label>
                                    <input type="text" name="contact2_value" class="form-control" placeholder="Введите контактные данные">
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Кнопки действия -->
                    <div class="d-flex gap-2 mt-4">
                        <button type="submit" class="btn btn-primary">
                            Сохранить
                        </button>
                        <a href="/resumes" class="btn btn-secondary">
                            Отмена
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@main.layout>