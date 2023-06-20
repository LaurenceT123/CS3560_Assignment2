package Swing;

import javax.swing.DefaultListModel;

/* Follows Observer Pattern
 * - Define a one-to-man dependency between objects so that when one object changes, all its dependents are notified and dated automatically
 * 
 * 		   _		Subject------------------------------> Observer
 * 			|		|									    /\
 * 			|		|										|	
 * 			|		|										|
 * 			----   User <---------------------------- 	GUI_User
 * */

public interface Observer 
{
	public void update(Subject subject);

}
