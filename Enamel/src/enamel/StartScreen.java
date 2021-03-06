//Instantiate the number of buttons and cells




package enamel;

import java.awt.EventQueue;


import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;


 public class StartScreen {
	public String cust_file;
	public String cust_filename;
	public JFrame frame;
	private JTextField txtProjectTitle;
	Voice voice;
	VoiceManager vm;
	private JTextField txtSetFileName;
	public int cellnumber;
	public int buttonnumber;
	public String buttontext;
	public String celltext;
	private JTextField buttontextfield;
	private JTextField celltextfield;
	public String invalidkey = "This is an invalid key, please enter a real number";
	private FeatureTracker track;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FeatureTracker track = new FeatureTracker();
		frame = new JFrame();
		frame.setBounds(100, 100, 663, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		buttontextfield = new JTextField();
		buttontextfield.setVisible(false);
		
		celltextfield = new JTextField();
		celltextfield.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 647, 435);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewProject = new JButton("Create New Project");
		btnNewProject.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewProject.getAccessibleContext().setAccessibleName("Create New Project");
		btnNewProject.setVisible(false);
		
		JButton btnBackToScenario = new JButton("Back to Scenario Selection");
		btnBackToScenario.getAccessibleContext().setAccessibleName("Back to Scenario Selection");
		btnBackToScenario.setVisible(false);
		
		JLabel lblSelectButtonNumber = new JLabel("Select Button Number:");
		lblSelectButtonNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectButtonNumber .setVisible(false);
		lblSelectButtonNumber.setBounds(6, 164, 188, 14);
		panel.add(lblSelectButtonNumber);
		
		JLabel lblSelectCellNumber = new JLabel("Select Cell Number:");
		lblSelectCellNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectCellNumber .setVisible(false);
		lblSelectCellNumber.setBounds(322, 164, 178, 14);
		panel.add(lblSelectCellNumber);
		
		JLabel lblEnterFileText = new JLabel("Enter Title of Project:");
		lblEnterFileText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterFileText.setVisible(false);
		lblEnterFileText.setBounds(6, 97, 188, 14);
		panel.add(lblEnterFileText);
		
		JLabel lblEnterFileName = new JLabel("Enter File Name Text:");
		lblEnterFileName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterFileName.setVisible(false);
		lblEnterFileName.setBounds(6, 240, 178, 14);
		panel.add(lblEnterFileName);
		
		txtSetFileName = new JTextField();
		txtSetFileName.getAccessibleContext().setAccessibleDescription("Set File Name");
		txtSetFileName.setVisible(false);
		
		txtProjectTitle = new JTextField();
		txtProjectTitle.getAccessibleContext().setAccessibleName("New Text File");
		txtProjectTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		
					String titlestring = txtProjectTitle.getText();
					if (!titlestring.isEmpty()){
						vm = VoiceManager.getInstance();
				        voice = vm.getVoice ("kevin16");
				        voice.allocate();
				        voice.speak(txtProjectTitle.getText());
				       
				        btnNewProject.setVisible(true);
					}
					else {
					JOptionPane.showMessageDialog(null, "Please Enter a Non-null title", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
				
		});
		txtProjectTitle.setVisible(false);
		
		
		

		
		KeyStroke keySave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK); 
		Action performCOpen = new AbstractAction("Choose Existing File") {  
		    public void actionPerformed(ActionEvent e) {     
		    	String file = "";
				try{
				//ScenarioParser s = new ScenarioParser(true);
				JFileChooser chooser = new JFileChooser(new File("FactoryScenarios/"));
				//Create textfield to allow user to name the file and save as string
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				chooser.setFileFilter(filter);
				int returnval = chooser.showOpenDialog(null);
				if (returnval == JFileChooser.APPROVE_OPTION) {
					file = "FactoryScenarios/" + chooser.getSelectedFile().getName();
					frame.dispose(); 
					ScenarioNode test2 = new ScenarioNode();
					test2.setScenarioFile(file);
					
				}
				
				 else if (returnval == JFileChooser.CANCEL_OPTION){
					chooser.setVisible(false);
					System.out.println("Dialogue Pane Closed");
				}
				

				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					StartScreen go = new StartScreen();
					go.frame.setVisible(true);
				}

			}
		}; 
		JButton btnChooseExistingFile = new JButton(performCOpen);
		btnChooseExistingFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnChooseExistingFile.getAccessibleContext().setAccessibleName("Choose Existing File");
		//ShortCut Works, redo for the createnew file
		btnChooseExistingFile.getActionMap().put("performCOpen", performCOpen);
		btnChooseExistingFile.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keySave, "performCOpen");
		btnChooseExistingFile.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0){

				
				String file = "";
				try{
				//ScenarioParser s = new ScenarioParser(true);
				JFileChooser chooser = new JFileChooser(new File("FactoryScenarios/"));
				//Create textfield to allow user to name the file and save as string
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				chooser.setFileFilter(filter);
				int returnval = chooser.showOpenDialog(null);
				if (returnval == JFileChooser.APPROVE_OPTION) {
					file = "FactoryScenarios/" + chooser.getSelectedFile().getName();
					frame.dispose(); 
					ScenarioNode test2 = new ScenarioNode();
					test2.setScenarioFile(file);
					
				}
				
				 else if (returnval == JFileChooser.CANCEL_OPTION){
					chooser.setVisible(false);
					System.out.println("Dialogue Pane Closed");
				}
				
				
				
				//s.setScenarioFile(file);
				
				//ScenarioNode test2 = new ScenarioNode();
				//test2.setScenarioFile(file);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					StartScreen go = new StartScreen();
					go.frame.setVisible(true);
				}

			}
			
		});
		btnChooseExistingFile.setBounds(6, 11, 306, 75);
		panel.add(btnChooseExistingFile);
		
		KeyStroke keyCreate = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK); 
		Action performC = new AbstractAction("Create New File") {  
		    public void actionPerformed(ActionEvent e) {     
				txtProjectTitle.setVisible(true);
				txtSetFileName.setVisible(true);
				buttontextfield.setVisible(true);
				celltextfield.setVisible(true);
				lblSelectButtonNumber .setVisible(true);
				lblSelectCellNumber .setVisible(true);
				lblEnterFileText.setVisible(true);
				lblEnterFileName.setVisible(true);
				btnBackToScenario.setVisible(true);
				btnNewProject.setVisible(true);
		    }
		}; 
		
		JButton btnCreateNewFile = new JButton(performC);
		
		
		
		btnCreateNewFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCreateNewFile.getAccessibleContext().setAccessibleName("Create New File");
		
		btnCreateNewFile.getActionMap().put("performC", performC);
		btnCreateNewFile.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyCreate, "performC");

		
		btnCreateNewFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				btnCreateNewFile.setVisible(false);
				btnChooseExistingFile.setVisible(false);
				txtProjectTitle.setVisible(true);
				txtSetFileName.setVisible(true);
				buttontextfield.setVisible(true);
				celltextfield.setVisible(true);
				lblSelectButtonNumber .setVisible(true);
				lblSelectCellNumber .setVisible(true);
				lblEnterFileText.setVisible(true);
				lblEnterFileName.setVisible(true);
				btnBackToScenario.setVisible(true);
				btnNewProject.setVisible(true);
			}
		});
		btnCreateNewFile.setBounds(331, 11, 306, 75);
		panel.add(btnCreateNewFile);
		
		txtSetFileName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String filenamecheck = txtSetFileName.getText();
					if (!filenamecheck.isEmpty()) {
						vm = VoiceManager.getInstance();
				        voice = vm.getVoice ("kevin16");
				        voice.allocate();
				        voice.speak(txtSetFileName.getText());
				       
				        
					}
					else {
						JOptionPane.showMessageDialog(null, "Please Enter a non-null file name", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}});
		txtSetFileName.setBounds(6, 265, 631, 54);
		panel.add(txtSetFileName);
		txtSetFileName.setColumns(10);
		txtProjectTitle.setBounds(6, 122, 631, 31);
		panel.add(txtProjectTitle);
		txtProjectTitle.setColumns(10);
		

		btnNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InputStream inStream = null;
				OutputStream outStream = null;
				
				if (txtProjectTitle.getText().isEmpty() || buttontextfield.getText().isEmpty() || txtSetFileName.getText().isEmpty() || celltextfield.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Enter in appropriate values in the textfields", "", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
				
					cust_file = txtProjectTitle.getText();
					buttontext = buttontextfield.getText();
					cust_filename = txtSetFileName.getText();
					celltext = celltextfield.getText();
				
					try {
						
			            PrintWriter out = new PrintWriter(new FileWriter(cust_filename + ".txt"));
						String filename = cust_filename + ".txt";
			            txtProjectTitle.getText();
			            buttontextfield.getText();
			            celltextfield.getText();
			            out.println("Button " + buttontext);
			            out.println("Cell "+ celltext);
			            out.println();
			            out.println(cust_file);
			            out.flush();
			            out.close();
			            
			            File afile =new File(filename);
			    	    File bfile =new File("FactoryScenarios/"+filename);
	
			    	    inStream = new FileInputStream(afile);
			    	    outStream = new FileOutputStream(bfile);
	
			    	    byte[] buffer = new byte[1024];
	
			    	    int length;
			    	    //copy the file content in bytes
			    	    while ((length = inStream.read(buffer)) > 0){
	
			    	    	outStream.write(buffer, 0, length);
	
			    	    }
	
			    	    inStream.close();
			    	    outStream.close();
	
			    	    //delete the original file
			    	    afile.delete();
	
			    	    System.out.println("File is copied successful!");
	
			            
			            //moves from lib to FactoryScenarios
	
			        } catch (IOException e1) {
			            System.err.println("Error occurred");
			            e1.printStackTrace();
			        }
					frame.dispose();
					//String filename = "FactoryScenarios/" +cust_filename + ".txt";
					//ScenarioNode test = new ScenarioNode();
					//test.setScenarioFile(filename);
					Scenario test = new Scenario();
					Node headnode = test.createNode(cust_file);
					test.setNumButtons(Integer.parseInt(buttontextfield.getText()));
					test.setNumCells(Integer.parseInt(celltextfield.getText()));
					test.addNode(headnode);
					for (int i = 0; i < Integer.parseInt(buttontextfield.getText()); i++)
					{
						test.getHead().addButton(i);
					}
					EditingScreen go = new EditingScreen(test);
				}
			}
		});
		btnNewProject.setBounds(6, 330, 306, 94);
		panel.add(btnNewProject);
		

		buttontextfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
						buttonnumber = Integer.parseInt(buttontextfield.getText());
						if (buttonnumber / 1 == buttonnumber) {

						vm = VoiceManager.getInstance();
				        voice = vm.getVoice ("kevin16");
				        voice.allocate();
				        voice.speak(buttontextfield.getText());
				       
				        
						}
						}
						catch (Exception e) {
							JOptionPane.showMessageDialog(null, invalidkey, "", JOptionPane.INFORMATION_MESSAGE);
							buttontextfield.setText("");
					    }
					
					}
			}
		});
		buttontextfield.setBounds(6, 189, 306, 40);
		panel.add(buttontextfield);
		buttontextfield.setColumns(10);
		
		
		celltextfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					cellnumber = Integer.parseInt(celltextfield.getText());
					if (cellnumber / 1 == cellnumber) {

					vm = VoiceManager.getInstance();
			        voice = vm.getVoice ("kevin16");
			        voice.allocate();
			        voice.speak(celltextfield.getText());
			       
			        
					}
					}
					catch (Exception m) {
						JOptionPane.showMessageDialog(null, invalidkey, "", JOptionPane.INFORMATION_MESSAGE);
						celltextfield.setText("");
				    }
				}
			}
		});
		
		celltextfield.setBounds(322, 189, 315, 40);
		panel.add(celltextfield);
		celltextfield.setColumns(10);
		

		btnBackToScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnCreateNewFile.setVisible(true);
				btnChooseExistingFile.setVisible(true);
				txtProjectTitle.setVisible(false);
				txtSetFileName.setVisible(false);
				buttontextfield.setVisible(false);
				celltextfield.setVisible(false);
				lblSelectButtonNumber .setVisible(false);
				lblSelectCellNumber .setVisible(false);
				lblEnterFileText.setVisible(false);
				lblEnterFileName.setVisible(false);
				btnBackToScenario.setVisible(false);
				btnNewProject.setVisible(false);
			}
		});
		btnBackToScenario.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBackToScenario.setBounds(322, 330, 315, 94);
		panel.add(btnBackToScenario);
		
		

	}
}
