-- Account --
INSERT INTO account (name)
VALUES ('developk1'),
       ('developk2'),
       ('developk3'),
       ('developk4'),
       ('developk5'),
       ('developk6'),
       ('developk7'),
       ('developk8'),
       ('developk9'),
       ('developk10'),
       ('developk11'),
       ('developk12'),
       ('developk13'),
       ('developk14'),
       ('developk15'),
       ('developk16'),
       ('developk17'),
       ('developk18'),
       ('developk19'),
       ('developk20');

-- UserInfo
INSERT INTO user_info (account_id, name, age, remark)
VALUES (1, '김상현1', 35, '테스트데이터'),
       (2, '김상현2', 35, '테스트데이터'),
       (3, '김상현3', 35, '테스트데이터'),
       (4, '김상현4', 35, '테스트데이터'),
       (5, '김상현5', 35, '테스트데이터'),
       (6, '김상현6', 35, '테스트데이터'),
       (7, '김상현7', 35, '테스트데이터'),
       (8, '김상현8', 35, '테스트데이터'),
       (9, '김상현9', 35, '테스트데이터'),
       (10, '김상현10', 35, '테스트데이터'),
       (11, '김상현11', 35, '테스트데이터'),
       (12, '김상현12', 35, '테스트데이터'),
       (13, '김상현13', 35, '테스트데이터'),
       (14, '김상현14', 35, '테스트데이터'),
       (15, '김상현15', 35, '테스트데이터'),
       (16, '김상현16', 35, '테스트데이터'),
       (17, '김상현17', 35, '테스트데이터'),
       (18, '김상현18', 35, '테스트데이터'),
       (19, '김상현19', 35, '테스트데이터'),
       (20, '김상현20', 35, '테스트데이터');

INSERT INTO data_types (int_val, long_val, decimal_val, string_val, date_val, date_time_val)
VALUES (1, 20, 123.2568, 'abc', DATE('2022-06-29'), STR_TO_DATE('2022-06-29 11:22:33', '%Y-%m-%d %H:%i:%s')),
       (1, 20, 123.2568, 'abc', DATE('2022-05-29'), STR_TO_DATE('2022-05-29 11:22:33', '%Y-%m-%d %H:%i:%s')),
       (1, 20, 123.2568, 'abc', DATE('2022-04-29'), STR_TO_DATE('2022-04-29 11:22:33', '%Y-%m-%d %H:%i:%s')),
       (1, 20, 123.2568, 'abc', DATE('2022-03-29'), STR_TO_DATE('2022-03-29 11:22:33', '%Y-%m-%d %H:%i:%s')),
       (1, 20, 123.2568, 'abc', DATE('2022-02-28'), STR_TO_DATE('2022-02-28 11:22:33', '%Y-%m-%d %H:%i:%s')),
       (1, 20, 123.2568, 'abc', DATE('2022-01-29'), STR_TO_DATE('2022-01-29 11:22:33', '%Y-%m-%d %H:%i:%s'))
;