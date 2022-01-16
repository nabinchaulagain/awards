--liquibase formatted sql
--changeset nabinchaulagain:create_unit_id_sequence:1

BEGIN
    EXECUTE IMMEDIATE '
    CREATE SEQUENCE unit_id_sequence MINVALUE 1 MAXVALUE 99999999999999 START WITH 1 INCREMENT BY 1 CACHE 20 NOORDER NOCYCLE NOKEEP GLOBAL
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

--rollback DROP SEQUENCE unit_id_sequence;
