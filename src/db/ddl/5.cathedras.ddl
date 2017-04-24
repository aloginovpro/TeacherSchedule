DROP TABLE IF EXISTS cathedras CASCADE;

CREATE TABLE cathedras(
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  faculty_id INT REFERENCES faculties(id)  ON DELETE CASCADE,

  CONSTRAINT cathedras_constraint UNIQUE (name, faculty_id)
);