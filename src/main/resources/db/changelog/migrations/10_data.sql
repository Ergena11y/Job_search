-- Пароль для всех: Password123
INSERT INTO users (name, surname, age, email, password, phone_number, account_type, enabled)
VALUES
    ('Ильяс', 'Асанов', 25, 'ilyas@mail.com', '$2a$10$ZvCmaOTS7.agDgKTyBGtDuNd20Ty1N9hy1H86kRCo4ENDcNpYVlsW', '+996700015', 'APPLICANT', true),
    ('Елена', 'Петрова', 31, 'elena@mail.com', '$2a$10$ZvCmaOTS7.agDgKTyBGtDuNd20Ty1N9hy1H86kRCo4ENDcNpYVlsW', '+996700016', 'APPLICANT', true),
    ('Алихан', 'Усенов', 23, 'alikhan@mail.com', '$2a$10$ZvCmaOTS7.agDgKTyBGtDuNd20Ty1N9hy1H86kRCo4ENDcNpYVlsW', '+996700017', 'APPLICANT', true);

INSERT INTO users (name, email, password, phone_number, account_type, enabled)
VALUES
    ('СкайНэт Медиа',  'skynet@corp.com', '$2a$10$ZvCmaOTS7.agDgKTyBGtDuNd20Ty1N9hy1H86kRCo4ENDcNpYVlsW', '+996700004', 'EMPLOYER', true),
    ('ФинТех Решения',  'fintech@corp.com', '$2a$10$ZvCmaOTS7.agDgKTyBGtDuNd20Ty1N9hy1H86kRCo4ENDcNpYVlsW', '+996700005', 'EMPLOYER', true),
    ('ДевСтудио', 'devstudio@corp.com', '$2a$10$ZvCmaOTS7.agDgKTyBGtDuNd20Ty1N9hy1H86kRCo4ENDcNpYVlsW', '+996700006', 'EMPLOYER', true);




INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Junior Java Разработчик', 'Поддержка существующих сервисов, написание unit-тестов. Знание Spring Boot приветствуется.',
     (SELECT id FROM categories WHERE name = 'Backend разработка'), 50000, 0, 1, TRUE,
     (SELECT id FROM users WHERE email = 'devstudio@corp.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Lead Frontend Engineer (Vue/React)', 'Управление командой фронтенда, проектирование архитектуры крупных веб-приложений.',
     (SELECT id FROM categories WHERE name = 'Frontend разработка'), 200000, 5, 8, TRUE,
     (SELECT id FROM users WHERE email = 'skynet@corp.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('Главный бухгалтер', 'Полное ведение налогового и управленческого учета финансовой организации.',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 130000, 4, 7, TRUE,
     (SELECT id FROM users WHERE email = 'fintech@corp.com'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO resumes (name, salary, is_active, created_date, update_time, category_id, applicant_id)
VALUES
    ('Middle Java Developer', 120000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     (SELECT id FROM categories WHERE name = 'Backend разработка'),
     (SELECT id FROM users WHERE email = 'ilyas@mail.com')),

    ('Стажер Frontend (HTML/CSS/JS)', 35000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     (SELECT id FROM categories WHERE name = 'Frontend разработка'),
     (SELECT id FROM users WHERE email = 'alikhan@mail.com')),

    ('Старший бухгалтер / Финансовый аналитик', 110000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     (SELECT id FROM users WHERE email = 'elena@mail.com'));