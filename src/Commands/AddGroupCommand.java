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

public class AddGroupCommand implements Command {

	private ArrayList <User_Group> listOfGroups;
	private JButton but;
	private JTree myTree;
	private JTextField groupId;
	private JTextArea reportArea;
	
	public AddGroupCommand(ArrayList <User_Group> listG, JButton button, JTree tree, JTextField text, JTextArea report)
	{
		listOfGroups = listG;
		but = button;
		myTree = tree;
		groupId = text;
		reportArea = report;
		
	}
	@Override
	public void execute() 
	{
		ActionListener buttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String reportingInfo = "Reporting Info:\n1. Clicked Add Group!...\nNOTE: adding a group will come with a blank file\nThis is not a user, it's sole purpose is to change the icon of file in tree";
			
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) myTree.getSelectionPath().getLastPathComponent();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(groupId.getText());
				User_Group newUserGroup = new User_Group(groupId.getText()); //create user
				
				if(groupArrayListContains(listOfGroups,selectedNode) != -1) //prevents adding a group when a user is selected
				{
					listOfGroups.add(newUserGroup); //add to ArrayList

					groupId.setText("");
					selectedNode.add(newNode);
					
					DefaultMutableTreeNode emptyNode = new DefaultMutableTreeNode("");
					newNode.add(emptyNode); //to help get the folder icon
											//when counting for user total, it will ignore this
					
				
					listOfGroups.get(groupArrayListContains(listOfGroups,selectedNode)).addGroup(newUserGroup);
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
