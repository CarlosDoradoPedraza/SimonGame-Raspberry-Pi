import javax.swing.JOptionPane;
import swiftbot.*;
import java.util.HashMap;
import java.util.Map;
public class game {
	static SwiftBotAPI swiftBot;
	public static void main(String[] args) {
		// Declare variables
		int score;
		int round;
		int colourNum;
		String colour;
		String colourInput;
		boolean flag;
		int count;
		char inputChar;
		char choice;
		//Set values to variables
		choice = 'Y';
		flag = false;
		score = 0;
		round = 1;
		count = 1;
		colour = "";
		System.out.println("Round: " + round + ", Score: " + score);
		//Define colours
	    int[] red = new int[] {255,0,0};
        int[] blue = new int[] {0,0,255};
        int[] green = new int[] {0,255,0};
        int[] yellow = new int[] {255, 255, 0};
        try {
            // Declaring an array of under lights excluding back left and back right
            Underlight[] underlights = new Underlight[] {
                    Underlight.MIDDLE_LEFT,
                    Underlight.MIDDLE_RIGHT,
                    Underlight.FRONT_LEFT,
                    Underlight.FRONT_RIGHT
            };
            // Assigning colour to each of the lights
            Map<Underlight, int[]> colorMap = new HashMap<>();
            colorMap.put(Underlight.MIDDLE_LEFT, red);
            colorMap.put(Underlight.MIDDLE_RIGHT, blue);
            colorMap.put(Underlight.FRONT_LEFT, green);
            colorMap.put(Underlight.FRONT_RIGHT, yellow);
           
            //Array for the buttons
          try{
          Button buttons[] = {Button.A, Button.B, Button.X, Button.Y}; 
       // Map buttons to underlight colors
          Map<Button, Underlight> buttonUnderlightMap = new HashMap<>();
          buttonUnderlightMap.put(Button.A, Underlight.MIDDLE_LEFT);
          buttonUnderlightMap.put(Button.B, Underlight.MIDDLE_RIGHT);
          buttonUnderlightMap.put(Button.X, Underlight.FRONT_LEFT);
          buttonUnderlightMap.put(Button.Y, Underlight.FRONT_RIGHT);
	}
}
