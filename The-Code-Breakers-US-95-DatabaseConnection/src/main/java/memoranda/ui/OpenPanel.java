package memoranda.ui;

import memoranda.auth.AuthenticationService;
import memoranda.util.Context;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class OpenPanel extends JFrame {

    JPanel openPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    TitledBorder titledBorder;

    JButton loginButton = new JButton();

    JButton registerButton = new JButton();

    JLabel title = new JLabel();

    String titleText = "<html><style> h1 {text-align: center;} </style><h1> Welcome to Teach Memoranda! </h1></html>";

    static OpenPanel op;

    public void setOpenPanel(AuthenticationService authenticationService) {
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(
                Color.white, new Color(156, 156, 158)));
        setResizable(false);

        setIconImage(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/jnotes16.png"))
                .getImage());
        setTitle("Teach Memoranda");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        openPanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        openPanel.setBackground(Color.decode("0x2b2b2b"));

        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;

        title.setText(titleText);
        title.setMaximumSize(new Dimension(200, 50));
        title.setPreferredSize(new Dimension(200, 50));
        title.setForeground(Color.decode("0xffffff"));
        openPanel.add(title, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 100, 20, 100);

        loginButton.setText("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        openPanel.add(loginButton, gbc);

        gbc.gridy = 3;

        registerButton.setText("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 16));
        registerButton.setPreferredSize(new Dimension(200, 50));
        registerButton.setBackground(new Color(46, 139, 87));
        registerButton.setForeground(Color.WHITE);
        openPanel.add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            op.setVisible(false);
            LoginPanel lp = new LoginPanel();
            lp.startLogin(authenticationService);
        });

        registerButton.addActionListener(e -> {
            op.setVisible(false);
            RegisterPanel rp = new RegisterPanel();
            rp.startRegister(authenticationService);
        });

        add(openPanel);
    }

    public static void openPanel(AuthenticationService authenticationService) {
        op = new OpenPanel();
        try {
            op.setOpenPanel(authenticationService);
            op.setLocationRelativeTo(null);
            op.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}