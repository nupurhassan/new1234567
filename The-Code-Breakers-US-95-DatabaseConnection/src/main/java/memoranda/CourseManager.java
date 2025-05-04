package memoranda;

import java.util.*;
import nu.xom.*;
import memoranda.util.CurrentStorage;
import memoranda.util.Util;
import memoranda.CourseImpl;
import java.awt.Color;
import javax.swing.JColorChooser;

public class CourseManager {
	
    private static Document _doc = null;
    private static Element _root = null;
	private static Color color;
    private static List<Course> courses = new ArrayList<>(); // Fix: Added missing courses list

    static {
        if (_doc == null) {
            _root = new Element("courselist");
            _doc = new Document(_root);
        } else {
            _root = _doc.getRootElement();
        }
        loadCoursesFromXML();  // Fix: Populate `courses` list from XML
    }

    // Load courses from XML into the list
    private static void loadCoursesFromXML() {
        courses.clear(); // Ensure the list is reset
        Elements els = _root.getChildElements("course");
        for (int i = 0; i < els.size(); i++) {
            Element courseElement = els.get(i);
            String id = courseElement.getAttribute("id").getValue();
            String title = courseElement.getAttribute("title").getValue();
            String instructor = courseElement.getAttribute("instructor").getValue();
            Date startDate = new Date(courseElement.getAttribute("startDate").getValue());
            Date endDate = new Date(courseElement.getAttribute("endDate").getValue());
            int credits = Integer.parseInt(courseElement.getAttribute("credits").getValue());
            String description = courseElement.getAttribute("description").getValue();
			String redColor = courseElement.getAttribute("redColor").getValue();
			String greenColor = courseElement.getAttribute("greenColor").getValue();
			String blueColor = courseElement.getAttribute("blueColor").getValue();
			
			//Color selectedColor = new Color(redColor, greenColor, blueColor);
			System.out.println(courses.size());
			
			
            Course course = new CourseImpl(id, title, instructor, startDate, endDate, credits, description, redColor, greenColor, blueColor);
            courses.add(course);
        }
    }
	
	// Create a new course and store it
	public static void createCourse(String name, String courseCode, String instructor, String schedule) {
        // Assuming you are parsing dates properly here
        Date startDate = new Date();  // Example, parse actual date here
        Date endDate = new Date();  // Example, parse actual date here
        int credits = 3;  // Example placeholder
        String description = "Course Description";  // Example placeholder
		
		color = new Color(225, 225, 225);
		String redColor = "";
		redColor += color.getRed();
		String greenColor = "";
		greenColor += color.getGreen();
		String blueColor = "";
		blueColor += color.getBlue();
        Course course = new CourseImpl(Util.generateId(), name, instructor, startDate, endDate, credits, description, redColor, greenColor, blueColor);
        courses.add(course); //Fix: Ensure the course is added to the list
		Element el = course.getContent();
        _root.appendChild(el);
    }

    // Get all courses
    public static List<Course> getCourses() {
        return new ArrayList<>(courses); // Fix: Return a copy of the list
    }

    // Remove a course
    public static void removeCourse(String courseId) {
        courses.removeIf(course -> course.getId().equals(courseId)); // Fix: Remove from list
        Elements els = _root.getChildElements("course");
        for (int i = 0; i < els.size(); i++) {
            Element course = els.get(i);
            if (course.getAttribute("id").getValue().equals(courseId)) {
                _root.removeChild(course);
                break;
            }
        }
    }
	
	// Update a course
    public static void updateCourse(String courseId, String name, String courseCode, String instructor, String schedule, String redColor, String greenColor, String blueColor) {

        Elements els = _root.getChildElements("course");
		
        for (int i = 0; i < els.size(); i++) {
            Element courseElement = els.get(i);
            if (courseElement.getAttribute("id").getValue().equals(courseId)) {
                courseElement.addAttribute(new Attribute("title", name));
                courseElement.addAttribute(new Attribute("code", courseCode));
                courseElement.addAttribute(new Attribute("instructor", instructor));
                courseElement.addAttribute(new Attribute("schedule", schedule));
				
				courseElement.addAttribute(new Attribute("redColor", redColor));
				courseElement.addAttribute(new Attribute("greenColor", greenColor));
				courseElement.addAttribute(new Attribute("blueColor", blueColor));

                // Fix: Also update the in-memory list
                // course.setTitle(name);
                // course.setInstructor(instructor);
				
                break;
            }
        }
		
		// Remove old course from list and add an updated version
		courses.removeIf(c -> c.getId().equals(courseId));
		Date startDate = new Date(); // Placeholder
		Date endDate = new Date(); // Placeholder
		int credits = 3; // Placeholder
		String description = "Updated Description"; // Placeholder

		Course updatedCourse = new CourseImpl(courseId, name, instructor, startDate, endDate, credits, description, redColor, greenColor, blueColor);
		courses.add(updatedCourse);
    }
    
    public static void updateCourse(String courseId, String name, String courseCode, String instructor, String schedule) {
    Elements els = _root.getChildElements("course");
    
    for (int i = 0; i < els.size(); i++) {
        Element courseElement = els.get(i);
        if (courseElement.getAttribute("id").getValue().equals(courseId)) {
            courseElement.addAttribute(new Attribute("title", name));
            courseElement.addAttribute(new Attribute("code", courseCode));
            courseElement.addAttribute(new Attribute("instructor", instructor));
            courseElement.addAttribute(new Attribute("schedule", schedule));
            break;
        }
    }
	courses.removeIf(c -> c.getId().equals(courseId));
	Date startDate = new Date();
	Date endDate = new Date();
	int credits = 3;
	String description = "Updated";
	
	Course updatedCourse = new CourseImpl(courseId, name, instructor, startDate, endDate, credits, description, "100", "100", "100");
	courses.add(updatedCourse);
}


    // Get course names for dropdown
    public static List<String> getCourseNamesForDropdown() {
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getTitle());
        }
        return courseNames;
    }

    // Get all course names
    public static List<String> getAllCourseNames() {
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getTitle());
        }
        return courseNames;
    }
}





