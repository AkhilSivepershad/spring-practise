CREATE TABLE sensitiveWords(
    id INT AUTO_INCREMENT PRIMARY KEY,
    sensitiveWord VARCHAR(50) NOT NULL
);

INSERT INTO sensitiveWords (sensitiveWord) VALUES
('SELECT'),('INSERT'),('DROP'),('FROM');