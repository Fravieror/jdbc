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

insert into person (id, name, location, birth_date)
values (10001, 'Ranga', 'Hyderabad', sysdate()),
(10002, 'Ranga', 'Hyderabad', sysdate()),
(10003, 'James', 'New York', sysdate()),
(10004, 'Peter', 'Amsterdam', sysdate()),
(10005, 'Javier', 'Bogotá', sysdate());


insert into course (id, name) values (1, 'Java'),
(2, 'Python')