--liquibase formatted sql

--changeset nabinchaulagain:units:1 endDelimiter:/ rollbackEndDelimiter:/
CREATE SEQUENCE IF NOT EXISTS unit_id_sequence MINVALUE 1 MAXVALUE 99999999999999 START WITH 1 INCREMENT BY 1 CACHE 20;/
--rollback DROP SEQUENCE unit_id_sequence;

--changeset nabinchaulagain:units:2 endDelimiter:/ rollbackEndDelimiter:/
CREATE TABLE IF NOT EXISTS units(
        id BIGINT,
        name VARCHAR(255),
        country VARCHAR(255),
        branch VARCHAR(255),
        established_date TIMESTAMP(6),
        CONSTRAINT unit_pk PRIMARY KEY (id)
);
/
--rollback DROP TABLE units;
