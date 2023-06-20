package Swing;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;


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
	
	//buttons
	private JButton addUser;
	private JButton addGroup;
	private JButton openUserView;
	private JButton showUserTotal;
	private JButton showGroupTotal;
	private JButton showMessageTotal;
	private JButton showPositivePercentage;
	
	//tree
	private JTree myTree;
	
	
	private ArrayList <User> listOfUsers = new ArrayList<User>();
	private ArrayList <User_Group> listOfGroups = new ArrayList<User_Group>();
	ShowTotalVisitor visit = new ShowTotalVisitor();
	
	
	private ArrayList<GUI_User> allGUIOpen;
	
	//Private constructor to force use of getInstance()
	private GUI()
	{
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
		mainContainer.setLayout(new GridLayout(2,2));
		mainContainer.setBackground(Color.WHITE);
		
		
		//Tree
		createTree(mainContainer);
		
		
		//--------------Create Right Side------------------
		
		JFrame testframe = new JFrame();
		Container rightSide = testframe.getContentPane();
		rightSide.setLayout(new GridLayout(3,1));
		
		//Create top right
		createTopRight(mainContainer);
		
		reportArea = new JTextArea();
		reportArea.setText("Report Area:");
		mainContainer.add(reportArea);
		
		//Create bottom right
		createBottomRight(mainContainer);
		
		//--------------------------------------------------
		
		frame.setSize(width,height);
		frame.setTitle("Admin Control Panel - Mini Twitter");
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
	private void createTree(Container main)
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		myTree = new JTree(root);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(myTree);
		
		main.add(scrollPane);
		
	}
	
	
	//+++++++++++++++++++++++++++++++++Button Action Methods+++++++++++++++++++++++++++++++++
	
	//Add User Button 
	private void jButtonAddUserActionPerformed(JButton but)
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
	
	private void jButtonAddGroupActionPerformed(JButton but)
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
	
	private void jButtonOpenUserView(JButton but)
	{
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
	
	private void jButtonGetTotalUsersPerformed(JButton but)
	{
		ActionListener buttonListener = new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						UserTotal usersT = new UserTotal(listOfUsers);
						usersT.accept(visit, reportArea);
					}
				};
				
				but.addActionListener(buttonListener);
	}
	
	private void jButtonGetTotalGroupPerformed(JButton but)
	{
		ActionListener buttonListener = new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						GroupTotal groupTotal = new GroupTotal(listOfGroups);
						groupTotal.accept(visit,reportArea);
						
					}
				};
				
				but.addActionListener(buttonListener);
	}
	
	private void jButtonGetTotalMessages(JButton but)
	{
		ActionListener buttonListener = new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						MessagesTotal temp = new MessagesTotal(newWindow);
						temp.accept(visit, reportArea);
						
					}
				};
				
				but.addActionListener(buttonListener);
	}
	
	private void jButtonGetTotalPositiveMessages(JButton but)
	{
		ActionListener buttonListener = new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						PositiveTotal temp = new PositiveTotal(newWindow);
						temp.accept(visit, reportArea);
						
					}
				};
				
				but.addActionListener(buttonListener);
	}
	
	
	
	//++++++++++++++++++++++++++++++++++++++++++Other Methods++++++++++++++++++++++++++++++++++++++++++++
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
