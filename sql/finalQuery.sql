
-- Create the Authors table
CREATE TABLE Authors (
    AuthorID INT AUTO_INCREMENT PRIMARY KEY,
    AuthorName VARCHAR(255) NOT NULL
);

-- Create the Categories table
CREATE TABLE Categories (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(255) NOT NULL
);

-- Create the Books table
CREATE TABLE Books (
    BookID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    ISBN VARCHAR(255) NOT NULL,
    AuthorID INT NOT NULL,
    CategoryID INT NOT NULL,
    Availability BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Create the BorrowedBooks table with corrected BorrowDate
CREATE TABLE BorrowedBooks (
    BorrowID INT AUTO_INCREMENT PRIMARY KEY,
    BookID INT NOT NULL,
    StudentID INT NOT NULL,
    BorrowDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

-- Stored Procedure to Borrow a Book
DELIMITER //

CREATE PROCEDURE BorrowBook(IN bookID INT, IN studentID INT)
BEGIN
    DECLARE bookAvailable BOOLEAN;

    -- Check if the book is available
    SELECT Availability INTO bookAvailable FROM Books WHERE BookID = bookID;

    IF bookAvailable THEN
        -- Update the book's availability
        UPDATE Books SET Availability = FALSE WHERE BookID = bookID;
        -- Insert into BorrowedBooks
        INSERT INTO BorrowedBooks (BookID, StudentID) VALUES (bookID, studentID);
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Book is not available for borrowing.';
    END IF;
END //

DELIMITER ;

-- Stored Procedure to Return a Book
DELIMITER //

CREATE PROCEDURE ReturnBook(IN bookID INT)
BEGIN
    DECLARE bookBorrowed BOOLEAN;

    -- Check if the book is borrowed
    SELECT COUNT(*) INTO bookBorrowed FROM BorrowedBooks WHERE BookID = bookID;

    IF bookBorrowed > 0 THEN
        -- Update the book's availability
        UPDATE Books SET Availability = TRUE WHERE BookID = bookID;
        -- Delete from BorrowedBooks
        DELETE FROM BorrowedBooks WHERE BookID = bookID;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Book is not currently borrowed.';
    END IF;
END //

DELIMITER ;

DELIMITER //

DELIMITER //

CREATE PROCEDURE BorrowBook(IN bookID INT, IN studentID INT)
BEGIN
    DECLARE bookAvailable BOOLEAN;

    -- Check if the book is available
    SELECT Availability INTO bookAvailable FROM Books WHERE BookID = bookID;

    IF bookAvailable THEN
        -- Update the book's availability
        UPDATE Books SET Availability = FALSE WHERE BookID = bookID;
        -- Insert into BorrowedBooks
        INSERT INTO BorrowedBooks (BookID, StudentID) VALUES (bookID, studentID);
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Book is not available for borrowing.';
    END IF;
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE ReturnBook(IN bookID INT)
BEGIN
    DECLARE bookBorrowed BOOLEAN;

    -- Check if the book is borrowed
    SELECT COUNT(*) INTO bookBorrowed FROM BorrowedBooks WHERE BookID = bookID;

    IF bookBorrowed > 0 THEN
        -- Update the book's availability
        UPDATE Books SET Availability = TRUE WHERE BookID = bookID;
        -- Delete from BorrowedBooks
        DELETE FROM BorrowedBooks WHERE BookID = bookID;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Book is not currently borrowed.';
    END IF;
END //

DELIMITER ;
