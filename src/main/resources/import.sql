INSERT INTO Publisher (id, name, address) VALUES (1, 'HarperCollins Publishers', 'New York, NY, USA');
INSERT INTO Publisher (id, name, address) VALUES (2, 'Secker & Warburg', 'London, UK');
INSERT INTO Publisher (id, name, address) VALUES (3, 'Charles Scribner Sons', 'New York, NY, USA');
INSERT INTO Publisher (id, name, address) VALUES (4, 'Thomas Egerton', 'London, UK');
INSERT INTO Publisher (id, name, address) VALUES (5, 'Little, Brown and Company', 'New York, NY, USA');

INSERT INTO Genre (id, name) VALUES (1, 'Fiction');
INSERT INTO Genre (id, name) VALUES (2, 'Science Fiction');
INSERT INTO Genre (id, name) VALUES (3, 'Classics');
INSERT INTO Genre (id, name) VALUES (4, 'Romance');
INSERT INTO Genre (id, name) VALUES (5, 'Coming of Age');

INSERT INTO Book (id, title, author, genre_id, publisher_id) VALUES (default, 'To Kill a Mockingbird', 'Harper Lee', 1, 1);
INSERT INTO Book (id, title, author, genre_id, publisher_id) VALUES (default, '1984', 'George Orwell', 2, 2);
INSERT INTO Book (id, title, author, genre_id, publisher_id) VALUES (default, 'The Great Gatsby', 'F Scott Fitzgerald', 3, 3);
INSERT INTO Book (id, title, author, genre_id, publisher_id) VALUES (default, 'Pride and Prejudice', 'Jane Austen', 4, 4);
INSERT INTO Book (id, title, author, genre_id, publisher_id) VALUES (default, 'The Catcher in the Rye', 'Jd Salinger', 5, 5);