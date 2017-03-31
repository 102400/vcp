CREATE TABLE message
(
	id INT NOT NULL AUTO_INCREMENT,
	sender_id INT NOT NULL,  -- user.user_id
	receiver_id INT NOT NULL,  -- user.user_id
	message VARCHAR(21000) NOT NULL,
	create_time DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);