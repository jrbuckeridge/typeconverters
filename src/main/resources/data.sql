DROP TABLE IF EXISTS lab_procedure;

CREATE TABLE lab_procedure (
  `id` BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(200) NOT NULL,
  `countries` VARCHAR(200)
);

DROP TABLE IF EXISTS lab_procedure2;

CREATE TABLE lab_procedure2 (
  `id` BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(200) NOT NULL,
  `countries` VARCHAR(200)
);

