 SELECT l_name,u_name 
 From library, users
 Where l_name=u_name;

SELECT u_userid,b_bookid 
From users, books
Where u_userid<=b_bookid;

Select lo_locid,u_userid
From location, users 
Where lo_locid > u_locid;

Select lo_city, lo_country
From location
Where lo_city > lo_country;

Select a_authid,u_userid 
From author, users
Where a_authid < u_userid;

Select u_age, b_bookid
From users, books
Where b_bookid <= u_age;

Select lo_city, b_authid 
From location, books 
Where b_authid > lo_city;

Select a_name, u_name, lo_city 
From author, users, location 
Where a_name > u_name
And u_name > lo_city;

Select b_bookid, b_authid, b_genre 
From books, author
Where b_bookid < b_authid
And b_genre < b_authid;

Select lo_country, lo_locid, u_age
From location, users
Where u_age < lo_country
And lo_country > lo_locid;
