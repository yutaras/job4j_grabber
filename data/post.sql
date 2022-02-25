create table if not exists post (
    id serial primary key,
	name varchar(200),
	text text,
	link varchar(200) unique,
    created timestamp
);