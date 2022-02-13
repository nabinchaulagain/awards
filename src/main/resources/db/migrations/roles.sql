--liquibase formatted sql

--changeset nabinchaulagain:roles:1 endDelimiter:/ rollbackEndDelimiter:/
CREATE SEQUENCE IF NOT EXISTS role_id_sequence MINVALUE 1 MAXVALUE 99999999999999 START WITH 1 INCREMENT BY 1 CACHE 20;/
--rollback DROP SEQUENCE role_id_sequence;

--changeset nabinchaulagain:roles:2 endDelimiter:/ rollbackEndDelimiter:/
CREATE TABLE IF NOT EXISTS roles(
            id BIGINT,
            name VARCHAR(255),
            CONSTRAINT ROLES_PK PRIMARY KEY (id)
)
;
/
--rollback DROP TABLE roles;
