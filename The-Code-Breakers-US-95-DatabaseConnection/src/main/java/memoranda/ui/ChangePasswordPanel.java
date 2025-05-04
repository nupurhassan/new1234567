package memoranda.ui;

import memoranda.auth.AuthenticationService;
import memoranda.util.Local;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ChangePasswordPanel extends JFrame {
    JPanel changePasswordPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    TitledBorder titledBorder;

    JPasswordField currentPassword = new JPasswordField();
    JPasswordField newPassword = new JPasswordField();


    JLabel currentPasswordText = new JLabel();

    JLabel newPasswordText = new JLabel();

    JButton saveButton = new JButton();

    JButton backButton = new JButton();

    JLabel title = new JLabel();
    JLabel errorText = new JLabel();

    String titleText = "<html><h1> Change Password. </h1><p> Enter your current password and a new one. </p></html>";

    static ChangePasswordPanel changePwdPanel;


    //Sets the login panel
    public void setChangePasswordPanel(AuthenticationService authenticationService) {

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
        changePasswordPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        //Sets background color
        changePasswordPanel.setBackground(Color.decode("0x2b2b2b"));

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

        changePasswordPanel.add(title, gbc);


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
        changePasswordPanel.add(errorText, gbc);


        //GBC setup for Current Password panel
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        //Current Password panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        currentPasswordText.setText("Current Password: ");
        currentPasswordText.setForeground(Color.decode("0xffffff"));
        changePasswordPanel.add(currentPasswordText, gbc);

        //GBC setup for Current Password text field
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        currentPassword.setMaximumSize(new Dimension(200, 25));
        currentPassword.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        changePasswordPanel.add(currentPassword, gbc);


        //New Password text panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        newPasswordText.setText("Password: ");
        newPasswordText.setForeground(Color.decode("0xffffff"));
        changePasswordPanel.add(newPasswordText, gbc);

        //GBC setup for New Password text field
        gbc.gridx = 1;
        gbc.gridwidth = 2;

        //New Password text field
        newPassword.setMaximumSize(new Dimension(200, 25));
        newPassword.setPreferredSize(new Dimension(200, 25));
        gbc.anchor = GridBagConstraints.WEST;
        changePasswordPanel.add(newPassword, gbc);

        //GBC setup for save button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;

        //Save button
        saveButton.setMaximumSize(new Dimension(200, 25));
        saveButton.setPreferredSize(new Dimension(200, 25));
        saveButton.setText("Save");
        changePasswordPanel.add(saveButton, gbc);

        //Action listener for save button
        saveButton.addActionListener(e -> {
            String currentPWD = new String(currentPassword.getPassword());
            String newPWD = new String(newPassword.getPassword());

            String changePWD =
            authenticationService.changePassword(authenticationService.getUsernameFromToken(App.token),
                    currentPWD, newPWD);

            changePwdPanel.errorText.setForeground(Color.decode("0xFF0000"));

            if(changePWD.compareTo("Could not change password, current password not valid!") == 0) {
                changePwdPanel.errorText.setText("Could not change username!");
            } else {
                changePwdPanel.errorText.setForeground(Color.decode("0x0FF000"));
                changePwdPanel.errorText.setText("Changed password succesfully!");
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
        changePasswordPanel.add(backButton, gbc);

        //Action listener for back button
        backButton.addActionListener(e -> {
            changePwdPanel.setVisible(false);
            UserPanel.startUserPanel(authenticationService);
        });

        //Allows popup window to close and not memoranda at the same time
        changePwdPanel.setDefaultCloseOperation(HIDE_ON_CLOSE);

        add(changePasswordPanel);

    }


    //Code for changePasswordPanel page opening
    public static void startChangePasswordPanel(AuthenticationService authenticationService) {
        changePwdPanel = new ChangePasswordPanel();

        try {
            //Starts panel and makes it visible
            changePwdPanel.setChangePasswordPanel(authenticationService);
            changePwdPanel.setLocationRelativeTo(null);
            changePwdPanel.setVisible(true);
            changePwdPanel.errorText.setText("");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}