package Swing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import Commands.Command;
import Commands.FollowUserCommand;

public class GUI_User implements Observer
{
	private static int totalMessages = 0;
	private static int totalPositiveMessages;
	
	private JFrame frame;
	private int width = 460;
	private int height = 700;
	
	//Text Areas
	private JTextField userId;
	private JTextField creationTime;
	private JTextField lastUpdateTime;
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
	
	public GUI_User(User focus, ArrayList<User> listOfUsers, ArrayList<GUI_User> allGUIOpen)
	{
		model = new DefaultListModel();
		model1 = new DefaultListModel();
		lastUpdateTime = new JTextField();
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
	
		lastUpdateTime.setText("Live Feed Update Time:" + (new Date(mainTarget.getLastUpdateTime())));
		lastUpdateTime.setEditable(false);
		t.ipady = 20;
		t.ipadx = 100;
		t.gridx = 0;
		t.gridy = 0;
		t.gridwidth = 2;
		tempContainer.add(lastUpdateTime,t);
		
		t.ipady = 50;
		t.ipadx = 50;
		t.gridx = 0;
		t.gridy = 1;
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
		
		creationTime = new JTextField();
		creationTime.setText("User Creation Time:" + (new Date(mainTarget.getCreationTime())));
		creationTime.setEditable(false);
		t.ipady = 20;
		t.ipadx = 20;
		t.gridx = 0;
		t.gridy = 3;
		t.gridwidth = 2;
		tempContainer.add(creationTime,t);
		
		g.ipady = 200;
		g.ipadx = 50;
		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 2;
		main.add(tempContainer,g);
			
	}
	
	public void updateJList() 
	{
		lastUpdateTime.setText("Live Feed Update Time: " + (new Date(mainTarget.getLastUpdateTime())));
		
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
	//Follows user, attaches observer, updates listOfFollowings and more -----------------> Check Commands package
	private void jButtonAddFollowUser(JButton but)
	{
		Command command = new FollowUserCommand(but, listOFUsers, listOfFollowings, userId, mainTarget, this, GUI_UserList, model);
		command.execute();
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
						tweetMessage.getText().toLowerCase().indexOf("excellent") != -1 ||
						tweetMessage.getText().toLowerCase().indexOf("happy") != -1)
				{
					totalPositiveMessages = getTotalPositiveMessages() + 1;
				}
				
				mainTarget.setLastUpdateTime(System.currentTimeMillis());
										
				tweetMessage.setText("");
				
				mainTarget.notifyObserver(); //updates followers live feeds
				updateJList(); //updates own live feed

			}
		};
		but.addActionListener(buttonListener);
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
			System.out.println("----------------------------------------------");
			System.out.println("Posting...updating followers live news board");	
			System.out.println("----------------------------------------------");
			
			mainTarget.setLastUpdateTime(((User)subject).getLastUpdateTime()); //update followers lastUpdateTime
			GUI_UserList.get(getIndexGUI(mainTarget)).updateJList(); //update follower's live feed
			
			
		}
	}//end update
	
	

}
