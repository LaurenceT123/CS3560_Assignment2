package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Swing.User;
import Swing.User_Group;

public class VerifyAllId implements Command 
{
	private JButton but;
	private ArrayList<User> listOfUsers;
	private ArrayList<User_Group> listOfGroups;
	private JTextArea reportArea2;

	public VerifyAllId(JButton button, ArrayList<User> listU, ArrayList <User_Group> listG, JTextArea report)
	{
		but = button;
		listOfUsers = listU;
		listOfGroups = listG;
		reportArea2 = report;
	}
	public void execute() 
	{
		ActionListener buttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				String reporting = "Checking all Users Id.....\n";
				int totalInvalidIds = 0;
				
				for(int i = 0; i < listOfUsers.size();i++)
				{
					if(containsUser(listOfUsers.get(i).getID()) > 1 || listOfUsers.get(i).getID().contains(" ") || containsGroup(listOfUsers.get(i).getID()) > 0)
					{
						totalInvalidIds++;
					}
				}
				
				reporting+= "Checking all Groups Id.....\n";
				
				for(int i = 0; i < listOfGroups.size();i++)
				{
					if(containsGroup(listOfGroups.get(i).getID()) > 1 || listOfGroups.get(i).getID().contains(" ") || containsUser(listOfGroups.get(i).getID()) > 0)
					{
						totalInvalidIds++;
					}
				}
				
				reporting+="Summary......\n";
				reporting+= "Total invalid IDs: " + totalInvalidIds + "\n";
				
				
				reportArea2.setText(reporting);
				
			}
		};
		
		but.addActionListener(buttonListener);
	}
	
	
	private int containsUser(String temp)
	{
		int answer = 0;
		
		for(int i = 0; i < listOfUsers.size();i++)
		{
			if(temp.equals(listOfUsers.get(i).getID()))
			{
				answer++;
			}
		}
		
		return answer;
		
	}
	
	private int containsGroup(String temp)
	{
		int answer = 0;
		
		for(int i = 0; i < listOfGroups.size();i++)
		{
			if(temp.equals(listOfGroups.get(i).getID()))
			{
				answer++;
			}
		}
		
		return answer;
		
	}
	

}
