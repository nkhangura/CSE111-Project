SELECT fb_bookid, b_title
FROM favbook, books
Group By fb_bookid
Order by count(*) DESC
LIMIT 1;