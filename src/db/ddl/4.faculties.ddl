DROP TABLE IF EXISTS faculties CASCADE;

CREATE TABLE faculties(
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  university_id INT REFERENCES universities(id) ON DELETE CASCADE,

  CONSTRAINT faculties_constraint UNIQUE (name, university_id)
);