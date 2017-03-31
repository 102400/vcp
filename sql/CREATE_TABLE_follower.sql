CREATE TABLE follower
(
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,  -- user.user_id
	follower_id INT NOT NULL,  -- user.user_id
	category_id INT NOT NULL,  -- category.category_id
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);