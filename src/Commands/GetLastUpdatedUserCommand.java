package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Swing.User;

public class GetLastUpdatedUserCommand implements Command {

	private JButton but;
	private ArrayList<User> listOfUsers;
	private JTextArea reportArea2;
	
	public GetLastUpdatedUserCommand(JButton button, ArrayList <User> list, JTextArea report)
	{
		but = button;
		listOfUsers = list;
		reportArea2 = report;
		
	}
	@Override
	public void execute() {
		ActionListener buttonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.out.println("Pressed button!");
				if(listOfUsers.size() > 0)
				{
					User lastUser = listOfUsers.get(0);
					for(int i = 0; i < listOfUsers.size(); i++)
					{
						if(listOfUsers.get(i).getLastUpdateTime() > lastUser.getLastUpdateTime())
						{
							lastUser = listOfUsers.get(i);
						}
					}
				
					reportArea2.setText("Last Updated User: \n" + lastUser.getID());
				}
			}
		};
		
		but.addActionListener(buttonListener);
		
	}

}
