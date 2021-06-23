
create table car_marks (
                           id serial primary key,
                           name VARCHAR (50) NOT NULL UNIQUE
);

create table car_models (
                            id serial primary key,
                            name VARCHAR (50) NOT NULL UNIQUE
);