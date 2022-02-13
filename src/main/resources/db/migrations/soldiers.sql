--liquibase formatted sql

--changeset nabinchaulagain:soldiers:1 endDelimiter:/ rollbackEndDelimiter:/
CREATE SEQUENCE IF NOT EXISTS soldier_id_sequence MINVALUE 1 MAXVALUE 99999999999999 START WITH 1 INCREMENT BY 1 CACHE 20;/
--rollback DROP SEQUENCE soldier_id_sequence;

--changeset nabinchaulagain:soldiers:2 endDelimiter:/ rollbackEndDelimeter:/
CREATE TABLE IF NOT EXISTS soldiers
(
    id BIGINT,
    name VARCHAR(255),
    date_of_birth TIMESTAMP(6),
    date_of_death TIMESTAMP(6),
    unit_id BIGINT, 
    birthplace VARCHAR(255),
    deathplace VARCHAR(255),
    rank VARCHAR(255),
    description TEXT,
    picture VARCHAR(255),
    CONSTRAINT soldier_pk PRIMARY KEY (id),
    CONSTRAINT unit_id_fk FOREIGN KEY(unit_id) REFERENCES units (id)
)
;
/
--rollback DROP TABLE soldiers;


--changeset nabinchaulagain:soldiers:3 endDelimiter:/ rollbackEndDelimeter:/
ALTER TABLE soldiers
    ADD COLUMN service_start_date TIMESTAMP(6),
    ADD COLUMN service_end_date TIMESTAMP(6);
    /
--rollback ALTER TABLE soldiers DROP (service_start_date, service_end_date);
