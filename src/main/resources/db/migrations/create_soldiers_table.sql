--liquibase formatted sql

--changeset nabinchaulagain:create_soldiers_table:1 endDelimiter:/ rollbackEndDelimeter:/
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE soldiers(
        id NUMBER,
        name VARCHAR2(255 CHAR),
        date_of_birth TIMESTAMP(6),
        date_of_death TIMESTAMP(6),
        unit_id NUMBER,
        birthplace VARCHAR2(255),
        deathplace VARCHAR2(255),
        CONSTRAINT soldier_pk PRIMARY KEY (id),
        CONSTRAINT unit_id_fk FOREIGN KEY(unit_id) REFERENCES units (id)
    )
    ';
EXCEPTION
    WHEN OTHERS THEN
        IF sqlcode = -955 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END;
/

--rollback DROP TABLE soldiers;
