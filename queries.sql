--Jagmeet Singh & Navroop Khangura
--CSE 111 Library Database Project 
-- Queries for Phase 2

-- Query 1 getAllGenre
SELECT g_name
FROM genre; 

-- Query 2  findthebooks
SELECT b_title
FROM books 
WHERE b_title = 'Far Away and Long Ago: A History of My Early Life';

-- Query 3 findoldestuser
SELECT Max(u_age), u_name, u_userid
FROM users;

--Query 4 findyounguser
SELECT Min(u_age), u_name, u_userid
FROM users; 

-- Query 5 InsertAuth
INSERT INTO author
VALUES(12 ,'Kobe Bryant');

-- Query 6 InsertBook
INSERT INTO books
VALUES(11, 1, 'The Mamba Mentality: How I Play', 2, 12, 1);

-- Query 7 MostPopAuth
SELECT fa_authid, a_name
FROM favauth,author
Group By fa_authid
Order by count(*) DESC
LIMIT 1;

-- Query 8 MostPopBook
SELECT fb_bookid, b_title
FROM favbook, books
Group By fb_bookid
Order by count(*)DESC
LIMIT 1;

-- Query 9 Top2MostVisitedLib
SELECT f_libid, l_libid, l_name
FROM frequents, library
WHERE f_libid = l_libid
Group BY f_libid
Order by count(*) DESC
LIMIT 2;

-- Query 10 Top3LibrariesWithMostBooks
SELECT o_bookid, o_libid, l_libid, l_name
FROM owns, library
WHERE o_libid = l_libid
Group BY o_libid
Order by count(*) DESC
LIMIT 3;

-- Query 11 AllAvailablebooks
SELECT b_available, b_title, b_bookid
FROM books 
Where b_available>=1;

-- Query 12 bookswrittenbyauthors
SELECT w_authid, w_bookid, b_bookid, b_title
FROM books, wrote
Where w_authid = 1
and w_bookid = b_bookid;

-- Query 13 useridandbookid
SELECT u_userid,b_bookid 
From users, books 
Where u_userid<=b_bookid; 

-- Query 14 deleteuser
DELETE FROM users
Where u_name = 'Brain';

DELETE FROM favauth
Where fa_userid = 11;

DELETE FROM favbook
Where fb_userid = 11;

-- Query 15 findLibLocation
SELECT lo_city, lo_country, lo_locid, ili_libid, ili_locid
FROM islocatedin, location
WHERE ili_libid = 1
AND ili_locid = lo_locid;

-- Query 16 findLibraryInUS
SELECT COUNT(lo_country)
FROM location
Where lo_country = 'United States';

-- Query 17 findUserLocation
SELECT if_userid, if_locid, lo_city, lo_country, lo_locid
FROM isfrom, location
WHERE if_userid = 6
AND if_locid = lo_locid;

-- Query 18  AuthorliketoWriteGenre
SELECT ltw_authid, ltw_genre, g_genreid, g_name
FROM likestowrite, genre 
Where ltw_authid = 9
And ltw_genre = g_genreid;
 
-- Query 19 getAllLibrary
SELECT l_name
FROM library;

-- Query 20 updateBookAvailability 
UPDATE books
set b_available = 2
where b_bookid = 6;

-- Query 21 checkAvalBook
SELECT b_title
FROM books 
WHERE b_title = 'Pale Fire'
AND b_available >=1;

