package memoranda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * This class is used to connect to the database and execute queries.
 */

public class DatabaseImplementation implements Database {
    // flag if the connection is established
    boolean connected = false;
    // database connection
    private Connection connection;
    // statement to execute queries
    private Statement statement;
   

    //TODO:PUT THIS IN A SECRET FILE
    private String hostname = "dpg-cutuh2d6l47c73a9go1g-a.oregon-postgres.render.com";
    private String port = "5432";
    private String database = "codebreaker";
    private String user = "codebreaker_user";
    private String password = "fB5cXnvmpMz5ET9I9EpJYwO88s6hJyFC";
    //


    public boolean connectServer() throws Exception{
        connection = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":" + port + "/" + database, user, password);
        //execute multiple queries at once
        connection.setAutoCommit(false);

        //TODO: make tables 


        //connection is established
        return connected = true;
        
    }
        
    public boolean verifyConnection() {
        boolean verify = false; 
        try {
            //check in again with the server
            if (connection.isValid(0)) {
                //connection is still valid
                verify = true;
            }
        } catch (SQLException e) {
            //lost connection between initial connection and now!
            e.printStackTrace();
        }
        return verify;
    }

    public void disconnect() {
        try {
            //sever the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String username) {
        //TODO: check if user exists in the database
        return false;
    }
    
    public String returnUserFirstName(String username) {
        //TODO: return the first name of the user
        return "John";
    }
    public boolean passwordMatches(String username, String password) throws SQLException {
        //TODO: check if the password matches the username
        return false;
    }
    public boolean addUser(String first_name, String username, String password) throws SQLException {
        //TODO: add a new user to the database
        return false;
    }
    public boolean updatePassword(String first_name, String username, String newPassword){
        //TODO: update the password of the user
        return false;
    }
}


    
            
