DROP TABLE IF EXISTS schedule_items CASCADE;

CREATE TABLE schedule_items(
  id SERIAL PRIMARY KEY,
  day INT NOT NULL,
  hour INT NOT NULL,
  teacher_id INT REFERENCES teachers(id) ON DELETE CASCADE,
  subject_id INT REFERENCES subjects(id) ON DELETE CASCADE,

  CONSTRAINT schedule_items_constraint UNIQUE (day, hour, teacher_id)
);