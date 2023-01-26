DROP TABLE IF EXISTS USERS ;

CREATE TABLE users (
twitter_user_id bigint NOT NULL,
twitter_screen_name varchar(255) NOT NULL,
PRIMARY KEY (twitter_user_id)
);

DROP TABLE IF EXISTS VIDEOS ;

CREATE TABLE videos (
id serial NOT NULL,
fk_twitter_user_id bigint NOT NULL,
video_link varchar(255) NOT NULL,
created_at bigint NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (fk_twitter_user_id) REFERENCES users (twitter_user_id)
);