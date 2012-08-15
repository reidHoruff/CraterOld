package craterActions;

import java.util.ArrayList;


import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class FunctionCallAction extends Action {

	protected ArrayList<Action> arguments;
	protected Action functionAction;

	public FunctionCallAction(Action action) {
		super();
		functionAction = action;
		arguments = new ArrayList<Action>();
	}

	public void addArgument(Action action) {
		arguments.add(action);
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {

		DataType data = functionAction.act(parentFunction, parentVariables);

		if (!(data instanceof CraterFunction)) {
			System.out.println("Type is not a function");
			return new CraterNull();
		}

		return ((CraterFunction) data).execute(parentFunction, parentVariables,
				arguments);
	}

	@Override
	public String toString() {
		return "FunctionCallAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {

		describeIndent(builder, depth);
		functionAction.describe(builder, depth);
		builder.append("(");
		boolean first = true;

		for (Action action : arguments) {

			if (!first) {
				builder.append(", ");
			}

			action.describe(builder, 0);

			first = false;
		}

		builder.append(")");
	}

}
