CREATE TABLE protected_picture
(
	id INT NOT NULL AUTO_INCREMENT,
	picture_id INT NOT NULL,  -- picture.picture_id
	category_id INT NOT NULL,  -- follower_category.category_id
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);