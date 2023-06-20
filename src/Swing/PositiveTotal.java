package Swing;

import javax.swing.JTextArea;

public class PositiveTotal implements ShowTotal
{

	private GUI_User newWindow;
	
	PositiveTotal(GUI_User temp)
	{
		newWindow = temp;
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