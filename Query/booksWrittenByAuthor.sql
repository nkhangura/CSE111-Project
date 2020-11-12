SELECT w_authid, w_bookid, b_bookid, b_title
FROM books, wrote
Where w_authid = 1
and w_bookid = b_bookid