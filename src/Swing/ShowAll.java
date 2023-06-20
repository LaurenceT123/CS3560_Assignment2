package Swing;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class ShowAll implements ShowTotal
{
	private ArrayList <ShowTotal> elements;
	
	public ArrayList<ShowTotal> getElements()
	{
		return elements;
	}

	@Override
	public void accept(Visitor visitor, JTextArea report) 
	{
		visitor.visitShowAll(this, report);
		
	}

}
