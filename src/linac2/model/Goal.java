package linac2.model;

import java.util.Objects;

import linac2.model.Status.Range;

public class Goal {

	public double priority = 0.0;
	public String status = "";
	public Range range = new Range();
	
	public boolean isMet(Status s) {
		return range.isValueInRange(s.getStatusValue(status));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(priority, status, range);
	}
}
