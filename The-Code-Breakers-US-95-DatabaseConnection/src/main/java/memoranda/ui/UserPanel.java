package memoranda.ui;

import memoranda.auth.AuthenticationService;
import memoranda.util.Local;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class UserPanel extends JFrame {
    JPanel userPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    TitledBorder titledBorder;

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();

    JButton changePasswordButton = new JButton();

    JLabel usernameText = new JLabel();

    JLabel passwordText = new JLabel();

    JButton saveButton = new JButton();

    JButton backButton = new JButton();

    JLabel title = new JLabel();
    JLabel errorText = new JLabel();

    String titleText = "<html><h1> User Information. </h1> </h2> Enter new username, and current password to change username. </h2> </html>";
    String uName = null;

    String pwd = null;

    static UserPanel up;


    //Sets the login panel
    public void setUserPanel(AuthenticationService authenticationService) {

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
        userPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        //Sets background color
        userPanel.setBackground(Color.decode("0x2b2b2b"));

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
        title.setMaximumSize(new Dimension(200, 100));
        //title.setPreferredSize(new Dimension(200, 100));
        title.setForeground(Color.decode("0xffffff"));

        //Sets text to center
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        userPanel.add(title, gbc);


        //GBC setup for error text field
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        //Error text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        errorText.setForeground(Color.decode("0xFF0000"));
        userPanel.add(errorText, gbc);


        //GBC setup for username panel
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        //Username panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        usernameText.setText("Username: ");
        usernameText.setForeground(Color.decode("0xffffff"));
        userPanel.add(usernameText, gbc);

        //GBC setup for username text field
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        username.setMaximumSize(new Dimension(200, 25));
        username.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        userPanel.add(username, gbc);


        //Password text panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        passwordText.setText("Password: ");
        passwordText.setForeground(Color.decode("0xffffff"));
        userPanel.add(passwordText, gbc);

        //GBC setup for password text field
        gbc.gridx = 1;
        gbc.gridwidth = 2;

        //Password text field
        password.setMaximumSize(new Dimension(200, 25));
        password.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        userPanel.add(password, gbc);

        //GBC setup for save button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Save button
        saveButton.setMaximumSize(new Dimension(200, 25));
        saveButton.setPreferredSize(new Dimension(200, 25));
        saveButton.setText("Save");
        userPanel.add(saveButton, gbc);

        //Action listener for save button
        saveButton.addActionListener(e -> {
            uName = username.getText();
            pwd = new String(password.getPassword());

            String changeUname =
                    authenticationService.changeUsername(authenticationService.getUsernameFromToken(App.token),
                    uName, pwd);

            up.errorText.setForeground(Color.decode("0xFF0000"));

            switch (changeUname){

                case "User does not exist!":
                    up.errorText.setText("User does not exist!");
                    break;

                case "Username already taken!":
                    up.errorText.setText("Username already taken!");
                    break;

                case "Could not change username!":
                    up.errorText.setText("Could not change username!");
                    break;

                default:
                    up.errorText.setForeground(Color.decode("0x0FF000"));
                    up.errorText.setText("Username changed succesfully!");
                    App.token = changeUname;

            }
        });

        //GBC setup for Change Password button
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Forgot Password button
        changePasswordButton.setMaximumSize(new Dimension(150, 16));
        changePasswordButton.setPreferredSize(new Dimension(150, 16));
        changePasswordButton.setText("Change Password");
        userPanel.add(changePasswordButton, gbc);

        //Action listener for forgotPassword button
        changePasswordButton.addActionListener(e -> {
            ChangePasswordPanel.startChangePasswordPanel(authenticationService);
            up.setVisible(false);
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
        userPanel.add(backButton, gbc);

        //Action listener for back button
        backButton.addActionListener(e -> {
            up.setVisible(false);
        });

        //Allows popup window to close and not memoranda at the same time
        up.setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(userPanel);

    }


    //Code for changePasswordPanel page opening
    public static void startUserPanel(AuthenticationService authenticationService) {
        up = new UserPanel();

        try {
            //Starts panel and makes it visible
            up.setUserPanel(authenticationService);
            up.setLocationRelativeTo(null);
            up.setVisible(true);
            up.errorText.setText("");

            up.username.setText(authenticationService.getLoggedInUser(App.token).getUsername());
            up.username.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                    public void focusLost(FocusEvent e) {
                    if(up.username.getText().isEmpty() || up.username.getText().isBlank()) {
                        up.username.setText(authenticationService.getLoggedInUser(App.token).getUsername());
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}