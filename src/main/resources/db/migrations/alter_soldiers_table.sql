--liquibase formatted sql

--changeset nabinchaulagain:alter_soldiers_table:1  endDelimiter:/ rollbackEndDelimiter:/

BEGIN
    EXECUTE IMMEDIATE 'ALTER TABLE soldiers ADD service_start_date TIMESTAMP(6) ADD service_end_date TIMESTAMP(6)';
EXCEPTION
    WHEN OTHERS THEN
        IF sqlcode = -1430 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END;
/

--rollback ALTER TABLE DROP COLUMN service_start_date;
--rollback ALTER TABLE DROP COLUMN service_end_date;

--changeset nabinchaulagain:alter_soldiers_table:2  endDelimiter:/ rollbackEndDelimiter:/
BEGIN
    EXECUTE IMMEDIATE 'ALTER TABLE soldiers ADD rank VARCHAR2(255 CHAR)';
EXCEPTION
    WHEN OTHERS THEN
        IF sqlcode = -1430 THEN
            NULL;
        ELSE
            RAISE;
        END IF;
END;
/

--rollback ALTER TABLE soldiers DROP COLUMN rank;
