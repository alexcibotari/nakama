#Account
INSERT INTO user (id, version, user_name, enabled, password) VALUE ('1', '0', 'admin', 1, '$2a$10$l/lHKoFTFdzfVyyZ9oIDPu3voNZZLu/9qi.8BhDMHRcaFmetHx/UO');
INSERT INTO user (id, version, user_name, enabled, password) VALUE ('2', '0', 'user', 0, '$2a$10$muOJKIPqChcNnFu8nduPJONfT3uSsTIoQRstlWFXYJ3c1Yln0kzt.');
#Authority
INSERT INTO authority (id, name, version) VALUES (1,'ROLE_ADMIN',0);
INSERT INTO authority (id, name, version) VALUES (2,'ROLE_USER',0);
#Authorities
INSERT INTO authorities (user_id, authority_id) VALUES (1,1);
INSERT INTO authorities (user_id, authority_id) VALUES (2,2);
