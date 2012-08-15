package craterActions;

import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.DataType;

public class DebugPrintAction extends Action {

	protected Action toPrint;

	public DebugPrintAction(Action action) {
		super();
		toPrint = action;
	}

	@Override
	public DataType act(CraterFunction vars, VariableMap parentVariables) {
		DataType d = toPrint.act(vars, parentVariables);
		System.out.println("dbg~ " + d.toString());
		return d;
	}

	@Override
	public String toString() {
		return "DebugPrintAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append("CraterDebugPrint(");
		toPrint.describe(builder, 0);
		builder.append(")");
	}

}
