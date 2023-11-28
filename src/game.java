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
        int count = 0;
        colourSelected.clear();
        
        System.out.println("Select any of the colours. A = Red, B = Blue, X = Green, Y = Yellow");

        try {
            while (count < seqLength) {
                swiftBot.enableButton(Button.A, () -> {
                    swiftBot.setUnderlight(Underlight.BACK_LEFT, RED);
                    colourSelected.add(0);
                });
                count++;

                swiftBot.enableButton(Button.B, () -> {
                    swiftBot.setUnderlight(Underlight.BACK_RIGHT, BLUE);
                    colourSelected.add(1);
                });
                count++;

                swiftBot.enableButton(Button.X, () -> {
                    swiftBot.setUnderlight(Underlight.FRONT_LEFT, GREEN);
                    colourSelected.add(2);
                });
                count++;

                swiftBot.enableButton(Button.Y, () -> {
                    swiftBot.setUnderlight(Underlight.FRONT_RIGHT, YELLOW);
                    colourSelected.add(3);
                });
                count++;
            }

            swiftBot.disableAllButtons();
            System.out.println("All buttons are now off.");
        } catch (Exception e) {
            System.out.println("ERROR occurred when setting up buttons.");
            e.printStackTrace();
        }
    }

    public static void generateSequence(int seqLength, int[] red, int[] blue, int[] green, int[] yellow) throws InterruptedException {
        if (seqLength > colourChoice.size()) {
            colourChoice.add(rand.nextInt(4));
        }

        try {
            Underlight[] underlights = new Underlight[] {Underlight.BACK_LEFT, Underlight.BACK_RIGHT, Underlight.FRONT_LEFT, Underlight.FRONT_RIGHT};

            for (int i = 0; i < seqLength; i++) {
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
            }
        } catch (Exception e) {
            System.out.println("ERROR: Unable to set underlight");
            e.printStackTrace();
        }

        System.out.println("SUCCESS: All under lights should be green");
        Thread.sleep(POST_SEQUENCE_DELAY);
        swiftBot.disableUnderlights();
    }
    
    // Constants for delay durations
    private static final int DELAY_DURATION = 100;
    private static final int POST_SEQUENCE_DELAY = 2000;

    // Constants for colors
    private static final int[] RED = new int[] {255, 0, 0};
    private static final int[] BLUE = new int[] {0, 0, 255};
    private static final int[] GREEN = new int[] {0, 255, 0};
    private static final int[] YELLOW = new int[] {255, 255, 0};
    
	
	
    public static void main(String[] args) {
        // Initialization of swiftBot object
        swiftBot = new SwiftBotAPI(); // Replace with actual initialization
        
        // Declare variables
        int score = 0;
        int round = 1;
        boolean gameOver = false;

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

            // Check user input against the displayed sequence
            for (int i = 0; i < sequenceLength; i++) {
                int displayedColor = colourChoice.get(i);
                int userSelectedColor = colourSelected.get(i);

                if (displayedColor != userSelectedColor) {
                    System.out.println("Incorrect! Game Over.");
                    gameOver = true;
                    break;
                } else {
                    System.out.println("Correct!");
                    score++;
                }
            }
            if (!gameOver) {
                int newColor = rand.nextInt(4); // Generate a new random color
                colourChoice.add(newColor); // Add the new color to the sequence
            }
            
            round++;
        }
    }
}



