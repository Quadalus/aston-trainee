INSERT INTO users(email, name)
VALUES ('test@gmail.com', 'testuser'),
       ('kit@ya.ru', 'dedal');

INSERT INTO chats(title, created_on)
VALUES ('Книжный клуб', current_timestamp),
       ('Фильмы', current_timestamp);

INSERT INTO message(text, user_id, chat_id, created_on)
VALUES ('Пушкин лучший писатель 19 века!', 1, 1, current_timestamp),
       ('Остров', 2, 1, current_timestamp),
       ('Терминатор 2 мой самый любимый фильм', 1, 2, current_timestamp),
       ('Тарантино гений', 2, 2, current_timestamp);

INSERT INTO users_chats(user_id, chat_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2);