package craterActions;

import builtin.*;
import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class BreakAction extends Action {

	protected Action depthAction;

	public BreakAction(Action action) {
		super();
		depthAction = action;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {

		if (parentControlStructure == null) {
			System.out.println("Error: break not in ControlStructure");
			return new CraterNull();
		}

		int levels = 1;

		if (depthAction != null) {
			DataType data = depthAction.act(parentFunction, parentVariables);

			if (data instanceof CraterIntegerInstance) {
				levels = ((CraterIntegerInstance) data).getValue();
			}
			else if (data instanceof CraterBooleanInstance) {
				levels = ((CraterBooleanInstance) data).getValue() ? 1 : 0;
			}
			else {
				System.out
						.println("break must take integer value, boolean value, or no value");
			}
		}

		parentControlStructure.breakControlStructure(levels);
		return new CraterNull();
	}

	@Override
	public String toString() {
		return "BreakAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append("break");
	}
}
