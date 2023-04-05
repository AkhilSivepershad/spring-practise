CREATE TABLE SensitiveWord(
    id int AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(50) NOT NULL
);

INSERT INTO SensitiveWord (word) VALUES
('SELECT'),('INSERT'),('DROP'),('FROM');