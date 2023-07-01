package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import Swing.GUI_User;
import Swing.MessagesTotal;
import Swing.ShowTotalVisitor;

public class ShowTotalMessagesCommand implements Command 
{
	
	private ArrayList <GUI_User> newWindow;
	private JButton but;
	private ShowTotalVisitor visit;
	private JTextArea reportArea;
	
	public ShowTotalMessagesCommand(ArrayList <GUI_User> window , JButton button, ShowTotalVisitor vis, JTextArea report)
	{
		newWindow = window;
		but = button;
		visit = vis;
		reportArea = report;
	}

	@Override
	public void execute() {
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

}
