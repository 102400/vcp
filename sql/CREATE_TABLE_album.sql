CREATE TABLE album
(
	album_id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,  -- user.user_id
	name VARCHAR(128) NOT NULL DEFAULT 'default',
	description VARCHAR(1024) NOT NULL DEFAULT '',
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(album_id)
);