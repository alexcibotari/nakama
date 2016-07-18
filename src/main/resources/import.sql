--Account
INSERT INTO user (id, user_name, email, enabled, password_hash, created_by, created_date) VALUES ('1', 'admin', 'admin@admin', 1, '$2a$10$l/lHKoFTFdzfVyyZ9oIDPu3voNZZLu/9qi.8BhDMHRcaFmetHx/UO','system', '2013-09-29 22:00:00');
INSERT INTO user (id, user_name, email, enabled, password_hash, created_by, created_date) VALUES ('2', 'user', 'user@user', 1, '$2a$10$muOJKIPqChcNnFu8nduPJONfT3uSsTIoQRstlWFXYJ3c1Yln0kzt.','system','2013-09-29 22:00:00');
--Authority
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');
--Authorities
INSERT INTO authorities (user_id, authority_name) VALUES (1,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority_name) VALUES (2,'ROLE_USER');
--Projects
INSERT INTO project (id, pkey, name, description, lead_user_id, created_by, created_date) VALUES (1, 'TEST', 'Test', 'description', 1, 'system','2013-09-29 22:00:00');
INSERT INTO project (id, pkey, name, description, lead_user_id, created_by, created_date) VALUES (2, 'BEST', 'Best', 'best desc', 1, 'system','2013-09-29 22:00:00');
INSERT INTO project (id, pkey, name, description, lead_user_id, created_by, created_date) VALUES (3, 'BEST1', 'Best1', 'best desc1', 1, 'system','2013-09-29 22:00:00');
INSERT INTO project (id, pkey, name, description, lead_user_id, created_by, created_date) VALUES (4, 'BEST2', 'Best2', 'best desc2', 1, 'system','2013-09-29 22:00:00');
INSERT INTO project (id, pkey, name, description, lead_user_id, created_by, created_date) VALUES (5, 'BEST3', 'Best3', 'best desc3', 1, 'system','2013-09-29 22:00:00');
--Issues
INSERT INTO issue (id, id_in_project, project_id, summery, description, created_by, created_date) VALUES (1, 1, 1, 'summery1', 'description1', 'system','2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, created_by, created_date) VALUES (2, 2, 1, 'summery2', 'description2', 'system','2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, created_by, created_date) VALUES (3, 1, 2, 'summery3', 'description3', 'system','2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, created_by, created_date) VALUES (4, 2, 2, 'summery4', 'description4', 'system','2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, created_by, created_date) VALUES (5, 3, 2, 'summery5', 'description5', 'system','2013-09-29 22:00:00');
