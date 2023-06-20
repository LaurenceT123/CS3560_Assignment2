package Swing;

import javax.swing.JTextArea;

public class MessagesTotal implements ShowTotal
{

	private GUI_User newWindow;
	
	MessagesTotal(GUI_User temp)
	{
		newWindow = temp;
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
