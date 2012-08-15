package craterActions;

import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class ReturnAction extends Action {

	protected Action returnAction;

	public ReturnAction(Action action) {
		super();

		returnAction = action;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {

		if (parentFunction == null) {
			System.out.println("Error: return has not parent function");
			return new CraterNull();
		}

		parentFunction.assertValue(returnAction.act(parentFunction,
				parentVariables));
		parentFunction.setReturnFlag();
		return parentFunction.getAssertedValue();
	}

	@Override
	public String toString() {
		return "ReturnAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append("return");

		if (returnAction != null) {
			builder.append(" ");
			returnAction.describe(builder, 0);
		}
	}
}
