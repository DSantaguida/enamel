package enamel;

import java.awt.EventQueue;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Recorder1 {

	private JFrame frame;
	Voice voice;
	VoiceManager vm;
	
	//helloworld
	
    void start() {
    	try {
    		
    		//Allow for name input in editing screen before moving to recorder screen to record??
    		//Need to name before calling this class
    		vm = VoiceManager.getInstance();
	        voice = vm.getVoice ("kevin16");
	        voice.allocate();
	        voice.speak("Please enter your recording name before we begin");
    		String name = JOptionPane.showInputDialog("Please enter your recording name before we begin:");
    		
    	    //Change recording time to allow user to choose when they want to stop
    		
    	    File wavFile = new File(name + ".wav");	
    		
    			AudioFormat format = getAudioFormat();
    			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
    			// checks if system supports the data line
    			if (!AudioSystem.isLineSupported(info)) {
    				System.out.println("Line not supported");
    				System.exit(0);
    			}
    			line = (TargetDataLine) AudioSystem.getLine(info);
    			line.open(format);
    			line.start();   // start capturing
 
    			System.out.println("Start capturing...");
 
    			AudioInputStream ais = new AudioInputStream(line);
 
    			System.out.println("Start recording...");
 
    			// start recording
    			AudioSystem.write(ais, fileType, wavFile);
    		
 
    		} catch (LineUnavailableException ex) {
            ex.printStackTrace();
    		} catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }
	

 
	/**
	 * Launch the application.
	 */
    static final long RECORD_TIME = 150000;  // 30 seconds limit past user
    

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
 
    // the line from which audio data is captured
    TargetDataLine line;
    private JButton btnStart;
    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
 
 
    /**
     * Closes the target data line to finish capturing and recording
     */
    void finish() {
        line.stop();
        line.close();
        System.out.println("Finished Recording...");
    }
 
    /**
     * Entry to run the program
     */

	public static void main(String[] args) {
		Recorder1 window = new Recorder1();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                    
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                window.finish();
                
            }
        });
 
        stopper.start();
        // start recording
        window.start();
	}

	/**
	 * Create the application.
	 */
	public Recorder1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 207);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEndRecording = new JButton("End Recording");
		btnEndRecording.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				try {
					finish();
				} catch (Exception err) {
					err.printStackTrace();
				}
				
	
			}
		});
		btnEndRecording.setBounds(10, 95, 414, 50);
		frame.getContentPane().add(btnEndRecording);
		
		btnStart = new JButton("Start");
		btnStart.setVisible(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		btnStart.setBounds(10, 11, 414, 59);
		frame.getContentPane().add(btnStart);
	}
	

    /**
     * Captures the sound and record into a WAV file
     */
    

}
