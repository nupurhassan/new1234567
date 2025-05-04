package memoranda.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.mindrot.jbcrypt.BCrypt;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private static final String SECRET = "your-secret-key";
    public static Map<String, String> activeSessions = new HashMap<>();

    public boolean authenticate(String username, String password) {
        User user = UserManager.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
            String token = generateToken(user);
            activeSessions.put(username, token);
            return true;
        }
        return false;
    }

    public String changePassword(String username, String oldPassword, String newPassword) {

        if(authenticate(username, oldPassword)) {
            String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            User user = UserManager.getUserByUsername(username);
            UserManager.updatePassword(username,
                    new User(username, newHashedPassword,
                            user.getTeacher(), user.getFpPhrase(), user.getFpQuestion()));
            return "Changed password succesfully!";
        }

        return "Could not change password, current password not valid!";
    }

    public String changeUsername(String oldUsername, String newUsername, String password) {
        User user = UserManager.getUserByUsername(oldUsername);

        if(user == null) {
            return "User does not exist!";
        }

        if(UserManager.getUserByUsername(newUsername) != null) {
            return "Username already taken!";
        }

        if(authenticate(oldUsername, password)) {
            if(UserManager.updateUsername(oldUsername, newUsername)) {

                activeSessions.remove(oldUsername);

                String token = generateToken(UserManager.getUserByUsername(newUsername));

                activeSessions.put(newUsername, token);

                return token;
            }
        }

        return "Could not change username!";
    }

    public String generateToken(User user) {

        //Makes sure a non-authentic user cannot get a token
        if(UserManager.getUserByUsername(user.getUsername()) == null ||
                !UserManager.getUserByUsername(user.getUsername()).equals(user)) {
            return null;
        }

        if(activeSessions.get(user.getUsername()) != null) {
            return activeSessions.get(user.getUsername());
        }

        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .sign(algorithm);
    }

    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void removeExpiredTokens() {
        activeSessions.entrySet().removeIf(entry -> !verifyToken(entry.getValue()));
    }

    public String getUsernameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            return null;
        }

    }

    public static User getLoggedInUser(String token) {
        String username = getUsernameFromToken(token);
        return UserManager.getUserByUsername(username);
    }
} 