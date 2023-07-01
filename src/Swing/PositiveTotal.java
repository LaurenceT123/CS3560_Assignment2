package Swing;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class PositiveTotal implements ShowTotal
{

	private GUI_User newWindow;
	
	public PositiveTotal(ArrayList<GUI_User> newWindow2)
	{
		if(newWindow2.size() != 0)
		{
			newWindow = newWindow2.get(0);
		}
		else
		{
			System.out.println("No user has sent out a message yet!");
		}
	}
	@Override
	public void accept(Visitor visitor, JTextArea report) 
	{
		visitor.visitPositivePercentageTotal(this, report);
		
	}
	public GUI_User getNewWindow() 
	{
		return newWindow;
	}
}