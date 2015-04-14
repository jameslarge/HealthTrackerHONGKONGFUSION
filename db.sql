DROP TABLE member CASCADE;
DROP TABLE physicalHealth CASCADE;
DROP TABLE weightProgress CASCADE;
DROP TABLE exercise CASCADE;
DROP TABLE exerciseProgress CASCADE;
DROP TABLE meal CASCADE;
DROP TABLE mealProgress CASCADE;
DROP TABLE goal CASCADE;
DROP TABLE member_goals CASCADE;
DROP TABLE foodItem CASCADE;


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


CREATE TABLE exercise
(
	id				SERIAL,
	name			VARCHAR(100),
	exerciseType		VARCHAR(100),
	calPerUnit		INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE exerciseProgress
(
	id				SERIAL,
	memberID 		INT REFERENCES member(id),
	exerciseDate		DATE,
	amount			INT,
	duration		INT,
	exerciseID		INT REFERENCES exercise(id),
	
	
	PRIMARY KEY (id)
);

CREATE TABLE foodItem
(
	id				SERIAL,
	name			VARCHAR(100),
	foodType		VARCHAR(100),
	calPerUnit		INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE  meal
(
	id				SERIAL,
	name			VARCHAR(100),
	calPerUnit		INT,
	
	PRIMARY KEY (id)
);


CREATE TABLE mealProgress
(
	id				SERIAL,
	memberID		INT REFERENCES member(id),
	mealID			INT REFERENCES meal(id),
	mealTime		INT,
	mealDate		DATE,
	amount			INT,
	
	PRIMARY KEY (id)
);


CREATE TABLE goal
(
	id				SERIAL,
	goalType		VARCHAR(100),
	goalDate		DATE,
	goalStart		DATE,
	goalDeadline	DATE,
	target			INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE member_goals
(
	id				SERIAL,
	memberID		INT REFERENCES member(id),
	goalID			INT REFERENCES goal(id),
	
	PRIMARY KEY (id)
);





	





---------------------------------------------
----POPULATING DATABASE FOR TESTING PURPOSES

INSERT INTO member(email, password, username, forename, surname)
	VALUES ('t_email', 't_pass', 't_user', 't_fname', 't_sname');
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (-1, 1);
INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (1, '2015-04-14', -1);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('t_name', 't_exercise', -1);
INSERT INTO exerciseProgress(memberID, exerciseDate, amount, duration, exerciseID)
	VALUES (1, '2015-04-14', -1, -1, 1);
INSERT INTO foodItem(name, foodType, calPerUnit)
	VALUES ('t_name', 't_ftype', -1);
INSERT INTO meal(name, calPerUnit)
	VALUES ('t_name', -1);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Pizza', 500);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Cereal', 200);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Burger', 400);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Sandwich', 300);
INSERT INTO mealProgress(memberID, mealID, mealDate, amount)
	VALUES (1, 1, '2015-04-14', -1);
INSERT INTO goal(goalType, goalDate, goalStart, goalDeadline, target)
	VALUES ('weight', '2015-04-14', '2015-04-14', '2015-04-14', -1);
INSERT INTO member_goals(memberID, goalID)
	VALUES (1, 1);
	
