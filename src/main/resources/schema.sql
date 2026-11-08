CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR NOT NULL
);
CREATE TABLE matches (
    matchId INT AUTO_INCREMENT PRIMARY KEY,
    user1 INT,
    user2 INT,
    user1Hand VARCHAR,
    user2Hand VARCHAR,
    isActive BOOLEAN
);
CREATE TABLE matchinfo (
    matchId INT AUTO_INCREMENT PRIMARY KEY,
    user1 INT,
    user2 INT,
    user1Hand VARCHAR,
    isActive BOOLEAN
);
