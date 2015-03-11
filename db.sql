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