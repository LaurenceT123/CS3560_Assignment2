package Swing;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class GroupTotal implements ShowTotal 
{
	private int total;
	private ArrayList <User_Group> GroupList = new ArrayList<User_Group>();
	
	GroupTotal(ArrayList <User_Group> userInput)
	{
		for(int i = 0; i < userInput.size();i++)
		{
			GroupList.add(userInput.get(i));
		}
		total = GroupList.size();
	}

	public int getTotal()
	{
		return total;
	}
	
	
	public void accept(Visitor visitor, JTextArea report) {
		visitor.visitGroupTotal(this, report);
		
	}

}
