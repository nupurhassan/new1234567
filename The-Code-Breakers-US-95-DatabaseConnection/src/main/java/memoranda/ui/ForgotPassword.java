package memoranda.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import memoranda.auth.AuthenticationService;
import memoranda.auth.User;
import memoranda.auth.UserManager;
import memoranda.util.Local;
import org.mindrot.jbcrypt.BCrypt;


public class ForgotPassword extends JFrame {

    JPanel forgotPasswordPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    TitledBorder titledBorder;

    JTextField userKeyEntry = new JTextField();

    JPasswordField userNewPassword = new JPasswordField();

    JLabel userKeyText = new JLabel();

    JLabel userPasswordText = new JLabel();

    JButton resetPasswordButton = new JButton();

    JButton backButton = new JButton();

    JButton getUserPrompt = new JButton();

    JLabel title = new JLabel();

    static String titleText;

    static String uName = null;

    String userPhrase = null;

    static ForgotPassword fp;


    //Sets the login panel
    public void setForgotPasswordPanel
    (AuthenticationService authenticationService) {

        //Create main panel with a TitledBorder
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(
                Color.white, new Color(156, 156, 158)), Local
                .getString("Sound"));
        setResizable(false);

        //Sets the icon/title/size for the app and exit on close
        setIconImage(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/jnotes16.png"))
                .getImage());
        setTitle("Teach Memoranda");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets border color
        forgotPasswordPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        //Sets background color
        forgotPasswordPanel.setBackground(Color.decode("0x2b2b2b"));

        //Sets spacing for GBC
        gbc.insets = new Insets(20, 20, 20, 20);

        //Initial setup for GBC
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;

        //GBC for title
        title.setText(titleText);
        title.setMaximumSize(new Dimension(100, 50));
        title.setPreferredSize(new Dimension(100, 50));
        title.setForeground(Color.decode("0xffffff"));
        forgotPasswordPanel.add(title, gbc);

        //GBC setup for user key text
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        //User Key text
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        userKeyText.setText("Enter Security Key: ");
        userKeyText.setForeground(Color.decode("0xffffff"));
        forgotPasswordPanel.add(userKeyText, gbc);

        //GBC setup for user key text field
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        userKeyEntry.setMaximumSize(new Dimension(200, 25));
        userKeyEntry.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        forgotPasswordPanel.add(userKeyEntry, gbc);

        //New user password text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        userPasswordText.setText("New password: ");
        userPasswordText.setForeground(Color.decode("0xffffff"));
        forgotPasswordPanel.add(userPasswordText, gbc);

        //GBC setup for secret new user password field
        gbc.gridx = 1;
        gbc.gridwidth = 2;

        //New password text field
        userNewPassword.setMaximumSize(new Dimension(200, 25));
        userNewPassword.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        forgotPasswordPanel.add(userNewPassword, gbc);

//        //Forgot password button
//        gbc.gridx = 1;
//        gbc.gridy = 4;
//        gbc.gridwidth = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//        forgotPassword.setText("Forgot Password");
//        loginPanel.add(forgotPassword, gbc);
//
//        //Action listener for forgot password button
//        forgotPassword.addActionListener(e -> {
//            uName = username.getText();
//            pwd = new String(secretKey.getPassword());
//
//            //Implemented login authentication service and starts up memoranda app
//            if (authenticationService.authenticate(uName, pwd)) {
//                System.out.println("Login successful!");
//                //Added this to keep track of logged-in user.
//                App.token = authenticationService.generateToken(UserManager.getUserByUsername(uName));
//                new App(true, authenticationService);
//                lp.setVisible(false);
//            } else {
//                System.out.println("Invalid username or password.");
//            }
//        });

        //GBC setup for login button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Login button
        resetPasswordButton.setMaximumSize(new Dimension(200, 25));
        resetPasswordButton.setPreferredSize(new Dimension(200, 25));
        resetPasswordButton.setText("Reset Password");
        forgotPasswordPanel.add(resetPasswordButton, gbc);

        //Action listener for resetPassword button
        resetPasswordButton.addActionListener(e -> {
            userPhrase = userKeyEntry.getText();

            User user = UserManager.getUserByUsername(uName);

            if(UserManager.getUserByUsername(uName).getFpPhrase().equals(userPhrase)) {
                User userPwdUpdated = new User(uName,
                        BCrypt.hashpw(new String(userNewPassword.getPassword()),
                                BCrypt.gensalt()),
                        user.getTeacher(), user.getFpPhrase(), user.getFpQuestion());
                UserManager.updatePassword(uName, userPwdUpdated);
                fp.setVisible(false);
            }

        });

        //GBC setup for back button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;

        //Back button
        backButton.setMaximumSize(new Dimension(100, 25));
        backButton.setPreferredSize(new Dimension(100, 25));
        backButton.setText("Back");
        forgotPasswordPanel.add(backButton, gbc);

        //Action listener for back button
        backButton.addActionListener(e -> {
            fp.setVisible(false);
        });

        add(forgotPasswordPanel);

    }


    //Code for login page opening
    public static void startForgotPassword(
            AuthenticationService authenticationService, String username) {
        uName = username;
        StringBuilder title = new StringBuilder();
        title.append("<html> <style> h1 {text-align: center;} <style> <h1> Your password reset question is: ");
        title.append(UserManager.getUserByUsername(username).getFpQuestion());
        title.append(" <h1> <html>");
        titleText = title.toString();
        fp = new ForgotPassword();
        try {
            //Starts panel and makes it visible
            fp.setForgotPasswordPanel(authenticationService);
            fp.setLocationRelativeTo(null);
            fp.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}