package craterActions;

import craterObjects.Action;
import craterObjects.OperatorType;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.DataType;

@Deprecated
public class VariableModifyAction extends Action {

	protected OperatorType operator;
	protected String variableName;
	protected Action assignAction;

	public VariableModifyAction(OperatorType op, String name, Action action) {
		super();

		operator = op;
		variableName = name;
		assignAction = action;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {

		return parentVariables.setVariable(variableName, parentVariables
				.getVariable(variableName).operate(operator,
						assignAction.act(parentFunction, null)));
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append(variableName);
		builder.append(" " + operator + " ");
		assignAction.describe(builder, 0);
	}

	@Override
	public String toString() {
		return "VeriableModifyAction";
	}
}
