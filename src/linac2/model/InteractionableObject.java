package linac2.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InteractionableObject {

	public String name;
	public Map<String, Effect> effectOfInteraction = new HashMap<>();
	
	public InteractionableObject(String name, String effect, OPERATOR operator, double amount) {
		this.name = name;
		Effect e = new Effect();
		e.operator = operator;
		e.amount = amount;
		this.effectOfInteraction.put(effect, e);
	}

	public void update(Status status) {
		for (String affectedStatus : effectOfInteraction.keySet()) {
			Effect e = effectOfInteraction.get(affectedStatus);
			if (e.operator == OPERATOR.DECREASE) {
				status.setStatusValue(affectedStatus, status.getStatusValue(affectedStatus) - e.amount);
			} else if (e.operator == OPERATOR.INCREASE) {
				status.setStatusValue(affectedStatus, status.getStatusValue(affectedStatus) + e.amount);
			} else if (e.operator == OPERATOR.SET) {
				status.setStatusValue(affectedStatus, e.amount);
			}
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, effectOfInteraction);
	}
	
	public static class Effect {
		public OPERATOR operator;
		public double amount;
	}
	
	public enum OPERATOR {
		INCREASE, DECREASE, SET
	}
}
