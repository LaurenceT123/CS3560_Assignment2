package Swing;

import javax.swing.JTextArea;

/*Follows Visitor Pattern
 * - represent an operation of elements of an object structure
 * - lets you define a new operation without changing classes of the elements on which it operates
 * 
 * 
 * 	 Visitor
 *	  	|											
 * 	ShowTotalVisitor							ShowTotal <--------------------------------------------
 * 													|												  |		
 * 										____________________________________________________ ShowAll--|
 * 					  				   |			|			|		   	|
 * 									UserTotal		|			|		PositivePercentageTotal
 * 												GroupTotal		|
 * 																|
 * 															MessagesTotal
 * 
 * 
 * */


public interface Visitor 
{
	public void visitUserTotal(UserTotal userTotal, JTextArea reportArea);
	public void visitGroupTotal(GroupTotal groupTotal, JTextArea reportArea);
	public void visitMessagesTotal(MessagesTotal test, JTextArea reportArea); 
	public void visitPositivePercentageTotal(PositiveTotal test, JTextArea reportArea);
	public void visitShowAll(ShowAll showAll, JTextArea reportArea);

}
