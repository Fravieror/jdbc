-- the schema update this key map is triggered by springboot auto configuration.
-- It is one of the features in Hibernate
--create table person
--(
--    id integer not null,
--    name varchar(255) not null,
--    location varchar(255),
--    birth_date timestamp,
--    primary key(id)
--);

--SPRINGBOOT AUTOCONFIGURATION CREATES THE TABLES
insert into passport (id, number)
values (40001, '4654123'),
(40002, '46559874'),
(40003, '88546'),
(40004, '98654'),
(40005, '7988765');

insert into person (id, name, location, birth_date, passport_id)
values (10001, 'Ranga', 'Hyderabad', sysdate(), 40001),
(10002, 'Ranger', 'Hyderabad', sysdate(), 40002),
(10003, 'James', 'New York', sysdate(), 40003),
(10004, 'Peter', 'Amsterdam', sysdate(), 40004),
(10005, 'Javier', 'Bogotá', sysdate(), 40005);

insert into course (id, name, is_deleted) values (1, 'Java', false),
(2, 'Python', false);

insert into review(id, rating, description)
values (50001, 1, '4654123'),
(50002, 2, '46559874'),
(50003, 3, '88546'),
(50004, 4, '98654'),
(50005, 5, '7988765');