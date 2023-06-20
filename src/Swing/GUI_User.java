package Swing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.html.ListView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class GUI_User implements Observer
{
	private static int totalMessages;
	private static int totalPositiveMessages;
	
	private JFrame frame;
	private int width = 460;
	private int height = 600;
	
	//Text Areas
	private JTextField userId;
	private JTextArea tweetMessage;
	
	//buttons
	private JButton followUser;
	private JButton postTweet;
	
	//List Views
	private DefaultListModel <String> model;
	private DefaultListModel <String> model1;
	private JList followings;
	private JList liveView;
	
	//Labels
	private JLabel followingsLabel;
	private JLabel liveLabel;

	//ArrayList
	private ArrayList<User> listOfFollowings;
	private ArrayList<User> listOFUsers;
	
	//Observers
	private ArrayList<GUI_User> GUI_UserList; //keeps track of all the GUI_User that are open
	private User mainTarget;
	
	GUI_User(User focus, ArrayList<User> listOfUsers, ArrayList<GUI_User> allGUIOpen)
	{
		model = new DefaultListModel();
		model1 = new DefaultListModel();
		this.GUI_UserList = allGUIOpen;
		mainTarget = focus;
		listOfFollowings = focus.getFollowings();
		this.listOFUsers = listOfUsers;
		this.frame = new JFrame();
		setUpGUI();
	}

	public User getUserGUI()
	{
		return mainTarget;
	}
	private void setUpGUI() 
	{
		
		//Sets up main Container
		Container mainContainer = frame.getContentPane();
		mainContainer.setLayout(new GridBagLayout());
		mainContainer.setBackground(Color.white);
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		
		createTop(mainContainer,c);
		
		showFollowingList(mainContainer,c);
		
		showFollowingList2(mainContainer,c);
		
		
		
		createEnd(mainContainer,c);
		
		
		frame.setSize(width,height);
		frame.setTitle( mainTarget.getID() + " User View");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}

	private void createTop(Container main,GridBagConstraints g)
	{
		JFrame tempFrame = new JFrame();
		Container tempContainer = tempFrame.getContentPane();
		tempContainer.setLayout(new GridBagLayout());
		GridBagConstraints t = new GridBagConstraints();
		
		t.weightx = 1.0;
		t.weighty = 1.0;
		t.fill = GridBagConstraints.HORIZONTAL;
		
		g.weightx = 1.0;
		g.weighty = 1.0;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10,10,0,10);
		
		t.gridx = 0;
		t.gridy = 0;
		userId = new JTextField();
		tempContainer.add(userId,t);
		
		t.gridx = 0;
		t.gridy = 1;
		followUser = new JButton("Follow User");
		tempContainer.add(followUser,t);
		jButtonAddFollowUser(followUser);
		
		g.gridx = 1;
		g.gridy = 0;
		main.add(tempContainer,g);
	}
	
	private void showFollowingList(Container main, GridBagConstraints g) 
	{
		JFrame tempFrame = new JFrame();
		Container tempContainer = tempFrame.getContentPane();
		tempContainer.setLayout(new GridBagLayout());
	
		
		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.VERTICAL;
		
		g.weightx = 1.0;
		g.weighty = 1.0;
		g.fill = GridBagConstraints.HORIZONTAL;
		
		t.gridx = 0;
		t.gridy = 0;
		followingsLabel = new JLabel("List View (Current Following)");
		tempContainer.add(followingsLabel,t);
		
		
		//Fill in List --> Good for update
		
		for(int i = 0; i< listOfFollowings.size();i++)
		{
			model.addElement(listOfFollowings.get(i).getID());
		}
		followings = new JList(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(followings);
		followings.setLayoutOrientation(JList.VERTICAL);
		
		t.ipady = 50;
		t.ipadx = 260;
		t.gridx = 0;
		t.gridy = 1;
		tempContainer.add(scrollPane,t);
		//---------------
		g.gridx = 0;
		g.gridy = 0;
		//g.gridwidth = 2;
		main.add(tempContainer,g);
	}
	
	private void showFollowingList2(Container main, GridBagConstraints g)
	{
		JFrame tempFrame = new JFrame();
		Container tempContainer = tempFrame.getContentPane();
		tempContainer.setLayout(new GridBagLayout());
	
		
		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.VERTICAL;
		
		g.weightx = 1.0;
		g.weighty = 1.0;
		g.fill = GridBagConstraints.HORIZONTAL;
		
		t.gridx = 0;
		t.gridy = 0;
		liveLabel = new JLabel("Live View (News Feed)");
		tempContainer.add(liveLabel,t);
		
		String value = "";
		
		updateJList();
		
		liveView = new JList(model1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(liveView);
		followings.setLayoutOrientation(JList.VERTICAL);
		
		t.ipady = 100;
		t.ipadx = 390;
		t.gridx = 0;
		t.gridy = 1;
		tempContainer.add(scrollPane,t);
		//---------------
		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 2;
		main.add(tempContainer,g);
		
		
	}
	
	private void createEnd(Container main, GridBagConstraints g)
	{
		JFrame tempFrame = new JFrame();
		Container tempContainer = tempFrame.getContentPane();
		tempContainer.setLayout(new GridBagLayout());
		
		GridBagConstraints t = new GridBagConstraints();
		t.fill = GridBagConstraints.VERTICAL;
		t.insets = new Insets(10,10,0,10);
		
		g.weightx = 1.0;
		g.weighty = 1.0;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10,10,0,10);
	
		t.ipady = 50;
		t.ipadx = 50;
		t.gridx = 0;
		t.gridy = 0;
		t.gridwidth = 2;
		tweetMessage = new JTextArea(3,30);
		tweetMessage.setForeground(Color.black);
		tweetMessage.setBackground(Color.LIGHT_GRAY);
		tweetMessage.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tweetMessage);
		followings.setLayoutOrientation(JList.VERTICAL);
		
		tempContainer.add(scrollPane,t);
		
		postTweet = new JButton("Post Tweet");
		t.ipady = 20;
		t.ipadx = 20;
		t.gridx = 0;
		t.gridy = 2;
		t.gridwidth = 2;
		tempContainer.add(postTweet,t);
		jButtonPost(postTweet);
		
		g.ipady = 200;
		g.ipadx = 50;
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 2;
		main.add(tempContainer,g);
			
	}
	
	public void updateJList() 
	{
		
		model1.clear();
		String value = "";
		for(int i = 0; i < mainTarget.getMessages().size();i++)
		{
			value = mainTarget.getID() + ":" + mainTarget.getMessages().get(i);
			model1.addElement(value);
		}
				
		value = "";
		//Get messages from people they are following
		for(int i = 0; i < listOfFollowings.size();i++)
		{
			ArrayList<String> messagesFromUser = listOfFollowings.get(i).getMessages();
			for(int j = 0; j < messagesFromUser.size(); j++)
			{
				value = listOfFollowings.get(i).getID() + ":" + messagesFromUser.get(j);
				model1.addElement(value);
			}
		}
		
	}
	
	/*--------------------------Button Actions--------------------*/
	
	private void jButtonAddFollowUser(JButton but)
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
								updateJList(); //update main target's live feed
								
								mainTarget.attach(GUI_UserList.get(getIndexGUI(listOFUsers.get(getIndex))));
								mainTarget.notifyObserver();
								
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
	
	private int getIndexGUI (User user)
	{
		int temp= -1;
		
		for(int i = 0; i < GUI_UserList.size();i++)
		{
			if(GUI_UserList.get(i) != null)
			{
				if(GUI_UserList.get(i).getUserGUI().getID().equals(user.getID()))
				{
					temp = i;
				}
			}
		}
		
		return temp;
	}
	
	private void jButtonPost(JButton but)
	{
		ActionListener buttonListener = new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						totalMessages++;
						mainTarget.addMessage(tweetMessage.getText());
						
						if(tweetMessage.getText().toLowerCase().indexOf("nice") != -1 || 
								tweetMessage.getText().toLowerCase().indexOf("good") != -1 || 
								tweetMessage.getText().toLowerCase().indexOf("excellent") != -1)
						{
							totalPositiveMessages++;
						}
						
						mainTarget.notifyObserver(); //updates followers live feeds
						
						tweetMessage.setText("");
						updateJList();
						
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
	
	public int getTotalMessages()
	{
		return totalMessages;
	}
	
	public int getTotalPositiveMessages()
	{
		return totalPositiveMessages;
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void update(Subject subject) 
	{
		if(subject instanceof User)
		{
			System.out.println("Posting...updating followers live news board");	
			for(int i = 0; i < GUI_UserList.size(); i++)
			{
				if(GUI_UserList.get(i) != null) //if GUI_User is not closed
				{
					GUI_UserList.get(i).updateJList();
				}
			}
		}
	}//end update
	
	

}
