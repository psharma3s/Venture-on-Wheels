START TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users 
(
	user_id serial,
	username varchar(64) not null unique,
	email varchar(64) not null,

	CONSTRAINT pk_users PRIMARY KEY (user_id)
);
COMMIT;