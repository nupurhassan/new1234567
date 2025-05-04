package memoranda.ui;

import java.awt.*;
import javax.swing.*;
import memoranda.Course; // Import the Course class

/**
 * CourseDialog class for managing course-related input and interactions.
 */
public class CourseDialog extends JDialog {
    
    private JTextField courseNameField;
    private JTextField courseCodeField;
    private JTextField instructorField;
    private JTextField scheduleField;
	private String redColor;
	private String greenColor;
	private String blueColor;
    private JButton okButton;
    private JButton cancelButton;
    private boolean confirmed = false;
    
    public CourseDialog(Frame parent, Course course) {
        super(parent, "Course Manager", true);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Course Name:"));
        courseNameField = new JTextField(20);
        panel.add(courseNameField);
        
        panel.add(new JLabel("Course Code:"));
        courseCodeField = new JTextField(20);
        panel.add(courseCodeField);
        
        panel.add(new JLabel("Instructor:"));
        instructorField = new JTextField(20);
        panel.add(instructorField);
        
        panel.add(new JLabel("Schedule:"));
        scheduleField = new JTextField(20);
        panel.add(scheduleField);
        
        if (course != null) { // Populate fields if editing an existing course
            courseNameField.setText(course.getTitle());
            courseCodeField.setText(course.getId());
            instructorField.setText(course.getInstructor());
            //scheduleField.setText(course.getSchedule());
			redColor = course.getRedColor();
			greenColor = course.getGreenColor();
			blueColor = course.getBlueColor();
			
        }
		else {
			redColor = "100";
			greenColor = "100";
			blueColor = "100";
		}
		
		
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.addActionListener(e -> {
            if (course != null) {
				course.setRedColor(redColor);
				course.setGreenColor(greenColor);
				course.setBlueColor(blueColor);
			}
			confirmed = true;
            setVisible(false);
			
        });
        
        cancelButton.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    public boolean isCancelled() {
        return !confirmed;
    }
    
    public String getCourseName() {
        return courseNameField.getText();
    }
    
    public String getCourseCode() {
        return courseCodeField.getText();
    }
    
    public String getInstructor() {
        return instructorField.getText();
    }
    
    public String getSchedule() {
       return scheduleField.getText();
    }
	
	public String getRedColor() {
		return redColor;
	}
	
	public String getGreenColor() {
		return greenColor;
	}
	
	public String getBlueColor() {
		return blueColor;
	}
}


