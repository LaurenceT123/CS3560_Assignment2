package Commands;

/* Follows Command Pattern
 * 
 * 											Command
 * 											   |
 * 			___________________________________|________________________________________________________________________________			
 *		   | 		|		|				   |						|														|
 * 		AddUser		|	AddGroup		OpenUserView					|														|
 * 					|													|											____________|__________
 * 					|								____________________|____________________						|					  |
 * 					|							    |			|		|					|					FollowUser				Post
 * 					|						ShowGroupTotal		|	ShowUserTotal		ShowPositiveMessages
 * 					|											|
 * 					|									ShowTotalMessages	
 * 					|________________________________
 * 							|						|					  
 * New Commands:		VerifyAllID			LastUpdatedUser	
 * */
public interface Command 
{
	public void execute();

}
