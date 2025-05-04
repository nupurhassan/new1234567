package memoranda.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import memoranda.Course;
import memoranda.CourseManager;
import memoranda.util.Local;
import javax.swing.JColorChooser;

public class CoursesTable extends JTable {

    private Vector<Course> courses = new Vector<>();
    private CoursesTableModel model;
    public CoursesTable() {
        super();
        model = new CoursesTableModel();
        setModel(model);
        initTable();
        this.setShowGrid(false);
        this.setRowHeight(25);
        //this.setSelectionMode(SINGLE_SELECTION);
        this.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		this.setOpaque(false);
    }

    public void initTable() {
        courses = new Vector<>(CourseManager.getCourses());
        model.setCourses(courses);
        getColumnModel().getColumn(0).setPreferredWidth(200); // Course Name
        getColumnModel().getColumn(1).setPreferredWidth(150); // Instructor
        clearSelection();
        updateUI();
    }

    public void refresh() {
        initTable();
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
				
				
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Course course = model.getCourseAt(row);
                // comp.setForeground(Color.black);
				Color color = new Color(Integer.parseInt(course.getRedColor()), Integer.parseInt(course.getGreenColor()), Integer.parseInt(course.getBlueColor()));
				//comp.setBackground(color);
				if (course != null) {
					comp.setBackground(color);
				}
				
				if (course.isActive()) {
                    comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                    comp.setForeground(java.awt.Color.black);
					//comp.setBackground(color);
                }

                return comp;
            }
        };
    }

    // Provide access to Course objects directly
    public Course getSelectedCourse() {
        int row = getSelectedRow();
        if (row >= 0 && row < courses.size()) {
            return courses.get(row);
        }
        return null;
    }

    class CoursesTableModel extends AbstractTableModel {
        private final String[] columnNames = {
            Local.getString("Course Name"),
            Local.getString("Instructor")
        };

        public void setCourses(Vector<Course> newCourses) {
            courses = newCourses;
            fireTableDataChanged();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return courses != null ? courses.size() : 0;
        }

        @Override
        public Object getValueAt(int row, int col) {
            Course course = courses.get(row);
            switch (col) {
                case 0: return course.getTitle();
                case 1: return course.getInstructor();
                default: return null;
            }
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        // Helper to get the full Course object at a row
        public Course getCourseAt(int row) {
            return courses.get(row);
        }
    }
}

