package craterActions;

import java.util.ArrayList;

import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class ActionList extends Action {

	protected ArrayList<Action> actions;

	public ActionList() {
		super();
		actions = new ArrayList<Action>();
	}

	public ActionList(ControlStructure parent) {
		super(parent);
		actions = new ArrayList<Action>();
	}

	public void addAction(Action action) {
		action.setParentControlStructure(this.parentControlStructure);
		actions.add(action);
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {

		DataType ret = new CraterNull();

		loop: for (Action action : actions) {
			ret = action.act(parentFunction, parentVariables);

			if (parentControlStructure.breakFlagIsSet()) {
				parentControlStructure.resetBreakFlag();
				break loop;
			}

			if (parentControlStructure.continueFlagIsSet()) {
				parentControlStructure.resetContinueFlag();
				continue loop;
			}

			if (parentFunction.isReturned()) {
				break loop;
			}

		}

		return ret;
	}
	
	@Override
	public void describe(StringBuilder builder, int depth) {
		for (Action action : actions) {
			action.describe(builder, depth);
			builder.append('\n');
		}
	}

	@Override
	public String toString() {
		return "ActionList";
	}

}
