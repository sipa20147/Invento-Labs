CREATE DATABASE IF NOT EXISTS db_test;

USE db_test;

-- create tables for logins.csv
CREATE TABLE IF NOT EXISTS application (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS job (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS departament (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  account_name VARCHAR(50) NOT NULL,
  is_active BOOLEAN NOT NULL,
  app_id INT UNSIGNED NOT NULL,
  job_id INT UNSIGNED NOT NULL,
  dep_id INT UNSIGNED NOT NULL,
  FOREIGN KEY (app_id) REFERENCES application(id),
  FOREIGN KEY (job_id) REFERENCES job(id),
  FOREIGN KEY (dep_id) REFERENCES departament(id)
);

-- create tables for postings.csv
CREATE TABLE IF NOT EXISTS material (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS currency (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  name VARCHAR(10) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS measurement (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS supply (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  mat_doc VARCHAR(50) NOT NULL,
  doc_date DATE NOT NULL,
  pstng_date DATE NOT NULL,
  employee_id INT UNSIGNED NULL,
  is_auth_supply BOOLEAN NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS supply_item (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  supply_id INT UNSIGNED NOT NULL,
  item INT NOT NULL,
  material_id INT UNSIGNED NOT NULL,
  quantity INT NOT NULL,
  measurement_id INT UNSIGNED NOT NULL,
  amount DECIMAL(10,2)  NOT NULL,
  currency_id INT UNSIGNED NOT NULL,
  FOREIGN KEY (supply_id) REFERENCES supply(id),
  FOREIGN KEY (material_id) REFERENCES material(id),
  FOREIGN KEY (measurement_id) REFERENCES measurement(id),
  FOREIGN KEY (currency_id) REFERENCES currency(id)
);