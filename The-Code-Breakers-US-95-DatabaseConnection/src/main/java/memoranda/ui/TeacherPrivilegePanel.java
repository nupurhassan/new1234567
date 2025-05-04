package memoranda.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class TeacherPrivilegePanel extends JPanel {
    public TeacherPrivilegePanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Teacher Privilege Panel", JLabel.CENTER), BorderLayout.CENTER);
    }
}