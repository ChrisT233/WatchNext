CREATE TABLE Role(id SERIAL PRIMARY KEY,
        name VARCHAR(100),
        isAdmin BOOLEAN);

CREATE TABLE Users(id SERIAL PRIMARY KEY,
        username VARCHAR(100),
        email VARCHAR(100) UNIQUE,
        password_hash VARCHAR(255),
        role INT,
        FOREIGN KEY(role) REFERENCES Role(id)
        );

CREATE TABLE Categories(id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL UNIQUE
        );

CREATE TABLE Movies(id SERIAL PRIMARY KEY,
        title VARCHAR(200) NOT NULL,
        trailerUrl VARCHAR(200) NOT NULL,
        originalSourceUrl VARCHAR(200) NOT NULL,
        coverUrl VARCHAR(200) NOT NULL,
        imdbId VARCHAR(200) NOT NULL,
        imdbScore FLOAT(2),
        description VARCHAR(1024),
        releaseTime TIMESTAMP NOT NULL
        );

CREATE TABLE MoviesCategories(id SERIAL PRIMARY KEY,
        category INT,
        movie INT,
        FOREIGN KEY(category) REFERENCES Categories(id),
        FOREIGN KEY(movie) REFERENCES Movies(id),
        UNIQUE(category, movie)
        );