-- Database: studentdb

-- DROP DATABASE studentdb;

CREATE DATABASE studentdb
  WITH OWNER = student
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United Kingdom.1252'
       LC_CTYPE = 'English_United Kingdom.1252'
       CONNECTION LIMIT = -1;



DROP TABLE userDetails CASCADE;

CREATE TABLE userDetails
(
	id SERIAL,
	email VARCHAR(100),
	password VARCHAR(20),
	username VARCHAR (100),
	PRIMARY KEY (id)
	
);

---------------------------------------------
----POPULATING DATABASE FOR TESTING PURPOSES

INSERT INTO userDetails(email, password, username)
	VALUES ('pedo', 'pedo', 'PEDO');