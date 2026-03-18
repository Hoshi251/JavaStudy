INSERT INTO students
(student_name, furigana, nickname, email, city, age, gender, remark, is_deleted)
VALUES
    ('岩元太郎', 'イワモトタロウ', 'タロ', 'taro.iwamoto@example.com', '渋谷区', 45, '男性', '', false),
    ('星野花子', 'ホシノハナコ', 'はなちゃん', 'hanako.hoshino@example.com', '新宿区', 23, '女性', '', false),
    ('鴨澤次郎', 'カモサワジロウ', 'ネギ', 'jiro.kamonegi@example.com', '横浜区', 30, '男性', '', false),
    ('花田香菜', 'ハナダカナ', 'ガーナ', 'test@example.com', '札幌', 21, '女性', '', false),
    ('佐藤一郎', 'サトウイチロウ', 'イッチー', 'sato@example.com', '名古屋', 28, '男性', '', false);

INSERT INTO students_courses
(id, course_name, start_date, end_date)
VALUES
    ('sc001', 'Java基礎コース', '2026-01-10', '2027-03-04'),
    ('sc002', 'AWSコース', '2026-02-01', '2026-04-30'),
    ('sc003', 'Java基礎コース', '2026-02-05', '2026-06-30'),
    ('sc004', 'AWSコース', '2026-01-20', '2026-04-20'),
    ('sc005', 'Java基礎コース', '2026-01-10', '2027-03-04');