CREATE TABLE hlarch.user(
    id SERIAL,
    username varchar(128),
    password varchar(128),
    name varchar(128),
    last_name varchar(128),
    date_of_birth date,
    gender varchar,
    hobbies varchar(250),
    city varchar(128)
);

CREATE TABLE hlarch.session(
    username varchar(128),
    token varchar(128),
    expiration_time timestamp
);