package memoranda.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import memoranda.Start;
import memoranda.auth.AuthenticationService;
import memoranda.auth.UserManager;
import memoranda.util.Local;
public class RegisterPanel extends JFrame {

    JPanel registerPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    TitledBorder titledBorder;

    JTextField username = new JTextField();

    JPasswordField password = new JPasswordField();

    JLabel usernameText = new JLabel();

    JLabel passwordText = new JLabel();

    JLabel teacherCheckText = new JLabel();

    JButton registerButton = new JButton();

    JButton backButton = new JButton();
    JCheckBox teacherCheck = new JCheckBox();

    JLabel title = new JLabel();

    String titleText = "<html> <style> h1 {text-align: center;} <style> <h1> Welcome to Teach Memoranda! \nPlease Login Using Your Credentials. <h1> <html>";

    String uName = null;

    String pwd = null;
    Boolean teacher = false;

    static RegisterPanel rp;


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
        registerPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        //Sets background color
        registerPanel.setBackground(Color.decode("0x2b2b2b"));

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
        registerPanel.add(title, gbc);

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
        registerPanel.add(usernameText, gbc);

        //GBC setup for password text field
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        username.setMaximumSize(new Dimension(200, 25));
        username.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(username, gbc);

        //Password text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        passwordText.setText("Password: ");
        passwordText.setForeground(Color.decode("0xffffff"));
        registerPanel.add(passwordText, gbc);

        //GBC setup for password
        gbc.gridx = 1;
        gbc.gridwidth = 2;

        //Password field
        password.setMaximumSize(new Dimension(200, 25));
        password.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(password, gbc);

        //Teacher check text field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        teacherCheckText.setText("Select for teacher account: ");
        teacherCheckText.setForeground(Color.decode("0xffffff"));
        teacherCheckText.setHorizontalTextPosition(SwingConstants.TRAILING);
        registerPanel.add(teacherCheckText, gbc);

        //GBC setup for teacher check
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        //Teacher check
        teacherCheck.setMaximumSize(new Dimension(25, 25));
        teacherCheck.setPreferredSize(new Dimension(25, 25));
        registerPanel.add(teacherCheck, gbc);

        //TODO ADD a forgot password section for the recovery phrase/question

        //GBC setup for login button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Login button
        registerButton.setMaximumSize(new Dimension(200, 25));
        registerButton.setPreferredSize(new Dimension(200, 25));
        registerButton.setText("Login");
        registerPanel.add(registerButton, gbc);

        //Action listener for register button
        registerButton.addActionListener(e -> {
            uName = username.getText();
            pwd = new String(password.getPassword());
            teacher = teacherCheck.isSelected();

            //Implemented register authentication service and starts up memoranda app
            //TODO make the user registration grab the values for fpPhrase and fpQuestion
            UserManager.addUser(uName, pwd, teacher, "test", "Hello there");
            if (authenticationService.authenticate(uName, pwd)) {
                System.out.println("Login successful!");
                //Added this to keep track of logged-in user.
                App.token = authenticationService.generateToken(UserManager.getUserByUsername(uName));
                new App(true, authenticationService);
                rp.setVisible(false);
            } else {
                System.out.println("Invalid username or password.");
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
        registerPanel.add(backButton, gbc);

        //Action listener for back button
        backButton.addActionListener(e -> {
            rp.setVisible(false);
            OpenPanel.openPanel(authenticationService);
        });

        add(registerPanel);

    }

    //Code for register page opening

    public void startRegister(AuthenticationService authenticationService) {
        rp = new RegisterPanel();
        rp.titleText = "<html> <style> h1 {text-align: center;} <style> <h1> Welcome to Teach Memoranda! \nPlease Create a Login. <h1> <html>";
        //rp.setTitleText("<html> <style> h1 {text-align: center;} <style> <h1> Welcome to Teach Memoranda! \nPlease Create a Login. <h1> <html>");

        try {
            //Starts panel and makes it visible
            rp.setLoginPanel(authenticationService);
            rp.setLocationRelativeTo(null);
            rp.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
