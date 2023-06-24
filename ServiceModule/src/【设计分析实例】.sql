DROP DATABASE IF EXISTS mao;
CREATE DATABASE mao CHARACTER SET UTF8;
USE mao;
CREATE TABLE member(
    mid             VARCHAR(20),
    name            VARCHAR(20),
    age             int(3),
    sex             VARCHAR(10),
    email           VARCHAR(50),
    birthday        DATE,
    note            BLOB,
    CONSTRAINT pk_mid PRIMARY KEY (mid)
)engine = innodb;