package craterActions;

import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.DataType;

public class AttributeAction extends Action {

	protected Action hostAction;
	protected String attributeName;

	public AttributeAction(Action action, String name) {
		super();
		hostAction = action;
		attributeName = name;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {
		DataType host = hostAction.act(parentFunction, parentVariables);
		return host.getAttribute(attributeName);
	}

	@Override
	public String toString() {
		return "AttributeAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		hostAction.describe(builder, depth);
		builder.append("->");
		builder.append(attributeName);
	}

}