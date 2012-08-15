package dataTypes;

import craterObjects.OperatorType;

public class CraterNull extends DataType {

	protected static final String TYPE = "CraterNull";

	public CraterNull() {
		super(TYPE);
	}

	@Override
	public DataType clone() {
		return new CraterNull();
	}

	@Override
	public DataType getAttribute(String name) {
		return this;
	}

	@Override
	public DataType getNewInstance() {
		return new CraterNull();
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		System.out.println("CraterNull has no operations");
		return new CraterNull();
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describe(builder, depth);
		builder.append("null");
	}
}
