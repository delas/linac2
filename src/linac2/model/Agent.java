package linac2.model;

import java.util.HashSet;
import java.util.Set;

public class Agent {

	public Status status = new Status();
	public Set<InteractionableObject> objects = new HashSet<>();
	public Set<Goal> goals = new HashSet<>();
	
	public InteractionableObject suggestNextInteraction() {
		Set<Goal> goalsNotMet = new HashSet<>();
		for (Goal g : goals) {
			if (!g.isMet(status)) {
				goalsNotMet.add(g);
			}
		}
		if (goalsNotMet.isEmpty()) {
			return null;
		}
		
		for (Goal g : goalsNotMet) {
			for (InteractionableObject i : objects) {
				Status cloned = (Status) status.clone();
				i.update(cloned);
				if (g.isMet(cloned)) {
					return i;
				}
			}
		}
		return null;
	}
	
	public void addGoal(String name, double min, double max, double priority) {
		Goal g = new Goal();
		g.status = name;
		g.range.min = min;
		g.range.max = max;
		g.priority = priority;
		this.goals.add(g);
	}
	
	public void addKnownObject(InteractionableObject obj) {
		objects.add(obj);
	}

	public void interactWith(InteractionableObject obj) {
		obj.update(status);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Agent status:\n");
		sb.append(status);
		return sb.toString();
	}
}
