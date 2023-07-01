package Swing;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class MessagesTotal implements ShowTotal
{

	private GUI_User newWindow;
	
	public MessagesTotal(ArrayList<GUI_User> newWindow2)
	{
		if(newWindow2.size() != 0)
		{
			newWindow = newWindow2.get(0);
		}
		else
		{
			System.out.println("No user has made a message!");
		}
	}
	@Override
	public void accept(Visitor visitor, JTextArea report) 
	{
		visitor.visitMessagesTotal(this, report);
		
	}
	public GUI_User getNewWindow() 
	{
		return newWindow;
	}
}
