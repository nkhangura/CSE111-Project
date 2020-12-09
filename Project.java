import java.util.Scanner;
import java.sql.* ;

public class Project {
    public static void main(String[] args) {
        Connection connection = null;
        Scanner scan = new Scanner(System.in);
        try { // create a database connection
            Statement statement;
            //Class.forName("org.jdbc.sqlite");
            connection = DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            int x = 0;
            boolean quit = false;
            while (!quit) {
                //librarian uses
                System.out.println("Enter 1 to add author.");
                System.out.println("Enter 2 to add a library book.");
                System.out.println("Enter 3 add a user.");
                System.out.println("Enter 4 to add a genre.");
                System.out.println("Enter 5 to remove author.");
                System.out.println("Enter 6 to remove a library book.");
                System.out.println("Enter 7 remove a user.");
                System.out.println("Enter 8 remove a genre.");

                //user uses
                System.out.println("Enter 9 to search for a book.");//by title,part of title, or author name
                System.out.println("Enter 10 to find a library in a location.");//will display all libraries in user's location 
                System.out.println("Enter 11 to checkout a book.");// By book name switch availability to false or give an error if book is not available
                System.out.println("Enter 12 return book.");//by title or part of title
                System.out.println("Enter 13 to print every users' favorite book that is available.");
                System.out.println("Enter 14 print out the library, author, books, genre, user, and wrote tables.");
                System.out.println("Enter 0 to disconnect and quit.");
                
                x=Integer.parseInt(scan.nextLine());
                switch (x) {
                    case 1: //adding author
                    {
                        String name="";
                        System.out.print("\nEnter author name: ");
                        name=scan.nextLine();
                        addAuthor(name);
                        break;
                    }
                    case 2: //adding a library book
                    { 
                        String available="";
                        String title="";
                        String genre="";
                        String authorId="";
                        String libraryId="";
                        System.out.print("\nEnter availability: ");
                        available=scan.nextLine();
                        System.out.print("Enter title: ");
                        title=scan.nextLine();
                        System.out.print("Enter genre id: ");
                        genre=scan.nextLine();
                        System.out.print("Enter book author id: ");
                        authorId=scan.nextLine();
                        System.out.print("Enter library id: ");
                        libraryId=scan.nextLine();
                        addBook(available, title, genre, authorId, libraryId);
                        break;
                    }
                    case 3: //add user
                    {
                        String name="";
                        String age="";
                        String loc="";
                        System.out.print("\nEnter user name: ");
                        name=scan.nextLine();
                        System.out.print("Enter user age: ");
                        age=scan.nextLine();
                        System.out.print("Enter user location id: ");
                        loc=scan.nextLine();
                        addUser(name,age,loc);
                        break;
                    }
                    case 4://add genre
                    {
                        String name="";
                        System.out.print("\nEnter genre name: ");
                        name=scan.nextLine();
                        addGenre(name);
                        break;
                    }
                    case 5://remove author
                    {
                        String id="";
                        System.out.print("\nEnter author id: ");
                        id=scan.nextLine();
                        removeAuthor(id);
                        break;
                    }
                    case 6://remove book
                    {
                        String id="";
                        System.out.print("\nEnter book id: ");
                        id=scan.nextLine();
                        removeBook(id);
                        break;
                    }
                    case 7://remove user
                    {
                        String id="";
                        System.out.print("\nEnter user id: ");
                        id=scan.nextLine();
                        removeUser(id);
                        break;
                    }
                    case 8://remove genre
                    {
                        String id="";
                        System.out.print("\nEnter genre id: ");
                        id=scan.nextLine();
                        removeGenre(id);
                        break;
                    }
                    case 9://find book
                    {
                        String name="";
                        System.out.print("\nEnter book title: ");
                        name=scan.nextLine();
                        findBook(name);
                        break;
                    }
                    case 10://find library in location x
                    {
                        String id="";
                        System.out.print("\nEnter location id: ");
                        id=scan.nextLine();
                        findLibrary(id);
                        break;
                    }
                    case 11://check out book
                    {
                        String id="";
                        System.out.println("\nEnter book id:");
                        id=scan.nextLine();
                        checkout(id);
                        break;
                    }
                    case 12://return book
                    {
                        String id="";
                        System.out.println("\nEnter book id: ");
                        id=scan.nextLine();
                        returnBook(id);
                        break;
                    }
                    case 13://all available favorite books
                    {
                        task13();
                        break;
                    }
                    case 14://print out the tables
                    {
                        System.out.println();
                        String q="SELECT * FROM author WHERE a_authid !=0";
                        System.out.println("Author table: ");
                        Statement s=connection.createStatement();
                        ResultSet r=s.executeQuery(q);
                        while(r.next()){
                            String id=r.getString(1);
                            String name=r.getString(2);
                            System.out.println("AuthorID:"+id+"   Name:"+name);
                        }
                        q="SELECT * FROM books ";
                        r=s.executeQuery(q);
                        System.out.println("\nBooks table:");
                        while(r.next()){
                            String id=r.getString(1);
                            String available=r.getString(2);
                            String title=r.getString(3);
                            String genre=r.getString(4);
                            String authid=r.getString(5);
                            String libid=r.getString(6);
                            System.out.println("BookID:"+id+"   Available:"+available+"   Genre ID:"+genre+"   Author ID:"+authid+"   Library ID:"+libid+"    title:"+title);
                        }
                        System.out.println("\nLibrary table:");
                        q="SELECT * FROM library ";
                        r=s.executeQuery(q);
                        while(r.next()){
                            String libid=r.getString(1);
                            String locid=r.getString(2);
                            System.out.println("Library ID:"+libid+"    Location ID:"+locid);
                        }
                        System.out.println("\nGenre table:");
                        q="SELECT * FROM genre WHERE g_genreid !=0 ";
                        r=s.executeQuery(q);
                        while(r.next()){
                            String libid=r.getString(1);
                            String locid=r.getString(2);
                            System.out.println("Genre ID:"+libid+"   Name:"+locid);
                        }
                        System.out.println("\nWrote table:");
                        q="SELECT * FROM wrote";
                        r=s.executeQuery(q);
                        while(r.next()){
                            String authid=r.getString(1);
                            String bookid=r.getString(2);
                            System.out.println("Author ID:"+bookid+"   Book ID:"+authid);
                        }
                        System.out.println("\nUsers table:");
                        q="SELECT * FROM users";
                        r=s.executeQuery(q);
                        while(r.next()){
                            String uid=r.getString(1);
                            String name=r.getString(2);
                            String age=r.getString(3);
                            String locid=r.getString(4);
                            System.out.println("User ID:"+uid+"   age:"+age+"   Location ID:"+locid+"   Name:"+name);
                        }
                        System.out.println();
                        break;
                    }
                    case 0: 
                    {
                        quit = true;
                        break;
                    }
                    default: 
                    {
                        System.out.println("Unknown selection.");
                    }
                }
            }
            connection.close();
            scan.close();
        } 
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.err.println("Input is not a number.");
            try{
                connection.close();
            }
            catch(SQLException t){
                System.err.println(t.getMessage());
            }
            scan.close();
        }
    }



    public static void addAuthor(String name){
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery("SELECT count(*) as cnt FROM author");
            double authId=r.getDouble("cnt");
            String add="INSERT into author values("+authId+",\'"+name+"\')";
            s.executeUpdate(add);
            System.out.println("id="+authId);
            c.close();
        }
        catch(SQLException e){
        System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void addBook(String avail, String title, String genre, String authId, String libId) {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s = c.createStatement();
            ResultSet book = s.executeQuery("SELECT count(*) as e FROM books");
            double id= book.getDouble("e");
            id+=1.0;
            s.executeUpdate("INSERT into books values(" + id + "," + avail + ",\'" + title + "\'," + genre + "," + authId + "," + libId + ")");
            s.executeUpdate("INSERT into wrote values(" + id + "," + authId + ")");
            System.out.println("id="+id);
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void addUser(String name,String age,String loc){
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet userId=s.executeQuery("SELECT count(*) as cnt FROM users");
            double id=userId.getDouble("cnt");
            id+=1.0;
            String add="INSERT into users values("+id+",\'"+name+"\',"+age+","+loc+")";
            s.executeUpdate(add);
            System.out.println("id="+id);
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void addGenre(String name) {
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery("SELECT count(*) as cnt FROM genre");
            double id=r.getDouble("cnt");
            String add="insert into genre values("+id+",\'"+name+"\')";
            System.out.println("id="+id);
            s.executeUpdate(add);
            c.close();
        }
        catch(SQLException e){
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void removeAuthor(String id){
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            String remove="DELETE FROM author WHERE a_authid="+id;
            s.executeUpdate(remove);
            c.close();
        }
        catch(SQLException e){
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void removeBook(String id){
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet book = s.executeQuery("SELECT b_authid as e FROM books WHERE b_bookid="+id);
            double authid= book.getDouble("e");
            String remove="DELETE FROM books WHERE b_bookid="+id;
            s.executeUpdate(remove);
            remove="DELETE FROM wrote WHERE w_bookid="+id+" and w_authid="+authid;
            s.executeUpdate(remove);
            c.close();
            System.out.println();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void removeUser(String id){
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            String remove="DELETE FROM users WHERE u_userid="+id;
            s.executeUpdate(remove);
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
    public static void removeGenre(String id){
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            String remove="DELETE FROM books WHERE b_bookid="+id;
            s.executeUpdate(remove);
            remove="DELETE FROM wrote WHERE w_bookid="+id;
            s.executeUpdate(remove);
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void findBook(String n){
        System.out.println();
        String q= " SELECT books.b_title,g_name FROM books,genre WHERE books.b_title like \'%"+n+"%\' and g_genreid=b_genre";
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery(q);
            while(r.next()){
                String t=r.getString(1);
                String z=r.getString(2);
                System.out.println(t+" genre:"+z);
            }
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        System.out.println();
    }

    public static void findLibrary(String n){
        System.out.println();
        String q= "SELECT l_name FROM islocatedin,library WHERE ili_locid="+n+" and l_libid=ili_libid";
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery(q);
            while(r.next()){
                String name=r.getString(1);
                System.out.println(name);
            }
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        System.out.println();
    }

    public static void checkout(String id){
        System.out.println();
        Connection c=null;
        if(bookAvail(id)){
            try{
                c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
                Statement s=c.createStatement();
                String update="UPDATE books set b_available=0 WHERE b_bookid="+id;
                s.executeUpdate(update);
                c.close();
                System.out.println("Success");
                System.out.println();
            }
            catch (SQLException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
        else{
            System.out.println("Book not available");
        }
    }

    public static void returnBook(String id){
        System.out.println();
        Connection c=null;
        if(!bookAvail(id)){
            try{
                c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
                Statement s=c.createStatement();
                String update="UPDATE books set b_available=1 WHERE b_bookid="+id;
                s.executeUpdate(update);
                c.close();
                System.out.println("Success");
                System.out.println();
            }
            catch (SQLException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
        else{
            System.out.println("Book not available");
        }
    }

    public static void task13(){
        System.out.println();
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery("SELECT b_title as t ,u_name as u FROM books,favbook,users WHERE b_available=true and fb_bookid=b_bookid and u_userid=fb_userid;");//print out results
            while(r.next()){
                String u=r.getString(2);
                String title=r.getString(1);
                System.out.println("USER:"+u+"   TITLE:"+title);
            }
            System.out.println();
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static boolean bookAvail(String id){
        boolean result=false;
        Connection c=null;
        try{
            c=DriverManager.getConnection("jdbc:sqlite:LibBooks.db");
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery("SELECT b_available as a FROM books WHERE b_bookid="+id);
            result=r.getBoolean("a");
            c.close();
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        return result;
    }
}