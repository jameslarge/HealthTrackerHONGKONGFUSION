DROP TABLE member CASCADE;
DROP TABLE physicalHealth CASCADE;
DROP TABLE weightProgress CASCADE;

CREATE TABLE member
(
	id 				SERIAL,
	email 			VARCHAR(100),
	password		VARCHAR(20),
	username 		VARCHAR (100),
	name			VARCHAR (100),
	surname			VARCHAR (100),
	
	
	
	PRIMARY KEY (id)
	
);



CREATE TABLE physicalHealth
(
	id 				SERIAL,
	height 			INT,
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

INSERT INTO member(email, password, username)
	VALUES ('pedo', 'pedo', 'PEDO');