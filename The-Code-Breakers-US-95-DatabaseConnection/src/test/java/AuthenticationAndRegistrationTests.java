import memoranda.auth.AuthenticationService;
import memoranda.auth.User;
import memoranda.auth.UserManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class AuthenticationAndRegistrationTests {

    @Test
    @DisplayName("Tests that the user can register and their information is saved")
    void registerTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = new User("Logan", "test1234", true, "test", "Hello there");

        assertSame(UserManager.getUserByUsername("Logan").getUsername(), user.getUsername());

    }

    @Test
    @DisplayName("Tests that the user password is hashed properly")
    void registerPasswordHashTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = new User("Logan", "test1234", true, "test", "Hello there");

        assertNotSame(UserManager.getUserByUsername("Logan").getPasswordHash(), user.getPasswordHash());

    }

    @Test
    @DisplayName("Tests that the user is authenticated properly")
    void authenticationTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        AuthenticationService as = new AuthenticationService();

        assertTrue(as.authenticate("Logan", "test1234"));

    }

    @Test
    @DisplayName("Tests that a non-existing user does not get logged in")
    void authenticationUserDoesNotExistTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        AuthenticationService as = new AuthenticationService();

        assertFalse(as.authenticate("Jacob", "1234Test"));

    }

    @Test
    @DisplayName("Tests that the user token generated is valid")
    void authenticationUserTokenTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = UserManager.getUserByUsername("Logan");

        AuthenticationService as = new AuthenticationService();

        assertTrue(as.verifyToken(as.generateToken(user)));

    }

    @Test
    @DisplayName("Tests that the user token does not expire immediately")
    void authenticationNonExpiredTokenTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = UserManager.getUserByUsername("Logan");

        AuthenticationService as = new AuthenticationService();

        as.removeExpiredTokens();

        assertTrue(as.verifyToken(as.generateToken(user)));

    }

    @Test
    @DisplayName("Tests that a non-existing user does not get a token")
    void authenticationUserDoesNotExistNoTokenTest() {
        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        AuthenticationService as = new AuthenticationService();

        User hacker = new User("Jacob", "1234Test", false, "test", "Hello there");

        assertFalse(as.verifyToken(as.generateToken(hacker)));

    }

    @Test
    @DisplayName("Tests that a user can only have one valid token, and if" +
            " generate token is called again, it returns the original valid token.")
    void onlyOneValidToken() {

        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = UserManager.getUserByUsername("Logan");

        AuthenticationService as = new AuthenticationService();

        String token1 = as.generateToken(user);

        String token2 = as.generateToken(user);

        assertEquals(token1, token2);

    }

    @Test
    @DisplayName("Tests that an updated username still has the same password hash")
    void changeUsername() {

        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = UserManager.getUserByUsername("Logan");

        AuthenticationService as = new AuthenticationService();

        as.changeUsername("Logan", "LoganTest", "test1234");

        assertEquals(user.getPasswordHash(), UserManager.getUserByUsername("LoganTest").getPasswordHash());

    }

    @Test
    @DisplayName("Tests that a users password gets updated")
    void updatePassword() {

        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = UserManager.getUserByUsername("Logan");

        AuthenticationService as = new AuthenticationService();

        as.changePassword("Logan", "test1234", "newTest");

        assertNotEquals(user.getPasswordHash(), UserManager.getUserByUsername("LoganTest").getPasswordHash());

    }

    @Test
    @DisplayName("Tests that a user can be grabbed using a valid token")
    void getValidUserFromToken() {

        UserManager.addUser("Logan","test1234", true, "test", "Hello there");

        User user = UserManager.getUserByUsername("Logan");

        AuthenticationService as = new AuthenticationService();

        String token = as.generateToken(user);

        User grabbedUser = as.getLoggedInUser(token);

        assertEquals(grabbedUser, user);

    }

}
