import swiftbot.*;
public class lightIndicators {
	public static void SequenceDecision(SwiftBotAPI swiftBot, int[] colour){
		int[] colour2 = {0, 0, 0};
//		swiftBot.fillUnderlights(colour2);
		if(colour[2] == 255) {
			for(int i = 0; i < 255; i++){
				colour2[2] = i; 
				swiftBot.fillUnderlights(colour2);
	        }
			for(int i = 255; i > 0; i--){
				colour2[2] = i; 
				swiftBot.fillUnderlights(colour2);
	        }
		}
		if(colour[0] == 255) {
			for(int i = 0; i < 255; i++){
				colour2[0] = i; 
				swiftBot.fillUnderlights(colour2);
	        }
			for(int i = 255; i > 0; i--){
				colour2[0] = i; 
				swiftBot.fillUnderlights(colour2);
	        }
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//swiftBot.disableUnderlights();
	}
}
