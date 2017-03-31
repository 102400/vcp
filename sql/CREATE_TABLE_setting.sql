CREATE TABLE setting
(
	user_id INT NOT NULL UNIQUE,  -- user.user_id
	default_album INT NOT NULL,  -- album.album_id
	default_follower_category INT NOT NULL,  -- follower_category.id
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(user_id)
);