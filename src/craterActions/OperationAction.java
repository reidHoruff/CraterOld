package craterActions;

import craterObjects.Action;
import craterObjects.OperatorType;
import craterObjects.VariableMap;
import dataTypes.CraterFunction;
import dataTypes.DataType;

public class OperationAction extends Action {

	protected OperatorType operator;
	protected Action operandA, operandB;

	public OperationAction(OperatorType op, Action opa) {
		super();
		operator = op;
		operandA = opa;
		operandA.setParentControlStructure(this.getParentControlStructure());
	}

	public OperationAction(OperatorType op, Action opa, Action opb) {
		this(op, opa);
		operandB = opb;
		operandB.setParentControlStructure(this.getParentControlStructure());
	}

	@Override
	public DataType act(CraterFunction parentFunction, VariableMap parentVariables) {
		return operandA.act(parentFunction, parentVariables).operate(operator,
				operandB.act(parentFunction, parentVariables));
	}

	@Override
	public String toString() {
		return "OperationAction";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append("(");
		operandA.describe(builder, 0);
		builder.append(" " + operator + " ");
		operandB.describe(builder, 0);
		builder.append(")");
	}

}
