package craterActions;

import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.DataType;

public class DerefrenceAction extends Action {

	private String variableName;

	public DerefrenceAction(String name) {
		super();
		variableName = name;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {
		return parentVariables.getVariable(variableName);
	}

	@Override
	public String toString() {
		return "DereferenceAction " + variableName;
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append(variableName);
	}
}
