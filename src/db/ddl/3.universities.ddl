DROP TABLE IF EXISTS universities CASCADE;

CREATE TABLE universities(
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  address VARCHAR(64) NOT NULL,
  city_id INT REFERENCES cities(id) ON DELETE CASCADE,

  CONSTRAINT universities_constraint UNIQUE (name, city_id)
);