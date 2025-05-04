package memoranda.auth;


import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    private String username;
    private String passwordHash;
    private Boolean teacher;
    private String fpPhrase;
    private String fpQuestion;

    public User(String username, String passwordHash, Boolean teacher,
                String fpPhrase, String fpQuestion) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.teacher = teacher;
        this.fpPhrase = fpPhrase;
        this.fpQuestion = fpQuestion;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}



