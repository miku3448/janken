CREATE TABLE users (
    userId INT NOT NULL PRIMARY KEY,
    userName VARCHAR NOT NULL
);
CREATE TABLE matches (
    matchId INT NOT NULL PRIMARY KEY,
    user1 INT,
    user2 INT,
    user1Hand VARCHAR,
    user2Hand VARCHAR
);
