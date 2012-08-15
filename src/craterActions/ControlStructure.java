package craterActions;

import java.util.ArrayList;

import craterObjects.Action;

public abstract class ControlStructure extends Action {

	protected boolean breakFlag;
	protected ArrayList<Action> positiveActionList;
	protected ArrayList<Action> negativeActionList;
	protected boolean continueFlag;

	public ControlStructure() {
		super();

		breakFlag = false;
		positiveActionList = new ArrayList<Action>();
		negativeActionList = new ArrayList<Action>();
		continueFlag = false;
	}

	public boolean breakFlagIsSet() {
		return breakFlag;
	}

	public void resetBreakFlag() {
		breakFlag = false;
	}

	public void breakControlStructure(int level) {

		if (level > 0) {
			this.breakFlag = true;
			level--;

			if (parentControlStructure != null) {
				parentControlStructure.breakControlStructure(level);
			}
		}
	}

	public void addAction(Action action) {

		action.setParentControlStructure(this);
		positiveActionList.add(action);
	}

	public void addElseAction(Action action) {

		this.setParentControlStructure(this);
		negativeActionList.add(action);
	}

	@Override
	public String toString() {
		return "ControlStructure";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		builder.append("ControlStructure");
	}

	public void continueControlStructure(int level) {

		if (level > 0) {
			this.continueFlag = true;
			level--;

			if (parentControlStructure != null) {
				parentControlStructure.continueControlStructure(level);
			}
		}
	}

	public boolean continueFlagIsSet() {
		return continueFlag;
	}

	public void resetContinueFlag() {
		continueFlag = false;
	}
}
