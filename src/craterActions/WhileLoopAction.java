package craterActions;

import builtin.CraterBooleanInstance;
import builtin.CraterIntegerInstance;
import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.DataType;

public class WhileLoopAction extends ControlStructure {

	protected Action controlAction;

	public WhileLoopAction(Action action) {
		super();
		controlAction = action;
	}

	private boolean shouldIterate(DataType data) {

		if (data instanceof CraterBooleanInstance) {
			return ((CraterBooleanInstance) data).getValue();
		}

		System.out.println("<while> argument must bo boolean");
		return false;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {

		int iterations = 0;

		loop: while (shouldIterate(controlAction.act(parentFunction,
				parentVariables))) {
			for (Action action : positiveActionList) {
				if (this.breakFlagIsSet()) {
					this.resetBreakFlag();
					break loop;
				}
				if (this.continueFlagIsSet()) {
					this.resetContinueFlag();
					continue loop;
				}
				if (parentFunction.isReturned()) {
					break loop;
				}
				action.act(parentFunction, parentVariables);
			}

			iterations++;
		}

		return new CraterIntegerInstance(iterations);
	}

	@Override
	public void describe(StringBuilder builder, int depth) {

		describeIndent(builder, depth);
		builder.append("while(");
		controlAction.describe(builder, 0);
		builder.append("){\n");

		for (Action action : positiveActionList) {
			action.describe(builder, depth + 1);
			builder.append('\n');
		}

		describeIndent(builder, depth);
		builder.append("}");
	}
}
