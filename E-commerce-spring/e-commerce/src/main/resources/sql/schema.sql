CREATE DATABASE ecommerce;

CREATE TABLE IF NOT EXISTS Category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE ,
    description VARCHAR(500),
    logo_url VARCHAR(255), -- standard size is 255.
    active BOOLEAN DEFAULT TRUE,
    parent_id INTEGER,
    created_at TIMESTAMP NOT NULL ,
    modified_at TIMESTAMP,
    slug VARCHAR(255) UNIQUE,
    CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES Category (id)
);

CREATE TABLE Brand (
    id SERIAL PRIMARY KEY, -- serial means auto-increment-int
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    logo_url VARCHAR(255),
    slug VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP
);