CREATE TABLE hlarch.tmp_user (

    last_name varchar(128),
    name varchar(128),
    birth_date varchar(20),
    city varchar(50)
);

COPY hlarch.tmp_user FROM '/opt/people.v2.csv' WITH (FORMAT csv, DELIMITER ',', HEADER false);

INSERT INTO hlarch.user(username, password, name, last_name, date_of_birth, gender, hobbies, city)
SELECT concat('username', row_number() over()), encode('passw123'::bytea, 'base64') as password, name, last_name, to_char(to_date(birth_date, 'YYYY-MM-DD'), 'DD-MM-YYYY')::date,
CASE WHEN last_name LIKE '%а' THEN 'FEMALE' ELSE 'MALE' END as gender, 'some hobby' as hobbies, city
FROM hlarch.tmp_user;

DROP TABLE hlarch.tmp_user;