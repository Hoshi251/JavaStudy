CREATE TABLE IF NOT EXISTS students
(
    student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(100),
    furigana VARCHAR(100),
    nickname VARCHAR(100),
    email VARCHAR(100),
    city VARCHAR(100),
    age INT,
    gender VARCHAR(20),
    remark VARCHAR(255),
    is_deleted BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS students_courses
(
    course_id INT NOT NULL PRIMARY KEY,
    student_id INT,
    course_name VARCHAR(100),
    start_date DATE,
    end_date DATE
);