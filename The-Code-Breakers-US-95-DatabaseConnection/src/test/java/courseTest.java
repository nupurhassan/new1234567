package memoranda;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import memoranda.date.CalendarDate;
import memoranda.date.CurrentDate;
import nu.xom.Attribute;
import nu.xom.Element;


class courseTest {
	
	
	// @Test
    // @DisplayName("Example test")
    // void exampleTest() {
        // assertEquals(1, 1);
    // }
	
	@Test
	@DisplayName("Test to see if course manager correctly creates a course.")
	void testCourseCreationWorks() {
		
		CourseManager cm = new CourseManager();
		
		cm.createCourse("TestCourse", "123", "John Pork", "MWF");
		
		assertEquals(2, cm.getCourses().size());
	}
	
	@Test
	@DisplayName("Test to see if course manager correctly creates a course with the right title.")
	void testCourseCreationInfoWorks() {
		
		CourseManager cm = new CourseManager();
		
		cm.createCourse("TestCourse", "123", "John Pork", "MWF");
		
		assertEquals(cm.getCourses().get(0).getTitle(), "TestCourse");
	}
	
	@Test
	@DisplayName("Test to see if course manager correctly creates a course with the right instructor.")
	void testCourseCreationInstructorWorks() {
		
		CourseManager cm = new CourseManager();
		
		cm.createCourse("TestCourse", "123", "John Pork", "MWF");
		
		assertEquals(cm.getCourses().get(0).getInstructor(), "John Pork");
	}
	
	@Test
	@DisplayName("Test to see if course manager correctly returns names of the courses.")
	void testCourseGetNames() {
		
		CourseManager cm = new CourseManager();
		
		cm.createCourse("TestCourse", "123", "John Pork", "MWF");
		
		assertEquals(cm.getAllCourseNames().get(0), "TestCourse");
	}
	
	//tests from this point are for Sprint 4
	
	@Test
	@DisplayName("Test to see if color is the default on a course.")
	void testCourseGetColorsDefault() {
		
		CourseManager.createCourse("TestCourse", "123", "John Pork", "MWF");
		
		assertEquals(CourseManager.getCourses().get(0).getRedColor(), "225");
		assertEquals(CourseManager.getCourses().get(0).getGreenColor(), "225");
		assertEquals(CourseManager.getCourses().get(0).getBlueColor(), "225");
	}
	
	@Test
	@DisplayName("Test to see if the color is changed on a course.")
	void testCourseGetColors() {
		
		CourseManager.createCourse("TestCourse", "123", "John Pork", "MFW");
		
		CourseManager.updateCourse(CourseManager.getCourses().get(0).getId(),
		CourseManager.getCourses().get(0).getCourseName(),
		"test",
		CourseManager.getCourses().get(0).getInstructor(),
		"test",
		"100", "100", "100");
		
		assertEquals(CourseManager.getCourses().get(2).getRedColor(), "100");
		assertEquals(CourseManager.getCourses().get(2).getGreenColor(), "100");
		assertEquals(CourseManager.getCourses().get(2).getBlueColor(), "100");
	}
	
	@Test
	@DisplayName("Test to see if course is properly removed.")
	void testCourseRemove() {
		
		CourseManager.createCourse("TestCourse", "123", "John Pork", "MWF");
		
		int size = CourseManager.getCourses().size();
		
		CourseManager.removeCourse(CourseManager.getCourses().get(0).getId());
		
		assertEquals(CourseManager.getCourses().size(), size - 1);
	}
	
	@Test
	@DisplayName("Test that course edits stay.")
	void testCourseEdit() {
		
		CourseManager.createCourse("TestCourse2", "123", "John Pork", "MFW");
		
		int size = CourseManager.getCourses().size();
		
		CourseManager.updateCourse(CourseManager.getCourses().get(size - 1).getId(),
		"TestCourse2",
		"1234",
		"Tim Cheese",
		"MFW");
		
		assertEquals(CourseManager.getCourses().get(size - 1).getInstructor(), "Tim Cheese");
	}
	
}