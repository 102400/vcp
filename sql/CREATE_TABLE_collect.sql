CREATE TABLE collect
(
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,  -- user.user_id
	album_id INT NOT NULL,  -- album.album_id
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);