INSERT INTO participant
(id, name, email, password, role, created_at)
VALUES
    (1, 'Abhisek', 'abhisek@gmail.com', 'pas123', 'PARTICIPANT', CURRENT_DATE),
    (2, 'Rahul', 'rahul@gmail.com', 'rul12', 'PARTICIPANT', CURRENT_DATE),
    (3, 'Sneha', 'sneha@gmail.com', 'snea12', 'ORGANIZER', CURRENT_DATE),
    (4, 'Admin', 'admin@gmail.com', 'adin123', 'ADMIN', CURRENT_DATE);



INSERT INTO event
(id, title, description, start_date, end_date, mode, location)
VALUES
    (1, 'Hackathon 2026', 'National level hackathon',
     CURRENT_DATE, CURRENT_DATE + 2, 'ONLINE', 'Discord'),

    (2, 'Spring Boot Workshop', 'Backend development event',
     CURRENT_DATE, CURRENT_DATE + 1, 'OFFLINE', 'Kolkata');



INSERT INTO team
(id, team_name)
VALUES
    (1, 'Code Warriors'),
    (2, 'Bug Hunters');


