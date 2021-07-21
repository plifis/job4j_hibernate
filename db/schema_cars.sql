create  table car (
    id serial primary key,
    model varchar(50),
    engine_id int not null references engine (id)
);

create table engine (
    id serial primary key,
    name varchar (50),
    power int
);

create table driver (
    id serial primary key,
    name varchar (100),
    license int,
    birthday date
);

create table history_owner (
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);