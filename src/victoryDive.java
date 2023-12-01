import swiftbot.*;

public class victoryDive {
	public static void Dive(SwiftBotAPI swiftBot) {
		Underlight[] underlights = new Underlight[] { Underlight.FRONT_LEFT, Underlight.BACK_LEFT,
				Underlight.FRONT_RIGHT, Underlight.BACK_RIGHT, Underlight.MIDDLE_RIGHT, Underlight.MIDDLE_LEFT };
		final int[] red = { 255, 0, 0 };
		final int[] blue = { 0, 255, 0 };
		final int[] green = { 0, 0, 255 };
		final int[] yellow = { 255, 0, 255 };
		final int[] purple = { 255, 255, 0};
		final int[] aqua = { 0, 255, 255 };

		swiftBot.setUnderlight(underlights[0], red);
		swiftBot.setUnderlight(underlights[1], blue);
		swiftBot.setUnderlight(underlights[2], green);
		swiftBot.setUnderlight(underlights[3], yellow);
		swiftBot.setUnderlight(underlights[4], purple);
		swiftBot.setUnderlight(underlights[5], aqua);

		swiftBot.move(-100, 0, 300);
		swiftBot.move(-100, -100, 1000);
		swiftBot.move(0, 0, 100);
		swiftBot.move(100, 0, 600);
		swiftBot.move(100, 100, 1000);
	}
}
