CREATE TABLE likes
(
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,  -- user.user_id
	picture_id INT NOT NULL,  -- picture.picture_id
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);