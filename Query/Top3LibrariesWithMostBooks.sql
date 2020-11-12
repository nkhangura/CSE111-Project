SELECT o_bookid, o_libid, l_libid, l_name
FROM owns, library
WHERE o_libid = l_libid
Group BY o_libid
Order by count(*) DESC
LIMIT 3;