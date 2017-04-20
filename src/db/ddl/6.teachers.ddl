DROP TABLE IF EXISTS teachers CASCADE;

CREATE TABLE teachers(
  id SERIAL PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  subjects VARCHAR(1024) NOT NULL,
  experience INT NOT NULL,
  age INT NOT NULL,
  email VARCHAR(64) NOT NULL,
  cathedra_id INT REFERENCES cathedras(id) NOT NULL,

  CONSTRAINT teachers_constraint UNIQUE (name, cathedra_id)
);