CREATE TABLE IF NOT EXISTS Genre (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Publisher (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Book (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre_id BIGINT,
    publisher_id BIGINT,
    FOREIGN KEY (genre_id) REFERENCES Genre(id),
    FOREIGN KEY (publisher_id) REFERENCES Publisher(id)
);