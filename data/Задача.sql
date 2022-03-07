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

SELECT * FROM company
INSERT INTO company(id,name) VALUES (7,'BMW');
INSERT INTO company(id,name) VALUES (1,'Mersedes');
INSERT INTO company(id,name) VALUES (2,'AUDI');
INSERT INTO company(id,name) VALUES (77,'MARS');
INSERT INTO company(id,name) VALUES (124,'PROCTER');
INSERT INTO company(id,name) VALUES (5,'ZARA');

INSERT INTO person(id,name,company_id ) VALUES (1,'Иван',7);
INSERT INTO person(id,name,company_id ) VALUES (2,'Максим',7);
INSERT INTO person(id,name,company_id ) VALUES (11,'Инна',7);
INSERT INTO person(id,name,company_id ) VALUES (3,'Николай',1);
INSERT INTO person(id,name,company_id ) VALUES (4,'Андрей',1);
INSERT INTO person(id,name,company_id ) VALUES (5,'Яна',2);
INSERT INTO person(id,name,company_id ) VALUES (6,'Света',2);
INSERT INTO person(id,name,company_id ) VALUES (7,'Александер',77);
INSERT INTO person(id,name,company_id ) VALUES (8,'Игорь',124);
INSERT INTO person(id,name,company_id ) VALUES (9,'Рафаэль',5);
INSERT INTO person(id,name,company_id ) VALUES (10,'Лео',5);


SELECT p.name, c.name
FROM company c
JOIN person p 
ON p.company_id = c.id
where c.id != 5;

WITH temp AS (
SELECT  c.name,COUNT(p.company_id) AS "количество"
FROM company c JOIN person p 
ON p.company_id = c.id
GROUP BY c.name
)
SELECT * FROM temp WHERE temp.количество = (SELECT MAX(temp.количество) FROM temp);

SELECT c.name, COUNT(p.company_id)
FROM company c JOIN person p 
ON c.id = p.company_id
GROUP BY c.name
having COUNT(p.company_id) = 
(SELECT  COUNT(*)
FROM company c
JOIN person p 
ON p.company_id = c.id
GROUP BY company_id
ORDER BY COUNT(*) DESC LIMIT 1);