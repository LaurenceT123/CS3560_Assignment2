package Swing;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class UserTotal implements ShowTotal 
{
	private int total;
	private ArrayList <User> UserList = new ArrayList<User>();
	
	UserTotal(ArrayList <User> userInput)
	{
		for(int i = 0; i < userInput.size();i++)
		{
			UserList.add(userInput.get(i));
		}
		total = UserList.size();
	}

	public int getTotal()
	{
		return total;
	}
	
	public void accept(Visitor visitor, JTextArea reportArea) {
		
		visitor.visitUserTotal(this, reportArea);
		
	}

}
