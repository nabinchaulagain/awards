--liquibase formatted sql

--changeset nabinchaulagain:seed_roles:1 endDelimiter:/ rollbackEndDelimiter:/
DECLARE role_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO role_count FROM roles;
    IF role_count = 0 THEN
        INSERT INTO roles(id,name) VALUES (1,'ADMIN');
        INSERT INTO roles(id,name) VALUES (2,'REVIEWER');
    END IF;
END;
/

--rollback DELETE FROM roles;