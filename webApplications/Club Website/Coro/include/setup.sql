DROP TABLE myclub;

CREATE TABLE myclub 
(	name varchar(50), 
	email varchar(40),
	password char(40),
	reg_date datetime,
	mem_type ENUM('Broro', 'Freshrep', 'Visitor', 'Lower') );

-- Example insert values --	
INSERT INTO myclub VALUES (
	'Test Name', 
	'test@email.com', 
	'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 
	now(),
	'Visitor'
	);

--Select all members with a particular email and name --
SELECT * FROM myclub WHERE email = 'test@email.com' and name = 'Test Name';

--Update club member data for a specific member--
UPDATE myclub SET name ='Tester Name' WHERE email = 'test@email.com';

-- Delete a member --
DELETE FROM myclub WHERE name = 'Tester Name';