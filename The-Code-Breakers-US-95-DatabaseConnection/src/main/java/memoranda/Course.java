/**
 * Course.java
 * Created on 07.03.2025
 * Package: memoranda
 * 
 * @Joseph
 * Copyright (c) 2025 Memoranda Team. http://memoranda.sf.net
 *-----------------------------------------------------
 */
package memoranda;

import java.util.Date;
import java.awt.Color;

/**
 * Interface representing a Course in the Memoranda system.
 */
public interface Course {
    
    String getId();
    
    String getTitle();
    
    String getInstructor();
    
    Date getStartDate();
    
    Date getEndDate();
    
    int getCredits();
    
    String getDescription();
    
    nu.xom.Element getContent();
    
    boolean isActive();

	String getRedColor();
	
	void setRedColor(String redColor);
	
	String getGreenColor();
	
	void setGreenColor(String greenColor);
	
	String getBlueColor();
	
	void setBlueColor(String blueColor);
	
	String getCourseName();
}
