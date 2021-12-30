CREATE TABLE IF NOT EXISTS selector(
    course_id INT NOT NULL,
    question_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
    );
INSERT INTO selector (course_id, question_id) VALUES (1,1);
INSERT INTO selector (course_id, question_id) VALUES (1,2);
INSERT INTO selector (course_id, question_id) VALUES (1,3);

CREATE TABLE IF NOT EXISTS c_s_connector(
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (student_id) REFERENCES users(id)
);
INSERT INTO c_s_connector (course_id, student_id) VALUES (1,1);
INSERT INTO c_s_connector (course_id, student_id) VALUES (2,1);

CREATE TABLE IF NOT EXISTS questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    optionA VARCHAR(255) NOT NULL,
    optionB VARCHAR(255) NOT NULL,
    optionC VARCHAR(255) NOT NULL,
    ans INT NOT NULL,
    chose INT NOT NULL
    );

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          1,
          'What is a correct syntax to output "Hello World" in Java?',
          'echo "Hello World"',
          'printf("Hello World")',
          'System.out.println("Hello World")',
          3,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          2,
          'Java is short for "JavaScript."',
          'True',
          'False',
          'Partially True',
          2,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          3,
          'How do you insert COMMENTS in Java code?',
          '# This is a comment',
          '// This is a comment',
          '/* This is a comment',
          2,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          4,
          'Which data type is used to create a variable that should store text?',
          'String',
          'Char',
          'Both',
          1,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          5,
          'How do you create a variable with the numeric value 5?',
          'num x = 5',
          'float x = 5',
          'int x = 5',
          3,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          6,
          'How do you create a variable with the floating number 2.8?',
          'num x = 2.8',
          'float x = 2.8',
          'int x = 2.8',
          2,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          7,
          'Which method can be used to find the length of a string?',
          'getSize()',
          'length()',
          'size()',
          2,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          8,
          'Which operator is used to add together two VALUES?',
          '&&',
          '.add()',
          '+',
          3,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          9,
          'The value of a string variable can be surrounded by single quotes.',
          'True',
          'False',
          'Maybe',
          2,
          -1)
;

INSERT INTO questions(id, title, optionA, optionB, optionC, ans, chose)
VALUES(
          10,
          'Which operator can be used to compare two VALUES?',
          '><',
          '&|',
          '==',
          3,
          -1)
;
