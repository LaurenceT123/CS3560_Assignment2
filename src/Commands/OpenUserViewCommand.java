package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import Swing.GUI_User;
import Swing.User;

public class OpenUserViewCommand implements Command 
{
	private ArrayList<User> listOfUsers;
	private JButton but;
	private JTree myTree;
	private GUI_User newWindow;
	private ArrayList<GUI_User> allGUIOpen;
	
	public OpenUserViewCommand(ArrayList <User> list, JButton button, JTree tree, GUI_User window, ArrayList<GUI_User> UserGUI)
	{
		listOfUsers = list;
		but = button;
		myTree = tree;
		newWindow = window;
		allGUIOpen = UserGUI;
	}

	@Override
	public void execute() {
		ActionListener buttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) myTree.getSelectionPath().getLastPathComponent();
				User selectedUser = listOfUsers.get(userArrayListContains(listOfUsers,selectedNode));

				newWindow = new GUI_User(selectedUser, listOfUsers, allGUIOpen);
				allGUIOpen.add(newWindow);
			}
		};
		
		but.addActionListener(buttonListener);
		
	}
	
	private int  userArrayListContains(ArrayList<User> temp, DefaultMutableTreeNode value)
	{
		int answer = -1;
		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(i).getID().equals(value.toString()))
			{
				answer = i;
			}
		}
		
		return answer;
	}

}
