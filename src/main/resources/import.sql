--Account
INSERT INTO user (id, user_name, email, enabled, password_hash, created_by, created_date) VALUES (1, 'admin', 'admin@admin', 1, '$2a$10$l/lHKoFTFdzfVyyZ9oIDPu3voNZZLu/9qi.8BhDMHRcaFmetHx/UO','system', '2013-09-29 22:00:00');
INSERT INTO user (id, user_name, email, enabled, password_hash, created_by, created_date) VALUES (2, 'user', 'user@user', 1, '$2a$10$muOJKIPqChcNnFu8nduPJONfT3uSsTIoQRstlWFXYJ3c1Yln0kzt.','system', '2013-09-29 22:00:00');
--Authority
INSERT INTO authority (id, pname) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, pname) VALUES (2, 'ROLE_USER');
--Authorities
INSERT INTO authorities (user_id, authority_id) VALUES (1, 1);
INSERT INTO authorities (user_id, authority_id) VALUES (2, 2);
--IssuePriority
INSERT INTO issue_priority(id, pname, description, created_by, created_date) VALUES (1, 'Highest', 'This problem will block progress.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_priority(id, pname, description, created_by, created_date) VALUES (2, 'High', 'Serious problem that could block progress.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_priority(id, pname, description, created_by, created_date) VALUES (3, 'Medium', 'Has the potential to affect progress.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_priority(id, pname, description, created_by, created_date) VALUES (4, 'Low', 'Minor problem or easily worked around.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_priority(id, pname, description, created_by, created_date) VALUES (5, 'Lowest', 'Trivial problem with little or no impact on progress.', 'system', '2013-09-29 22:00:00');
--IssueStatus
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (1, 'Open', 'The issue is open and ready for the assignee to start work on it.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (2, 'In Progress', 'This issue is being actively worked on at the moment by the assignee.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (3, 'Reopened', 'This issue was once resolved, but the resolution was deemed incorrect. From here issues are either marked assigned or resolved.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (4, 'Resolved', 'A resolution has been taken, and it is awaiting verification by reporter. From here issues are either reopened, or are closed.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (5, 'Closed', 'The issue is considered finished, the resolution is correct. Issues which are closed can be reopened.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (6, 'To Do', '', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_status(id, pname, description, created_by, created_date) VALUES (7, 'Done', '', 'system', '2013-09-29 22:00:00');
--IssueType
INSERT INTO issue_type(id, pname, description, created_by, created_date) VALUES (1, 'Bug', 'A problem which impairs or prevents the functions of the product.', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_type(id, pname, description, created_by, created_date) VALUES (2, 'Epic', '', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_type(id, pname, description, created_by, created_date) VALUES (3, 'Story', '', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_type(id, pname, description, created_by, created_date) VALUES (4, 'Task', 'A task that needs to be done.', 'system', '2013-09-29 22:00:00');
--Projects
INSERT INTO project (id, pkey, pname, description, deleted, lead_user_id, created_by, created_date) VALUES (1, 'TEST', 'Test', 'description', false, 1, 'system', '2013-09-29 22:00:00');
INSERT INTO project (id, pkey, pname, description, deleted, lead_user_id, created_by, created_date) VALUES (2, 'BEST', 'Best', 'best desc', false, 1, 'system', '2013-09-29 22:00:00');
INSERT INTO project (id, pkey, pname, description, deleted, lead_user_id, created_by, created_date) VALUES (3, 'BEST1', 'Best1', 'best desc1', false, 1, 'system', '2013-09-29 22:00:00');
INSERT INTO project (id, pkey, pname, description, deleted, lead_user_id, created_by, created_date) VALUES (4, 'BEST2', 'Best2', 'best desc2', false, 1, 'system', '2013-09-29 22:00:00');
INSERT INTO project (id, pkey, pname, description, deleted, lead_user_id, created_by, created_date) VALUES (5, 'BEST3', 'Best3', 'best desc3', true, 1, 'system', '2013-09-29 22:00:00');
--Issues
INSERT INTO issue (id, id_in_project, project_id, summery, description, priority_id, status_id, type_id, deleted, created_by, created_date) VALUES (1, 1, 1, 'summery1', 'description1', 1, 1, 1, false, 'system', '2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, priority_id, status_id, type_id, deleted, created_by, created_date) VALUES (2, 2, 1, 'summery2', 'description2', 1, 1, 1, false, 'system', '2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, priority_id, status_id, type_id, deleted, created_by, created_date) VALUES (3, 1, 2, 'summery3', 'description3', 1, 1, 1, false, 'system', '2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, priority_id, status_id, type_id, deleted, created_by, created_date) VALUES (4, 2, 2, 'summery4', 'description4', 1, 1, 1, false, 'system', '2013-09-29 22:00:00');
INSERT INTO issue (id, id_in_project, project_id, summery, description, priority_id, status_id, type_id, deleted, created_by, created_date) VALUES (5, 3, 2, 'summery5', 'description5', 1, 1, 1, false, 'system', '2013-09-29 22:00:00');
--Issues Comments
INSERT INTO issue_comment (id, id_in_issue, issue_id, content, created_by, created_date) VALUES (1, 1, 1, 'comment 1', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_comment (id, id_in_issue, issue_id, content, created_by, created_date) VALUES (2, 2, 1, 'comment 2', 'system', '2013-09-29 22:00:30');
INSERT INTO issue_comment (id, id_in_issue, issue_id, content, created_by, created_date) VALUES (3, 3, 1, 'comment 3', 'system', '2013-09-29 22:00:45');
INSERT INTO issue_comment (id, id_in_issue, issue_id, content, created_by, created_date) VALUES (4, 1, 2, 'comment 4', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_comment (id, id_in_issue, issue_id, content, created_by, created_date) VALUES (5, 2, 2, 'comment 5', 'system', '2013-09-29 22:00:30');
INSERT INTO issue_comment (id, id_in_issue, issue_id, content, created_by, created_date) VALUES (6, 1, 3, 'comment 6', 'system', '2013-09-29 22:00:00');
--Issues WorkLog
INSERT INTO issue_work_log (id, id_in_issue, issue_id, time_worked, content, start_date, created_by, created_date) VALUES (1, 1, 1, 20, 'worklog 1', '2013-09-29 22:00:00', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_work_log (id, id_in_issue, issue_id, time_worked, content, start_date, created_by, created_date) VALUES (2, 2, 1, 30, 'worklog 2', '2013-09-29 22:00:00', 'system', '2013-09-29 22:00:30');
INSERT INTO issue_work_log (id, id_in_issue, issue_id, time_worked, content, start_date, created_by, created_date) VALUES (3, 3, 1, 40, 'worklog 3', '2013-09-29 22:00:00', 'system', '2013-09-29 22:00:45');
INSERT INTO issue_work_log (id, id_in_issue, issue_id, time_worked, content, start_date, created_by, created_date) VALUES (4, 1, 2, 5, 'worklog 4', '2013-09-29 22:00:00', 'system', '2013-09-29 22:00:00');
INSERT INTO issue_work_log (id, id_in_issue, issue_id, time_worked, content, start_date, created_by, created_date) VALUES (5, 2, 2, 15, 'worklog 5', '2013-09-29 22:00:00', 'system', '2013-09-29 22:00:30');
INSERT INTO issue_work_log (id, id_in_issue, issue_id, time_worked, content, start_date, created_by, created_date) VALUES (6, 1, 3, 90, 'worklog 6', '2013-09-29 22:00:00', 'system', '2013-09-29 22:00:00');
