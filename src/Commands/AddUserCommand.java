package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Swing.User;
import Swing.User_Group;

public class AddUserCommand implements Command {

	private ArrayList <User> listOfUsers;
	private ArrayList <User_Group> listOfGroups;
	private JButton but;
	private JTree myTree;
	private JTextField userId;
	private JTextArea reportArea;
	
	public AddUserCommand(ArrayList <User> list, ArrayList <User_Group> listG, JButton button, JTree tree, JTextField text, JTextArea report)
	{
		listOfUsers = list;
		listOfGroups = listG;
		but = button;
		myTree = tree;
		userId = text;
		reportArea = report;
		
	}
	@Override
	public void execute() 
	{
		ActionListener buttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String reportingInfo = "Reporting Info:\n1. Clicked Add User!...\n";
				
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) myTree.getSelectionPath().getLastPathComponent();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(userId.getText());
				User newUser = new User(userId.getText()); //create user
				
				listOfUsers.add(newUser); //add to ArrayList
				
				if(groupArrayListContains(listOfGroups,selectedNode) != -1) //prevents adding a user when a user is selected
				{
					reportingInfo += "2. Adding user to group...\n";
					int getIndex = groupArrayListContains(listOfGroups,selectedNode);
					listOfGroups.get(getIndex).addUser(newUser);
					
					userId.setText("");
					
					selectedNode.add(newNode);
						
					DefaultTreeModel refreshTree = (DefaultTreeModel) myTree.getModel();
					refreshTree.reload();
				}
				
				reportArea.setText(reportingInfo);
			}
		};
		
		but.addActionListener(buttonListener);
		
	}
	
	private int  groupArrayListContains(ArrayList<User_Group> temp, DefaultMutableTreeNode value)
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
