package enamel;

import java.io.*;
import java.util.*;

public class SkipButton extends NodeButton {
	protected String response;
	protected Node nextNode;
	protected Map<Integer, int[]> pins;
	protected String audioFile;
	
	public SkipButton(int buttonNumber) {
		super(buttonNumber);
		this.response = "";
		this.nextNode = null;
		this.audioFile = "";
		this.pins = new HashMap<>();
	}

	public SkipButton(int buttonNumber, String response) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = null;
		this.audioFile = "";
		this.pins = new HashMap<>();
	}

	public SkipButton(int buttonNumber, String response, Node nextNode) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.audioFile = "";
		this.pins = new HashMap<>();

		// TODO Auto-generated constructor stub
	}
	
	public SkipButton(int buttonNumber, String response, String audioFile, Node nextNode) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.audioFile = audioFile;
		this.pins = new HashMap<>();

		// TODO Auto-generated constructor stub
	}

	public SkipButton(int buttonNumber, String response, Node nextNode,  Map<Integer, int[]> pins) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.pins = new HashMap<>(pins);
		this.audioFile = "";

		// TODO Auto-generated constructor stub
	}
	
	public SkipButton(int buttonNumber, String response, Node nextNode, Map<Integer, int[]> pins, String audioFile) {
		super(buttonNumber);
		this.response = response;
		this.nextNode = nextNode;
		this.pins = new HashMap<>(pins);
		this.audioFile = audioFile;

		// TODO Auto-generated constructor stub
	}

	public SkipButton(int buttonNumber, Node nextNode) {
		super(buttonNumber);
		this.response = "";
		this.nextNode = nextNode;
		this.pins = new HashMap<>();
		this.audioFile = "";

		// TODO Auto-generated constructor stub
	}

	public SkipButton(SkipButton other) {
		super(other);
		this.response = other.response;
		this.nextNode = other.nextNode;
		this.pins = new HashMap<>(other.pins);
		this.audioFile = other.audioFile;

		// TODO Auto-generated constructor stub
	}
	
	public String getResponse() {
		return this.response;
	}
			
	public void setResponse(String response) {
		this.response = response;
	}
	
	public void setNextNode(Node next) {
		this.nextNode = next;
	}
	
	public Node getNextNode() {
		return this.nextNode;
	}

	/**
	 * @return the listOfPins
	 */
	public int[] getPins(int cellNumber) {
		return this.pins.get(cellNumber);
	}

	/**
	 * @param pins the listOfPins to set
	 */
	public void setPins(int[] pins, int cellNumber) {
		this.pins.put(cellNumber, pins);
	}
	
	public void setPin(int cellNumber, int pin, int value) {
		if (this.pins.get(cellNumber) == null) {
			this.pins.put(cellNumber, new int[8]);
		}
		this.pins.get(cellNumber)[pin-1] = value;
	}
	
	public void setAudioFile(String audioFile) {
		try {
			Scanner file = new Scanner(new File(audioFile));
			file.close();
			this.audioFile = audioFile;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("The file name does not exist"); 
		}
		
		
	}
	
	public String getAudioFile() {
		return this.audioFile;
	}

}

