package Swing;

import javax.swing.JTextArea;

/*Visitor Pattern
 * - represent an operation of elements of an object structure
 * - let's you define a new operation without changing classes of the elements on which it operates
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
 * NOTE: Go to ShowTotalVisitor and add the last two buttons!
 * */
public interface ShowTotal 
{
	public void accept(Visitor visitor, JTextArea report);

}
