INSERT INTO categories (name, parent_id) VALUES ('IT', NULL);
INSERT INTO categories (name, parent_id) VALUES ('Финансы', NULL);


INSERT INTO categories (name, parent_id)
VALUES
    ('Backend разработка', (SELECT id FROM categories WHERE name = 'IT')),
    ('Frontend разработка', (SELECT id FROM categories WHERE name = 'IT')),
    ('Бухгалтерия', (SELECT id FROM categories WHERE name = 'Финансы'));



INSERT INTO users (name, surname, age, email, password, phone_number, account_type, enabled)
VALUES
    ('ООО Альфа', 'Компания', 30, 'alpha@corp.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700001', 'EMPLOYER', true),
    ('ООО Бета', 'Компания', 35, 'beta@corp.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700002', 'EMPLOYER', true),
    ('ТехСтарт', 'Компания', 28, 'techstart@corp.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700003', 'EMPLOYER', true),
    ('Айгуль', 'Сейткали', 24, 'aigul@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700010', 'APPLICANT', true),
    ('Бакыт', 'Токтосунов', 27, 'bakyt@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700011', 'APPLICANT', true),
    ('Жазгуль', 'Мамытова', 22, 'jazgul@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700012', 'APPLICANT', true),
    ('Нурлан', 'Абдыкеров', 29, 'nurlan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700013', 'APPLICANT', true),
    ('Адилет', 'Кенжебаев', 26, 'adilet@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '+996700014', 'APPLICANT', true);


INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Senior Java Developer', 'Разработка микросервисов',
     (SELECT id FROM categories WHERE name = 'Backend разработка'), 150000, 3, 6, TRUE,
     (SELECT id FROM users WHERE email = 'alpha@corp.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('React Developer', 'Разработка SPA',
     (SELECT id FROM categories WHERE name = 'Frontend разработка'), 110000, 1, 4, TRUE,
     (SELECT id FROM users WHERE email = 'beta@corp.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Бухгалтер', 'Ведение учёта',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 70000, 2, 5, TRUE,
     (SELECT id FROM users WHERE email = 'beta@corp.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



INSERT INTO resumes (name, salary, is_active, created_date, update_time, category_id, applicant_id)
VALUES
    ('Java Developer', 90000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     (SELECT id FROM categories WHERE name = 'Backend разработка'),
     (SELECT id FROM users WHERE email = 'aigul@mail.com')),

    ('Frontend React', 80000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     (SELECT id FROM categories WHERE name = 'Frontend разработка'),
     (SELECT id FROM users WHERE email = 'jazgul@mail.com')),

    ('Младший бухгалтер', 60000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     (SELECT id FROM users WHERE email = 'adilet@mail.com'));