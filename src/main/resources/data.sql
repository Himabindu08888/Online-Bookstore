-- Sample books (only inserted if the table is empty)
INSERT INTO books (title, author, genre, price, stock, description, image_url)
SELECT * FROM (VALUES
  ('The Silent Patient', 'Alex Michaelides', 'Thriller', 399.00, 25, 'A shocking psychological thriller of a woman''s act of violence against her husband.', 'https://covers.openlibrary.org/b/id/8235112-L.jpg'),
  ('Atomic Habits', 'James Clear', 'Self-Help', 499.00, 40, 'An easy and proven way to build good habits and break bad ones.', 'https://covers.openlibrary.org/b/id/10527843-L.jpg'),
  ('Clean Code', 'Robert C. Martin', 'Technology', 899.00, 15, 'A handbook of agile software craftsmanship.', 'https://covers.openlibrary.org/b/id/8231856-L.jpg'),
  ('The Alchemist', 'Paulo Coelho', 'Fiction', 299.00, 60, 'A shepherd boy''s journey to find a worldly treasure.', 'https://covers.openlibrary.org/b/id/8438533-L.jpg'),
  ('Sapiens', 'Yuval Noah Harari', 'Non-Fiction', 599.00, 30, 'A brief history of humankind.', 'https://covers.openlibrary.org/b/id/8235116-L.jpg'),
  ('Introduction to Algorithms', 'Thomas H. Cormen', 'Technology', 1299.00, 10, 'Comprehensive textbook on algorithms.', 'https://covers.openlibrary.org/b/id/8259447-L.jpg'),
  ('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Fantasy', 349.00, 50, 'The first book in the Harry Potter series.', 'https://covers.openlibrary.org/b/id/8267857-L.jpg'),
  ('Rich Dad Poor Dad', 'Robert Kiyosaki', 'Finance', 349.00, 35, 'What the rich teach their kids about money.', 'https://covers.openlibrary.org/b/id/8259444-L.jpg')
) AS v(title, author, genre, price, stock, description, image_url)
WHERE NOT EXISTS (SELECT 1 FROM books);
