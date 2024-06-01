CREATE TABLE users (
  id varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE songs (
  id varchar(255) NOT NULL,
  album varchar(255) DEFAULT NULL,
  author varchar(255) NOT NULL,
  genre varchar(255) DEFAULT NULL,
  is_public bit(1) NOT NULL,
  size bigint NOT NULL,
  title varchar(255) NOT NULL,
  created_by_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_tittle (title),
  KEY user_creator_id (created_by_id),
  CONSTRAINT user_creator_id FOREIGN KEY (created_by_id) REFERENCES users (id)
);