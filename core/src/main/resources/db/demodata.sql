insert into Users (id, dateOfBirth, phone, email, firstName, lastName, login, password, patronymic, employmentDate, salary, DTYPE) values (1, '1998-03-11', '+375291234567','de32z@mail.ru', 'Dmitry', 'Koshelev', 'admin', 'password', 'Michailovic', '2018-04-10', 500, 'Admin');
insert into Passports (id, authority, dateOfIssue, identNumber, number, user_id) values (1, 'Первомайский РОВД', '2017-01-01', '3110111111EEE', 'BM3333333', 1);

insert into Users (id, dateOfBirth, phone, email, firstName, lastName, login, password, patronymic, employmentDate, salary, DTYPE) values (2, '1998-03-11', '+375291234567','3cx2b@mail.ru', 'Kirillov', 'Alexander', 'realtor', 'password', 'Vladimorovich', '2018-04-10', 500, 'Realtor');
insert into Passports (id, authority, dateOfIssue, identNumber, number, user_id) values (2, 'Первомайский РОВД', '2017-01-01', '3222222222EEE', 'BM4444444', 2);

insert into Users (id, dateOfBirth, phone, email, firstName, lastName, login, password, patronymic, employmentDate, salary, DTYPE) values (3, '1998-03-11', '+375291234567','vr3c2@mail.ru', 'Davidovich', 'Ilya', 'client', 'password', 'Vladimorovich', null, null, 'Client');
insert into Passports (id, authority, dateOfIssue, identNumber, number, user_id) values (3, 'Первомайский РОВД', '2017-01-01', '3333333333EEE', 'BM5555555', 3);