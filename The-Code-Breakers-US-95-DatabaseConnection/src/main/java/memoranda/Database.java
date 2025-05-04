package memoranda;
import java.sql.SQLException;

/**
 * The Database interface represents a connection to a database and provides methods for interacting with it.
 */
public interface Database {
        
        /**
         * Connects to the database server.
         *
         * @return true if the connection is successful, false otherwise.
         * @throws Exception if an error occurs while connecting to the server.
         */
        boolean connectServer() throws Exception;
        
        /**
         * Verifies that the connection to the database server is still active.
         *
         * @return true if the connection is still active, false otherwise.
         */
        boolean verifyConnection();

        /**
         * Disconnects from the database server.
         *
         * @throws Exception if an error occurs while disconnecting from the server.
         */
        void disconnect() throws Exception;
        
        /**
         * Checks if a user with the given username exists in the database.
         *
         * @param username the username to check.
         * @return true if the user exists, false otherwise.
         */
        boolean userExists(String username);
        
        /**
         * Returns the first name of the user with the given username.
         *
         * @param username the username to check.
         * @return the first name of the user as a String.
         */

        String returnUserFirstName(String username);
        /**
         * Checks if the given password matches the password associated with the given username in the database.
         *
         * @param username the username to check.
         * @param password the password to compare.
         * @return true if the password matches, false otherwise.
         * @throws SQLException if an error occurs while querying the database.
         */
        boolean passwordMatches(String username, String password) throws SQLException;
        
        /**
         * Adds a new user with the given username and password to the database.
         *
         * @param first_name the first name of the new user.
         * @param username the username of the new user.
         * @param password the password of the new user.
         * @return true if the user is successfully added, false otherwise.
         * @throws SQLException if an error occurs while inserting the user into the database.
         */
        boolean addUser(String first_name, String username, String password) throws SQLException; 
        
        /**
         * Update a users password using the new password that was provided. 
         * @param first_name the first name of the user 
         * @param username the username of the user
         * @param newPassword the password the user wants to change
         * @return true if the password was update, false if not updated
         */
        boolean updatePassword(String first_name, String username, String newPassword);
                

             
}