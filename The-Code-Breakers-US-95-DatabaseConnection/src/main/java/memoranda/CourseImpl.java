package memoranda;

import java.util.Date;
import nu.xom.Element;
import java.awt.Color;

/**
 * A concrete implementation of the Course interface.
 */

public class CourseImpl implements Course {
    private String id;
    private String title;
    private String instructor;
    private Date startDate;
    private Date endDate;
    private int credits;
    private String description;
    private Element content; // Using XOM Element to hold course data
	private String redColor;
	private String greenColor;
	private String blueColor;


    public CourseImpl(String id, String title, String instructor, Date startDate, Date endDate, int credits, String description, String redColor, String greenColor, String blueColor) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credits = credits;
        this.description = description;
        this.content = new Element("course");
        this.content.addAttribute(new nu.xom.Attribute("id", id));
        this.content.addAttribute(new nu.xom.Attribute("title", title));
        this.content.addAttribute(new nu.xom.Attribute("instructor", instructor));
        this.content.addAttribute(new nu.xom.Attribute("startDate", startDate.toString()));
        this.content.addAttribute(new nu.xom.Attribute("endDate", endDate.toString()));
        this.content.addAttribute(new nu.xom.Attribute("credits", Integer.toString(credits)));
        this.content.addAttribute(new nu.xom.Attribute("description", description));
		this.redColor = redColor;
		this.greenColor = greenColor;
		this.blueColor = blueColor;
		this.content.addAttribute(new nu.xom.Attribute("redColor", redColor));
		this.content.addAttribute(new nu.xom.Attribute("greenColor", greenColor));
		this.content.addAttribute(new nu.xom.Attribute("blueColor", blueColor));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getInstructor() {
        return instructor;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Element getContent() {
        return content;
    }

    @Override
    public boolean isActive() {
        return new Date().before(endDate);
    }

    // Implement getCourseName() method
    @Override
    public String getCourseName() {
        return this.title; // return the title as the course name
    }
	
	public String getRedColor() {
		return redColor;
	}
	
	public void setRedColor(String selectedRedColor) {
		redColor = selectedRedColor;
	}
	
	public String getGreenColor() {
		return greenColor;
	}
	
	public void setGreenColor(String selectedGreenColor) {
		greenColor = selectedGreenColor;
	}
	
	public String getBlueColor() {
		return blueColor;
	}
	
	public void setBlueColor(String selectedBlueColor) {
		blueColor = selectedBlueColor;
	}
}

