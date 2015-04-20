/* 1) select the number of copies from the branch 'LibraryOne' where the book title is 'Book title 5'*/
SELECT title,noOfCopies,branchName
FROM tbl_book,tbl_book_copies,tbl_library_branch
WHERE tbl_book.bookID = tbl_book_copies.bookID
AND tbl_book_copies.branchID = tbl_library_branch.branchID
AND tbl_book.title = 'Book title 5'
AND tbl_library_branch.branchName = 'LibraryOne';
    
/* 2) how many copies of the book 'Book title 1' are owned by each branch? */
SELECT tbl_book.title,noOfCopies,branchName
FROM tbl_book_copies,tbl_book,tbl_library_branch
WHERE tbl_book_copies.branchID = tbl_library_branch.branchID
AND tbl_book.bookID = tbl_book_copies.bookID
AND tbl_book.title = 'Book title 1';

/* 3) Retrieve names of borrows who do not have books checked out
Selects the borrowers names from the borrower and book loans table where the 
borrower's card number is not in the book loans card number colum*/
SELECT name as NotCheckedOut
FROM tbl_borrower
WHERE tbl_borrower.cardNo
NOT IN (SELECT cardNo FROM tbl_book_loans);

/* 4) For each book that is loaned out from the "Sharpstown" branch and whose 
DueDate is today, retrieve the book title, the borrower's name, and the borrower's address.*/
SELECT title,name,address
FROM tbl_book,tbl_borrower,tbl_book_loans, tbl_library_branch/* SELECTS the title of book, name,address of borrower from book and borrower
    tables*/
WHERE tbl_library_branch.branchName = 'LibraryOne'
AND tbl_book_loans.branchID = tbl_library_branch.branchID
AND tbl_borrower.cardNo = tbl_book_loans.cardNo
AND tbl_book_loans.bookID = tbl_book.bookID
AND tbl_book_loans.dueDate = '2005-02-14';

/* 5) For each library branch, retrieve the branch name and the total number of books 
loaned out from that branch.*/
SELECT branchName,count(tbl_book_loans.branchID) AS NumberOfBooksLoaned
FROM tbl_book_loans,tbl_library_branch
WHERE tbl_book_loans.branchID = tbl_library_branch.branchID
GROUP BY tbl_library_branch.branchName;
    
/* 6) Retrieve the names, addresses, and number of books checked out for all borrowers
 who have more than five books checked out.*/
SELECT name,tbl_borrower.address,count(tbl_book_loans.cardNo)
FROM tbl_borrower,tbl_book_loans
WHERE tbl_borrower.cardNo = tbl_book_loans.cardNo
GROUP BY name, tbl_borrower.address    
HAVING count(tbl_book_loans.cardNo) > 1;

/* 7) For each book authored (or co-authored) by "Stephen King", retrieve the title and the 
number of copies owned by the library branch whose name is "Central" */
SELECT title,tbl_book_copies.noOfCopies
FROM tbl_book_copies,tbl_book,tbl_author,tbl_book_authors,tbl_library_branch
WHERE tbl_book_authors.authorID = tbl_author.authorID
AND tbl_library_branch.branchID = tbl_book_copies.branchID
AND tbl_book_authors.bookID = tbl_book.bookID
AND tbl_book.bookID = tbl_book_copies.bookID
AND tbl_author.authorName = 'Lynch'
AND tbl_library_branch.branchName = 'LibraryFour';
