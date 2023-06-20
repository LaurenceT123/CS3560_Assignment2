package Swing;
import java.util.ArrayList;

public class User_Group implements Users
{
	private String groupID;
	private ArrayList<Users> listOfUsers = new ArrayList<Users>(); //a list of users (including other groups) that are in the group
	private int numberOfUsers;
	
	//Create group
	User_Group(String userInput)
	{
		this.groupID = userInput;
		numberOfUsers = 0;
	}
	
	public void addUser(User userInput)
	{
		if(!contain(userInput.getID()))
		{
			listOfUsers.add(userInput);
			numberOfUsers++;
		}
		else
		{
			System.out.println("Can't add this user cause this user already exist in this group");
		}
	}
	
	public void addGroup(User_Group userInput)
	{
		listOfUsers.add(userInput);
		numberOfUsers++;
	}
	
	@Override
	public String getID() {
		return this.groupID;
	}

	public ArrayList<Users> getListOfUsers() {
		return listOfUsers;
	}

	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	
	private boolean contain(String value)
	{
		boolean answer = false;
		for(int i = 0; i < listOfUsers.size(); i++)
		{
			if(listOfUsers.get(i).getID().equals(value))
			{
				answer = true;
			}
		}
		
		return answer;
	}
	
	
	//For testing purposes
	private void printListOfUsers()
	{
		System.out.println("-+-" + getID() + "-+-");
		for(int i = 0; i < listOfUsers.size();i++)
		{
			System.out.print(listOfUsers.get(i).getID() + " ");
		}
		System.out.println("");
	}
	
	
	
}
