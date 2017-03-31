CREATE TABLE picture
(
	picture_id INT NOT NULL AUTO_INCREMENT,
	uuid CHAR(32) NOT NULL,
	size BIGINT NOT NULL,
	content_type VARCHAR(128) NOT NULL,
	suffix VARCHAR(32) NOT NULL,
	user_id INT NOT NULL,  -- user.user_id
	album_id INT NOT NULL,  -- album.album_id
	authorize INT NOT NULL DEFAULT 1,  -- private:1, public:2, protected:3
	original_name VARCHAR(256) NOT NULL,
	name VARCHAR(256) NOT NULL DEFAULT 'unknown',
	description VARCHAR(1024) NOT NULL DEFAULT '',
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(picture_id)
);