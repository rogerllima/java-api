CREATE TABLE schedules (
    id VARCHAR  PRIMARY KEY NOT NULL,
    idClient VARCHAR NOT NULL,
    attendanceHour VARCHAR NOT NULL,
    attendanceDate VARCHAR NOT NULL,
    confirmClient BOOLEAN
);

CREATE TABLE users (
    id VARCHAR  PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE clients (
    id VARCHAR  PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    phone VARCHAR NOT NULL
);