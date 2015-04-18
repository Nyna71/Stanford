/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

package assignment7.facepamphlet;
import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		double imageTopLocation = TOP_MARGIN + name.getHeight() + IMAGE_MARGIN;
		
		message.setFont(MESSAGE_FONT);
		add(message);
		
		name.setLocation(LEFT_MARGIN, TOP_MARGIN);
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name);
		
		noImage = new GRect(LEFT_MARGIN, imageTopLocation, IMAGE_WIDTH, IMAGE_HEIGHT);
		noImage.setVisible(false);
		add(noImage);
		
		image = new GImage(IMG_DIRECTORY + "StanfordLogo.jpg");
		image.setLocation(LEFT_MARGIN, imageTopLocation);
		image.setVisible(false);
		image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(image);
		
		status.setLocation(LEFT_MARGIN, noImage.getY() + noImage.getHeight() + STATUS_MARGIN);
		add(status);
		
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		friends.setLocation(noImage.getX() + noImage.getWidth() * 2, imageTopLocation);
		add(friends);

		friendList.setLocation(friends.getX(), friends.getY() + friends.getHeight());
		add(friendList);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
		message.setLocation((this.getWidth() - message.getWidth()) / 2, this.getHeight() - BOTTOM_MESSAGE_MARGIN - message.getHeight());
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		displayProfileName(profile.getName());
		displayProfileImage(profile.getImage());
		displayProfileStatus(profile.getStatus());
		displayProfileFriends(profile.getFriends());
	}

    private void displayProfileFriends(Iterator<String> friendsIterator) {
    	double yPos = 0;
    	friendList.removeAll();
    	while(friendsIterator.hasNext()) {
    		GLabel friend = new GLabel(friendsIterator.next(), 0, yPos); 
    		friendList.add(friend);
			yPos += friend.getHeight();
		}	
	}


	private void displayProfileStatus(String profileStatus) {
		if(profileStatus == null) status.setLabel("No Current Status");
		else status.setLabel(profileStatus);
	}


	private void displayProfileImage(GImage profileImage) {
		if(profileImage == null) {
			image.setVisible(false);
			noImage.setVisible(true);
		}
		else {
			noImage.setVisible(false);
			remove(image);
			image = new GImage(profileImage.getImage());
			image.setLocation(LEFT_MARGIN, noImage.getY());
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			image.setVisible(true);
			add(image);
		}	
	}


	private void displayProfileName(String profileName) {
		name.setLabel(profileName);
	}

	private GLabel message = new GLabel("");
	private GLabel name = new GLabel("");
	private GLabel status = new GLabel("");
	private GLabel friends = new GLabel("Friends");
	private GCompound friendList = new GCompound();
	private GRect noImage;
	private GImage image;
}
