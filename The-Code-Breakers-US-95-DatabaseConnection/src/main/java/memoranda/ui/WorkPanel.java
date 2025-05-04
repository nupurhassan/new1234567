package memoranda.ui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import memoranda.auth.AuthenticationService;

import memoranda.ui.*;

import memoranda.util.Context;
import memoranda.util.Local;


/**
 *
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $*/
public class WorkPanel extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	JToolBar toolBar = new JToolBar();
	JPanel panel = new JPanel();
	CardLayout cardLayout1 = new CardLayout();

	public JButton notesB = new JButton();
	public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
	public ResourcesPanel filesPanel = new ResourcesPanel();
	public CoursePanel coursePanel = new CoursePanel();
	public JButton agendaB = new JButton();
	public JButton tasksB = new JButton();
	public JButton eventsB = new JButton();
	public JButton filesB = new JButton();
	public JButton coursesB = new JButton(); // Added Courses Button
	public JButton teacherPrivilegeB = new JButton();
	public TeacherPrivilegePanel teacherPrivilegePanel = new TeacherPrivilegePanel();
	public UserPanel userPanel = new UserPanel();
	public JButton userPanelB = new JButton(); // Adds user panel button
	JButton currentB = null;
	Border border1;

	AuthenticationService as;

	public WorkPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	void jbInit() throws Exception {
		border1 =
				BorderFactory.createCompoundBorder(
						BorderFactory.createBevelBorder(
								BevelBorder.LOWERED,
								Color.black,
								Color.black,
								new Color(255, 255, 255),
								new Color(255, 255, 255)),
						BorderFactory.createEmptyBorder(0, 2, 0, 0));

		this.setLayout(borderLayout1);
		toolBar.setOrientation(JToolBar.VERTICAL);
		//toolBar.setBackground(Color.white); //main side bar

		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);

		toolBar.setOpaque(true);

		panel.setLayout(cardLayout1);

		panel.add(coursePanel, "COURSES");

		// Initialize Courses Button
		coursesB.setBackground(Color.white);
		coursesB.setMaximumSize(new Dimension(60, 80));
		coursesB.setMinimumSize(new Dimension(30, 30));
		coursesB.setFont(new java.awt.Font("Dialog", 1, 10));
		coursesB.setPreferredSize(new Dimension(50, 50));
		coursesB.setBorderPainted(false);
		coursesB.setContentAreaFilled(false);
		coursesB.setFocusPainted(false);
		coursesB.setHorizontalTextPosition(SwingConstants.CENTER);
		coursesB.setText(Local.getString("Courses"));
		coursesB.setVerticalAlignment(SwingConstants.TOP);
		coursesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		coursesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursesB_actionPerformed(e);
			}
		});
		ImageIcon coursesIcon = new ImageIcon(
				memoranda.ui.AppFrame.class.getResource("/ui/icons/CourseIcon.png")
		);
		Image resizedCoursesImg = coursesIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		coursesB.setIcon(new ImageIcon(resizedCoursesImg));
		coursesB.setOpaque(false);
		coursesB.setMargin(new Insets(0, 0, 0, 0));


		//panel.add(userPanel, "USER");

		// Initialize User Button
		userPanelB.setBackground(Color.white);
		userPanelB.setMaximumSize(new Dimension(60, 80));
		userPanelB.setMinimumSize(new Dimension(30, 30));
		userPanelB.setFont(new java.awt.Font("Dialog", 1, 10));
		userPanelB.setPreferredSize(new Dimension(50, 50));
		userPanelB.setBorderPainted(false);
		userPanelB.setContentAreaFilled(false);
		userPanelB.setFocusPainted(false);
		userPanelB.setHorizontalTextPosition(SwingConstants.CENTER);
		userPanelB.setText(Local.getString("User"));
		userPanelB.setVerticalAlignment(SwingConstants.TOP);
		userPanelB.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPanelB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPanelB_actionPerformed(e);
			}
		});
		ImageIcon userIcon = new ImageIcon(
				memoranda.ui.AppFrame.class.getResource("/ui/icons/usericon.png")
		);
		Image resizedUserIcon = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		userPanelB.setIcon(new ImageIcon(resizedUserIcon));
		userPanelB.setOpaque(false);
		userPanelB.setMargin(new Insets(0, 0, 0, 0));



		//agendaB.setBackground(Color.white);
		agendaB.setOpaque(true);
		agendaB.setMaximumSize(new Dimension(60, 80));
		agendaB.setMinimumSize(new Dimension(30, 30));

		agendaB.setFont(new java.awt.Font("Dialog", 1, 10));
		agendaB.setPreferredSize(new Dimension(50, 50));
		agendaB.setBorderPainted(false);
		agendaB.setContentAreaFilled(false);
		agendaB.setFocusPainted(false);
		agendaB.setHorizontalTextPosition(SwingConstants.CENTER);
		agendaB.setText(Local.getString("Agenda"));
		agendaB.setVerticalAlignment(SwingConstants.TOP);
		agendaB.setVerticalTextPosition(SwingConstants.BOTTOM);
		agendaB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendaB_actionPerformed(e);
			}
		});
		ImageIcon originalIcon = new ImageIcon(
				memoranda.ui.AppFrame.class.getResource("/ui/icons/AgendaIcon.jpg")
		);
		Image img = originalIcon.getImage();
		Image resizedImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImg);
		agendaB.setIcon(resizedIcon);

		agendaB.setOpaque(true);
		agendaB.setMargin(new Insets(0, 0, 0, 0));
		agendaB.setSelected(true);

		//eventsB.setBackground(Color.white);
		eventsB.setOpaque(true);
		eventsB.setMaximumSize(new Dimension(60, 80));
		eventsB.setMinimumSize(new Dimension(30, 30));

		eventsB.setFont(new java.awt.Font("Dialog", 1, 10));
		eventsB.setPreferredSize(new Dimension(50, 50));
		eventsB.setBorderPainted(false);
		eventsB.setContentAreaFilled(false); //does this make it color it in? No
		eventsB.setFocusPainted(false);
		eventsB.setHorizontalTextPosition(SwingConstants.CENTER);
		eventsB.setText(Local.getString("Classes")); //this is the side panel for classes
		eventsB.setVerticalAlignment(SwingConstants.TOP);
		eventsB.setVerticalTextPosition(SwingConstants.BOTTOM);
		eventsB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsB_actionPerformed(e);
			}
		});
		ImageIcon classIcon = new ImageIcon(
				memoranda.ui.AppFrame.class.getResource("/ui/icons/classIcon.png")
		);
		Image resizedImage = classIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		eventsB.setIcon(new ImageIcon(resizedImage));

		eventsB.setOpaque(true);
		eventsB.setMargin(new Insets(0, 0, 0, 0));

		//eventsB.setSelected(true);

		tasksB.setSelected(true);
		tasksB.setFont(new java.awt.Font("Dialog", 1, 10));
		tasksB.setMargin(new Insets(0, 0, 0, 0));
		ImageIcon originalTaskIcon = new ImageIcon(
				memoranda.ui.AppFrame.class.getResource("/ui/icons/AssignmentsIcon.png")
		);
		Image taskImg = originalTaskIcon.getImage();
		Image resizedTaskImg = taskImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedTaskIcon = new ImageIcon(resizedTaskImg);
		tasksB.setIcon(resizedTaskIcon);

		tasksB.setVerticalTextPosition(SwingConstants.BOTTOM);
		tasksB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tasksB_actionPerformed(e);
			}
		});
		tasksB.setVerticalAlignment(SwingConstants.TOP);
		tasksB.setText(Local.getString("Assignments"));
		tasksB.setHorizontalTextPosition(SwingConstants.CENTER);
		tasksB.setFocusPainted(false);
		tasksB.setBorderPainted(false);
		tasksB.setContentAreaFilled(false);
		tasksB.setPreferredSize(new Dimension(50, 50));
		tasksB.setMinimumSize(new Dimension(30, 30));
		tasksB.setOpaque(true);
		tasksB.setMaximumSize(new Dimension(60, 80));
		//tasksB.setBackground(Color.white);

		notesB.setFont(new java.awt.Font("Dialog", 1, 10));
		//notesB.setBackground(Color.white);
		notesB.setBorder(null);
		notesB.setMaximumSize(new Dimension(60, 80));
		notesB.setMinimumSize(new Dimension(30, 30));
		notesB.setOpaque(true);
		notesB.setPreferredSize(new Dimension(60, 50));
		notesB.setBorderPainted(false);
		notesB.setContentAreaFilled(false);
		notesB.setFocusPainted(false);
		notesB.setHorizontalTextPosition(SwingConstants.CENTER);
		notesB.setText(Local.getString("Notes"));
		notesB.setVerticalAlignment(SwingConstants.TOP);
		notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		notesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notesB_actionPerformed(e);
			}
		});
		ImageIcon originalNotesIcon = new ImageIcon(
				memoranda.ui.AppFrame.class.getResource("/ui/icons/notesIcon.jpg")
		);
		Image notesImg = originalNotesIcon.getImage();
		Image resizedNotesImg = notesImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedNotesIcon = new ImageIcon(resizedNotesImg);
		notesB.setIcon(resizedNotesIcon);

		notesB.setMargin(new Insets(0, 0, 0, 0));
		notesB.setSelected(true);
		this.setPreferredSize(new Dimension(1073, 300));

		filesB.setSelected(true);
		filesB.setMargin(new Insets(0, 0, 0, 0));
		filesB.setIcon(
				new ImageIcon(
						memoranda.ui.AppFrame.class.getResource(
								"/ui/icons/files.png")));
		filesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		filesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filesB_actionPerformed(e);
			}
		});
		filesB.setFont(new java.awt.Font("Dialog", 1, 10));
		filesB.setVerticalAlignment(SwingConstants.TOP);
		filesB.setText(Local.getString("Resources"));
		filesB.setHorizontalTextPosition(SwingConstants.CENTER);
		filesB.setFocusPainted(false);
		filesB.setBorderPainted(false);
		filesB.setContentAreaFilled(false);
		filesB.setPreferredSize(new Dimension(50, 50));
		filesB.setMinimumSize(new Dimension(30, 30));
		filesB.setOpaque(true);
		filesB.setMaximumSize(new Dimension(60, 80));
		//filesB.setBackground(Color.white);
		this.add(toolBar, BorderLayout.WEST);
		this.add(panel, BorderLayout.CENTER);
		panel.add(dailyItemsPanel, "DAILYITEMS");
		panel.add(filesPanel, "FILES");

		if(AuthenticationService.getLoggedInUser(App.token).getTeacher()) {

			teacherPrivilegeB.setBackground(Color.white);
			teacherPrivilegeB.setMaximumSize(new Dimension(60, 80));
			teacherPrivilegeB.setMinimumSize(new Dimension(30, 30));
			teacherPrivilegeB.setFont(new java.awt.Font("Dialog", 1, 10));
			teacherPrivilegeB.setPreferredSize(new Dimension(50, 50));
			teacherPrivilegeB.setBorderPainted(false);
			teacherPrivilegeB.setContentAreaFilled(false);
			teacherPrivilegeB.setFocusPainted(false);
			teacherPrivilegeB.setHorizontalTextPosition(SwingConstants.CENTER);
			teacherPrivilegeB.setText(Local.getString("Teacher Privilege"));
			teacherPrivilegeB.setVerticalAlignment(SwingConstants.TOP);
			teacherPrivilegeB.setVerticalTextPosition(SwingConstants.BOTTOM);
			teacherPrivilegeB.addActionListener(e -> teacherPrivilegeB_actionPerformed(e));
			ImageIcon tpIcon = new ImageIcon(memoranda.ui.AppFrame.class.getResource("/ui/icons/logo123.png"));
			Image tpImg = tpIcon.getImage();
			Image resizedTpImg = tpImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			teacherPrivilegeB.setIcon(new ImageIcon(resizedTpImg));
			teacherPrivilegeB.setOpaque(false);
			teacherPrivilegeB.setMargin(new Insets(0, 0, 0, 0));
			panel.add(teacherPrivilegePanel, "TEACHER_PRIVILEGE");

		}

		toolBar.add(agendaB, null);
		toolBar.add(eventsB, null);
		toolBar.add(tasksB, null);
		toolBar.add(notesB, null);
		toolBar.add(filesB, null);
		toolBar.add(coursesB, null); // Added Courses Button to Toolbar
		if(AuthenticationService.getLoggedInUser(App.token).getTeacher()) {
			toolBar.add(teacherPrivilegeB, null);
		}
		toolBar.add(Box.createVerticalGlue());
		toolBar.add(userPanelB, null);
		currentB = agendaB;
		// Default blue color
		//currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);

		toolBar.setBorder(null);
		panel.setBorder(null);
		dailyItemsPanel.setBorder(null);
		filesPanel.setBorder(null);
		if(AuthenticationService.getLoggedInUser(App.token).getTeacher()) {
			teacherPrivilegePanel.setBorder(null);
		}

		// Check if the current user is a teacher, and only show the Teacher Privilege button if they are
		String isTeacher = (String) Context.get("IS_TEACHER");
		if (isTeacher != null && isTeacher.equals("true")) {
			teacherPrivilegeB.setVisible(true);
		} else {
			teacherPrivilegeB.setVisible(false);
		}
	}

	public void selectPanel(String pan) {
		if (pan != null) {
			if (pan.equals("NOTES"))
				notesB_actionPerformed(null);
			else if (pan.equals("TASKS"))
				tasksB_actionPerformed(null);
			else if (pan.equals("CLASSES"))
				eventsB_actionPerformed(null);
			else if (pan.equals("FILES"))
				filesB_actionPerformed(null);
			else if (pan.equals("COURSES"))
				coursesB_actionPerformed(null); // Added Courses Selection
			else if (pan.equals("TEACHER_PRIVILEGE"))
				teacherPrivilegeB_actionPerformed(null);
			else if (pan.equals("USER"))
				userPanelB_actionPerformed(null);
		}
	}

	public void agendaB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("AGENDA");
		setCurrentButton(agendaB);
		Context.put("CURRENT_PANEL", "AGENDA");
	}

	public void coursesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "COURSES");
		setCurrentButton(coursesB);
		Context.put("CURRENT_PANEL", "COURSES");
	}


	public void notesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("NOTES");
		setCurrentButton(notesB);
		Context.put("CURRENT_PANEL", "NOTES");
	}

	public void tasksB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("TASKS");
		setCurrentButton(tasksB);
		Context.put("CURRENT_PANEL", "TASKS");
	}

	public void eventsB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("CLASSES");
		setCurrentButton(eventsB);
		Context.put("CURRENT_PANEL", "CLASSES");
	}

	public void filesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "FILES");
		setCurrentButton(filesB);
		Context.put("CURRENT_PANEL", "FILES");
	}

	public void teacherPrivilegeB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "TEACHER_PRIVILEGE");
		setCurrentButton(teacherPrivilegeB);
		Context.put("CURRENT_PANEL", "TEACHER_PRIVILEGE");
	}

	public void userPanelB_actionPerformed(ActionEvent e) {
		UserPanel.startUserPanel(as);
		setCurrentButton(userPanelB);
	}

	void setCurrentButton(JButton cb) {
		if (currentB != null) {
			currentB.setBackground(Color.white);
			currentB.setOpaque(false);
		}
		currentB = cb;
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);

		// void setCurrentButton(JButton cb) { //this highlights the current button on the left panel when clicked
		//currentB.setBackground(Color.white);
		// currentB.setBackground(UIManager.getColor(cb));
		// currentB.setOpaque(true);
		// currentB = cb;
		//Default color blue
		// currentB.setBackground(new Color(215, 225, 250));
		// currentB.setOpaque(true);
	}

	public void addAuthAndToken(AuthenticationService authenticationService) {
		this.as = authenticationService;
	}
}