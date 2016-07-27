# CREATE DATABASE IF NOT EXISTS nerdvana;
# mysql -unerdvana -p -D nerdvana < init-nerdvana.sql

DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS AnswerDefinition;
DROP TABLE IF EXISTS DataType;

CREATE TABLE Question (
	id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE KEY
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE AnswerDefinition (
	id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	question_id INT(10) NOT NULL,
	name VARCHAR(100) NOT NULL,
	cost NUMERIC(19,4) NOT NULL,
	data_type_id INT(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE DataType (
	id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE KEY,
	description VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Question
(name)
VALUES
('What projects should we do this year?');

INSERT INTO DataType
(name, description)
VALUES
('date', 'What does this date tell you?'),
('number', 'What does this number tell you?'),
('rank', 'Select Low, Medium, or High.'),
('size', 'Select Small, Medium, or Large.'),
('text', 'Describe the text an answerer would provide.');
