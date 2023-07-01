package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Swing.ShowTotalVisitor;
import Swing.UserTotal;
import Swing.User;

public class ShowUserTotalCommand implements Command {
	
	private ArrayList <User> listOfUsers;
	private JButton but;
	private JTextArea reportArea;
	private ShowTotalVisitor visit;

	public ShowUserTotalCommand(ArrayList <User> list, JButton button, JTextArea report, ShowTotalVisitor vis)
	{
		listOfUsers = list;
		but = button;
		reportArea = report;
		visit = vis;
	}
	@Override
	public void execute() 
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

}
