package memoranda.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import memoranda.auth.AuthenticationService;
import memoranda.auth.UserManager;
import memoranda.util.Local;

public class LoginPanel extends JFrame {

    JPanel loginPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    TitledBorder titledBorder;

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();

    JLabel usernameText = new JLabel();
    JLabel passwordText = new JLabel();

    JButton loginButton = new JButton();
    JButton backButton = new JButton();

    JButton forgotPassword = new JButton();

    JLabel title = new JLabel();
    String titleText = "<html> <style> h1 {text-align: center;} <style> <h1> Welcome to Teach Memoranda! \nPlease Login Using Your Credentials. <h1> <html>";

    String uName = null;
    String pwd = null;

    static LoginPanel lp;

    //Sets the login panel
    public void setLoginPanel(AuthenticationService authenticationService) {

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
        loginPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        //Sets background color
        loginPanel.setBackground(Color.decode("0x2b2b2b"));

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
        loginPanel.add(title, gbc);

        //GBC setup for username text field
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        //Username text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        usernameText.setText("Username: ");
        usernameText.setForeground(Color.decode("0xffffff"));
        loginPanel.add(usernameText, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        username.setMaximumSize(new Dimension(200, 25));
        username.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(username, gbc);

        //Password text field label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        passwordText.setText("Password: ");
        passwordText.setForeground(Color.decode("0xffffff"));
        loginPanel.add(passwordText, gbc);

        //Password field
        gbc.gridx = 1;
        gbc.gridwidth = 2;

        password.setMaximumSize(new Dimension(200, 25));
        password.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(password, gbc);

        //Forgot password button
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        forgotPassword.setText("Forgot Password");
        loginPanel.add(forgotPassword, gbc);

        //Action listener for forgot password button
        forgotPassword.addActionListener(e -> {
            if(UserManager.getUserByUsername(username.getText()) != null) {
                ForgotPassword.startForgotPassword(authenticationService, username.getText());
            }
        });

        //GBC setup for login button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Login button
        loginButton.setMaximumSize(new Dimension(200, 25));
        loginButton.setPreferredSize(new Dimension(200, 25));
        loginButton.setText("Login");
        loginPanel.add(loginButton, gbc);

        //Action listener for login button
        loginButton.addActionListener(e -> {
            uName = username.getText();
            pwd = new String(password.getPassword());

            //Implemented login authentication service and starts up memoranda app
            if (authenticationService.authenticate(uName, pwd)) {
                System.out.println("Login successful!");
                //Added this to keep track of logged-in user.
                App.token = authenticationService.generateToken(UserManager.getUserByUsername(uName));
                new App(true, authenticationService);
                lp.setVisible(false);
            } else {
                System.out.println("Invalid username or password.");
                // Show error dialog on wrong credentials
                JOptionPane.showMessageDialog(LoginPanel.this,
                        "Wrong password. Try again!!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                //Clears the password field for retry
                password.setText("");
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
        loginPanel.add(backButton, gbc);

        //Action listener for back button
        backButton.addActionListener(e -> {
            lp.setVisible(false);
            OpenPanel.openPanel(authenticationService);
        });

        add(loginPanel);
    }

    //Code for login page opening
    public void startLogin(AuthenticationService authenticationService) {
        lp = new LoginPanel();
        try {
            //Starts panel and makes it visible
            lp.setLoginPanel(authenticationService);
            lp.setLocationRelativeTo(null);
            lp.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
<

    //Gets the password
    public String getPwd() {
        return this.pwd;
    }

    //gets the username
    public String getUName() {
        return this.uName;
    }

    public void setTitleText(String title) {
        this.titleText = title;
    }
}


