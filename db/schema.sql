
create table car_marks (
                           id serial primary key,
                           name VARCHAR (50) NOT NULL UNIQUE
);

create table car_models (
                            id serial primary key,
                            name VARCHAR (50) NOT NULL UNIQUE
);

create table books (
    id serial primary key,
    name varchar (200) not null
);

create table authors (
    id serial primary key,
    name varchar (200) not null
);