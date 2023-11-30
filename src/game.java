import swiftbot.*;
import java.util.Random;
import java.util.ArrayList;

public class game {
    static Random rand = new Random();
	static ArrayList<Integer> colourChoice = new ArrayList<>();
	static ArrayList<Integer> colourSelected = new ArrayList<>();
	static SwiftBotAPI swiftBot;
    
	//Constructor
	public game(SwiftBotAPI swiftBot) {
		game.swiftBot = swiftBot;
	}
	
	public static void enterSequence(int seqLength) throws InterruptedException {
        //int count = 0;
        colourSelected.clear();
        
        System.out.println("Select any of the colours. A = Red, B = Blue, X = Green, Y = Yellow");

        try {
        	//System.out.println("Try");
        	long endtime = System.currentTimeMillis() + 5_000;
            //while (count < seqLength) {
            	
                swiftBot.enableButton(Button.A, () -> {
                    swiftBot.setUnderlight(Underlight.BACK_LEFT, RED);
                    colourSelected.add(0);
                    swiftBot.disableUnderlights();
                });
                //count++;

                swiftBot.enableButton(Button.B, () -> {
                    swiftBot.setUnderlight(Underlight.BACK_RIGHT, BLUE);
                    colourSelected.add(1);
                    swiftBot.disableUnderlights();
                });
                //count++;

                swiftBot.enableButton(Button.X, () -> {
                    swiftBot.setUnderlight(Underlight.FRONT_LEFT, GREEN);
                    colourSelected.add(2);
                    swiftBot.disableUnderlights();
                });
                //count++;

                swiftBot.enableButton(Button.Y, () -> {
                    swiftBot.setUnderlight(Underlight.FRONT_RIGHT, YELLOW);
                    colourSelected.add(3);
                    swiftBot.disableUnderlights();
                });
                //count++;
           // }
            while(System.currentTimeMillis() < endtime){
                ; // This while loop does nothing for 10 seconds.
            }
            while (colourSelected.size() < seqLength) {
                Thread.sleep(1000);
            }

            
            swiftBot.disableAllButtons();
            System.out.println("All buttons are now off.");
        } catch (Exception e) {
            System.out.println("ERROR occurred when setting up buttons.");
            e.printStackTrace();
        }
    }


    public static void generateSequence(int seqLength, int[] red, int[] blue, int[] green, int[] yellow) throws InterruptedException {
        try {
            while (colourChoice.size() < seqLength) {
                colourChoice.add(rand.nextInt(4));
            }
            
            System.out.println("colourChoice : " + colourChoice);

            Underlight[] underlights = new Underlight[] {Underlight.BACK_LEFT, Underlight.BACK_RIGHT, Underlight.FRONT_LEFT, Underlight.FRONT_RIGHT};

            for (int i = 0; i < colourChoice.size(); i++) {
                int colourPick = colourChoice.get(i);
                switch (colourPick) {
                    case 0:
                        swiftBot.setUnderlight(underlights[0], red);
                        Thread.sleep(DELAY_DURATION);
                        break;
                    case 1:
                        swiftBot.setUnderlight(underlights[1], blue);
                        Thread.sleep(DELAY_DURATION);
                        break;
                    case 2:
                        swiftBot.setUnderlight(underlights[2], green);
                        Thread.sleep(DELAY_DURATION);
                        break;
                    case 3:
                        swiftBot.setUnderlight(underlights[3], yellow);
                        Thread.sleep(DELAY_DURATION);
                        break;
                }
                Thread.sleep(1000);
                swiftBot.disableUnderlights(); // Disable underlights after each color display
                Thread.sleep(DELAY_DURATION); // Add a delay between colors
            }
        } catch (Exception e) {
            System.out.println("ERROR: Unable to set underlight");
            e.printStackTrace();
        }

        System.out.println("SUCCESS: All under lights should be green");
        Thread.sleep(POST_SEQUENCE_DELAY);
    }
    
    // Constants for delay durations
    private static final int DELAY_DURATION = 500;
    private static final int POST_SEQUENCE_DELAY = 2000;

    // Constants for colors
    private static final int[] RED = new int[] {255, 0, 0};
    private static final int[] BLUE = new int[] {0, 255, 0};
    private static final int[] GREEN = new int[] {0, 0, 255};
    private static final int[] YELLOW = new int[] {255, 0, 255};
    
	
	
    public static void main(String[] args) {
    	System.out.println("Program starts");
        /// Initialization of swiftBot object
        swiftBot = new SwiftBotAPI();

        // Declare variables
        int score = 0;
        int round = 1;
        boolean gameOver = false;

        int initialSequenceLength = 10; // Set an initial sequence length
        while (colourChoice.size() < initialSequenceLength) {
            colourChoice.add(rand.nextInt(4)); // Add random colors to start the sequence
        }

        colourChoice.clear();
        while (!gameOver) {
            System.out.println("Round: " + round + ", Score: " + score);

            // Generate sequence
            int sequenceLength = round;
            try {
                generateSequence(sequenceLength, RED, BLUE, GREEN, YELLOW);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Enter sequence - User input validation
            try {
                enterSequence(sequenceLength);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("colourSelected.size() : "+colourSelected.size()+"sequenceLength : "+sequenceLength);
            // Check if colourSelected has enough elements for the current round
            if (colourSelected.size() < sequenceLength) {
                System.out.println("User input array does not have enough elements.");
                // Handle the situation, break the loop, or perform necessary actions
                break;
            }

            // Check user input against the displayed sequence
            boolean sequenceCorrect = true;

            for (int i = 0; i < sequenceLength; i++) {
                int displayedColor = colourChoice.get(i);
                int userSelectedColor = colourSelected.get(i);

                if (displayedColor != userSelectedColor) {
                    System.out.println("Incorrect! Game Over.");
                    gameOver = true;
                    sequenceCorrect = false;
                    swiftBot.disableAllButtons();
                    swiftBot.disableUnderlights();
                    break;
                }
            }

            if (sequenceCorrect) {
                System.out.println("Correct!");
                score++;
                int newColor = rand.nextInt(4); // Generate a new random color
                colourChoice.add(newColor); // Add the new color to the sequence
            }

            round++;
            colourSelected.clear(); 

            // Add a delay or perform other actions before the next round/game logic
        }
    }
}



