package memoranda.ui;

import java.io.File;
import java.util.Vector;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;

import memoranda.util.Configuration;
import memoranda.util.CurrentStorage;
import memoranda.util.Local;
import memoranda.util.MimeTypesList;

import java.awt.event.*;

/*$Id: PreferencesDialog.java,v 1.16 2006/06/28 22:58:31 alexeya Exp $*/
public class PreferencesDialog extends JDialog {
	
	JPanel topPanel = new JPanel(new BorderLayout());

	JTabbedPane tabbedPanel = new JTabbedPane();

	JPanel GeneralPanel = new JPanel(new GridBagLayout());

	GridBagConstraints gbc;

	JLabel jLabel1 = new JLabel();

	ButtonGroup minGroup = new ButtonGroup();

	JRadioButton minTaskbarRB = new JRadioButton();

	JRadioButton minHideRB = new JRadioButton();

	ButtonGroup closeGroup = new ButtonGroup();

	JLabel jLabel2 = new JLabel();

	JRadioButton closeExitRB = new JRadioButton();

	JCheckBox askConfirmChB = new JCheckBox();

	JRadioButton closeHideRB = new JRadioButton();

	//Code No Longer In Use

//	JLabel jLabel3 = new JLabel();

//	ButtonGroup lfGroup = new ButtonGroup();
//
//	JRadioButton lfSystemRB = new JRadioButton();
//
//	JRadioButton lfJavaRB = new JRadioButton();
//
//	JRadioButton lfCustomRB = new JRadioButton();

//	JLabel classNameLabel = new JLabel();

//	JTextField lfClassName = new JTextField();

	JLabel jLabel4 = new JLabel();

	JCheckBox enSystrayChB = new JCheckBox();

	JCheckBox startMinimizedChB = new JCheckBox();

	JCheckBox enSplashChB = new JCheckBox();

	JCheckBox enL10nChB = new JCheckBox();

	JCheckBox firstdow = new JCheckBox();

	JPanel resourcePanel = new JPanel(new BorderLayout());

	ResourceTypePanel resourceTypePanel = new ResourceTypePanel();

	Border rstPanelBorder;

	JPanel rsBottomPanel = new JPanel(new GridBagLayout());

	TitledBorder rsbpBorder;

	JButton okB = new JButton();

	JButton cancelB = new JButton();

	JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

	JLabel jLabel5 = new JLabel();

	JTextField browserPath = new JTextField();

	JButton browseB = new JButton();

	JLabel lblExit = new JLabel();

	JPanel soundPanel = new JPanel();

	JCheckBox enableSoundCB = new JCheckBox();

	BorderLayout borderLayout1 = new BorderLayout();

	TitledBorder titledBorder1;

	ButtonGroup soundGroup = new ButtonGroup();

	JPanel jPanel2 = new JPanel();

	JButton soundFileBrowseB = new JButton();

	GridLayout gridLayout1 = new GridLayout();

	JPanel jPanel1 = new JPanel();

	JRadioButton soundBeepRB = new JRadioButton();

	JLabel jLabel6 = new JLabel();

	JTextField soundFile = new JTextField();

	JRadioButton soundDefaultRB = new JRadioButton();

	BorderLayout borderLayout3 = new BorderLayout();

	JPanel jPanel3 = new JPanel();

	JRadioButton soundCustomRB = new JRadioButton();

	BorderLayout borderLayout2 = new BorderLayout();
	
	JPanel themeConfigPanel = new JPanel();
	
	JButton pickColorButton = new JButton("Choose theme color");
	
	JRadioButton lightModeButton = new JRadioButton("Light Mode");
	
	JRadioButton darkModeButton = new JRadioButton("Dark Mode");
	
	JPanel editorConfigPanel = new JPanel(new BorderLayout());
	JPanel econfPanel = new JPanel(new GridLayout(5, 2));
	Vector fontnames = getFontNames();
	JComboBox normalFontCB = new JComboBox(fontnames);
	JComboBox headerFontCB = new JComboBox(fontnames);
	JComboBox monoFontCB = new JComboBox(fontnames);
	JSpinner baseFontSize = new JSpinner();
	JCheckBox antialiasChB = new JCheckBox();
	JLabel normalFontLabel = new JLabel();
	JLabel headerFontLabel = new JLabel();
	JLabel monoFontLabel = new JLabel();
	JLabel baseFontSizeLabel = new JLabel();
	
	
	
	
	public PreferencesDialog(Frame frame) {
		super(frame, Local.getString("Preferences"), true);
		try {
			jbInit(frame);
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	public PreferencesDialog() {
		this(null);
	}
	
	void jbInit(Frame frame) throws Exception {
		titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(
				), Local
				.getString("Sound"));
		this.setResizable(false);
		// Build Tab1
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText(Local.getString("Window minimize action:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		enableSoundCB.setText(Local.getString("Enable sound notifications"));
		enableSoundCB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableSoundCB_actionPerformed(e);
			}
		});
		
		
		themeConfigPanel.add(lightModeButton);
		themeConfigPanel.add(darkModeButton);
		themeConfigPanel.add(pickColorButton);
		
		ButtonGroup themeGroup = new ButtonGroup();
		themeGroup.add(lightModeButton);
		themeGroup.add(darkModeButton);
		
		
		
		//themeConfigPanel.setLayout(borderLayout1);
		
		pickColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color pickedColor = JColorChooser.showDialog(null, "Pick a theme color", Color.black); 
				if (pickedColor != null) {
					ColorUIResource baseColor = new ColorUIResource(pickedColor);
					ColorUIResource darkerColor = new ColorUIResource(pickedColor.darker());
					ColorUIResource lighterColor = new ColorUIResource(pickedColor.brighter());
					
					double red = baseColor.getRed() / 255.0;
					double green = baseColor.getGreen() / 255.0;
					double blue = baseColor.getBlue() / 255.0;
					
					double luminanceVal = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
					ColorUIResource textColor;
					if (luminanceVal < 0.5) {
						textColor = new ColorUIResource(baseColor.brighter().brighter().brighter());
					}
					else {
						textColor = new ColorUIResource(baseColor.darker().darker().darker());
					}
					
					
					UIManager.put("Button.background", baseColor);
					UIManager.put("Button.foreground", textColor);
					
					UIManager.put("Button.select", baseColor);
					UIManager.put("Button.shadow", darkerColor);

					UIManager.put("CheckBox.background", baseColor);
					UIManager.put("CheckBox.foreground", textColor);
					
					UIManager.put("CheckBox.select", baseColor);

					UIManager.put("CheckBoxMenuItem.background", baseColor);
					UIManager.put("CheckBoxMenuItem.foreground", textColor);
					UIManager.put("CheckBoxMenuItem.selectionBackground", baseColor);
					UIManager.put("CheckBoxMenuItem.selectionforeground", textColor);
					UIManager.put("CheckBoxMenuItem.disabledBackground", baseColor);
					UIManager.put("CheckBoxMenuItem.disabledforeground", textColor);
					UIManager.put("ColorChooser.background", baseColor);
					UIManager.put("ColorChooser.foreground", textColor);
					UIManager.put("ComboBox.background", baseColor);
					UIManager.put("ComboBox.foreground", textColor);
					UIManager.put("ComboBox.buttonBackground", baseColor);
					UIManager.put("ComboBox.buttonDarkShadow", baseColor);
					UIManager.put("ComboBox.buttonHighlight", baseColor);
					UIManager.put("ComboBox.buttonShadow", baseColor);
					UIManager.put("ComboBox.disabledBackground", baseColor);
					UIManager.put("ComboBox.disabledforeground", textColor);
					UIManager.put("ComboBox.foreground", textColor);
					UIManager.put("ComboBox.selectionBackground", baseColor);
					UIManager.put("ComboBox.selectionforeground", textColor);

					UIManager.put("Desktop.background", baseColor);
					

					UIManager.put("EditorPane.background", baseColor);
					UIManager.put("EditorPane.foreground", textColor);
					UIManager.put("EditorPane.selectionBackground", baseColor);
					UIManager.put("EditorPane.selectionforeground", textColor);
					UIManager.put("EditorPane.inactiveBackground", baseColor);
					UIManager.put("EditorPane.inactiveforeground", textColor);

					UIManager.put("FormattedTextField.background", baseColor);
					UIManager.put("FormattedTextField.foreground", textColor);
					UIManager.put("FormattedTextField.selectionBackground", baseColor);
					UIManager.put("FormattedTextField.selectionforeground", textColor);
					UIManager.put("FormattedTextField.inactiveBackground", baseColor);
					UIManager.put("FormattedTextField.inactiveforeground", textColor);

					UIManager.put("IconButton.font", baseColor);
					UIManager.put("InsetBorder.aquaVariant", baseColor);

					UIManager.put("InternalFrame.activeTitleBackground", baseColor);
					UIManager.put("InternalFrame.activeTitleForeground", baseColor);
					UIManager.put("InternalFrame.background", baseColor);
					
					UIManager.put("InternalFrame.optionDialogBackground", baseColor);
					UIManager.put("InternalFrame.paletteBackground", baseColor);

					UIManager.put("Label.background", baseColor);
					UIManager.put("Label.disabledforeground", textColor);
					UIManager.put("Label.disabledShadow", baseColor);
					UIManager.put("Label.foreground", textColor);

					UIManager.put("List.background", baseColor);
					
					UIManager.put("List.dropLineColor", baseColor);
					UIManager.put("List.evenRowBackgroundPainter", baseColor);
					UIManager.put("List.focusCellHighlightBorder", baseColor);
					UIManager.put("List.foreground", textColor);
					UIManager.put("List.oddRowBackgroundPainter", baseColor);
					UIManager.put("List.selectionBackground", baseColor);
					UIManager.put("List.selectionforeground", textColor);
					UIManager.put("List.sourceListBackgroundPainter", baseColor);
					UIManager.put("List.sourceListFocusedSelectionBackgroundPainter", baseColor);
					UIManager.put("List.sourceListSelectionBackgroundPainter", baseColor);

					
					UIManager.put("Menu.background", baseColor);
					
					UIManager.put("Menu.disabledBackground", baseColor);
					UIManager.put("Menu.disabledforeground", textColor);
					UIManager.put("Menu.foreground", textColor);
					UIManager.put("Menu.selectionBackground", baseColor);
					UIManager.put("Menu.selectionforeground", textColor);

					UIManager.put("MenuBar.background", baseColor);
					
					UIManager.put("MenuBar.disabledBackground", baseColor);
					UIManager.put("MenuBar.disabledforeground", textColor);
					UIManager.put("MenuBar.foreground", textColor);
					UIManager.put("MenuBar.selectionBackground", baseColor);
					UIManager.put("MenuBar.selectionforeground", textColor);

					UIManager.put("MenuItem.acceleratorForeground", baseColor);
					UIManager.put("MenuItem.acceleratorSelectionForeground", baseColor);
					UIManager.put("MenuItem.background", baseColor);
					
					UIManager.put("MenuItem.disabledBackground", baseColor);
					UIManager.put("MenuItem.disabledforeground", textColor);
					UIManager.put("MenuItem.foreground", textColor);
					UIManager.put("MenuItem.selectionBackground", baseColor);
					UIManager.put("MenuItem.selectionforeground", textColor);

					UIManager.put("OptionPane.background", baseColor);
					
					UIManager.put("OptionPane.foreground", textColor);
					UIManager.put("OptionPane.messageForeground", baseColor);

					UIManager.put("Panel.background", baseColor);
					UIManager.put("Panel.foreground", textColor);

					UIManager.put("PasswordField.background", baseColor);
					UIManager.put("PasswordField.foreground", textColor);
					UIManager.put("PasswordField.inactiveBackground", baseColor);
					UIManager.put("PasswordField.inactiveforeground", textColor);
					UIManager.put("PasswordField.selectionBackground", baseColor);
					UIManager.put("PasswordField.selectionforeground", textColor);

					UIManager.put("PopupMenu.background", baseColor);
					
					UIManager.put("PopupMenu.foreground", textColor);
					UIManager.put("PopupMenu.selectionBackground", baseColor);
					UIManager.put("PopupMenu.selectionforeground", textColor);

					UIManager.put("ProgressBar.background", baseColor);
				
					UIManager.put("ProgressBar.foreground", textColor);
					UIManager.put("ProgressBar.selectionBackground", baseColor);
					UIManager.put("ProgressBar.selectionforeground", textColor);

				

					UIManager.put("ScrollBar.background", baseColor);
					UIManager.put("ScrollBar.foreground", textColor);
					UIManager.put("ScrollBar.thumb", baseColor);
					UIManager.put("ScrollBar.thumbDarkShadow", baseColor);
					UIManager.put("ScrollBar.thumbHighlight", baseColor);
					UIManager.put("ScrollBar.thumbShadow", baseColor);
					UIManager.put("ScrollBar.track", baseColor);

					UIManager.put("ScrollPane.background", baseColor);
				
					UIManager.put("ScrollPane.foreground", textColor);

					UIManager.put("Separator.foreground", textColor);
					UIManager.put("Separator.highlight", lighterColor);
					UIManager.put("Separator.shadow", darkerColor);

					UIManager.put("Slider.background", baseColor);
					UIManager.put("Slider.foreground", textColor);
					UIManager.put("Slider.highlight", lighterColor);
					UIManager.put("Slider.shadow", darkerColor);
					UIManager.put("Slider.tickColor", baseColor);

					UIManager.put("Spinner.background", baseColor);
					UIManager.put("Spinner.foreground", textColor);

					UIManager.put("SplitPane.background", baseColor);
					
					UIManager.put("SplitPane.highlight", lighterColor);
					UIManager.put("SplitPane.shadow", darkerColor);
					UIManager.put("SplitPaneDivider.draggingColor", baseColor);

					UIManager.put("TabbedPane.background", baseColor);
					UIManager.put("TabbedPane.foreground", textColor);
					UIManager.put("TabbedPane.highlight", lighterColor);
					UIManager.put("TabbedPane.light", lighterColor);
					UIManager.put("TabbedPane.shadow", darkerColor);
					UIManager.put("TabbedPane.selectedTabPadInsets", baseColor);

					UIManager.put("Table.background", baseColor);
					UIManager.put("Table.foreground", textColor);
					UIManager.put("Table.gridColor", baseColor);
					UIManager.put("Table.selectionBackground", baseColor);
					UIManager.put("Table.selectionforeground", textColor);

					UIManager.put("TableHeader.background", baseColor);
					UIManager.put("TableHeader.foreground", textColor);

					UIManager.put("TextArea.background", baseColor);
					UIManager.put("TextArea.foreground", textColor);
					UIManager.put("TextArea.selectionBackground", baseColor);
					UIManager.put("TextArea.selectionforeground", textColor);

					UIManager.put("TextField.background", baseColor);
					UIManager.put("TextField.foreground", textColor);
					UIManager.put("TextField.selectionBackground", baseColor);
					UIManager.put("TextField.selectionforeground", textColor);

					UIManager.put("TextPane.background", baseColor);
					UIManager.put("TextPane.foreground", textColor);
					UIManager.put("TextPane.selectionBackground", baseColor);
					UIManager.put("TextPane.selectionforeground", textColor);

					UIManager.put("ToggleButton.background", baseColor);
					UIManager.put("ToggleButton.foreground", textColor);
					UIManager.put("ToggleButton.highlight", lighterColor);
					UIManager.put("ToggleButton.light", lighterColor);
					UIManager.put("ToggleButton.shadow", darkerColor);

					UIManager.put("ToolBar.background", baseColor);
					UIManager.put("ToolBar.foreground", textColor);
					UIManager.put("ToolBar.highlight", lighterColor);
					UIManager.put("ToolBar.light", lighterColor);
					UIManager.put("ToolBar.shadow", darkerColor);

					UIManager.put("ToolTip.background", baseColor);
					UIManager.put("ToolTip.foreground", textColor);

					UIManager.put("Tree.background", baseColor);
					UIManager.put("Tree.foreground", textColor);
					UIManager.put("Tree.selectionBackground", baseColor);
					UIManager.put("Tree.selectionforeground", textColor);

					UIManager.put("Viewport.background", baseColor);
					UIManager.put("Viewport.foreground", textColor);

					UIManager.put("window", baseColor);
					UIManager.put("windowText", baseColor);
					
					
					UIManager.put("Panel.background", baseColor);
					UIManager.put("Panel.foreground", textColor);
					UIManager.put("Button.background", darkerColor);
					UIManager.put("Button.foreground", textColor);
					
			
					
					UIManager.put("SplitPane.background", baseColor);
					UIManager.put("SplitPane.highlight", lighterColor);
					UIManager.put("SplitPane.shadow", darkerColor);
					UIManager.put("SplitPaneDivider.draggingColor", baseColor);
					
					UIManager.put("TextArea.background", baseColor);
					UIManager.put("TextArea.foreground", textColor);
					UIManager.put("TextArea.selectionBackground", baseColor);
					UIManager.put("TextArea.selectionforeground", textColor);

					UIManager.put("TextField.background", baseColor);
					UIManager.put("TextField.foreground", textColor);
					UIManager.put("TextField.selectionBackground", baseColor);
					UIManager.put("TextField.selectionforeground", textColor);

					UIManager.put("TextPane.background", baseColor);
					UIManager.put("TextPane.foreground", textColor);
					UIManager.put("TextPane.selectionBackground", baseColor);
					UIManager.put("TextPane.selectionforeground", textColor);
					
					UIManager.put("InternalFrame.activeTitleBackground", baseColor);
					UIManager.put("InternalFrame.activeTitleforeground", textColor);
					UIManager.put("InternalFrame.background", baseColor);
				
					
					UIManager.put("InternalFrame.optionDialogBackground", baseColor);
					UIManager.put("InternalFrame.paletteBackground", baseColor);
					
					UIManager.put("ImageIcon.background", baseColor);
					UIManager.put("ImageIcon.foreground", textColor);
					
					UIManager.put("table.AbstractTableModel.background", baseColor);
					UIManager.put("table.AbstractTableModel.foreground", textColor);
					
					
					
					for (Window window : Window.getWindows()) {
						SwingUtilities.updateComponentTreeUI(window);
						window.repaint();
					}
					
					setColor(frame, pickedColor);
					
					
					
				}
			}
		});
		
		
		darkModeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Color colorBase = new Color(71, 71, 71);
					ColorUIResource baseColor = new ColorUIResource(71, 71, 71);
					ColorUIResource darkerColor = new ColorUIResource(61, 61, 61);
					ColorUIResource lighterColor = new ColorUIResource(81, 81, 81);
					
					ColorUIResource textColor = new ColorUIResource(0, 0, 0);
					
					
					UIManager.put("Button.background", baseColor);
					UIManager.put("Button.foreground", textColor);
					
					UIManager.put("Button.select", baseColor);
					UIManager.put("Button.shadow", darkerColor);

					UIManager.put("CheckBox.background", baseColor);
					UIManager.put("CheckBox.foreground", textColor);
					
					UIManager.put("CheckBox.select", baseColor);

					UIManager.put("CheckBoxMenuItem.background", baseColor);
					UIManager.put("CheckBoxMenuItem.foreground", textColor);
					UIManager.put("CheckBoxMenuItem.selectionBackground", baseColor);
					UIManager.put("CheckBoxMenuItem.selectionforeground", textColor);
					UIManager.put("CheckBoxMenuItem.disabledBackground", baseColor);
					UIManager.put("CheckBoxMenuItem.disabledforeground", textColor);
					UIManager.put("ColorChooser.background", baseColor);
					UIManager.put("ColorChooser.foreground", textColor);
					UIManager.put("ComboBox.background", baseColor);
					UIManager.put("ComboBox.foreground", textColor);
					UIManager.put("ComboBox.buttonBackground", baseColor);
					UIManager.put("ComboBox.buttonDarkShadow", baseColor);
					UIManager.put("ComboBox.buttonHighlight", baseColor);
					UIManager.put("ComboBox.buttonShadow", baseColor);
					UIManager.put("ComboBox.disabledBackground", baseColor);
					UIManager.put("ComboBox.disabledforeground", textColor);
					UIManager.put("ComboBox.foreground", textColor);
					UIManager.put("ComboBox.selectionBackground", baseColor);
					UIManager.put("ComboBox.selectionforeground", textColor);

					UIManager.put("Desktop.background", baseColor);
					

					UIManager.put("EditorPane.background", baseColor);
					UIManager.put("EditorPane.foreground", textColor);
					UIManager.put("EditorPane.selectionBackground", baseColor);
					UIManager.put("EditorPane.selectionforeground", textColor);
					UIManager.put("EditorPane.inactiveBackground", baseColor);
					UIManager.put("EditorPane.inactiveforeground", textColor);

					UIManager.put("FormattedTextField.background", baseColor);
					UIManager.put("FormattedTextField.foreground", textColor);
					UIManager.put("FormattedTextField.selectionBackground", baseColor);
					UIManager.put("FormattedTextField.selectionforeground", textColor);
					UIManager.put("FormattedTextField.inactiveBackground", baseColor);
					UIManager.put("FormattedTextField.inactiveforeground", textColor);

					UIManager.put("IconButton.font", baseColor);
					UIManager.put("InsetBorder.aquaVariant", baseColor);

					UIManager.put("InternalFrame.activeTitleBackground", baseColor);
					UIManager.put("InternalFrame.activeTitleForeground", baseColor);
					UIManager.put("InternalFrame.background", baseColor);
					
					UIManager.put("InternalFrame.optionDialogBackground", baseColor);
					UIManager.put("InternalFrame.paletteBackground", baseColor);

					UIManager.put("Label.background", baseColor);
					UIManager.put("Label.disabledforeground", textColor);
					UIManager.put("Label.disabledShadow", baseColor);
					UIManager.put("Label.foreground", textColor);

					UIManager.put("List.background", baseColor);
					
					UIManager.put("List.dropLineColor", baseColor);
					UIManager.put("List.evenRowBackgroundPainter", baseColor);
					UIManager.put("List.focusCellHighlightBorder", baseColor);
					UIManager.put("List.foreground", textColor);
					UIManager.put("List.oddRowBackgroundPainter", baseColor);
					UIManager.put("List.selectionBackground", baseColor);
					UIManager.put("List.selectionforeground", textColor);
					UIManager.put("List.sourceListBackgroundPainter", baseColor);
					UIManager.put("List.sourceListFocusedSelectionBackgroundPainter", baseColor);
					UIManager.put("List.sourceListSelectionBackgroundPainter", baseColor);

					
					UIManager.put("Menu.background", baseColor);
					
					UIManager.put("Menu.disabledBackground", baseColor);
					UIManager.put("Menu.disabledforeground", textColor);
					UIManager.put("Menu.foreground", textColor);
					UIManager.put("Menu.selectionBackground", baseColor);
					UIManager.put("Menu.selectionforeground", textColor);

					UIManager.put("MenuBar.background", baseColor);
					
					UIManager.put("MenuBar.disabledBackground", baseColor);
					UIManager.put("MenuBar.disabledforeground", textColor);
					UIManager.put("MenuBar.foreground", textColor);
					UIManager.put("MenuBar.selectionBackground", baseColor);
					UIManager.put("MenuBar.selectionforeground", textColor);

					UIManager.put("MenuItem.acceleratorForeground", baseColor);
					UIManager.put("MenuItem.acceleratorSelectionForeground", baseColor);
					UIManager.put("MenuItem.background", baseColor);
					
					UIManager.put("MenuItem.disabledBackground", baseColor);
					UIManager.put("MenuItem.disabledforeground", textColor);
					UIManager.put("MenuItem.foreground", textColor);
					UIManager.put("MenuItem.selectionBackground", baseColor);
					UIManager.put("MenuItem.selectionforeground", textColor);

					UIManager.put("OptionPane.background", baseColor);
					
					UIManager.put("OptionPane.foreground", textColor);
					UIManager.put("OptionPane.messageForeground", baseColor);

					UIManager.put("Panel.background", baseColor);
					UIManager.put("Panel.foreground", textColor);

					UIManager.put("PasswordField.background", baseColor);
					UIManager.put("PasswordField.foreground", textColor);
					UIManager.put("PasswordField.inactiveBackground", baseColor);
					UIManager.put("PasswordField.inactiveforeground", textColor);
					UIManager.put("PasswordField.selectionBackground", baseColor);
					UIManager.put("PasswordField.selectionforeground", textColor);

					UIManager.put("PopupMenu.background", baseColor);
					
					UIManager.put("PopupMenu.foreground", textColor);
					UIManager.put("PopupMenu.selectionBackground", baseColor);
					UIManager.put("PopupMenu.selectionforeground", textColor);

					UIManager.put("ProgressBar.background", baseColor);
				
					UIManager.put("ProgressBar.foreground", textColor);
					UIManager.put("ProgressBar.selectionBackground", baseColor);
					UIManager.put("ProgressBar.selectionforeground", textColor);

				

					UIManager.put("ScrollBar.background", baseColor);
					UIManager.put("ScrollBar.foreground", textColor);
					UIManager.put("ScrollBar.thumb", baseColor);
					UIManager.put("ScrollBar.thumbDarkShadow", baseColor);
					UIManager.put("ScrollBar.thumbHighlight", baseColor);
					UIManager.put("ScrollBar.thumbShadow", baseColor);
					UIManager.put("ScrollBar.track", baseColor);

					UIManager.put("ScrollPane.background", baseColor);
				
					UIManager.put("ScrollPane.foreground", textColor);

					UIManager.put("Separator.foreground", textColor);
					UIManager.put("Separator.highlight", lighterColor);
					UIManager.put("Separator.shadow", darkerColor);

					UIManager.put("Slider.background", baseColor);
					UIManager.put("Slider.foreground", textColor);
					UIManager.put("Slider.highlight", lighterColor);
					UIManager.put("Slider.shadow", darkerColor);
					UIManager.put("Slider.tickColor", baseColor);

					UIManager.put("Spinner.background", baseColor);
					UIManager.put("Spinner.foreground", textColor);

					UIManager.put("SplitPane.background", baseColor);
					
					UIManager.put("SplitPane.highlight", lighterColor);
					UIManager.put("SplitPane.shadow", darkerColor);
					UIManager.put("SplitPaneDivider.draggingColor", baseColor);

					UIManager.put("TabbedPane.background", baseColor);
					UIManager.put("TabbedPane.foreground", textColor);
					UIManager.put("TabbedPane.highlight", lighterColor);
					UIManager.put("TabbedPane.light", lighterColor);
					UIManager.put("TabbedPane.shadow", darkerColor);
					UIManager.put("TabbedPane.selectedTabPadInsets", baseColor);

					UIManager.put("Table.background", baseColor);
					UIManager.put("Table.foreground", textColor);
					UIManager.put("Table.gridColor", baseColor);
					UIManager.put("Table.selectionBackground", baseColor);
					UIManager.put("Table.selectionforeground", textColor);

					UIManager.put("TableHeader.background", baseColor);
					UIManager.put("TableHeader.foreground", textColor);

					UIManager.put("TextArea.background", baseColor);
					UIManager.put("TextArea.foreground", textColor);
					UIManager.put("TextArea.selectionBackground", baseColor);
					UIManager.put("TextArea.selectionforeground", textColor);

					UIManager.put("TextField.background", baseColor);
					UIManager.put("TextField.foreground", textColor);
					UIManager.put("TextField.selectionBackground", baseColor);
					UIManager.put("TextField.selectionforeground", textColor);

					UIManager.put("TextPane.background", baseColor);
					UIManager.put("TextPane.foreground", textColor);
					UIManager.put("TextPane.selectionBackground", baseColor);
					UIManager.put("TextPane.selectionforeground", textColor);

					UIManager.put("ToggleButton.background", baseColor);
					UIManager.put("ToggleButton.foreground", textColor);
					UIManager.put("ToggleButton.highlight", lighterColor);
					UIManager.put("ToggleButton.light", lighterColor);
					UIManager.put("ToggleButton.shadow", darkerColor);

					UIManager.put("ToolBar.background", baseColor);
					UIManager.put("ToolBar.foreground", textColor);
					UIManager.put("ToolBar.highlight", lighterColor);
					UIManager.put("ToolBar.light", lighterColor);
					UIManager.put("ToolBar.shadow", darkerColor);

					UIManager.put("ToolTip.background", baseColor);
					UIManager.put("ToolTip.foreground", textColor);

					UIManager.put("Tree.background", baseColor);
					UIManager.put("Tree.foreground", textColor);
					UIManager.put("Tree.selectionBackground", baseColor);
					UIManager.put("Tree.selectionforeground", textColor);

					UIManager.put("Viewport.background", baseColor);
					UIManager.put("Viewport.foreground", textColor);

					UIManager.put("window", baseColor);
					UIManager.put("windowText", baseColor);
					
					
					UIManager.put("Panel.background", baseColor);
					UIManager.put("Panel.foreground", textColor);
					UIManager.put("Button.background", darkerColor);
					UIManager.put("Button.foreground", textColor);
					
			
					
					UIManager.put("SplitPane.background", baseColor);
					UIManager.put("SplitPane.highlight", lighterColor);
					UIManager.put("SplitPane.shadow", darkerColor);
					UIManager.put("SplitPaneDivider.draggingColor", baseColor);
					
					UIManager.put("TextArea.background", baseColor);
					UIManager.put("TextArea.foreground", textColor);
					UIManager.put("TextArea.selectionBackground", baseColor);
					UIManager.put("TextArea.selectionforeground", textColor);

					UIManager.put("TextField.background", baseColor);
					UIManager.put("TextField.foreground", textColor);
					UIManager.put("TextField.selectionBackground", baseColor);
					UIManager.put("TextField.selectionforeground", textColor);

					UIManager.put("TextPane.background", baseColor);
					UIManager.put("TextPane.foreground", textColor);
					UIManager.put("TextPane.selectionBackground", baseColor);
					UIManager.put("TextPane.selectionforeground", textColor);
					
					UIManager.put("InternalFrame.activeTitleBackground", baseColor);
					UIManager.put("InternalFrame.activeTitleforeground", textColor);
					UIManager.put("InternalFrame.background", baseColor);
				
					
					UIManager.put("InternalFrame.optionDialogBackground", baseColor);
					UIManager.put("InternalFrame.paletteBackground", baseColor);
					
					UIManager.put("ImageIcon.background", baseColor);
					UIManager.put("ImageIcon.foreground", textColor);
					
					UIManager.put("table.AbstractTableModel.background", baseColor);
					UIManager.put("table.AbstractTableModel.foreground", textColor);
					
					
					
					for (Window window : Window.getWindows()) {
						SwingUtilities.updateComponentTreeUI(window);
						window.repaint();
					}
					
					setColor(frame, colorBase);
					
					
					
				
			}
		});
		
		
		lightModeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Color colorBase = new Color(238, 238, 238);
					ColorUIResource baseColor = new ColorUIResource(238, 238, 238);
					ColorUIResource darkerColor = new ColorUIResource(230, 230, 230);
					ColorUIResource lighterColor = new ColorUIResource(255, 255, 255);
					
					ColorUIResource textColor = new ColorUIResource(0, 0, 0);
					
					
					UIManager.put("Button.background", baseColor);
					UIManager.put("Button.foreground", textColor);
					
					UIManager.put("Button.select", baseColor);
					UIManager.put("Button.shadow", darkerColor);

					UIManager.put("CheckBox.background", baseColor);
					UIManager.put("CheckBox.foreground", textColor);
					
					UIManager.put("CheckBox.select", baseColor);

					UIManager.put("CheckBoxMenuItem.background", baseColor);
					UIManager.put("CheckBoxMenuItem.foreground", textColor);
					UIManager.put("CheckBoxMenuItem.selectionBackground", baseColor);
					UIManager.put("CheckBoxMenuItem.selectionforeground", textColor);
					UIManager.put("CheckBoxMenuItem.disabledBackground", baseColor);
					UIManager.put("CheckBoxMenuItem.disabledforeground", textColor);
					UIManager.put("ColorChooser.background", baseColor);
					UIManager.put("ColorChooser.foreground", textColor);
					UIManager.put("ComboBox.background", baseColor);
					UIManager.put("ComboBox.foreground", textColor);
					UIManager.put("ComboBox.buttonBackground", baseColor);
					UIManager.put("ComboBox.buttonDarkShadow", baseColor);
					UIManager.put("ComboBox.buttonHighlight", baseColor);
					UIManager.put("ComboBox.buttonShadow", baseColor);
					UIManager.put("ComboBox.disabledBackground", baseColor);
					UIManager.put("ComboBox.disabledforeground", textColor);
					UIManager.put("ComboBox.foreground", textColor);
					UIManager.put("ComboBox.selectionBackground", baseColor);
					UIManager.put("ComboBox.selectionforeground", textColor);

					UIManager.put("Desktop.background", baseColor);
					

					UIManager.put("EditorPane.background", baseColor);
					UIManager.put("EditorPane.foreground", textColor);
					UIManager.put("EditorPane.selectionBackground", baseColor);
					UIManager.put("EditorPane.selectionforeground", textColor);
					UIManager.put("EditorPane.inactiveBackground", baseColor);
					UIManager.put("EditorPane.inactiveforeground", textColor);

					UIManager.put("FormattedTextField.background", baseColor);
					UIManager.put("FormattedTextField.foreground", textColor);
					UIManager.put("FormattedTextField.selectionBackground", baseColor);
					UIManager.put("FormattedTextField.selectionforeground", textColor);
					UIManager.put("FormattedTextField.inactiveBackground", baseColor);
					UIManager.put("FormattedTextField.inactiveforeground", textColor);

					UIManager.put("IconButton.font", baseColor);
					UIManager.put("InsetBorder.aquaVariant", baseColor);

					UIManager.put("InternalFrame.activeTitleBackground", baseColor);
					UIManager.put("InternalFrame.activeTitleForeground", baseColor);
					UIManager.put("InternalFrame.background", baseColor);
					
					UIManager.put("InternalFrame.optionDialogBackground", baseColor);
					UIManager.put("InternalFrame.paletteBackground", baseColor);

					UIManager.put("Label.background", baseColor);
					UIManager.put("Label.disabledforeground", textColor);
					UIManager.put("Label.disabledShadow", baseColor);
					UIManager.put("Label.foreground", textColor);

					UIManager.put("List.background", baseColor);
					
					UIManager.put("List.dropLineColor", baseColor);
					UIManager.put("List.evenRowBackgroundPainter", baseColor);
					UIManager.put("List.focusCellHighlightBorder", baseColor);
					UIManager.put("List.foreground", textColor);
					UIManager.put("List.oddRowBackgroundPainter", baseColor);
					UIManager.put("List.selectionBackground", baseColor);
					UIManager.put("List.selectionforeground", textColor);
					UIManager.put("List.sourceListBackgroundPainter", baseColor);
					UIManager.put("List.sourceListFocusedSelectionBackgroundPainter", baseColor);
					UIManager.put("List.sourceListSelectionBackgroundPainter", baseColor);

					
					UIManager.put("Menu.background", baseColor);
					
					UIManager.put("Menu.disabledBackground", baseColor);
					UIManager.put("Menu.disabledforeground", textColor);
					UIManager.put("Menu.foreground", textColor);
					UIManager.put("Menu.selectionBackground", baseColor);
					UIManager.put("Menu.selectionforeground", textColor);

					UIManager.put("MenuBar.background", baseColor);
					
					UIManager.put("MenuBar.disabledBackground", baseColor);
					UIManager.put("MenuBar.disabledforeground", textColor);
					UIManager.put("MenuBar.foreground", textColor);
					UIManager.put("MenuBar.selectionBackground", baseColor);
					UIManager.put("MenuBar.selectionforeground", textColor);

					UIManager.put("MenuItem.acceleratorForeground", baseColor);
					UIManager.put("MenuItem.acceleratorSelectionForeground", baseColor);
					UIManager.put("MenuItem.background", baseColor);
					
					UIManager.put("MenuItem.disabledBackground", baseColor);
					UIManager.put("MenuItem.disabledforeground", textColor);
					UIManager.put("MenuItem.foreground", textColor);
					UIManager.put("MenuItem.selectionBackground", baseColor);
					UIManager.put("MenuItem.selectionforeground", textColor);

					UIManager.put("OptionPane.background", baseColor);
					
					UIManager.put("OptionPane.foreground", textColor);
					UIManager.put("OptionPane.messageForeground", baseColor);

					UIManager.put("Panel.background", baseColor);
					UIManager.put("Panel.foreground", textColor);

					UIManager.put("PasswordField.background", baseColor);
					UIManager.put("PasswordField.foreground", textColor);
					UIManager.put("PasswordField.inactiveBackground", baseColor);
					UIManager.put("PasswordField.inactiveforeground", textColor);
					UIManager.put("PasswordField.selectionBackground", baseColor);
					UIManager.put("PasswordField.selectionforeground", textColor);

					UIManager.put("PopupMenu.background", baseColor);
					
					UIManager.put("PopupMenu.foreground", textColor);
					UIManager.put("PopupMenu.selectionBackground", baseColor);
					UIManager.put("PopupMenu.selectionforeground", textColor);

					UIManager.put("ProgressBar.background", baseColor);
				
					UIManager.put("ProgressBar.foreground", textColor);
					UIManager.put("ProgressBar.selectionBackground", baseColor);
					UIManager.put("ProgressBar.selectionforeground", textColor);

				

					UIManager.put("ScrollBar.background", baseColor);
					UIManager.put("ScrollBar.foreground", textColor);
					UIManager.put("ScrollBar.thumb", baseColor);
					UIManager.put("ScrollBar.thumbDarkShadow", baseColor);
					UIManager.put("ScrollBar.thumbHighlight", baseColor);
					UIManager.put("ScrollBar.thumbShadow", baseColor);
					UIManager.put("ScrollBar.track", baseColor);

					UIManager.put("ScrollPane.background", baseColor);
				
					UIManager.put("ScrollPane.foreground", textColor);

					UIManager.put("Separator.foreground", textColor);
					UIManager.put("Separator.highlight", lighterColor);
					UIManager.put("Separator.shadow", darkerColor);

					UIManager.put("Slider.background", baseColor);
					UIManager.put("Slider.foreground", textColor);
					UIManager.put("Slider.highlight", lighterColor);
					UIManager.put("Slider.shadow", darkerColor);
					UIManager.put("Slider.tickColor", baseColor);

					UIManager.put("Spinner.background", baseColor);
					UIManager.put("Spinner.foreground", textColor);

					UIManager.put("SplitPane.background", baseColor);
					
					UIManager.put("SplitPane.highlight", lighterColor);
					UIManager.put("SplitPane.shadow", darkerColor);
					UIManager.put("SplitPaneDivider.draggingColor", baseColor);

					UIManager.put("TabbedPane.background", baseColor);
					UIManager.put("TabbedPane.foreground", textColor);
					UIManager.put("TabbedPane.highlight", lighterColor);
					UIManager.put("TabbedPane.light", lighterColor);
					UIManager.put("TabbedPane.shadow", darkerColor);
					UIManager.put("TabbedPane.selectedTabPadInsets", baseColor);

					UIManager.put("Table.background", baseColor);
					UIManager.put("Table.foreground", textColor);
					UIManager.put("Table.gridColor", baseColor);
					UIManager.put("Table.selectionBackground", baseColor);
					UIManager.put("Table.selectionforeground", textColor);

					UIManager.put("TableHeader.background", baseColor);
					UIManager.put("TableHeader.foreground", textColor);

					UIManager.put("TextArea.background", baseColor);
					UIManager.put("TextArea.foreground", textColor);
					UIManager.put("TextArea.selectionBackground", baseColor);
					UIManager.put("TextArea.selectionforeground", textColor);

					UIManager.put("TextField.background", baseColor);
					UIManager.put("TextField.foreground", textColor);
					UIManager.put("TextField.selectionBackground", baseColor);
					UIManager.put("TextField.selectionforeground", textColor);

					UIManager.put("TextPane.background", baseColor);
					UIManager.put("TextPane.foreground", textColor);
					UIManager.put("TextPane.selectionBackground", baseColor);
					UIManager.put("TextPane.selectionforeground", textColor);

					UIManager.put("ToggleButton.background", baseColor);
					UIManager.put("ToggleButton.foreground", textColor);
					UIManager.put("ToggleButton.highlight", lighterColor);
					UIManager.put("ToggleButton.light", lighterColor);
					UIManager.put("ToggleButton.shadow", darkerColor);

					UIManager.put("ToolBar.background", baseColor);
					UIManager.put("ToolBar.foreground", textColor);
					UIManager.put("ToolBar.highlight", lighterColor);
					UIManager.put("ToolBar.light", lighterColor);
					UIManager.put("ToolBar.shadow", darkerColor);

					UIManager.put("ToolTip.background", baseColor);
					UIManager.put("ToolTip.foreground", textColor);

					UIManager.put("Tree.background", baseColor);
					UIManager.put("Tree.foreground", textColor);
					UIManager.put("Tree.selectionBackground", baseColor);
					UIManager.put("Tree.selectionforeground", textColor);

					UIManager.put("Viewport.background", baseColor);
					UIManager.put("Viewport.foreground", textColor);

					UIManager.put("window", baseColor);
					UIManager.put("windowText", baseColor);
					
					
					UIManager.put("Panel.background", baseColor);
					UIManager.put("Panel.foreground", textColor);
					UIManager.put("Button.background", darkerColor);
					UIManager.put("Button.foreground", textColor);
					
			
					
					UIManager.put("SplitPane.background", baseColor);
					UIManager.put("SplitPane.highlight", lighterColor);
					UIManager.put("SplitPane.shadow", darkerColor);
					UIManager.put("SplitPaneDivider.draggingColor", baseColor);
					
					UIManager.put("TextArea.background", baseColor);
					UIManager.put("TextArea.foreground", textColor);
					UIManager.put("TextArea.selectionBackground", baseColor);
					UIManager.put("TextArea.selectionforeground", textColor);

					UIManager.put("TextField.background", baseColor);
					UIManager.put("TextField.foreground", textColor);
					UIManager.put("TextField.selectionBackground", baseColor);
					UIManager.put("TextField.selectionforeground", textColor);

					UIManager.put("TextPane.background", baseColor);
					UIManager.put("TextPane.foreground", textColor);
					UIManager.put("TextPane.selectionBackground", baseColor);
					UIManager.put("TextPane.selectionforeground", textColor);
					
					UIManager.put("InternalFrame.activeTitleBackground", baseColor);
					UIManager.put("InternalFrame.activeTitleforeground", textColor);
					UIManager.put("InternalFrame.background", baseColor);
				
					
					UIManager.put("InternalFrame.optionDialogBackground", baseColor);
					UIManager.put("InternalFrame.paletteBackground", baseColor);
					
					UIManager.put("ImageIcon.background", baseColor);
					UIManager.put("ImageIcon.foreground", textColor);
					
					UIManager.put("table.AbstractTableModel.background", baseColor);
					UIManager.put("table.AbstractTableModel.foreground", textColor);
					
					
					
					for (Window window : Window.getWindows()) {
						SwingUtilities.updateComponentTreeUI(window);
						window.repaint();
					}
					
					setColor(frame, colorBase);
					
					
					
				
			}
		});
		
		soundPanel.setLayout(borderLayout1);
		soundFileBrowseB.setText(Local.getString("Browse"));
		soundFileBrowseB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundFileBrowseB_actionPerformed(e);
			}
		});
		gridLayout1.setRows(4);
		jPanel1.setBorder(titledBorder1);
		jPanel1.setLayout(gridLayout1);
		soundBeepRB.setText(Local.getString("System beep"));
		soundBeepRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundBeepRB_actionPerformed(e);
			}
		});
		jLabel6.setText(Local.getString("Sound file") + ":");
		soundDefaultRB.setText(Local.getString("Default"));
		soundDefaultRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundDefaultRB_actionPerformed(e);
			}
		});
		jPanel3.setLayout(borderLayout3);
		soundCustomRB.setText(Local.getString("Custom"));
		soundCustomRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundCustomRB_actionPerformed(e);
			}
		});
		jPanel2.setLayout(borderLayout2);
		soundPanel.add(jPanel2, BorderLayout.CENTER);
		jPanel2.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(soundDefaultRB, null);
		jPanel1.add(soundBeepRB, null);
		jPanel1.add(soundCustomRB, null);
		this.soundGroup.add(soundDefaultRB);
		this.soundGroup.add(soundBeepRB);
		this.soundGroup.add(soundCustomRB);
		jPanel1.add(jPanel3, null);
		jPanel3.add(soundFile, BorderLayout.CENTER);
		jPanel3.add(soundFileBrowseB, BorderLayout.EAST);
		jPanel3.add(jLabel6, BorderLayout.WEST);
		GeneralPanel.add(jLabel1, gbc);
		minGroup.add(minTaskbarRB);
		minTaskbarRB.setSelected(true);
		minTaskbarRB.setText(Local.getString("Minimize to taskbar"));
		minTaskbarRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minTaskbarRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(minTaskbarRB, gbc);
		minGroup.add(minHideRB);
		minHideRB.setText(Local.getString("Hide"));
		minHideRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minHideRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(minHideRB, gbc);
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setText(Local.getString("Window close action:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel2, gbc);
		closeGroup.add(closeExitRB);
		closeExitRB.setSelected(true);
		closeExitRB.setText(Local.getString("Close and exit"));
		closeExitRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeExitRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(closeExitRB, gbc);

		closeGroup.add(closeHideRB);
		closeHideRB.setText(Local.getString("Hide"));
		closeHideRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeHideRB_actionPerformed(e);
			}
		});

		//Code No Longer In Use


//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 3;
//		gbc.insets = new Insets(2, 0, 0, 10);
//		gbc.anchor = GridBagConstraints.WEST;
//		GeneralPanel.add(closeHideRB, gbc);
//		jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
//		jLabel3.setText(Local.getString("Look and feel:"));
//		gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 4;
//		gbc.insets = new Insets(2, 10, 0, 15);
//		gbc.anchor = GridBagConstraints.EAST;
//		GeneralPanel.add(jLabel3, gbc);
//
//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 4;
//		gbc.insets = new Insets(2, 0, 0, 10);
//		gbc.anchor = GridBagConstraints.WEST;


		//Code No Longer In Use

//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 5;
//		gbc.insets = new Insets(2, 0, 0, 10);
//		gbc.anchor = GridBagConstraints.WEST;
//		GeneralPanel.add(lfSystemRB, gbc);

//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 6;
//		gbc.insets = new Insets(2, 0, 0, 10);
//		gbc.anchor = GridBagConstraints.WEST;
//		GeneralPanel.add(lfJavaRB, gbc);
//		lfGroup.add(lfCustomRB);
//		lfCustomRB.setText(Local.getString("Custom"));
//		lfCustomRB.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				lfCustomRB_actionPerformed(e);
//			}
//		});


		//Code No Longer In Use

//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 7;
//		gbc.insets = new Insets(2, 0, 0, 10);
//		gbc.anchor = GridBagConstraints.WEST;
////		GeneralPanel.add(lfCustomRB, gbc);
//		classNameLabel.setEnabled(false);
//		classNameLabel.setText(Local.getString("L&F class name:"));
//		gbc = new GridBagConstraints();
//		gbc.gridx = 1;
//		gbc.gridy = 8;
//		gbc.insets = new Insets(2, 20, 0, 10);
//		gbc.anchor = GridBagConstraints.WEST;
//		GeneralPanel.add(classNameLabel, gbc);



//		lfClassName.setEnabled(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.insets = new Insets(7, 20, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
//		GeneralPanel.add(lfClassName, gbc);
		jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel4.setText(Local.getString("Startup:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel4, gbc);
		enSystrayChB.setText(Local.getString("Enable system tray icon"));
		enSystrayChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enSystrayChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enSystrayChB, gbc);
		startMinimizedChB.setText(Local.getString("Start minimized"));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(startMinimizedChB, gbc);
		enSplashChB.setText(Local.getString("Show splash screen"));
		enSplashChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enSplashChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enSplashChB, gbc);
		enL10nChB.setText(Local.getString("Enable localization"));
		enL10nChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enL10nChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enL10nChB, gbc);
		firstdow.setText(Local.getString("First day of week - Monday"));
		firstdow.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(firstdow, gbc);
		lblExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExit.setText(Local.getString("Exit") + ":");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 10, 10, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(lblExit, gbc);
		askConfirmChB.setSelected(true);
		askConfirmChB.setText(Local.getString("Ask confirmation"));
		askConfirmChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askConfirmChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 0, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(askConfirmChB, gbc);

		// Build Tab2
		rstPanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		resourceTypePanel.setBorder(rstPanelBorder);
		resourcePanel.add(resourceTypePanel, BorderLayout.CENTER);
		rsbpBorder = new TitledBorder(BorderFactory.createEmptyBorder(5, 5, 5,
				5), Local.getString("Web browser executable"));
		rsBottomPanel.setBorder(rsbpBorder);
		jLabel5.setText(Local.getString("Path") + ":");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.anchor = GridBagConstraints.WEST;
		rsBottomPanel.add(jLabel5, gbc);
		browserPath.setPreferredSize(new Dimension(250, 25));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rsBottomPanel.add(browserPath, gbc);
		browseB.setText(Local.getString("Browse"));
		browseB.setPreferredSize(new Dimension(110, 25));
		browseB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		// gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.EAST;
		rsBottomPanel.add(browseB, gbc);

		resourcePanel.add(rsBottomPanel, BorderLayout.SOUTH);
		
		// Build editorConfigPanel
		normalFontLabel.setText(Local.getString("Normal text font"));
		normalFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		headerFontLabel.setText(Local.getString("Header font"));
		headerFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		monoFontLabel.setText(Local.getString("Monospaced font"));
		monoFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		baseFontSizeLabel.setText(Local.getString("Base font size"));
		baseFontSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		antialiasChB.setText(Local.getString("Antialias text"));
		JPanel bfsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		bfsPanel.add(baseFontSize);
		econfPanel.add(normalFontLabel);
		econfPanel.add(normalFontCB);
		econfPanel.add(headerFontLabel);
		econfPanel.add(headerFontCB);
		econfPanel.add(monoFontLabel);
		econfPanel.add(monoFontCB);
		econfPanel.add(baseFontSizeLabel);
		econfPanel.add(bfsPanel);
		econfPanel.add(antialiasChB);
		econfPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,10));
		((GridLayout)econfPanel.getLayout()).setHgap(10);
		((GridLayout)econfPanel.getLayout()).setVgap(5);
		editorConfigPanel.add(econfPanel, BorderLayout.NORTH);
		// Build TabbedPanel
		tabbedPanel.add(GeneralPanel, Local.getString("General"));
		tabbedPanel.add(resourcePanel, Local.getString("Resource types"));
		tabbedPanel.add(soundPanel, Local.getString("Sound"));
		tabbedPanel.add(editorConfigPanel, Local.getString("Editor"));
		tabbedPanel.add(themeConfigPanel, Local.getString("Theme"));
		
		
		// Build TopPanel
		topPanel.add(tabbedPanel, BorderLayout.CENTER);

		// Build BottomPanel
		okB.setMaximumSize(new Dimension(100, 25));
		okB.setPreferredSize(new Dimension(100, 25));
		okB.setText(Local.getString("Ok"));
		okB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okB_actionPerformed(e);
			}
		});
		this.getRootPane().setDefaultButton(okB);
		bottomPanel.add(okB);
		cancelB.setMaximumSize(new Dimension(100, 25));
		cancelB.setPreferredSize(new Dimension(100, 25));
		cancelB.setText(Local.getString("Cancel"));
		cancelB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelB_actionPerformed(e);
			}
		});
		bottomPanel.add(cancelB);

		// Build Preferences-Dialog
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		soundPanel.add(enableSoundCB, BorderLayout.NORTH);

		// set all config-values
		setValues();

	}

	void setValues() {
		enL10nChB.setSelected(!Configuration.get("DISABLE_L10N").toString()
				.equalsIgnoreCase("yes"));
		enSplashChB.setSelected(!Configuration.get("SHOW_SPLASH").toString()
				.equalsIgnoreCase("no"));
		enSystrayChB.setSelected(!Configuration.get("DISABLE_SYSTRAY")
				.toString().equalsIgnoreCase("yes"));
		startMinimizedChB.setSelected(Configuration.get("START_MINIMIZED")
				.toString().equalsIgnoreCase("yes"));
		firstdow.setSelected(Configuration.get("FIRST_DAY_OF_WEEK").toString()
				.equalsIgnoreCase("mon"));

		//Code No Longer In Use

//		enableCustomLF(false);
//		String lf = Configuration.get("LOOK_AND_FEEL").toString();
//		if (lf.equalsIgnoreCase("system"))
//			lfSystemRB.setSelected(true);
//		else if (lf.equalsIgnoreCase("default"))
//			lfJavaRB.setSelected(true);
//		else if (lf.length() > 0) {
//			lfCustomRB.setSelected(true);
//			enableCustomLF(true);
//			lfClassName.setText(lf);
//		} else
//			lfJavaRB.setSelected(true);

		askConfirmChB.setSelected(!Configuration.get("ASK_ON_EXIT").toString()
				.equalsIgnoreCase("no"));
		String onclose = Configuration.get("ON_CLOSE").toString();
		if (onclose.equals("exit")) {
			this.closeExitRB.setSelected(true);
			// this.askConfirmChB.setEnabled(true);
		} else {
			this.closeHideRB.setSelected(true);
			// this.askConfirmChB.setEnabled(false);
		}

		String onmin = Configuration.get("ON_MINIMIZE").toString();
		this.minTaskbarRB.setSelected(true);

		if (!System.getProperty("os.name").startsWith("Win"))
			this.browserPath.setText(MimeTypesList.getAppList()
					.getBrowserExec());
		if (Configuration.get("NOTIFY_SOUND").equals("")) {
			Configuration.put("NOTIFY_SOUND", "DEFAULT");
		}

		boolean enableSnd = !Configuration.get("NOTIFY_SOUND").toString()
				.equalsIgnoreCase("DISABLED");
		enableSoundCB.setSelected(enableSnd);
		if (Configuration.get("NOTIFY_SOUND").toString().equalsIgnoreCase(
				"DEFAULT")
				|| Configuration.get("NOTIFY_SOUND").toString()
						.equalsIgnoreCase("DISABLED")) {
			this.soundDefaultRB.setSelected(true);
			this.enableCustomSound(false);
		} else if (Configuration.get("NOTIFY_SOUND").toString()
				.equalsIgnoreCase("BEEP")) {
			this.soundBeepRB.setSelected(true);
			this.enableCustomSound(false);
		} else {
			System.out.println(Configuration.get("NOTIFY_SOUND").toString());
			this.soundCustomRB.setSelected(true);
			this.soundFile
					.setText(Configuration.get("NOTIFY_SOUND").toString());
			this.enableCustomSound(true);
		}
		this.enableSound(enableSnd);
		
		antialiasChB.setSelected(Configuration.get("ANTIALIAS_TEXT")
				.toString().equalsIgnoreCase("yes"));
		if (Configuration.get("NORMAL_FONT").toString().length() >0)
			normalFontCB.setSelectedItem(Configuration.get("NORMAL_FONT").toString());
		else
			normalFontCB.setSelectedItem("serif");
		if (Configuration.get("HEADER_FONT").toString().length() >0)
			headerFontCB.setSelectedItem(Configuration.get("HEADER_FONT").toString());
		else
			headerFontCB.setSelectedItem("sans-serif");
		if (Configuration.get("MONO_FONT").toString().length() >0)
			monoFontCB.setSelectedItem(Configuration.get("MONO_FONT").toString());
		else
			monoFontCB.setSelectedItem("monospaced");
		if (Configuration.get("BASE_FONT_SIZE").toString().length() >0)
			baseFontSize.setValue(Integer.decode(Configuration.get("BASE_FONT_SIZE").toString()));
		else
			baseFontSize.setValue(16);
	}

	void apply() {
		if (this.firstdow.isSelected())
			Configuration.put("FIRST_DAY_OF_WEEK", "mon");
		else
			Configuration.put("FIRST_DAY_OF_WEEK", "sun");

		if (this.enL10nChB.isSelected())
			Configuration.put("DISABLE_L10N", "no");
		else
			Configuration.put("DISABLE_L10N", "yes");

		if (this.enSplashChB.isSelected())
			Configuration.put("SHOW_SPLASH", "yes");
		else
			Configuration.put("SHOW_SPLASH", "no");

		if (this.enSystrayChB.isSelected())
			Configuration.put("DISABLE_SYSTRAY", "no");
		else
			Configuration.put("DISABLE_SYSTRAY", "yes");

		if (this.startMinimizedChB.isSelected())
			Configuration.put("START_MINIMIZED", "yes");
		else
			Configuration.put("START_MINIMIZED", "no");

		if (this.askConfirmChB.isSelected())
			Configuration.put("ASK_ON_EXIT", "yes");
		else
			Configuration.put("ASK_ON_EXIT", "no");

		if (this.closeExitRB.isSelected())
			Configuration.put("ON_CLOSE", "exit");
		else
			Configuration.put("ON_CLOSE", "minimize");

		Configuration.put("ON_MINIMIZE", "normal");

		//Code No Longer In Use

//		String lf = Configuration.get("LOOK_AND_FEEL").toString();
//		String newlf = "";
//
//		if (this.lfSystemRB.isSelected())
//			newlf = "system";
//		else if (this.lfJavaRB.isSelected())
//			newlf = "default";
//		else if (this.lfCustomRB.isSelected())
//			newlf = this.lfClassName.getText();
//
//		if (!lf.equalsIgnoreCase(newlf)) {
//			Configuration.put("LOOK_AND_FEEL", newlf);
//			try {
//				if (Configuration.get("LOOK_AND_FEEL").equals("system"))
//					UIManager.setLookAndFeel(UIManager
//							.getSystemLookAndFeelClassName());
//				else if (Configuration.get("LOOK_AND_FEEL").equals("default"))
//					UIManager.setLookAndFeel(UIManager
//							.getCrossPlatformLookAndFeelClassName());
//				else if (Configuration.get("LOOK_AND_FEEL").toString().length() > 0)
//					UIManager.setLookAndFeel(Configuration.get("LOOK_AND_FEEL")
//							.toString());
//
//				SwingUtilities.updateComponentTreeUI(App.getFrame());
//
//			} catch (Exception e) {
//				Configuration.put("LOOK_AND_FEEL", lf);
//				new ExceptionDialog(
//						e,
//						"Error when initializing a pluggable look-and-feel. Default LF will be used.",
//						"Make sure that specified look-and-feel library classes are on the CLASSPATH.");
//			}
//		}


		String brPath = this.browserPath.getText();
		if (new java.io.File(brPath).isFile()) {
			MimeTypesList.getAppList().setBrowserExec(brPath);
			CurrentStorage.get().storeMimeTypesList();
		}

		if (!this.enableSoundCB.isSelected())
			Configuration.put("NOTIFY_SOUND", "DISABLED");
		else if (this.soundDefaultRB.isSelected())
			Configuration.put("NOTIFY_SOUND", "DEFAULT");
		else if (this.soundBeepRB.isSelected())
			Configuration.put("NOTIFY_SOUND", "BEEP");
		else if ((this.soundCustomRB.isSelected())
				&& (this.soundFile.getText().trim().length() > 0))
			Configuration.put("NOTIFY_SOUND", this.soundFile.getText().trim());

		if (antialiasChB.isSelected())
			Configuration.put("ANTIALIAS_TEXT", "yes");
		else
			Configuration.put("ANTIALIAS_TEXT", "no");
		
		Configuration.put("NORMAL_FONT", normalFontCB.getSelectedItem());
		Configuration.put("HEADER_FONT", headerFontCB.getSelectedItem());
		Configuration.put("MONO_FONT", monoFontCB.getSelectedItem());
		Configuration.put("BASE_FONT_SIZE", baseFontSize.getValue());
		App.getFrame().workPanel.dailyItemsPanel.editorPanel.editor.editor.setAntiAlias(antialiasChB.isSelected());
		App.getFrame().workPanel.dailyItemsPanel.editorPanel.initCSS();
		App.getFrame().workPanel.dailyItemsPanel.editorPanel.editor.repaint();
		
		Configuration.saveConfig();
		
	}
	
	public void setColor(Container parent, Color color) {

    for (Component c : parent.getComponents()) {
		
		c.setBackground(color);
		if (c instanceof Container) {
			setColor((Container)c, color);
		}
		
    }
}


	//Code No Longer In Use

//	void enableCustomLF(boolean is) {
//		this.classNameLabel.setEnabled(is);
//		this.lfClassName.setEnabled(is);
//	}

	void enableCustomSound(boolean is) {
		this.soundFile.setEnabled(is);
		this.soundFileBrowseB.setEnabled(is);
		this.jLabel6.setEnabled(is);
	}

	void enableSound(boolean is) {
		this.soundDefaultRB.setEnabled(is);
		this.soundBeepRB.setEnabled(is);
		this.soundCustomRB.setEnabled(is);
		enableCustomSound(is);

		this.soundFileBrowseB.setEnabled(is && soundCustomRB.isSelected());
		this.soundFile.setEnabled(is && soundCustomRB.isSelected());
		this.jLabel6.setEnabled(is && soundCustomRB.isSelected());

	}

	void okB_actionPerformed(ActionEvent e) {
		apply();
		this.dispose();
	}

	void cancelB_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	void minTaskbarRB_actionPerformed(ActionEvent e) {

	}

	void minHideRB_actionPerformed(ActionEvent e) {

	}

	void closeExitRB_actionPerformed(ActionEvent e) {
		// this.askConfirmChB.setEnabled(true);
	}

	void askConfirmChB_actionPerformed(ActionEvent e) {

	}

	void closeHideRB_actionPerformed(ActionEvent e) {
		// this.askConfirmChB.setEnabled(false);
	}

	//Code No Longer In use

//	void lfSystemRB_actionPerformed(ActionEvent e) {
//		this.enableCustomLF(false);
//	}
//
//	void lfJavaRB_actionPerformed(ActionEvent e) {
//		this.enableCustomLF(false);
//	}
//
//	void lfCustomRB_actionPerformed(ActionEvent e) {
//		this.enableCustomLF(true);
//	}

	void enSystrayChB_actionPerformed(ActionEvent e) {

	}

	void enSplashChB_actionPerformed(ActionEvent e) {

	}

	void enL10nChB_actionPerformed(ActionEvent e) {

	}

	void browseB_actionPerformed(ActionEvent e) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Look in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
		UIManager.put("FileChooser.openButtonToolTipText", Local
				.getString("Open selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local
				.getString("Select the web-browser executable"));
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setPreferredSize(new Dimension(550, 375));
		if (System.getProperty("os.name").startsWith("Win")) {
			chooser.setFileFilter(new AllFilesFilter(AllFilesFilter.EXE));
			chooser.setCurrentDirectory(new File("C:\\Program Files"));
		}
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			this.browserPath.setText(chooser.getSelectedFile().getPath());
	}

	void enableSoundCB_actionPerformed(ActionEvent e) {
		enableSound(enableSoundCB.isSelected());
	}

	void soundFileBrowseB_actionPerformed(ActionEvent e) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Look in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
		UIManager.put("FileChooser.openButtonToolTipText", Local
				.getString("Open selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local.getString("Select the sound file"));
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setPreferredSize(new Dimension(550, 375));
		chooser.setFileFilter(new AllFilesFilter(AllFilesFilter.WAV));
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			this.soundFile.setText(chooser.getSelectedFile().getPath());
	}

	void soundDefaultRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(false);
	}

	void soundBeepRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(false);
	}

	void soundCustomRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(true);
	}
	
	Vector getFontNames() {
		GraphicsEnvironment gEnv = 
        	GraphicsEnvironment.getLocalGraphicsEnvironment();
        String envfonts[] = gEnv.getAvailableFontFamilyNames();
        Vector fonts = new Vector();
        fonts.add("serif");
        fonts.add("sans-serif");
        fonts.add("monospaced");
        for (int i = 0; i < envfonts.length; i++)
            fonts.add(envfonts[i]);
		return fonts;
	}
}