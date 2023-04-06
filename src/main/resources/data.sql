CREATE TABLE SensitiveWord(
    id int AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(50) NOT NULL,
    createTime DATETIME NOT NULL
);

INSERT INTO SensitiveWord (word,createTime) VALUES
('SELECT',NOW()),('INSERT',NOW()),('DROP',NOW()),('FROM',NOW());