package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import Swing.GUI_User;
import Swing.User;

public class FollowUserCommand implements Command 
{
	
	private JButton but;
	private ArrayList<User> listOFUsers;
	private ArrayList<User> listOfFollowings;
	private JTextField userId;
	private User mainTarget;
	private GUI_User mainGUI;
	private ArrayList<GUI_User> GUI_UserList;
	private DefaultListModel <String> model;
	
	public FollowUserCommand(JButton button, ArrayList<User> listU, ArrayList<User> listF, JTextField getId, User main, GUI_User g, ArrayList <GUI_User> listofG, DefaultListModel m)
	{
		but = button;
		listOFUsers = listU;
		listOfFollowings = listF;
		userId = getId;
		mainTarget = main;
		mainGUI = g;
		GUI_UserList = listofG;
		model = m;
		
	}

	@Override
	public void execute() 
	{
		ActionListener buttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(UserArrayListContains(listOFUsers,userId.getText()) != -1 && !userId.getText().equals(mainTarget.getID()))
				{
					System.out.println("User found!....Trying to follow User...");
					
					if( UserArrayListContains(listOfFollowings, userId.getText()) == -1)
					{
						int getIndex = UserArrayListContains(listOFUsers, userId.getText());
						listOfFollowings.add(listOFUsers.get(getIndex)); //update listOfFollowings
						
						//Update followed user followers
						listOFUsers.get(getIndex).addFollower(mainTarget); 
						System.out.println("--------" + listOFUsers.get(getIndex).getID());
						System.out.println("Followers: " + printArrayList(listOFUsers.get(getIndex).getFollowers()));
				
						//Update maintargets followings list
						mainTarget.addFollowings(listOFUsers.get(getIndex));
						System.out.println("--------" + mainTarget.getID());
						System.out.println("Following: " + printArrayList(mainTarget.getFollowings()));
						
						model.addElement(userId.getText());
						mainGUI.updateJList(); //update main target's live feed
						
						System.out.println(listOFUsers.get(getIndex).getID()); //Subject
						System.out.println("Attach -->" + GUI_UserList.get(getIndexGUI(mainTarget)).getUserGUI().getID()); //Observer
						
						
						listOFUsers.get(getIndex).attach(GUI_UserList.get(getIndexGUI(mainTarget)));
						
					}
					else
					{
						System.out.println("Following failed since this user is already following provided user");
						userId.setText("");
					}
				}
				else
				{
					System.out.println("Sorry...that User doesn't exist or user can't follow themselves");
				}
			}

			private String printArrayList(ArrayList<Swing.User> followers) 
			{
				String empty = "";
				for(int i = 0; i<followers.size();i++)
				{
					empty += followers.get(i).getID();
				}
				return empty;
			}
		};
		
		but.addActionListener(buttonListener);
		
	}
	
	private int UserArrayListContains(ArrayList<User> temp, String value)
	{
		
		int answer = -1;
		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(i).getID().equals(value))
			{
				answer = i;
			}
		}
		
		return answer;
	}
	
	private int getIndexGUI (User user)
	{
		int temp= -1;
		
		for(int i = 0; i < GUI_UserList.size();i++)
		{
			if(GUI_UserList.get(i).getUserGUI().getID().equals(user.getID()))
			{
				temp = i;
			}
		}
		
		return temp;
	}

}
