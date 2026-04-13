INSERT INTO users (name, surname, age, email, password, phone_number, account_type, enabled)
VALUES
    ('ООО Альфа', 'Компания', 30, 'alpha@corp.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700001', 'EMPLOYER', true),
    ('ООО Бета', 'Компания', 35, 'beta@corp.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700002', 'EMPLOYER', true),
    ('ТехСтарт', 'Компания', 28, 'techstart@corp.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700003', 'EMPLOYER', true);

INSERT INTO users (name, surname, age, email, password, phone_number, account_type, enabled)
VALUES
    ('Айгуль', 'Сейткали', 24, 'aigul@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700010', 'APPLICANT', true),
    ('Бакыт', 'Токтосунов', 27, 'bakyt@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700011', 'APPLICANT', true),
    ('Жазгуль', 'Мамытова', 22, 'jazgul@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700012', 'APPLICANT', true),
    ('Нурлан', 'Абдыкеров', 29, 'nurlan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700013', 'APPLICANT', true),
    ('Адилет', 'Кенжебаев', 26, 'adilet@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700014', 'APPLICANT', true);


INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Senior Java Developer', 'Разработка микросервисов на Spring Boot', 2, 150000, 3, 6, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('DevOps Engineer', 'Настройка CI/CD, Docker, Kubernetes', 2, 130000, 2, 5, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('React Developer', 'Разработка SPA приложений на React', 3, 110000, 1, 4, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Vue.js Developer', 'Разработка на Vue 3 + TypeScript', 3, 105000, 1, 3, TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Python Backend Developer', 'Разработка на Django/FastAPI', 2, 120000, 2, 5, TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Data Analyst', 'Анализ данных, SQL, Power BI', 2, 90000, 1, 3, TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('QA Engineer', 'Ручное и автоматизированное тестирование', 2, 80000, 0, 3, TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('UI/UX Designer', 'Проектирование интерфейсов в Figma', 3, 95000, 1, 4, TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Project Manager', 'Управление IT-проектами, Scrum', 2, 140000, 3, 7, TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Бухгалтер', 'Ведение бухгалтерского учёта', 5, 70000, 2, 5, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Финансовый аналитик', 'Анализ финансовой отчётности', 4, 100000, 2, 5, TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Android Developer', 'Разработка мобильных приложений на Kotlin', 2, 125000, 2, 5, TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('iOS Developer', 'Разработка на Swift', 2, 130000, 2, 5, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



INSERT INTO resumes (name, salary, is_active, created_date, update_time, category_id, applicant_id)
VALUES
    ('Java Developer', 90000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1),
    ('Python разработчик', 85000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 5),
    ('Frontend React', 80000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 6),
    ('Vue.js разработчик', 75000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 7),
    ('QA инженер', 65000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 8),
    ('Data Scientist', 95000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 9),
    ('DevOps специалист', 110000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1),
    ('Android разработчик', 88000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 5),
    ('iOS разработчик', 92000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 6),
    ('UI/UX дизайнер', 78000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 7),
    ('Бухгалтер', 60000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 8),
    ('Финансист', 70000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 9),
    ('Project Manager', 100000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1),
    ('Fullstack разработчик Node.js', 95000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 5),
    ('Системный аналитик', 85000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 6);