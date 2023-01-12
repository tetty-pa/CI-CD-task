INSERT INTO tag(id, name)
VALUES (1, '1'),
       (2, '2'),
       (3, '3');

INSERT INTO gift_certificate(id, name, description, price, duration, create_date, last_updated_date)
VALUES (1, '1', '1', 1, 1, '2023-01-04 12:07:19', '2023-01-04 12:07:19'),
       (2, '2', '2', 2, 2, '2023-01-04 12:07:19', '2023-01-04 12:07:19'),
       (3, '3', '3', 3, 3, '2023-01-04 12:07:19', '2023-01-04 12:07:19');

INSERT INTO gift_certificate_has_tag
VALUES (1, 2),
       (1, 1);

