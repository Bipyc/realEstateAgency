INSERT INTO Users (id, dateOfBirth, phone, email, firstName, lastName, login, password, patronymic, employmentDate, salary, DTYPE)
VALUES (1, '1998-03-11', '+375291234567', 'de32z@mail.ru', 'Dmitry', 'Koshelev', 'admin',
           '$2a$11$kmrxD70ZsG5q0gSgsEqK6O7y.dmkLW0eXnQCzVEUOuOnTnLZ5rIvS', 'Michailovic', '2018-04-10', 500, 'Admin');
INSERT INTO Passports (id, authority, dateOfIssue, identNumber, number, user_id)
VALUES (1, 'Первомайский РОВД', '2017-01-01', '3110111111EEE', 'BM3333333', 1);

INSERT INTO Users (id, dateOfBirth, phone, email, firstName, lastName, login, password, patronymic, employmentDate, salary, DTYPE)
VALUES (2, '1998-03-11', '+375291234567', '3cx2b@mail.ru', 'Kirillov', 'Alexander', 'realtor',
           '$2a$11$fjRMze4xfp6.1rD1nAy9kOrriGltAfgObtRqt5kgZ/iYaMw2E6GPm', 'Olegovich', '2018-04-10',
           500, 'Realtor');
INSERT INTO Passports (id, authority, dateOfIssue, identNumber, number, user_id)
VALUES (2, 'Первомайский РОВД', '2017-01-01', '3222222222EEE', 'BM4444444', 2);

INSERT INTO Users (id, dateOfBirth, phone, email, firstName, lastName, login, password, patronymic, employmentDate, salary, DTYPE)
VALUES (3, '1998-03-11', '+375291234567', 'vr3c2@mail.ru', 'Davidovich', 'Ilya', 'client',
           '$2a$11$xQrbmnS4RQC3qlblZC.5NutDy2BQSwAZbJz9pUDDgHzpzzqs4ANQC', 'Igorevich', NULL, NULL, 'Client');
INSERT INTO Passports (id, authority, dateOfIssue, identNumber, number, user_id)
VALUES (3, 'Первомайский РОВД', '2017-01-01', '3333333333EEE', 'BM5555555', 3);

INSERT INTO TypeApplications (id, commission, name) VALUES (1, 5.0, 'Buy');
INSERT INTO TypeApplications (id, commission, name) VALUES (2, 5.0, 'Sell');
INSERT INTO TypeApplications (id, commission, name) VALUES (3, 100.0, 'Rent');
INSERT INTO TypeApplications (id, commission, name) VALUES (4, 100.0, 'Rent out');