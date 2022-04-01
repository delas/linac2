package linac2.model;

import java.util.HashMap;
import java.util.Set;

public class Status {

	private HashMap<String, Range> ranges = new HashMap<>();
	private HashMap<String, Double> values = new HashMap<>();
	
	public void addStatus(String name, Double min, Double max, Double current) {
		Range r = new Range();
		r.min = min;
		r.max = max;
		ranges.put(name, r);
		values.put(name, current);
	}
	
	public Set<String> getAllStatuses() {
		return ranges.keySet();
	}
	
	public void setStatusValue(String status, double value) {
		values.put(status, value);
	}
	
	public double getStatusValue(String status) {
		return values.get(status);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() {
		Status s1 = new Status();
		s1.ranges = (HashMap<String, Range>) this.ranges.clone();
		s1.values = (HashMap<String, Double>) this.values.clone();
		return s1;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (String status : getAllStatuses()) {
			sb.append(" - " + status + " : " + values.get(status) + "\n");
		}
		return sb.toString();
	}
	
	public static class Range {
		public double min;
		public double max;
		
		public boolean isValueInRange(double value) {
			return value > min && value < max;
		}
	}
}
