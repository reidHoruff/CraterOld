package craterActions;

import builtin.CraterBooleanInstance;
import builtin.CraterIntegerInstance;
import craterObjects.Action;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class LiteralAction extends Action {

	protected DataType literal;

	public LiteralAction() {
		super();
		literal = new CraterNull();
	}

	public LiteralAction(DataType data) {
		super();

		if (data == null) {
			data = new CraterNull();
		}
		else {
			literal = data;
		}

	}

	public LiteralAction(int i) {
		super();
		literal = new CraterIntegerInstance(i);
	}

	public LiteralAction(boolean b) {
		super();
		literal = new CraterBooleanInstance(b);
	}

	public void setLiteral(DataType data) {
		literal = data;
	}

	@Override
	public DataType act(CraterFunction parentFunction,
			VariableMap parentVariables) {
		return literal;
	}

	@Override
	public String toString() {
		return "LiteralAction " + literal;
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		literal.describe(builder, depth);
	}
}
