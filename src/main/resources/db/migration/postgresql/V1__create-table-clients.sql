CREATE TABLE clients (
    id VARCHAR  PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    attendance_hour VARCHAR NOT NULL,
    attendance_date VARCHAR NOT NULL,
    confirm_client BOOLEAN
);

CREATE TABLE users (
    id VARCHAR  PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);