/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */
package assignment7.facepamphlet;
import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		canvas = new FacePamphletCanvas();
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		this.add(canvas);
		
		initNorthBoder();
		initWestBorder();
		initCanvas();
		
		addActionListeners();
		
    }
 
	private void initNorthBoder() {
    	nameTF = new JTextField(TEXT_FIELD_SIZE);
    	addProfileBT = new JButton("Add");
    	deleteProfileBT = new JButton("Delete");
    	lookupProfileBT = new JButton("Lookup");
    	
		add(new JLabel ("Name"), NORTH);
	    add(nameTF, NORTH);
	    add(addProfileBT, NORTH);
	    add(deleteProfileBT, NORTH);
	    add(lookupProfileBT, NORTH);
	}

    private void initWestBorder() {
    	addStatusTF = new JTextField(TEXT_FIELD_SIZE);
    	addFriendTF = new JTextField(TEXT_FIELD_SIZE);
    	
    	addStatusBT = new JButton("Change Status");
    	addPictureBT = new JButton("Select Picture");
    	addFriendBT = new JButton("Add Friend");
    	
    	addPictureFC = new JFileChooser();
    	addPictureFC.setCurrentDirectory(new File(IMG_DIRECTORY));

        add(addStatusTF, WEST);
        add(addStatusBT, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(addPictureBT, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(addFriendTF, WEST);
        add(addFriendBT, WEST);
		
        addStatusTF.addActionListener(this);
        addFriendTF.addActionListener(this);
	}

	private void initCanvas() {
		// Add an empty message label to the canvas.
	}
    
	/**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add") && nameTF.getText().length() !=0) {
			currentProfile = new FacePamphletProfile(nameTF.getText());
			// Does profile already exists ?
			if(facePamphletDb.containsProfile(nameTF.getText())) {
				canvas.showMessage("This Profile already exists!");
				currentProfile = facePamphletDb.getProfile(nameTF.getText());
			}
			else {
				facePamphletDb.addProfile(currentProfile);
				canvas.showMessage("Profile " + currentProfile.getName() + " added to the network.");	
			}
		}
		
		if(e.getActionCommand().equals("Delete") && nameTF.getText().length() !=0) {
			if(facePamphletDb.containsProfile(nameTF.getText())) {
				canvas.showMessage("Profile " + nameTF.getText() + " deleted from the network.");
				facePamphletDb.deleteProfile(nameTF.getText());
				
				// Delete all relationships
				for(String name : facePamphletDb.getProfileNames())
					facePamphletDb.getProfile(name).removeFriend(nameTF.getText());
				
			} else canvas.showMessage("The Profile " + nameTF.getText() + " does not exists in the network.");
		}
		
		if(e.getActionCommand().equals("Lookup") && nameTF.getText().length() !=0) {
			currentProfile = facePamphletDb.getProfile(nameTF.getText());
			if(currentProfile != null)
				canvas.showMessage("Displaying " + nameTF.getText() + "'s profile.");
			else
				canvas.showMessage("The Profile " + nameTF.getText() + " does not exists in the network.");
		}
		
		if(e.getActionCommand().equals("Change Status") || e.getSource() == addStatusTF) {
			currentProfile = facePamphletDb.getProfile(nameTF.getText());
			if(currentProfile != null) {
				currentProfile.setStatus(addStatusTF.getText());
				canvas.showMessage("Changing " + nameTF.getText() + "'s status.");
			}
		}
		
		if(e.getActionCommand().equals("Select Picture") && nameTF.getText().length() !=0) {
			if(addPictureFC.showOpenDialog(canvas) == JFileChooser.APPROVE_OPTION) {
				canvas.showMessage("Changed profile picture to " + addPictureFC.getSelectedFile().getName());
				currentProfile = facePamphletDb.getProfile(nameTF.getText());
				if(currentProfile != null) currentProfile.setImage(new GImage(addPictureFC.getSelectedFile().getPath()));
			}
		}
		
		// Did Add Friend button or Add Friend text fields get trigerred ?
		if((e.getActionCommand().equals("Add Friend") || e.getSource() == addFriendTF)
				// Does both text fields contain Profile Names ?
				&& addFriendTF.getText().length() !=0 && nameTF.getText().length() != 0) {
			
			// Check if Add Friend text box contains a valid profile name
			if(!facePamphletDb.containsProfile(addFriendTF.getText()))
				canvas.showMessage(addFriendTF.getText() + " does not exist in the network!");
			
			//Check if Name text box contains a valid profile name
			else if(!facePamphletDb.containsProfile(nameTF.getText()))
				canvas.showMessage(nameTF.getText() + " does not exist in the network!");
			
			// Check if Name field and Text field are the same profile
			else if(nameTF.getText().equals(addFriendTF.getText()))
				canvas.showMessage(nameTF.getText() + " cannot be friend with himself!");
			else {
				currentProfile = facePamphletDb.getProfile(nameTF.getText());
				currentProfile.addFriend(addFriendTF.getText());
				facePamphletDb.getProfile(addFriendTF.getText()).addFriend(nameTF.getText());
				canvas.showMessage("Adding friend " + addFriendTF.getText());
			}
		}
		
		if(currentProfile != null) canvas.displayProfile(currentProfile);
	}
    
    private FacePamphletCanvas canvas;
    private	JTextField nameTF;
    private JButton addProfileBT;
    private JButton deleteProfileBT;
    private JButton lookupProfileBT;
    
    private JTextField addStatusTF;
    private JButton addStatusBT;
    private JButton addPictureBT;
    private JTextField addFriendTF;
    private JButton addFriendBT;
    
    private JFileChooser addPictureFC;
    
    private FacePamphletProfile currentProfile;
    private FacePamphletDatabase facePamphletDb = new FacePamphletDatabase();
}
