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

INSERT INTO coach (id, team_id) VALUES (1,1);
UPDATE team SET coach_id = 1 WHERE id = 1;

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
(16, 15, 'LEFT_WINGER', FALSE, 1),
(17, 16, 'STRIKER', FALSE, 1);
