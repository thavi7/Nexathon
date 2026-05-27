INSERT INTO "Participant"
(id, name, email, password, role, created_at)
VALUES
    (1, 'Abhisek', 'abhisek@gmail.com', 'pass123', 'USER', CURRENT_DATE),
    (2, 'Rahul', 'rahul@gmail.com', 'rahul12', 'USER', CURRENT_DATE),
    (3, 'Sneha', 'sneha@gmail.com', 'sneha12', 'USER', CURRENT_DATE),
    (4, 'Admin', 'admin@gmail.com', 'admin123', 'ADMIN', CURRENT_DATE);



INSERT INTO event
(id, title, description, start_date, end_date, mode, location)
VALUES
    (1, 'Hackathon 2026', 'National level hackathon',
     CURRENT_DATE, CURRENT_DATE + 2, 'ONLINE', 'Discord'),

    (2, 'Spring Boot Workshop', 'Backend development event',
     CURRENT_DATE, CURRENT_DATE + 1, 'OFFLINE', 'Kolkata');



INSERT INTO team
(id, team_name, event_id)
VALUES
    (1, 'Code Warriors', 1),
    (2, 'Bug Hunters', 1);


