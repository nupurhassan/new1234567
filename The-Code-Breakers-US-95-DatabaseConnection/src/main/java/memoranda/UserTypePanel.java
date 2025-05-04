package memoranda.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import memoranda.auth.AuthenticationService;
import memoranda.util.Context;
import memoranda.util.Local;

public class UserTypePanel extends JFrame {

    JPanel userTypePanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    TitledBorder titledBorder;

    JButton studentButton = new JButton();
    JButton teacherButton = new JButton();
    JLabel title = new JLabel();

    String titleText = "<html><style> h1 {text-align: center;} </style><h1>Welcome to Teach Memoranda!<br>Please select your user type:</h1></html>";

    static UserTypePanel utp;
    private boolean isTeacher = false;

    public void setUserTypePanel(AuthenticationService authenticationService) {
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(
                Color.white, new Color(156, 156, 158)), Local
                .getString("Sound"));
        setResizable(false);

        setIconImage(new ImageIcon(AppFrame.class.getResource(
                "/ui/icons/jnotes16.png"))
                .getImage());
        setTitle("Teach Memoranda");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userTypePanel.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(Color.white, new Color(43, 43, 43))));

        userTypePanel.setBackground(Color.decode("0x2b2b2b"));

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
        title.setMaximumSize(new Dimension(400, 100));
        title.setPreferredSize(new Dimension(400, 100));
        title.setForeground(Color.decode("0xffffff"));
        userTypePanel.add(title, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 100, 20, 100);

        ImageIcon studentIcon = new ImageIcon(AppFrame.class.getResource("/ui/icons/agenda.png"));
        Image studentImg = studentIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        studentButton.setIcon(new ImageIcon(studentImg));
        studentButton.setText("Student");
        studentButton.setFont(new Font("Dialog", Font.BOLD, 16));
        studentButton.setPreferredSize(new Dimension(200, 50));
        studentButton.setBackground(new Color(100, 149, 237));
        studentButton.setForeground(Color.WHITE);
        userTypePanel.add(studentButton, gbc);

        gbc.gridy = 3;

        ImageIcon teacherIcon = new ImageIcon(AppFrame.class.getResource("/ui/icons/logo123.png"));
        Image teacherImg = teacherIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        teacherButton.setIcon(new ImageIcon(teacherImg));
        teacherButton.setText("Teacher");
        teacherButton.setFont(new Font("Dialog", Font.BOLD, 16));
        teacherButton.setPreferredSize(new Dimension(200, 50));
        teacherButton.setBackground(new Color(46, 139, 87));
        teacherButton.setForeground(Color.WHITE);
        userTypePanel.add(teacherButton, gbc);

        studentButton.addActionListener(e -> {
            isTeacher = false;
            utp.setVisible(false);
            Context.put("IS_TEACHER", "false");
            OpenPanel.openPanel(authenticationService);
        });

        teacherButton.addActionListener(e -> {
            isTeacher = true;
            utp.setVisible(false);
            Context.put("IS_TEACHER", "true");
            OpenPanel.openPanel(authenticationService);
        });

        add(userTypePanel);
    }

    public static void startUserTypePanel(AuthenticationService authenticationService) {
        utp = new UserTypePanel();
        try {
            utp.setUserTypePanel(authenticationService);
            utp.setLocationRelativeTo(null);
            utp.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}