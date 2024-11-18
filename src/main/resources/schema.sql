CREATE TABLE films (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    release_date VARCHAR(50)
);

CREATE TABLE starships (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    max_atmosphering_speed INT
);

CREATE TABLE starship_films (
    starship_id BIGINT NOT NULL,
    film_id BIGINT NOT NULL,
    PRIMARY KEY (starship_id, film_id),
    FOREIGN KEY (starship_id) REFERENCES starships(id) ON DELETE CASCADE,
    FOREIGN KEY (film_id) REFERENCES films(id) ON DELETE CASCADE
);
