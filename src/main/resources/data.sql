DROP TABLE IF EXISTS features;

CREATE TABLE features (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  enable int DEFAULT 1
);

INSERT INTO features (name, email, enable) VALUES
  ('feature1', 'email@email.com', 1);