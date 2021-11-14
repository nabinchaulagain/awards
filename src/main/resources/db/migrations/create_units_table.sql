--liquibase formatted sql

--changeset nabinchaulagain:create_unit_table:1

BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE units(
        id NUMBER,
        name VARCHAR2(255 CHAR),
        country VARCHAR2(255 CHAR),
        branch VARCHAR2(255 CHAR),
        established_date TIMESTAMP(6),
        CONSTRAINT unit_pk PRIMARY KEY (id)
    )';
EXCEPTION
    WHEN OTHERS THEN
        IF sqlcode = -955 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END;
/

--rollback DROP TABLE units;
