CREATE TABLE user
(
	user_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(16) NOT NULL UNIQUE,
	email VARCHAR(128) UNIQUE,
	password CHAR(32) NOT NULL,
	nickname VARCHAR(64) NOT NULL DEFAULT 'default',
	following INT NOT NULL DEFAULT 0,
	followers INT NOT NULL DEFAULT 0,
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(user_id)
);