----------------------------------
-- TEAM 1: Greenfield United
----------------------------------
-- Insert the team
INSERT INTO team (name) VALUES ('Greenfield United');

-- Insert the coach's base User info
--pass: coach1
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path)
VALUES (
           'coach@squadmate.com',
           '$2a$12$Xgt.i4g7hiChZa.ypHFS1OF5ligmSTASEeCPRhuKZ7T9oNJIJgwqG',
           'John',
           'Doe',
           '1980-01-01',
           NULL
       );
--pass: coach2
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path)
VALUES (
           'coach2@squadmate.com',
           '$2a$12$2I7fvX54yJLOfcbjdf92VOf8L.J3WPDlPK0i4N5oonXivAWlGOJ3q',
           'Kaloyan',
           'Ivanov',
           '1980-12-02',
           NULL
       );

INSERT INTO coach (id, team_id) VALUES (1,1);
UPDATE team SET coach_id = 1 WHERE id = 1;

INSERT INTO coach (id) VALUES (2);

-- Players (pass: player1–player16)
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path) VALUES
-- Starting XI
('player1@squadmate.com', '$2a$12$kavhSzfuNWZcDJuL0bvy3.1e08XyAlabMR/nOMcwipzr7kbXLKLgC', 'Alice', 'Smith', '2000-05-10', NULL),
('player2@squadmate.com', '$2a$12$MkHK3w9nPqTWq6xldXGHN.Ta0pj7OqKAPuOCFyls11x16mcTsXMTy', 'Bob', 'Jones', '1999-08-20', NULL),
('player3@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Charlie', 'Brown', '2001-03-15', NULL),
('player4@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Daniel', 'Clark', '1998-07-22', NULL),
('player5@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Ethan', 'White', '2002-11-11', NULL),
('player6@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Finn', 'Black', '2001-04-09', NULL),
('player7@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'George', 'Stone', '1997-09-14', NULL),
('player8@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Harry', 'Frost', '2003-01-25', NULL),
('player9@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Ivan', 'Knight', '1998-10-17', NULL),
('player10@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Jack', 'Moore', '2000-12-12', NULL),
('player11@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Kevin', 'Ford', '1996-03-05', NULL),

-- Substitutes
('player12@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Leo', 'Woods', '1999-06-21', NULL),
('player13@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Mason', 'Shaw', '2001-08-30', NULL),
('player14@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Nathan', 'Hill', '2002-02-14', NULL),
('player15@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Oscar', 'Lane', '1997-07-03', NULL),
('player16@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Paul', 'Grant', '2000-09-09', NULL);

-- Player positions and assignments
INSERT INTO player (id, jersey_number, position, is_starter, team_id) VALUES
-- Starting XI
(2, 1, 'GOALKEEPER', TRUE, 1),
(3, 2, 'LEFT_BACK', TRUE, 1),
(4, 3, 'CENTER_BACK', TRUE, 1),
(5, 4, 'CENTER_BACK', TRUE, 1),
(6, 5, 'RIGHT_BACK', TRUE, 1),
(7, 6, 'DEFENSIVE_MIDFIELDER', TRUE, 1),
(8, 7, 'CENTRAL_MIDFIELDER', TRUE, 1),
(9, 8, 'ATTACKING_MIDFIELDER', TRUE, 1),
(10, 9, 'LEFT_WINGER', TRUE, 1),
(11, 10, 'RIGHT_WINGER', TRUE, 1),
(12, 11, 'STRIKER', TRUE, 1),

-- Substitutes
(13, 12, 'GOALKEEPER', FALSE, 1),
(14, 13, 'CENTER_BACK', FALSE, 1),
(15, 14, 'CENTRAL_MIDFIELDER', FALSE, 1),
(16, 15, 'LEFT_WINGER', FALSE, 1);

INSERT INTO player (id, is_starter, jersey_number, position) VALUES
    (17, FALSE, 1,'GOALKEEPER');

----------------------------------
-- ADDITIONAL TEAMS + COACHES
----------------------------------

-- Insert new teams
INSERT INTO team (name) VALUES ('Bluefield Rovers'); -- id=2
INSERT INTO team (name) VALUES ('Redstone FC');      -- id=3
INSERT INTO team (name) VALUES ('Silverlake City');  -- id=4

-- Coaches base user info
-- pass: coach3
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path)
VALUES ('coach3@squadmate.com',
        '$2a$12$AcHkXI6p5rDD2nbLgZk.4emjlNYTNXMg17a7XzT1V3iwOaaZIVF4G',
        'Michael', 'Johnson', '1975-06-15', NULL);

-- pass: coach4
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path)
VALUES ('coach4@squadmate.com',
        '$2a$12$oOZbdaIND6hlQzkz5OJmDOKFHQGb5uhCzZ65Ktd8mwqpyuoSCGX8K',
        'David', 'Peterson', '1982-09-30', NULL);

-- pass: coach5
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path)
VALUES ('coach5@squadmate.com',
        '$2a$12$Bglj0N3AoC8xPEEXczZ8/OyZ9O/tfKRuqoOny9AZO84DcS1cPYwYy',
        'Stefan', 'Mihaylov', '1988-04-12', NULL);

-- Link coaches to teams
INSERT INTO coach (id, team_id) VALUES (18, 2);
UPDATE team SET coach_id = 18 WHERE id = 2;

INSERT INTO coach (id, team_id) VALUES (19, 3);
UPDATE team SET coach_id = 19 WHERE id = 3;

INSERT INTO coach (id, team_id) VALUES (20, 4);
UPDATE team SET coach_id = 20 WHERE id = 4;

----------------------------------
-- PLAYERS FOR NEW TEAMS
----------------------------------

-- Bluefield Rovers players (team 2)
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path) VALUES
                                                                                                          ('blue1@squadmate.com', '$2a$12$5cU78BuDY42R3RRSo9FYeu63SILVPNJmtI68/HpUTDoY4GiLWfS0K', 'Liam', 'Walker', '2000-03-10', NULL),
                                                                                                          ('blue2@squadmate.com', '$2a$12$sBvabLSk71m4E9YhomPGruZnpLqXB/xB4BxR/QF2HMqLE9nNrmYSq', 'Noah', 'Harris', '1999-07-22', NULL);

INSERT INTO player (id, jersey_number, position, is_starter, team_id) VALUES
                                                                          (21, 1, 'GOALKEEPER', TRUE, 2),
                                                                          (22, 9, 'STRIKER', TRUE, 2);

-- Redstone FC players (team 3)
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path) VALUES
                                                                                                          ('red1@squadmate.com', '$2a$12$8J7ZbYHpoqsQvc7RRvKLlexPxUhUTshbMt.oTI8fqseHTi9TJkZDO', 'Oliver', 'Baker', '1998-05-14', NULL),
                                                                                                          ('red2@squadmate.com', '$2a$12$4EyazfwdIfaWXpHK9yir2Of.0ZtFaQkZAg4qjnjRwo2hJ8N9mq85y', 'James', 'Young', '2001-12-01', NULL);

INSERT INTO player (id, jersey_number, position, is_starter, team_id) VALUES
                                                                          (23, 1, 'GOALKEEPER', TRUE, 3),
                                                                          (24, 10, 'STRIKER', TRUE, 3);

-- Silverlake City players (team 4)
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path) VALUES
                                                                                                          ('silver1@squadmate.com', '$2a$12$tCS8rDBYTbEwtfnlZUZsI.kJevALp9K8uzfUUoeE1OzhY4fmSecRi', 'William', 'Adams', '1997-11-23', NULL),
                                                                                                          ('silver2@squadmate.com', '$2a$12$OKmqtKPxYWHFCsWNaLXHwuaEJr9hFRdtp7RMa5UFYdYN/gSRLe8oO', 'Benjamin', 'Turner', '1999-06-05', NULL);

INSERT INTO player (id, jersey_number, position, is_starter, team_id) VALUES
                                                                          (25, 1, 'GOALKEEPER', TRUE, 4),
                                                                          (26, 11, 'STRIKER', TRUE, 4);

-- Matches
INSERT INTO match (match_date, location) VALUES
                                                 ('2025-09-20 15:00:00', 'Stadium A'),
                                                 ('2024-09-05 18:00:00', 'Stadium B'),
                                                 ('2024-09-15 20:00:00', 'Stadium C');

-- Team_Match (with 0 defaults for all stats)
INSERT INTO team_match (team_id, match_id, team_name, goals, red_cards, yellow_cards, corners, ball_possession, total_shots, shots_on_target, fouls, line_up_notes, total_passes)
VALUES
    (1, 1, 'Greenfield United',0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (2, 1, 'Bluefield Rovers',0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (3, 2, 'Redstone FC',3, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (1, 2,'Greenfield United' ,5, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (4, 3,'Silverlake City' ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    (2, 3,'Bluefield Rovers' ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
