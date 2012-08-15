package builtin;

import craterObjects.OperatorType;
import dataTypes.CraterNull;
import dataTypes.DataType;
import dataTypes.NativeClassInstance;

public class CraterIntegerInstance extends NativeClassInstance {

	protected int value;
	protected static final String TYPE = "CraterInteger";

	public CraterIntegerInstance() {
		super(TYPE, CraterInteger.getInstance());
		this.value = 0;
	}

	public CraterIntegerInstance(int value) {
		this();
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public DataType getNewInstance() {
		return new CraterIntegerInstance();
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {

		switch (operator) {
		/* modifier */
		case PLUS_EQUALS:
			return this.plusEquals(operand);

		case ADD:
			return this.add(operand);

		case EQUAL_TO:
			return this.equalTo(operand);
		}

		System.out.println("sdfsdfsdf");
		return new CraterNull();
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append(this.value);
	}

	@Override
	public String toString() {
		return "<" + this.type + " " + this.value + ">";
	}

	public DataType plusEquals(DataType operand) {
		if (operand instanceof CraterIntegerInstance) {
			this.value += ((CraterIntegerInstance) operand).value;
			return this;
		}
		return new CraterNull();
	}

	public DataType add(DataType operand) {
		if (operand instanceof CraterIntegerInstance) {
			return new CraterIntegerInstance(this.value
					+ ((CraterIntegerInstance) operand).value);
		}
		return new CraterNull();
	}

	public DataType equalTo(DataType operand) {

		if (operand instanceof CraterIntegerInstance) {
			return new CraterBooleanInstance(
					this.value == ((CraterIntegerInstance) operand).value);
		}

		return new CraterNull();
	}
}
