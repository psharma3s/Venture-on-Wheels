START TRANSACTION;

DROP TABLE IF EXISTS trips CASCADE;

CREATE TABLE trips (

	trip_id serial,
	user_id integer not null,
	start_location varchar (300) not null,
	destination varchar (300) not null,
	start_date date not null,
	end_date date not null,

	CONSTRAINT pk_trips PRIMARY KEY (trip_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
COMMIT;