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

INSERT INTO coach (id, team_id)
VALUES (1,1);

-- pass: player1, player2, player3
INSERT INTO application_user (email, password, first_name, last_name, birth_date, profile_image_path)
VALUES
    ('player1@squadmate.com', '$2a$12$kavhSzfuNWZcDJuL0bvy3.1e08XyAlabMR/nOMcwipzr7kbXLKLgC', 'Alice', 'Smith', '2000-05-10', NULL),
    ('player2@squadmate.com', '$2a$12$MkHK3w9nPqTWq6xldXGHN.Ta0pj7OqKAPuOCFyls11x16mcTsXMTy', 'Bob', 'Jones', '1999-08-20', NULL),
    ('player3@squadmate.com', '$2a$12$uImVMrZ3Y/lTgGPLFlVxzum58d4iEXVqzl9sbCi4ypv2Oukql3Nh.', 'Charlie', 'Brown', '2001-03-15', NULL);


INSERT INTO player (id, jersey_number, position, team_id)
VALUES
    (2, 8, 'DEFENSIVE_MIDFIELDER', 1),
    (3, 10, 'CENTRAL_MIDFIELDER', 1),
    (4, 5, 'ATTACKING_MIDFIELDER', 1);