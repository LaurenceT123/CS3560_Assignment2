package Swing;

import javax.swing.JTextArea;

public class ShowTotalVisitor implements Visitor {

	
	public ShowTotalVisitor()
	{
		
	}
	
	@Override
	public void visitUserTotal(UserTotal userTotal, JTextArea report) 
	{
		System.out.println("------Visiting User Total");
		report.setText("Total Users: " + userTotal.getTotal());
		
	}

	@Override
	public void visitGroupTotal(GroupTotal groupTotal, JTextArea report) {
		System.out.println("------Visiting Group Total");
		report.setText("Total Groups: " + groupTotal.getTotal() + "\nNOTE: Including root!");
	}

	@Override
	public void visitMessagesTotal(MessagesTotal test, JTextArea report) {
		System.out.println("---Visiting Messages Total");
		report.setText("Total Messages: " + test.getNewWindow().getTotalMessages());
	}

	@Override
	public void visitPositivePercentageTotal(PositiveTotal test, JTextArea report) 
	{
		System.out.println("--Visiting Positive Percentage Total");
		report.setText("Total Positive Messages: " + test.getNewWindow().getTotalPositiveMessages() + "\n" );
		
	}

	@Override
	public void visitShowAll(ShowAll showAll, JTextArea report) {
		
	}

}
