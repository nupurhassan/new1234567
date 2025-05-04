package memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import memoranda.CourseManager;
import memoranda.Course;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import memoranda.util.Local;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.ColorUIResource;
import java.awt.Component;

public class CoursePanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JButton newCourseB = new JButton();
    JButton editCourseB = new JButton();
    JButton removeCourseB = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    CoursesTable courseTable = new CoursesTable();
    JPopupMenu coursePPMenu = new JPopupMenu();
    JMenuItem ppEditCourse = new JMenuItem();
    JMenuItem ppRemoveCourse = new JMenuItem();
    JMenuItem ppNewCourse = new JMenuItem();
	JToolBar toolBar = new JToolBar();
	JMenuItem ppSetColor = new JMenuItem();

    public CoursePanel() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        //scrollPane.getViewport().setBackground(Color.white);

        newCourseB.setText("New Course");
        newCourseB.addActionListener(this::newCourseB_actionPerformed);

        editCourseB.setText("Edit Course");
        editCourseB.addActionListener(this::editCourseB_actionPerformed);
        editCourseB.setEnabled(false);

        removeCourseB.setText("Remove Course");
        removeCourseB.addActionListener(this::removeCourseB_actionPerformed);
        removeCourseB.setEnabled(false);

        scrollPane.getViewport().add(courseTable, null);
        this.add(scrollPane, BorderLayout.CENTER);

        //  button panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newCourseB);
        buttonPanel.add(editCourseB);
        buttonPanel.add(removeCourseB);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // selection listener to enable/disable buttons
        courseTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateButtonStates();
            }
        });
		
		ppEditCourse.setFont(new java.awt.Font("Dialogue", 1, 11));
		ppEditCourse.setText("Edit course");
		ppEditCourse.addActionListener(this::editCourseB_actionPerformed);
		ppEditCourse.setIcon(
			new ImageIcon(memoranda.ui.AppFrame.class.getResource("/ui/icons/resourcecolor.png")));
		
		ppNewCourse.setFont(new java.awt.Font("Dialog", 1, 11));
		ppNewCourse.setText("Add new course");
		ppNewCourse.addActionListener(this::newCourseB_actionPerformed);
		ppNewCourse.setIcon(
			new ImageIcon(memoranda.ui.AppFrame.class.getResource("/ui/icons/task_completed.png")));
			
		ppRemoveCourse.setFont(new java.awt.Font("Dialog", 1, 11));
		ppRemoveCourse.setText("Remove course");
		ppRemoveCourse.addActionListener(this::removeCourseB_actionPerformed);
		ppRemoveCourse.setIcon(
			new ImageIcon(memoranda.ui.AppFrame.class.getResource("/ui/icons/task_failed.png")));	
			
		ppSetColor.setFont(new java.awt.Font("Dialogue", 1, 11));
		ppSetColor.setText("Set course color");
		ppSetColor.addActionListener(this::ppSetColor_actionPerformed);
		ppSetColor.setIcon(
			new ImageIcon(memoranda.ui.AppFrame.class.getResource("/ui/icons/resourcecolor.png")));	
		
        coursePPMenu.add(ppEditCourse);
		coursePPMenu.add(ppSetColor);
        coursePPMenu.addSeparator();
        coursePPMenu.add(ppNewCourse);
        coursePPMenu.add(ppRemoveCourse);
		coursePPMenu.addSeparator();
		
		PopupListener ppListener = new PopupListener();
        //scrollPane.addMouseListener(ppListener);
        courseTable.addMouseListener(ppListener);
		scrollPane.getViewport().addMouseListener(ppListener);
		
    }
	
	void ppSetColor_actionPerformed(ActionEvent e) {
		Course selectedCourse = courseTable.getSelectedCourse();
		if (selectedCourse == null) {
			return;
		}
		
		Color color = new Color(Integer.parseInt(selectedCourse.getRedColor()), Integer.parseInt(selectedCourse.getGreenColor()), Integer.parseInt(selectedCourse.getBlueColor()));
		
		Color chosenColor = JColorChooser.showDialog(this, "Choose new color", color);
		
		if (chosenColor != null) {
			CourseManager.updateCourse(selectedCourse.getId(), selectedCourse.getCourseName(), "test", selectedCourse.getInstructor(), "test", Integer.toString(chosenColor.getRed()), Integer.toString(chosenColor.getGreen()), Integer.toString(chosenColor.getBlue()));
			refreshCourses();
		}
		
	}
	
	void newCourseB_actionPerformed(ActionEvent e) {
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        CourseDialog dlg = new CourseDialog(parent, null);
        dlg.setVisible(true);

        if (dlg.isConfirmed()) {
            CourseManager.createCourse(
                dlg.getCourseName(),
                dlg.getCourseCode(),
                dlg.getInstructor(),
                dlg.getSchedule()
            );
            refreshCourses();
        }
	}
	
    void editCourseB_actionPerformed(ActionEvent e) {
    Course selectedCourse = courseTable.getSelectedCourse();
    if (selectedCourse == null) return;
        
    Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
    CourseDialog dlg = new CourseDialog(parent, selectedCourse);
    dlg.setVisible(true);

    if (dlg.isConfirmed()) {
        CourseManager.updateCourse(
            selectedCourse.getId(),
            dlg.getCourseName(),
            dlg.getCourseCode(),
            dlg.getInstructor(),
            dlg.getSchedule(),
			dlg.getRedColor(),
			dlg.getGreenColor(),
			dlg.getBlueColor()
        );
        refreshCourses();
    }
}
  
   void removeCourseB_actionPerformed(ActionEvent e) {
    Course selectedCourse = courseTable.getSelectedCourse();
    if (selectedCourse == null) return;

    CourseManager.removeCourse(selectedCourse.getId());
    refreshCourses();
}

    private void refreshCourses() {
        courseTable.refresh();
        updateButtonStates(); 
		courseTable.repaint();
    }

    private void updateButtonStates() {
        boolean rowSelected = courseTable.getSelectedRow() >= 0;
        editCourseB.setEnabled(rowSelected);
        removeCourseB.setEnabled(rowSelected);
    }
	
	class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if ((e.getClickCount() == 2) && (courseTable.getSelectedRow() > -1)) {
                String path = (String) courseTable.getValueAt(courseTable.getSelectedRow(), 3);
            }
            //editTaskB_actionPerformed(null);
        }

                public void mousePressed(MouseEvent e) {
                    maybeShowPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    maybeShowPopup(e);
                }

                private void maybeShowPopup(MouseEvent e) {
                    int row = courseTable.rowAtPoint(e.getPoint());
					
					if (e.isPopupTrigger()) {
						if (row != -1) {
							courseTable.setRowSelectionInterval(row, row);
							ppEditCourse.setEnabled(true);
							ppRemoveCourse.setEnabled(true);
							ppSetColor.setEnabled(true);
							
						}
						else {
							courseTable.clearSelection();
							ppEditCourse.setEnabled(false);
							ppRemoveCourse.setEnabled(false);
							ppSetColor.setEnabled(false);
						}
						coursePPMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
    }
}

