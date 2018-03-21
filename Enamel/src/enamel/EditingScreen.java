package enamel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EditingScreen implements ActionListener {
	// UNCOMMENT 973 WHEN DONE
	private JFrame frame;
	JLabel lblEventPosition;
	private String addMod;
	private JFrame Dframe;
	ActionListener editListen;
	ActionListener addListen;
	Event edit;
	List<JButton> buttons = new ArrayList<JButton>();
	Node currentNode;
	NodeButton currentNodeButton;
	private static HashMap<Character, String> alphabet = new HashMap<Character, String>();
	Scenario scenario;
	private ScenarioWriter writer;
	JFrame Aframe;
	JComboBox Box;
	private JTextField textField;
	private JTextField positionField;
	String file;
	JButton btnChoosewav;
	JComboBox deleteBox;
	JLabel lblTitle;
	String pins = "";
	JRadioButton aradioButton;
	JRadioButton aradioButton_1;
	JRadioButton aradioButton_2;
	JRadioButton aradioButton_3;
	JRadioButton aradioButton_4;
	JRadioButton aradioButton_5;
	JRadioButton aradioButton_6;
	JRadioButton aradioButton_7;
	Node node;
	// Scenario s;
	int count = 0;
	private JPanel panel_2;
	private JLabel lblChooseCell;
	private JComboBox cellBox;
	JPanel panel;
	JPanel subPanel;
	JPanel optionCard;
	JPanel eventPanel;
	JPanel nodeCard;
	JButton btn;
	JLabel lblAvaliableButtons;
	JLabel lblCurrentButton;
	JRadioButton radioButton;
	JRadioButton radioButton_1;
	JRadioButton radioButton_2;
	JRadioButton radioButton_3;
	JRadioButton radioButton_4;
	JRadioButton radioButton_5;
	int buttonCount = 5;
	int boxCount = 4;
	int currentButton = 0;
	private JPanel buttonCard;
	private GraphCanvas graphCanvas;
	protected boolean testActionListenerActive = true;

	// int[] indvCell = new int[boxCount];

	// Node currentNode;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditingScreen window = new EditingScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditingScreen(Scenario hold) {
		alphabet.put('a', "10000000");
		alphabet.put('b', "11000000");
		alphabet.put('c', "10100000");
		alphabet.put('d', "10011000");
		alphabet.put('e', "10001000");
		alphabet.put('f', "11010000");
		alphabet.put('g', "11011000");
		alphabet.put('h', "11001000");
		alphabet.put('i', "01010000");
		alphabet.put('j', "01011000");
		alphabet.put('k', "10100000");
		alphabet.put('l', "11100000");
		alphabet.put('m', "10110000");
		alphabet.put('n', "10111000");
		alphabet.put('o', "10101000");
		alphabet.put('p', "11110000");
		alphabet.put('q', "11111000");
		alphabet.put('r', "11101000");
		alphabet.put('s', "01110000");
		alphabet.put('t', "01111000");
		alphabet.put('u', "10100100");
		alphabet.put('v', "11100100");
		alphabet.put('w', "01011100");
		alphabet.put('x', "10110100");
		alphabet.put('y', "10111100");
		alphabet.put('z', "10101100");
		alphabet.put(' ', "11111111");
		// scenario = new Scenario(hold);
		scenario = hold;
		currentNode = scenario.getHead();
		currentNodeButton = scenario.getHead().getButton(0);
		currentButton = 0;
		buttonCount = scenario.getNumButtons();
		boxCount = scenario.getNumCells();
		writer = new ScenarioWriter(scenario);
		initialize();
		initializeAdd();
		loadNodeEvents();
	}

	public EditingScreen() {
		alphabet.put('a', "10000000");
		alphabet.put('b', "11000000");
		alphabet.put('c', "10100000");
		alphabet.put('d', "10011000");
		alphabet.put('e', "10001000");
		alphabet.put('f', "11010000");
		alphabet.put('g', "11011000");
		alphabet.put('h', "11001000");
		alphabet.put('i', "01010000");
		alphabet.put('j', "01011000");
		alphabet.put('k', "10100000");
		alphabet.put('l', "11100000");
		alphabet.put('m', "10110000");
		alphabet.put('n', "10111000");
		alphabet.put('o', "10101000");
		alphabet.put('p', "11110000");
		alphabet.put('q', "11111000");
		alphabet.put('r', "11101000");
		alphabet.put('s', "01110000");
		alphabet.put('t', "01111000");
		alphabet.put('u', "10100100");
		alphabet.put('v', "11100100");
		alphabet.put('w', "01011100");
		alphabet.put('x', "10110100");
		alphabet.put('y', "10111100");
		alphabet.put('z', "10101100");
		alphabet.put(' ', "11111111");
		currentNode = new Node(1);
		currentNode.addButton(0);
		currentNodeButton = currentNode.getButton(0);
		currentButton = 0;
		initialize();

		loadNodeEvents();
	}

	private void initialize() { // Initialize GUI
		int x1 = 0;
		
		// Initialize Main JFrame
		frame = new JFrame();
		frame.setVisible(true);

		frame.setBounds(100, 100, 975, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		graphCanvas = new GraphCanvas(scenario, scenario.getHead());
		graphCanvas.setBounds(0, 0, 550, 525);
		frame.getContentPane().add(graphCanvas);
		graphCanvas.setVisible(true);

		// Initialize Main JPanel
		panel = new JPanel();
		panel.setBounds(12, 0, 957, 740);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Label to display the amount of buttons
		lblAvaliableButtons = new JLabel("Available Buttons:");
		lblAvaliableButtons.setForeground(Color.BLACK);
		lblAvaliableButtons.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvaliableButtons.setBounds(12, 663, 183, 16);
		panel.add(lblAvaliableButtons);

		lblCurrentButton = new JLabel("Current Button: " + currentButton);
		// lblCurrentButton.getAccessibleContext().setAccessibleName(lblCurrentButton.getText());
		lblCurrentButton.setForeground(Color.BLACK);
		lblCurrentButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurrentButton.setBounds(12, 634, 183, 16);
		panel.add(lblCurrentButton);

		optionCard = new JPanel();
		optionCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		optionCard.setBounds(654, 13, 270, 666);
		optionCard.setLayout(new CardLayout());
		panel.add(optionCard);

		eventPanel = new JPanel();
		eventPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		eventPanel.setBounds(772, 13, 152, 603);
		optionCard.add(eventPanel);

		initEventMods();

		nodeCard = new JPanel();
		nodeCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nodeCard.setBounds(772, 13, 152, 603);
		optionCard.add(nodeCard, "Node");
		nodeCard.setLayout(new BoxLayout(nodeCard, BoxLayout.Y_AXIS));

		buttonCard = new JPanel();
		buttonCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonCard.setBounds(772, 13, 152, 603);
		optionCard.add(buttonCard, "Button");
		buttonCard.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 692, 457, 35);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(698, 692, 195, 35);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton run = new JButton("Run");
		panel_2.add(run);
		run.getAccessibleContext().setAccessibleName("Run a scenario");
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ScenarioParser s = new ScenarioParser(true);

				FileSearch fileSearch = new FileSearch();
				String found = scenario.getFileName();
				// try different directory and filename
				fileSearch.searchDirectory(new File(System.getProperty("user.dir")), found);

				int count = fileSearch.getResult().size();
				if (count == 0) {
					throw new IllegalArgumentException("File does not exist");
				} else {
					s.setScenarioFile(found);
				}
				// Thread t = new Thread(new Runnable() {
				//
				// @Override
				// public void run() {
				// String file = "";
				// JFileChooser chooser = new JFileChooser(new
				// File("FactoryScenarios"));
				// FileNameExtensionFilter filter = new
				// FileNameExtensionFilter("Text Files", "txt");
				// chooser.setFileFilter(filter);
				// int returnval = chooser.showOpenDialog(null);
				// if (returnval == JFileChooser.APPROVE_OPTION) {
				// file = "FactoryScenarios/" +
				// chooser.getSelectedFile().getName();
				// }
				// ScenarioParser s = new ScenarioParser(true);
				// s.setScenarioFile(file);
				// }
				//
				// });
				// frame.dispose();
				// t.start();
			}

		});

		// Apply button
		JButton btnApply = new JButton("Save");
		panel_2.add(btnApply);
		btnApply.addActionListener(new ActionListener() {//
			public void actionPerformed(ActionEvent arg0) {

				// if (lblCurrentButton.getText().equals("Node Selected")) {
				// String option = "" + nodeCellBox.getSelectedItem();
				// currentNode.setResponse(speakText2.getText());
				// currentNode.setRepeatedText(repeatText.getText());
				//
				// if (!lblNoFileChosen.getText().equals("No file chosen"))
				// currentNode.setAudioFile(lblNoFileChosen.getText());
				// // ADD PAUSE
				// if (option.equals("Clear")) {
				// int cell = Integer.parseInt("" +
				// clearChoose2.getSelectedItem());
				// int[] pins = new int[8];
				// String[] hold = alphabet.get(' ').split("");
				// if (clearRadioButton2.isSelected()) {
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				// }
				// currentNode.setPins(pins, cell);
				// }
				//
				// }
				//
				// else if (option.equals("Pins")) {
				// int cell = Integer.parseInt("" +
				// blockChooser2.getSelectedItem());
				//
				// if (pin21.isSelected())
				// currentNode.setPin(cell, 1, 1);
				// else
				// currentNode.setPin(cell, 1, 0);
				//
				// if (pin22.isSelected())
				// currentNode.setPin(cell, 2, 1);
				// else
				// currentNode.setPin(cell, 2, 0);
				//
				// if (pin23.isSelected())
				// currentNode.setPin(cell, 3, 1);
				// else
				// currentNode.setPin(cell, 3, 0);
				//
				// if (pin24.isSelected())
				// currentNode.setPin(cell, 4, 1);
				// else
				// currentNode.setPin(cell, 4, 0);
				//
				// if (pin25.isSelected())
				// currentNode.setPin(cell, 5, 1);
				// else
				// currentNode.setPin(cell, 5, 0);
				//
				// if (pin26.isSelected())
				// currentNode.setPin(cell, 6, 1);
				// else
				// currentNode.setPin(cell, 6, 0);
				//
				// if (pin27.isSelected())
				// currentNode.setPin(cell, 7, 1);
				// else
				// currentNode.setPin(cell, 7, 0);
				//
				// if (pin28.isSelected())
				// currentNode.setPin(cell, 8, 1);
				// else
				// currentNode.setPin(cell, 8, 0);
				// }
				//
				// else if (option.equals("Character")) {
				// int cell = Integer.parseInt("" +
				// charChoose2.getSelectedItem());
				//
				// // System.out.println(nodeEnterCharHere.getText().length());
				// if (nodeEnterCharHere.getText().length() > 1) {
				// // Display Some Error Here
				//
				// } else {
				// int[] pins = new int[8];
				// char[] x = nodeEnterCharHere.getText().toCharArray();
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				// System.out.println(pins[i]);
				// }
				// currentNode.setPins(pins, cell);
				//
				// }
				// }
				//
				// else if (option.equals("Word")) {
				// if (nodeEnterWordHere.getText().length() > boxCount) {
				// // Display Some Error Here
				// } else {
				// String[] word = nodeEnterWordHere.getText().split("");
				// int[] pins = new int[8];
				// for (int i = 0; i < word.length; i++) {
				// char[] x = word[i].toCharArray();
				//
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int j = 0; j < 8; j++) {
				// pins[j] = Integer.parseInt(hold[j]);
				//
				// }
				// currentNode.setPins(pins, i);
				// }
				// }
				// }
				//
				// else {
				// // Display some error here for nothing selected
				// }
				// }
				//
				// else if (lblCurrentButton.getText().split("
				// ")[0].equals("Current"))// CHANGE
				// // BUTTON
				// // STUFF
				// {
				// String option = "" + btnCellBox.getSelectedItem();
				// int[] pins = new int[8];
				//
				// if (buttonBox.getSelectedItem().equals("Repeat")) {
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// currentNode.addRepeatButton(currentButton,
				// repeatText.getText());
				// }
				// } else {
				// if (currentNode.getButton(currentButton).equals(null)) {
				// // SkipButton skip = new SkipButton();
				// int number = currentButton;
				// String response = textField.getText();
				// Node next = null; // DONT KNOW WHAT TO PUT HERE
				//
				// if (option.equals("Clear")) {
				// int cell = Integer.parseInt("" +
				// clearChoose.getSelectedItem());
				// String[] hold = alphabet.get(' ').split("");
				// if (clearRadioButton.isSelected()) {
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				// }
				// }
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, cell);
				// }
				//
				// } else if (option.equals("Pins")) {
				// int cell = Integer.parseInt("" +
				// blockChooser.getSelectedItem());
				//
				// if (pin1.isSelected())
				// pins[0] = 1;
				// else//
				// pins[0] = 0;
				//
				// if (pin2.isSelected())
				// pins[1] = 1;
				// else
				// pins[1] = 0;
				//
				// if (pin3.isSelected())
				// pins[2] = 1;
				// else
				// pins[2] = 0;
				//
				// if (pin4.isSelected())
				// pins[3] = 1;
				// else
				// pins[3] = 0;
				//
				// if (pin5.isSelected())
				// pins[4] = 1;
				// else
				// pins[4] = 0;
				//
				// if (pin6.isSelected())
				// pins[5] = 1;
				// else
				// pins[5] = 0;
				//
				// if (pin7.isSelected())
				// pins[6] = 1;
				// else
				// pins[6] = 0;
				//
				// if (pin8.isSelected())
				// pins[7] = 1;
				// else
				// pins[7] = 0;
				//
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, cell);
				// }
				//
				// }
				//
				// else if (option.equals("Character")) {
				// int cell = Integer.parseInt("" +
				// charChoose.getSelectedItem());
				//
				// // System.out.println(nodeEnterCharHere.getText().length());
				// if (txtEnterCharHere.getText().length() > 1) {
				// // Display Some Error Here
				//
				// } else {
				// char[] x = txtEnterCharHere.getText().toCharArray();
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int i = 0; i < 8; i++) {
				// pins[i] = Integer.parseInt(hold[i]);
				//
				// }
				// }
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, cell);
				// }
				// } else if (option.equals("Word")) {
				// if (txtEnterWordHere.getText().length() > boxCount) {
				// // Display Some Error Here
				// } else {
				// String[] word = txtEnterWordHere.getText().split("");
				// for (int i = 0; i < word.length; i++) {
				// char[] x = word[i].toCharArray();
				//
				// String[] hold = alphabet.get(x[0]).split("");
				// for (int j = 0; j < 8; j++) {
				// pins[j] = Integer.parseInt(hold[j]);
				//
				// }
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// ((SkipButton) currentNodeButton).setPins(pins, i);
				// }
				// }
				// }
				// } else {
				// // Do nothing for now
				// }
				//
				// NodeButton button = currentNode.getButton(currentButton);
				// if (currentNodeButton.getClass() == SkipButton.class) {
				//
				// }
				// if (!lblCurrentFile.getText().equals("No file chosen")) {
				// // currentNodeButton = new SkipButton(1);
				//
				// if (currentNodeButton.getClass() == SkipButton.class) {
				// int cell = Integer.parseInt("" +
				// blockChooser.getSelectedItem());
				// ((SkipButton) currentNodeButton).setResponse(response);
				// ((SkipButton)
				// currentNodeButton).setAudioFile(lblCurrentFile.getText());
				// if (comboBoxConnectTo.getSelectedItem().equals("New Node"))
				// {
				// //((SkipButton)
				// currentNodeButton).setNextNode(scenario.createNode());
				// Node hold = scenario.createNode();
				// scenario.setEdge(currentNode, hold, currentButton);
				// }
				// else {//
				// ((SkipButton)
				// currentNodeButton).setNextNode((Node)comboBoxConnectTo.getSelectedItem());
				// }
				// }
				//
				// } else {
				// // SkipButton button = new SkipButton(number,
				// // response, next);
				// }
				//
				// }
				//
				// }
				// }
				// writer.changeFileName(scenario.getName());
				// try {
				// writer.save();
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			}
		});
		btnApply.getAccessibleContext().setAccessibleName("Click to apply changes");

		for (int i = 0; i < buttonCount; i++) // Display buttons based on the
												// amount of buttons passed in
												// through the text file
		{
			x1 = x1 + 50;
			buttons.add(new JButton("" + i));
			buttons.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					CardLayout cl = (CardLayout) (optionCard.getLayout());
					cl.show(optionCard, "Button");

				}

			});
			buttons.get(i).setBounds(x1, 0, 50, 25);
			buttons.get(i).addActionListener(this);
			buttons.get(i).getAccessibleContext().setAccessibleName("Button " + i);
			panel_1.add(buttons.get(i));
		}
		CardLayout cl = (CardLayout) (optionCard.getLayout());
		cl.show(optionCard, "Do Nothing");
		lblCurrentButton.setText("Node Selected");

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(223, 644, 152, 35);
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StartScreen go = new StartScreen();
				go.frame.setVisible(true);
			}
		});
		panel_3.add(btnMainMenu);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(481, 606, 152, 109);
		panel.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JLabel lblPreviousNodes = new JLabel("Previous Nodes:");
		panel_6.add(lblPreviousNodes);

		JComboBox<Node> comboBoxPrevNodes = new JComboBox<>();
		panel_6.add(comboBoxPrevNodes);

		JButton btnNode = new JButton("Current Node");
		panel_6.add(btnNode);

		JLabel lblNextNodes = new JLabel("Next Nodes:");
		panel_6.add(lblNextNodes);

		JComboBox<Node> comboBoxNextNodes = new JComboBox<>();
		panel_6.add(comboBoxNextNodes);

		btnNode.setVisible(true);
		btnNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (optionCard.getLayout());
				nodeCard.removeAll();
				nodeCard.revalidate();
				nodeCard.repaint();

				loadNodeEvents();
			}
		});

		for (Node node : this.scenario.getPrevNodes(this.currentNode)) {
			System.out.println(currentNode + "NO");
			comboBoxPrevNodes.addItem(node);
		}
		for (Node node : this.scenario.getNextNodes(this.currentNode)) {
			comboBoxNextNodes.addItem(node);
		}

		comboBoxNextNodes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (testActionListenerActive) {
					testActionListenerActive = false;
					currentNode = (Node) comboBoxNextNodes.getSelectedItem();
					CardLayout cl = (CardLayout) (optionCard.getLayout());
					cl.show(optionCard, "Do Nothing");
					lblCurrentButton.setText("Node Selected");
					graphCanvas.setNode(currentNode);
					graphCanvas.repaint();
					comboBoxNextNodes.removeAllItems();
					for (Node node : scenario.getNextNodes(currentNode)) {
						comboBoxNextNodes.addItem(node);
					}
					comboBoxPrevNodes.removeAllItems();
					for (Node node : scenario.getPrevNodes(currentNode)) {
						comboBoxPrevNodes.addItem(node);
					}
					loadNodeEvents();
					testActionListenerActive = true;
				}

			}
		});

		comboBoxPrevNodes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (testActionListenerActive) {
					testActionListenerActive = false;
					currentNode = (Node) comboBoxPrevNodes.getSelectedItem();
					CardLayout cl = (CardLayout) (optionCard.getLayout());
					cl.show(optionCard, "Do Nothing");
					lblCurrentButton.setText("Node Selected");
					graphCanvas.setNode(currentNode);
					graphCanvas.repaint();
					comboBoxNextNodes.removeAllItems();
					if (scenario.hasNextNodes(currentNode)) {
						for (Node node : scenario.getNextNodes(currentNode)) {
							comboBoxNextNodes.addItem(node);
						}
					}
					comboBoxPrevNodes.removeAllItems();
					for (Node node : scenario.getPrevNodes(currentNode)) {
						comboBoxPrevNodes.addItem(node);
					}
					loadNodeEvents();
					testActionListenerActive = true;
				}
			}
		});
		// loadNodeEvents();
	}

	private void initEventMods() {
		JPanel panel_5 = new JPanel();
		eventPanel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblEventEditor = new JLabel("Event Editor:");
		lblEventEditor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblEventEditor);

		JButton btn = new JButton("Add");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				setAddVisible(false);
				positionField.setText("");
				positionField.setVisible(true);
				lblEventPosition.setVisible(true);
				textField.setText("");
				Box.setSelectedItem("");
				Aframe.setVisible(true);
				addAction();

			}
		});

		panel_5.add(btn);

		JButton btnDeleteEvent = new JButton("Delete");
		btnDeleteEvent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dframe.setVisible(true);
				deleteBox.removeAll();
				// for (int i = 0; )
			}

		});
		panel_5.add(btnDeleteEvent);
	}

	public void loadNodeEvents() {
		count = 0;
		resetAddComponents();
		eventPanel.removeAll();
		initEventMods();
		Timeline t = currentNode.getTimeline();
		for (Event e : t.getEvents()) {
			count++;
		//	System.out.println(count);
			eventPanel.add(new JLabel("Event " + count));
			String disp = e.getClass().toString();
			JButton eventButton = new JButton(e.getClass().toString());
			eventButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent eve) {
					// EDIT EVENT SCREEN
					int counter = count;
					
					editAction(e, t);
					Aframe.setVisible(true);
					addMod = "Edit";
					positionField.setVisible(false);
					lblEventPosition.setVisible(false);
					
					positionField.setText("" + (counter));
					// t.removeEvent(e);

					if (e.getClass() == Response.class) {
						Box.setSelectedItem("Response");
						textField.setText(((Response) e).getData());
					} else if (e.getClass() == DisplayPins.class) {
						Box.setSelectedItem("Pins");
						String[] set = ((DisplayPins) e).getData().split("");

						if (set[0].equals("0"))
							aradioButton.setSelected(false);
						else
							aradioButton.setSelected(true);
						if (set[1].equals("0"))
							aradioButton_1.setSelected(false);
						else
							aradioButton_1.setSelected(true);
						if (set[2].equals("0"))
							aradioButton_2.setSelected(false);
						else
							aradioButton_2.setSelected(true);
						if (set[3].equals("0"))
							aradioButton_3.setSelected(false);
						else
							aradioButton_3.setSelected(true);
						if (set[4].equals("0"))
							aradioButton_4.setSelected(false);
						else
							aradioButton_4.setSelected(true);
						if (set[5].equals("0"))
							aradioButton_5.setSelected(false);
						else
							aradioButton_5.setSelected(true);
						if (set[6].equals("0"))
							aradioButton_6.setSelected(false);
						else
							aradioButton_6.setSelected(true);
						if (set[7].equals("0"))
							aradioButton_7.setSelected(false);
						else
							aradioButton_7.setSelected(true);

						cellBox.setSelectedItem(((DisplayPins) e).getCellNumber());

					} else if (e.getClass() == Sound.class) {
						Box.setSelectedItem("Sound");
					}
				}

			});
			eventPanel.add(eventButton);
			eventPanel.revalidate();
			eventPanel.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttonCount; i++) // Sets the current button to edit
		{
			if (e.getSource().equals(buttons.get(i))) {
				currentButton = i;
				// currentNodeButton = currentNode.getButton(i);
				lblCurrentButton.setText("Current Button: " + currentButton);
			}
		}

	}

	public void initializeAdd() {
		Aframe = new JFrame();
		Aframe.setBounds(100, 100, 450, 332);
		Aframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Aframe.getContentPane().setLayout(null);
		Aframe.setVisible(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 285);
		Aframe.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 109, 397, 176);
		panel.add(panel_1);
		panel_1.setLayout(null);

		aradioButton = new JRadioButton("");
		aradioButton.setBounds(55, 34, 25, 25);
		aradioButton.setVisible(false);
		panel_1.add(aradioButton);

		aradioButton_1 = new JRadioButton("");
		aradioButton_1.setBounds(55, 63, 25, 25);
		aradioButton_1.setVisible(false);
		panel_1.add(aradioButton_1);

		aradioButton_2 = new JRadioButton("");
		aradioButton_2.setBounds(55, 93, 25, 25);
		aradioButton_2.setVisible(false);
		panel_1.add(aradioButton_2);

		aradioButton_3 = new JRadioButton("");
		aradioButton_3.setBounds(55, 119, 25, 25);
		aradioButton_3.setVisible(false);
		panel_1.add(aradioButton_3);

		aradioButton_4 = new JRadioButton("");
		aradioButton_4.setBounds(84, 34, 25, 25);
		aradioButton_4.setVisible(false);
		panel_1.add(aradioButton_4);

		aradioButton_5 = new JRadioButton("");
		aradioButton_5.setBounds(84, 63, 25, 25);
		aradioButton_5.setVisible(false);
		panel_1.add(aradioButton_5);

		aradioButton_6 = new JRadioButton("");
		aradioButton_6.setBounds(84, 93, 25, 25);
		aradioButton_6.setVisible(false);
		panel_1.add(aradioButton_6);

		aradioButton_7 = new JRadioButton("");
		aradioButton_7.setBounds(84, 119, 25, 25);
		aradioButton_7.setVisible(false);
		panel_1.add(aradioButton_7);

		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(0, 0, 385, 25);
		panel_1.add(lblTitle);

		textField = new JTextField();
		textField.setBounds(0, 32, 397, 25);
		panel_1.add(textField);
		textField.setVisible(false);
		textField.setColumns(10);

		JLabel lblChooseWav = new JLabel("");
		lblChooseWav.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseWav.setBounds(0, 63, 397, 30);
		panel_1.add(lblChooseWav);

		btnChoosewav = new JButton("Choose .wav");
		btnChoosewav.setBounds(0, 32, 127, 25);
		btnChoosewav.setVisible(false);
		btnChoosewav.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser(new File("FactoryScenarios/"));
				// Create textfield to allow user to name the file and save as
				// string
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Sound Files", "wav");
				chooser.setFileFilter(filter);
				int returnval = chooser.showOpenDialog(null);
				if (returnval == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile().getName();
				}
				lblChooseWav.setText("Selected sound file: " + file);
			}

		});
		panel_1.add(btnChoosewav);

		Box = new JComboBox();
		Box.setBounds(12, 28, 184, 22);
		Box.addItem("Select Type");
		Box.addItem("Response");
		Box.addItem("Sound");
		Box.addItem("Pins");
		Box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Box.getSelectedItem().equals("Response")) {
					cellBox.setVisible(false);
					setPinVisible(false);
					lblChooseCell.setText("");
					lblTitle.setText("Type your response here:");
					lblChooseWav.setText("");

					textField.setVisible(true);
				} else if (Box.getSelectedItem().equals("Sound")) {
					cellBox.setVisible(false);
					setPinVisible(false);
					lblChooseCell.setText("");
					textField.setVisible(false);
					lblTitle.setText("Choose the sound file you wish to play:");
					btnChoosewav.setVisible(true);
				} else if (Box.getSelectedItem().equals("Pins")) {
					cellBox.setVisible(true);
					lblTitle.setText("Set active pins:");
					textField.setVisible(false);
					lblChooseCell.setText("Choose Cell:");
					lblChooseWav.setText("");
					btnChoosewav.setVisible(false);
					setPinVisible(true);
				} else if (Box.getSelectedItem().equals("Select Type")) {
					setAddVisible(false);
				}
			}

		});
		panel.add(Box);

		JLabel lblChooseEventType = new JLabel("Choose Event Type:");
		lblChooseEventType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseEventType.setBounds(12, 0, 184, 30);
		panel.add(lblChooseEventType);

		lblEventPosition = new JLabel("Event Position:");
		lblEventPosition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEventPosition.setBounds(12, 52, 133, 26);
		panel.add(lblEventPosition);

		positionField = new JTextField();
		positionField.setBounds(12, 74, 116, 22);
		panel.add(positionField);
		positionField.setColumns(10);

		btn = new JButton("Add Event");

		btn.setBounds(288, 138, 97, 25);
		panel_1.add(btn);

		panel_2 = new JPanel();
		panel_2.setBounds(208, 0, 212, 105);
		panel.add(panel_2);
		panel_2.setLayout(null);

		lblChooseCell = new JLabel("");
		lblChooseCell.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseCell.setBounds(0, 0, 153, 29);
		panel_2.add(lblChooseCell);

		cellBox = new JComboBox();
		cellBox.setVisible(false);
		cellBox.setBounds(0, 25, 112, 22);
		for (int i = 0; i < scenario.getNumButtons(); i++) {
			cellBox.addItem(i);
		}
		panel_2.add(cellBox);
	}

	private void setPinVisible(boolean b) {
		aradioButton.setVisible(b);
		aradioButton_1.setVisible(b);
		aradioButton_2.setVisible(b);
		aradioButton_3.setVisible(b);
		aradioButton_4.setVisible(b);
		aradioButton_5.setVisible(b);
		aradioButton_6.setVisible(b);
		aradioButton_7.setVisible(b);
	}

	private void setAddVisible(boolean b) {
		setPinVisible(b);
		cellBox.setVisible(b);
		lblChooseCell.setText("");
		textField.setVisible(b);
		btnChoosewav.setVisible(b);
		lblTitle.setText("");

	}

	private void initializeDelete() {
		Dframe = new JFrame();
		Dframe.setBounds(100, 100, 368, 269);
		Dframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dframe.getContentPane().setLayout(null);
		Dframe.setVisible(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 560, 337);
		Dframe.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblDeleteEvent = new JLabel("Delete Event:");
		lblDeleteEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDeleteEvent.setBounds(12, 13, 200, 39);
		panel.add(lblDeleteEvent);

		JLabel lblWhichEventWould = new JLabel("Which event would you like to delete?");
		lblWhichEventWould.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhichEventWould.setBounds(12, 63, 311, 25);
		panel.add(lblWhichEventWould);

		deleteBox = new JComboBox();
		deleteBox.setBounds(12, 88, 140, 22);
		panel.add(deleteBox);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(229, 166, 97, 25);
		panel.add(btnDelete);
	}

	private void addAction() {

		btn.removeActionListener(addListen);
		btn.removeActionListener(editListen);
		initAddListen();
		resetAddComponents();
		btn.addActionListener(addListen);
	}

	private void editAction(Event e, Timeline t) {
		btn.removeActionListener(editListen);
		btn.removeActionListener(addListen);
		initEditListen(e, t);
		btn.addActionListener(editListen);

	}
	
	private void initAddListen(){
		addListen = new ActionListener() {
			public void actionPerformed(ActionEvent eve) {

				if (Box.getSelectedItem().equals("Response")) {
					currentNode.addToResponse(textField.getText());
					Aframe.dispose();
				} else if (Box.getSelectedItem().equals("Sound")) {
					currentNode.setAudioFile(file);
					Aframe.dispose();
				} else if (Box.getSelectedItem().equals("Pins")) {
					pins = "";
					if (aradioButton.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_1.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_2.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_3.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_4.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_5.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_6.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_7.isSelected())
						pins += 1;
					else
						pins += 0;

					currentNode.setPins(pins, (int) cellBox.getSelectedItem());
					Aframe.dispose();

				} else if (Box.getSelectedItem().equals("Select Type")) {
					Aframe.dispose();
				}
				loadNodeEvents();
				Aframe.dispose();
			}
		};
	}
	
	private void initEditListen(Event e, Timeline t)
	{
		editListen = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent eve) {
				// TODO Auto-generated method stub

				int pos =t.indexOf(e);
				System.out.println(pos);
				System.out.println(t.size());
				
				if (Box.getSelectedItem().equals("Response")) {

					Event en = new Response(textField.getText());
					t.change(pos, en);
					Aframe.dispose();
				} else if (Box.getSelectedItem().equals("Sound")) {
					Event en = new Sound(file);
					t.change(pos, en);
					Aframe.dispose();
				} else if (Box.getSelectedItem().equals("Pins")) {
					pins = "";
					if (aradioButton.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_1.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_2.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_3.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_4.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_5.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_6.isSelected())
						pins += 1;
					else
						pins += 0;
					if (aradioButton_7.isSelected())
						pins += 1;
					else
						pins += 0;

					Event en = new DisplayPins(pins, (int) cellBox.getSelectedItem());
					System.out.println(en);
					t.change(pos, en);
					Aframe.dispose();

				} else if (Box.getSelectedItem().equals("Select Type")) {
					Aframe.dispose();
				}
				loadNodeEvents();
				System.out.println(t);
			}//
			
		};
	}
	
	private void resetAddComponents()
	{
		positionField.setText("");
		textField.setText("");
		aradioButton.setSelected(false);
		aradioButton_1.setSelected(false);
		aradioButton_2.setSelected(false);
		aradioButton_3.setSelected(false);
		aradioButton_4.setSelected(false);
		aradioButton_5.setSelected(false);
		aradioButton_6.setSelected(false);
		aradioButton_7.setSelected(false);
	}
}
