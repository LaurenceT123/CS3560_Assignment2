package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Swing.GroupTotal;
import Swing.ShowTotalVisitor;
import Swing.User_Group;

public class ShowGroupTotalCommand implements Command {
	
	private ArrayList <User_Group> listOfGroups;
	private JButton but;
	private JTextArea reportArea;
	private ShowTotalVisitor visit;

	public ShowGroupTotalCommand(ArrayList <User_Group> list, JButton button, JTextArea report, ShowTotalVisitor vis)
	{
		listOfGroups = list;
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
				GroupTotal groupTotal = new GroupTotal(listOfGroups);
				groupTotal.accept(visit,reportArea);
				
			}
		};
		
		but.addActionListener(buttonListener);
		
	}

}
