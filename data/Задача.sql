CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

select * from company
insert into company(id,name) values (7,'BMW');
insert into company(id,name) values (1,'Mersedes');
insert into company(id,name) values (2,'AUDI');
insert into company(id,name) values (77,'MARS');
insert into company(id,name) values (124,'PROCTER');
insert into company(id,name) values (5,'ZARA');

insert into person(id,name,company_id ) values (1,'Иван',7);
insert into person(id,name,company_id ) values (2,'Максим',7);
insert into person(id,name,company_id ) values (11,'Инна',7);
insert into person(id,name,company_id ) values (3,'Николай',1);
insert into person(id,name,company_id ) values (4,'Андрей',1);
insert into person(id,name,company_id ) values (5,'Яна',2);
insert into person(id,name,company_id ) values (6,'Света',2);
insert into person(id,name,company_id ) values (7,'Александер',77);
insert into person(id,name,company_id ) values (8,'Игорь',124);
insert into person(id,name,company_id ) values (9,'Рафаэль',5);
insert into person(id,name,company_id ) values (10,'Лео',5);


select p.name, c.name
from company c
join person p 
on p.company_id = c.id
where c.id != 5;