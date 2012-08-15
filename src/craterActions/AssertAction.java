package craterActions;

import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class AssertAction extends Action {

	protected Action assertAction;

	public AssertAction(Action action) {
		super();

		assertAction = action;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {
		if (parentFunction == null) {
			System.out
					.println("Error: assert statement has no parent function");
			return new CraterNull();
		}

		return parentFunction.assertValue(assertAction.act(parentFunction,
				parentVariables));
	}

	@Override
	public String toString() {
		return "AssertAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append("assert");

		if (assertAction != null) {
			builder.append(" ");
			assertAction.describe(builder, 0);
		}
	}

}
