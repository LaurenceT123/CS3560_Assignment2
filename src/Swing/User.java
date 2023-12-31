package Swing;

import java.util.ArrayList;
import java.util.Date;

public class User extends Subject implements Users
{
	private String ID; //Unique id of user
	private ArrayList <User> followers = new ArrayList<User>(); //list of userIDs that are following this user
	private ArrayList<User> followings = new ArrayList<User>(); //list of userIDs that user is following
	private ArrayList<String> messages = new ArrayList<String>(); //list of user's messages
	
	//Added variables for mini twitter 2.0
	private long creationTime; //system timestamp that object was created
	private long lastUpdateTime; //last updated timestamp of object, when posting
	
	public User(String userInput)
	{
		this.ID = userInput;
		setCreationTime(System.currentTimeMillis());
		setLastUpdateTime(creationTime);
	}
	
	public void addFollower(User inputID)
	{
		if(!followers.contains(inputID))
		{
			followers.add(inputID);
		}
		else
		{
			System.out.println("This follower is already following this user!");
		}
	}
	
	public void addFollowings(User inputID)
	{
		if(!followings.contains(inputID))
		{
			followings.add(inputID);
		}
		else
		{
			System.out.println("This user is already following this user!");
		}
	}
	
	public void addMessage(String userInput)
	{
		messages.add(userInput);
		setLastUpdateTime(System.currentTimeMillis());
	}

	//Getter for User ID --> Will not allow a change so no setter
	@Override
	public String getID() {
		return ID;
	}

	//Getting ArrayList of followers --> Will not allow a change so no setter
	public ArrayList <User> getFollowers() {
		ArrayList <User> temp = new ArrayList<User> ();
		for(int i = 0; i < followers.size(); i++)
		{
			temp.add(followers.get(i));
		}
		
		return temp;
	}

	//Getter ArrayList of followings --> Will not allow a change so no setter
	public ArrayList<User> getFollowings() {
		ArrayList <User> temp = new ArrayList<User> ();
		for(int i = 0; i < followings.size(); i++)
		{
			temp.add(followings.get(i));
		}
		
		return temp;
	}
	
	public ArrayList<String> getMessages() {
		ArrayList <String> temp = new ArrayList<String> ();
		for(int i = 0; i < messages.size(); i++)
		{
			temp.add(messages.get(i));
		}
		
		return temp;
	}

	public long getCreationTime() {
		return creationTime;
	}
	public String getCreationTimeString()
	{
		return Long.toString(creationTime);
	}

	public void setCreationTime(long date) {
		this.creationTime = date;
	}

	public long getLastUpdateTime() {
			return lastUpdateTime;
	}

	public void setLastUpdateTime(long creationTime2) {
		this.lastUpdateTime = creationTime2;
	}


}
