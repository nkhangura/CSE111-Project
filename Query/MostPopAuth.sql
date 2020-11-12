SELECT fa_authid, a_name
FROM favauth,author
Group By fa_authid
Order by count(*) DESC
LIMIT 1;
