--liquibase formatted sql

--changeset nabinchaulagain:seed_roles:1 endDelimiter:/ rollbackEndDelimiter:/
INSERT INTO roles(id, name) VALUES (1, 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles(id, name) VALUES (2, 'REVIEWER') ON CONFLICT DO NOTHING;
--rollback DELETE FROM roles WHERE name in ('ADMIN', 'REVIEWER');
