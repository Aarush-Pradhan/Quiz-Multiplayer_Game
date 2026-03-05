
CREATE DATABASE quizDB;
USE quizDB;

CREATE TABLE questions (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           question TEXT,
                           optionA VARCHAR(255),
                           optionB VARCHAR(255),
                           optionC VARCHAR(255),
                           optionD VARCHAR(255),
                           correct_answer VARCHAR(10)
);



INSERT INTO questions
(question, optionA, optionB, optionC, optionD, correct_answer)
VALUES
('Capital of India?', 'Mumbai', 'Delhi', 'Chennai', 'Kolkata', 2),
('2 + 2 = ?', '3', '4', '5', '6', 2),
('Java is?', 'Language', 'Browser', 'OS', 'Database', 1);