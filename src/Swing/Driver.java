package Swing;

public class Driver {

	public static void main(String[] args) 
	{
		GUI.getInstance();
		GUI.getInstance(); 	//for testing reasons only
							// GUI follows Singleton pattern, therefore this second code should not create a new instance
							//Will print out a message in console
		
	}
}
