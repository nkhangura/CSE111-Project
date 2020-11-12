SELECT ltw_authid, ltw_genre, g_genreid, g_name
FROM likestowrite, genre 
Where ltw_authid = 9
And ltw_genre = g_genreid