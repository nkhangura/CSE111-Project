SELECT f_libid, l_libid, l_name
FROM frequents, library
WHERE f_libid = l_libid
Group BY f_libid
Order by count(*) DESC
LIMIT 2;