package linac2;

import linac2.model.Agent;
import linac2.model.InteractionableObject;

public class Simulator {

	public static void main(String[] args) {
		
		Agent a = new Agent();
		a.status.addStatus("hunger",    0.0, 1.0, 0.9); // 0 means stuffed ; 1 means starving
		a.status.addStatus("tiredness", 0.0, 1.0, 0.5); // 0 means over-excited ; 1 means falling asleep
		
		a.addGoal("hunger", 0.3, 0.7, 1); // ideal food range
		a.addGoal("tiredness", 0.3, 0.7, 0.5); // ideal tiredness range
		
		a.addKnownObject(new InteractionableObject("apple", "hunger", InteractionableObject.OPERATOR.DECREASE, 0.1));
		a.addKnownObject(new InteractionableObject("big snack", "hunger", InteractionableObject.OPERATOR.DECREASE, 0.2));
		a.addKnownObject(new InteractionableObject("meal", "hunger", InteractionableObject.OPERATOR.DECREASE, 0.5));
		a.addKnownObject(new InteractionableObject("bed", "tiredness", InteractionableObject.OPERATOR.SET, 0.3));
		
		System.out.println("Current status:");
		System.out.println(a);
		
		
		System.out.println("Suggested interaction:");
		InteractionableObject i = a.suggestNextInteraction();
		if (i == null) {
			System.out.println("none");
		} else {
			System.out.println(i.name);
			System.out.println("");
			
			System.out.println("Interacting with " + i.name);
			a.interactWith(i);
			System.out.println("");
			
			System.out.println("New status:");
			System.out.println(a);
		}
	}
}
