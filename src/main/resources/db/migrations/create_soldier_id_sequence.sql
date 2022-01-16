--liquibase formatted sql

--changeset nabinchaulagain:create_soldier_id_sequence:1
BEGIN
    EXECUTE IMMEDIATE '
    CREATE sequence soldier_id_sequence MINVALUE 1 MAXVALUE 99999999999999 START WITH 1 INCREMENT BY 1 CACHE 20 NOORDER NOCYCLE NOKEEP  NOSCALE  GLOBAL
    ';
EXCEPTION
    WHEN OTHERS THEN
        IF sqlcode = -955 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END ;
/

--rollback DROP sequence soldier_id_sequence;
