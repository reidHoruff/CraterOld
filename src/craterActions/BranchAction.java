package craterActions;

import builtin.CraterBooleanInstance;
import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class BranchAction extends ControlStructure {

	protected Action controlAction;

	public BranchAction(Action action) {
		super();
		controlAction = action;
	}

	/*
	 * if statements don't count as a level for breaks, they do for continues,
	 * though
	 */
	@Override
	public void breakControlStructure(int level) {

		if (level > 0) {
			this.breakFlag = true;

			if (parentControlStructure != null) {
				parentControlStructure.breakControlStructure(level);
			}
		}
	}

	@Override
	public DataType act(CraterFunction parentFunction, VariableMap parentVariables) {
		DataType type = controlAction.act(parentFunction, parentVariables);

		/* is is boolean type */
		if (type instanceof CraterBooleanInstance) {
			
			/* if value is true */
			if (((CraterBooleanInstance) type).getValue()) {
				for (Action action : positiveActionList) {
					action.act(parentFunction, parentVariables);
					if (this.breakFlagIsSet() || parentFunction.isReturned()) {
						break;
					}

				}
			}
			/* if value is false */
			else {
				for (Action action : negativeActionList) {
					action.act(parentFunction, parentVariables);
					if (this.breakFlagIsSet() || parentFunction.isReturned()) {
						break;
					}
				}
			}

			return type;
		}
		else {
			System.out.println("BranchAction takes argument boolean");
			return new CraterNull();
		}
	}

	@Override
	public String toString() {
		return "BranchAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {

		builder.append("\n");
		describeIndent(builder, depth);
		builder.append("if");
		controlAction.describe(builder, 0);
		builder.append("{\n");

		for (Action action : positiveActionList) {
			action.describe(builder, depth + 1);
			builder.append('\n');
		}

		describeIndent(builder, depth);
		builder.append("}");

		if (!negativeActionList.isEmpty()) {
			builder.append("else{\n");

			for (Action action : negativeActionList) {
				action.describe(builder, depth + 1);
				builder.append('\n');
			}

			describeIndent(builder, depth);
			builder.append("}");
		}

		builder.append("\n");
	}
}
