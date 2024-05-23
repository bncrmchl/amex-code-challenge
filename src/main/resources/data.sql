INSERT INTO student (id, name, date_of_birth, joining_date, class) VALUES
(1, 'John Doe', '2003-04-12', '2021-09-01', '10A'),
(2, 'Jane Smith', '2004-10-24', '2021-09-01', '10B'),
(3, 'Alice Johnson', '2003-01-18', '2021-09-01', '10A')
ON CONFLICT(id) DO NOTHING;