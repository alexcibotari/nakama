#Account
INSERT INTO user (id, version, user_name, email, enabled, password) VALUE ('1', '0', 'admin', 'admin@admin', 1, '$2a$10$l/lHKoFTFdzfVyyZ9oIDPu3voNZZLu/9qi.8BhDMHRcaFmetHx/UO');
INSERT INTO user (id, version, user_name, email, enabled, password) VALUE ('2', '0', 'user', 'user@user', 0, '$2a$10$muOJKIPqChcNnFu8nduPJONfT3uSsTIoQRstlWFXYJ3c1Yln0kzt.');
#Authority
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_USER');
#Authorities
INSERT INTO authorities (user_id, authority_name) VALUES (1,'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority_name) VALUES (2,'ROLE_USER');
