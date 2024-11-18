-- Insert films
INSERT INTO films (title, director, release_date) VALUES ('A New Hope', 'George Lucas', '1977-05-25');
INSERT INTO films (title, director, release_date) VALUES ('The Empire Strikes Back', 'Irvin Kershner', '1980-05-21');
INSERT INTO films (title, director, release_date) VALUES ('Return of the Jedi', 'Richard Marquand', '1983-05-25');
INSERT INTO films (title, director, release_date) VALUES ('The Phantom Menace', 'George Lucas', '1999-05-19');
INSERT INTO films (title, director, release_date) VALUES ('Attack of the Clones', 'George Lucas', '2002-05-16');
INSERT INTO films (title, director, release_date) VALUES ('Revenge of the Sith', 'George Lucas', '2005-05-19');

-- Insert starships
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('X-wing', 1050);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('TIE Fighter', 1200);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Millennium Falcon', 1050);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Slave I', 1000);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Star Destroyer', 975);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Imperial Shuttle', 850);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Executor', 800);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('A-wing', 1300);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Y-wing', 1000);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Naboo Starfighter', 1100);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('B-Wing', 950);
INSERT INTO starships (name, max_atmosphering_speed) VALUES ('Rebel Transport', 650);

-- Link starships to films
INSERT INTO starship_films (starship_id, film_id) VALUES (1, 1);
INSERT INTO starship_films (starship_id, film_id) VALUES (1, 2);
INSERT INTO starship_films (starship_id, film_id) VALUES (2, 1);
INSERT INTO starship_films (starship_id, film_id) VALUES (3, 3);
INSERT INTO starship_films (starship_id, film_id) VALUES (4, 5);
INSERT INTO starship_films (starship_id, film_id) VALUES (5, 2);
INSERT INTO starship_films (starship_id, film_id) VALUES (6, 6);
INSERT INTO starship_films (starship_id, film_id) VALUES (7, 3);
INSERT INTO starship_films (starship_id, film_id) VALUES (8, 2);
INSERT INTO starship_films (starship_id, film_id) VALUES (9, 1);
INSERT INTO starship_films (starship_id, film_id) VALUES (10, 4);
INSERT INTO starship_films (starship_id, film_id) VALUES (11, 2);
INSERT INTO starship_films (starship_id, film_id) VALUES (12, 1);
