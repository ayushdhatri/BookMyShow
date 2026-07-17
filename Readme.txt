-- Explain select * from products where created_at <= NOW()
EXPLAIN SELECT * FROM products
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 1 DAY); -- Here scan happens on index

-- THIS USES YOUR INDEX PERFECTLY
Explain SELECT * FROM products
WHERE created_at <=  TIMESTAMP('2026-01-01 10:00:00'); -- here full table scan because all the dates were smaller so eventually it need to do full scan

EXPLAIN SELECT * FROM products
WHERE created_at = '2025-01-01 10:00:00'
  AND price = 100.00; -- Here index_arr scan occured


  EXPLAIN SELECT * FROM products
WHERE category_id = 1
  AND rating = 4
  AND price = 99.99
  AND created_at = '2025-01-01 10:00:00'; -- Here index_arr scan occured

EXPLAIN SELECT * FROM products
WHERE category_id = 1
  AND created_at = '2025-01-01 10:00:00'; -- Here index_arr scan occured because a valid prefix was available

EXPLAIN SELECT * FROM products
WHERE created_at = '2025-01-01 10:00:00'
  AND price > 100.00
  AND rating = 5;

  EXPLAIN SELECT * FROM products
WHERE created_at = '2025-01-01 10:00:00'
  AND price IN (19.99, 49.99, 99.99)
  AND rating = 5;

  EXPLAIN SELECT * FROM products
WHERE created_at = '2025-01-01 10:00:00'
   OR price = 50.00;

   EXPLAIN SELECT created_at, COUNT(*)
FROM products
GROUP BY created_at;

EXPLAIN SELECT * FROM products
ORDER BY price ASC
LIMIT 10;

show databases
use bms

show tables
select * from movie

INSERT INTO city (name, created_at, updated_at) values
('C1', NOW(), NOW()),
('C2', NOW(), NOW());

select * from city

INSERT INTO theatre(name, address, city_id, created_at, updated_at) VALUES
('T1', 'A1', 1, NOW(), NOW()),
('T2', 'A2', 2, NOW(), NOW())


INSERT INTO auditorium (name, capacity, theatre_id, created_at, updated_at)
values
('Screen 1', 200, 1, NOW(), NOW()),
('Screen 2', 150, 1, NOW(), NOW()),
('Screen 3', 180, 2, NOW(), NOW())
select * from auditorium

select * from auditorium

select * from seat
INSERT INTO seat(seat_number, row_value, col_value, seat_type, auditorium_id, created_at, updated_at)
values
('A1', 1, 1, 0, 1, NOW(), NOW()),
('A2', 1, 2, 0, 1, NOW(), NOW()),
('B1', 2, 1, 1, 2, NOW(), NOW()),
('B2', 2, 2, 1, 2, NOW(), NOW());


INSERT INTO movie (name, poster, created_at, updated_at)
values
('Inception', 'inception.jpg', NOW(), NOW() ),
('Interstellar', 'interstellar.jpg', NOW(), NOW());

select * from movie

INSERT INTO shows (movie_id, start_time, end_time, auditorium_id, created_at, updated_at)
values
(1, '2025-03-15 14:00:00','2025-03-15 16:30:00', 1, NOW(), NOW()),
(2, '2025-03-15 18:00:00','2025-03-15 21:00:00', 2, NOW(), NOW())

select * from shows
select * from show_seat

INSERT into show_seat (show_id, seat_id, show_seat_status, created_at, updated_at)
values
(1, 1, 0, NOW(), NOW()),
(1, 2, 1, NOW(), NOW()),
(2, 3, 0, NOW(), NOW()),
(2, 4, 2, NOW(), NOW())

select * from seat

select * from shows



