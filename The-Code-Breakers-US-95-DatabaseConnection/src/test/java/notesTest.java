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


class notesTest {
	
	
	// @Test
    // @DisplayName("Example test")
    // void exampleTest() {
        // assertEquals(1, 1);
    // }
	
	
	
	@Test
	@DisplayName("Test note list's getDate returns the right date with correct date.")
	void noteListGetDateCorrect() {
		
		Element element = new Element("note");
		
		ProjectImpl prj = new ProjectImpl(element);
		NoteListImpl notelist = new NoteListImpl(prj);
		CalendarDate currDate = new CalendarDate(23, 3, 2025);
		
		Note currNote = notelist.createNoteForDate(currDate);
		
		assertEquals(currNote.getDate().toString(), currDate.toString());
		
	}
	
	@Test
	@DisplayName("Test note list's getDate returns the right date with wrong date.")
	void noteListGetDateWrong() {
		
		Element element = new Element("note");
		
		ProjectImpl prj = new ProjectImpl(element);
		NoteListImpl notelist = new NoteListImpl(prj);
		CalendarDate currDate = new CalendarDate(23, 3, 2025);
		
		Note currNote = notelist.createNoteForDate(currDate);
		
		assertNotEquals(currNote.getDate().toString(), currDate.yesterday().toString());
		
	}
	
	@Test
	@DisplayName("Test note list's getNoteForDate with the correct note.")
	void noteListGetCorrectNote() {
		
		Element element = new Element("note");
		
		ProjectImpl prj = new ProjectImpl(element);
		NoteListImpl notelist = new NoteListImpl(prj);
		CalendarDate currDate = new CalendarDate(23, 3, 2025);
		
		Note currNote = notelist.createNoteForDate(currDate);
		currNote.setId("1");
		
		assertEquals(notelist.getNoteForDate(currDate).getId(), currNote.getId());
		
	}
	
	@Test
	@DisplayName("Test note list's getNoteForDate with the wrong note.")
	void noteListGetWrongNote() {
		
		Element element = new Element("note");
		
		ProjectImpl prj = new ProjectImpl(element);
		NoteListImpl notelist = new NoteListImpl(prj);
		CalendarDate currDate = new CalendarDate(23, 3, 2025);
		CalendarDate otherDate = new CalendarDate(22, 3, 2025);
		
		Note currNote = notelist.createNoteForDate(currDate);
		Note otherNote = notelist.createNoteForDate(otherDate);
		currNote.setId("1");
		otherNote.setId("2");
		
		assertNotEquals(notelist.getNoteForDate(currDate).getId(), otherNote.getId());
		
	}
}