﻿DROP TABLE member CASCADE;
DROP TABLE physicalHealth CASCADE;
DROP TABLE weightProgress CASCADE;

CREATE TABLE member
(
	id 				SERIAL,
	email 			VARCHAR(100),
	password		VARCHAR(20),
	username 		VARCHAR (100),
	forename		VARCHAR (100),
	surname			VARCHAR (100),
	
	PRIMARY KEY (id)
	
);



CREATE TABLE physicalHealth
(
	ID 				SERIAL,
	heightcm 		INT,
	memberID		INT REFERENCES member(id),
	PRIMARY KEY (id)


);

CREATE TABLE weightProgress
(
	id				 SERIAL,
	physicalHealthID INT	REFERENCES physicalHealth(id),
	weightDate 		 DATE,
	weight			 INT,
	
	PRIMARY KEY (id)
);



---------------------------------------------
----POPULATING DATABASE FOR TESTING PURPOSES

INSERT INTO member(email, password, username, forename, surname)
	VALUES ('t_email', 't_pass', 't_user', 't_fname', 't_sname');