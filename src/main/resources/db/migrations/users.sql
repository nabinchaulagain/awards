--liquibase formatted sql

--changeset nabinchaulagain:users:1 endDelimiter:/ rollbackEndDelimiter:/
CREATE SEQUENCE IF NOT EXISTS user_id_sequence MINVALUE 1 MAXVALUE 99999999999999 START WITH 1 INCREMENT BY 1 CACHE 20;/
--rollback DROP SEQUENCE user_id_sequence;

--changeset nabinchaulagain:users:2 endDelimiter:/ rollbackEndDelimiter:/
CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    CONSTRAINT user_id_pk PRIMARY KEY (id)
);
/
--rollback DROP TABLE users;
