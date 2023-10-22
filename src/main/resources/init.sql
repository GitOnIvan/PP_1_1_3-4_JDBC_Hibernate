CREATE SCHEMA IF NOT EXISTS pp_db;
USE pp_db;
DROP TABLE IF EXISTS users;
CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(45));
CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(45), lastname VARCHAR(45), age TINYINT);
DELETE FROM user;




INSERT INTO user (name, lastname, age) VALUES (?, ?, ?);





#insert into pp (name, lastname, age) values ('John', 'Smith', 25);
#insert into pp values (DEFAULT, 'John', 'Smith', 25);
#delete from pp where id=11;
#delete from pp where id=10;
#select * from pp;
#update pp set name='LOL', age=999 where id=12;