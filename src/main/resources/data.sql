-- Insert values into calc_user table
INSERT INTO calc_user (user_name, name, password, status, balance)
VALUES
  ('johndoe', 'John Doe', 'mypassword', 'active', 1000.00),
  ('janedoe', 'Jane Doe', 'anotherpassword', 'inactive', 5000.00),
  ('bobsmith', 'Bob Smith', 'password123', 'active', 750.50);

-- Insert values into operation table
INSERT INTO operation (type, cost, user_id)
VALUES
  ('addition', 0.00, 1),
  ('subtraction', 0.00, 2),
  ('multiplication', 1.50, 3);

-- Insert values into user_record table
INSERT INTO user_record (operation_id, user_id, amount, user_balance, operation_response, record_date)
VALUES
  (1, 1, 25.00, 1025.00, 'Operation successful', '2023-03-13'),
  (2, 2, 10.00, 4990.00, 'Insufficient funds', '2023-03-12'),
  (3, 3, 5.00, 745.25, 'Operation successful', '2023-03-10'),
  (1, 1, 75.00, 1100.00, 'Operation successful', '2023-03-09'),
  (3, 2, 2.50, 4972.50, 'Operation successful', '2023-03-08');
