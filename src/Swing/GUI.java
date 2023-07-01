package Swing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

//Commands import
import Commands.AddGroupCommand;
import Commands.AddUserCommand;
import Commands.Command;
import Commands.GetLastUpdatedUserCommand;
import Commands.OpenUserViewCommand;
import Commands.ShowGroupTotalCommand;
import Commands.ShowTotalMessagesCommand;
import Commands.ShowTotalPositiveMessagesCommand;
import Commands.ShowUserTotalCommand;
import Commands.VerifyAllId;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/*Follows Singleton Pattern
 * - ensure a class has only one instance and provide a global point of access to it
 * - Check Driver class and console, creating another GUI will send out a message
 * 
 * GUI ()
 * Purpose:
 * - Create a GUI of admin panel for a "Mini Twitter"
 * */
public class GUI 
{
	GUI_User newWindow;
	
	private static GUI pointer;
	
	private JFrame frame;
	private int width =750;
	private int height = 500;
	
	//Text Areas
	private JTextField userId;
	private JTextField groupId;
	private JTextArea reportArea;
	private JTextArea reportArea2; //this report area is ONLY for the following buttons:
								   // userGroupIDVerify
								   // lastUpdatedUser
	
	//buttons
	private JButton addUser;
	private JButton addGroup;
	private JButton openUserView;
	private JButton showUserTotal;
	private JButton showGroupTotal;
	private JButton showMessageTotal;
	private JButton showPositivePercentage;
	
	private JButton userGroupIDVerify;
	private JButton lastUpdatedUser;
	
	//tree
	private JTree myTree;
	
	
	private ArrayList <User> listOfUsers = new ArrayList<User>();
	private ArrayList <User_Group> listOfGroups = new ArrayList<User_Group>();
	ShowTotalVisitor visit = new ShowTotalVisitor();
	
	
	private ArrayList<GUI_User> allGUIOpen;
	
	//Private constructor to force use of getInstance()
	private GUI()
	{
		reportArea = new JTextArea();
		reportArea2 = new JTextArea();
		allGUIOpen = new ArrayList<GUI_User>();
		this.frame = new JFrame();
		User_Group Root = new User_Group("Root");
		listOfGroups.add(Root);
		setUpGUI();
	}
	
	public static synchronized GUI getInstance()
	{
		if(pointer == null)
		{
			pointer = new GUI();
		}
		else
		{
			System.out.println("There already exists a GUI, will not make this one");
		}
		return pointer;
	}
	
	private void setUpGUI()
	{
		//Sets up main Container
		Container mainContainer = frame.getContentPane();
		mainContainer.setLayout(new GridLayout(3,2));
		mainContainer.setBackground(Color.WHITE);
		
		
		//Tree
		createTree(mainContainer);
		
		//--------------Major Buttons------------------
		
		//Create top right
		createTopRight(mainContainer);
		
		reportArea.setText("Report Area for the following buttons:------------------------>");
		reportArea.setEditable(false);
		mainContainer.add(reportArea);
		
		//Create bottom right
		createBottomRight(mainContainer);
				
		
		reportArea2.setText("Report Area for the following buttons:----------------------->");
		reportArea2.setEditable(false);
		mainContainer.add(reportArea2);
		
		createBottomLeft (mainContainer);
		
		//--------------------------------------------------
		
		
		frame.setSize(width,height);
		frame.setTitle("Admin Control Panel - Mini Twitter 2.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void createTopRight(Container main)
	{
		JFrame topRightFrame = new JFrame();
		Container topRightContainer = topRightFrame.getContentPane();
		topRightContainer.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		userId = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 0;
		topRightContainer.add(userId,gbc);
		
		groupId = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 1;
		topRightContainer.add(groupId,gbc);
		
		addUser = new JButton("Add User");
		jButtonAddUserActionPerformed(addUser);
		gbc.gridx = 1;
		gbc.gridy = 0;
		topRightContainer.add(addUser, gbc);

		addGroup = new JButton("Add Group");
		jButtonAddGroupActionPerformed(addGroup);
		gbc.gridx = 1;
		gbc.gridy = 1;
		topRightContainer.add(addGroup, gbc);
		
		openUserView = new JButton("Open User View");
		gbc.ipady = 15;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		topRightContainer.add(openUserView, gbc);
		jButtonOpenUserView(openUserView);
		
		main.add(topRightContainer);
		
	}
	
	private void createBottomRight(Container main)
	{
		JPanel bottomRight = new JPanel(new GridLayout(2,2));	
		
		showUserTotal = new JButton("Show User Total");
		bottomRight.add(showUserTotal);
		jButtonGetTotalUsersPerformed(showUserTotal);
		
		showGroupTotal = new JButton("Show Group Total");
		bottomRight.add(showGroupTotal);
		jButtonGetTotalGroupPerformed(showGroupTotal);
		
		showMessageTotal = new JButton("Show Message Total");
		bottomRight.add(showMessageTotal);
		jButtonGetTotalMessages(showMessageTotal);
		
		showPositivePercentage = new JButton("Show Positive Messages Total");
		bottomRight.add(showPositivePercentage);
		jButtonGetTotalPositiveMessages(showPositivePercentage);

		main.add(bottomRight);
			
	}
	
	private void createBottomLeft(Container main)
	{
		JPanel bottomLeft = new JPanel(new GridLayout(1,2));
		
		userGroupIDVerify = new JButton ("User and Group ID Verify");
		bottomLeft.add(userGroupIDVerify);
		jbuttonUserGroupIDVerify(userGroupIDVerify);
		
		lastUpdatedUser = new JButton("Get Last Updated User");
		bottomLeft.add(lastUpdatedUser);
		jbuttonGetLastUpdatedUser(lastUpdatedUser);
		
		main.add(bottomLeft);
		
	}
	private void createTree(Container main)
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		myTree = new JTree(root);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(myTree);
		
		main.add(scrollPane);
		
	}
	
	
	//+++++++++++++++++++++++++++++++++Button Action Methods+++++++++++++++++++++++++++++++++
	
	//Add User Button ---------------> Check package named Commands
	private void jButtonAddUserActionPerformed(JButton but)
	{
		Command command = new AddUserCommand(listOfUsers, listOfGroups, but, myTree, userId,reportArea);
		command.execute();
	}
	
	//Add Group Button -------------> Check package named Commands
	private void jButtonAddGroupActionPerformed(JButton but)
	{
		Command command = new AddGroupCommand(listOfGroups, but, myTree, groupId, reportArea);
		command.execute();
	}
	
	//Opens User view --------------> Check package named Commands
	private void jButtonOpenUserView(JButton but)
	{
		Command command = new OpenUserViewCommand(listOfUsers, but, myTree, newWindow, allGUIOpen);
		command.execute();
	}
	
	//Show total users made in reportArea -----------> Check package named Commands
	private void jButtonGetTotalUsersPerformed(JButton but)
	{
		Command command = new ShowUserTotalCommand(listOfUsers,but, reportArea,visit);
		command.execute();
	}
	
	//Show total groups made in reportArea -----------> Check package named Commands
	private void jButtonGetTotalGroupPerformed(JButton but)
	{
		Command command = new ShowGroupTotalCommand(listOfGroups,but,reportArea,visit);
		command.execute();
	}
	
	//Show total messages made in reportArea -----------> Check package named Commands
	private void jButtonGetTotalMessages(JButton but)
	{
		Command command = new ShowTotalMessagesCommand(allGUIOpen, but, visit,reportArea);
		command.execute();
	}
	
	//Show total positive messages made in reportArea -----------> Check package named Commands
	private void jButtonGetTotalPositiveMessages(JButton but)
	{
		Command command = new ShowTotalPositiveMessagesCommand(allGUIOpen, but, visit, reportArea);
		command.execute();
	}
	
	//Check all user and group Id are unique -----------> Check package named Commands
	private void jbuttonUserGroupIDVerify(JButton but)
	{
		Command command = new VerifyAllId(but, listOfUsers, listOfGroups,reportArea2);
		command.execute();
	}
	//Show last updated user made in reportArea -----------> Check package named Commands
	private void jbuttonGetLastUpdatedUser(JButton but)
	{
		Command command = new GetLastUpdatedUserCommand(but, listOfUsers, reportArea2);
		command.execute();
	}
	
	
	

}
