CREATE TABLE IF NOT EXISTS member (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS course (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    instructor_name VARCHAR(50) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id)
);
