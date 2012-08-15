package builtin;

import craterObjects.OperatorType;
import dataTypes.CraterNull;
import dataTypes.DataType;
import dataTypes.NativeClassInstance;

public class CraterBooleanInstance extends NativeClassInstance {

	protected boolean value;
	protected static final String TYPE = "CraterBoolean";

	public CraterBooleanInstance() {
		super(TYPE, CraterBoolean.getInstance());
		this.value = false;
	}

	public CraterBooleanInstance(boolean value) {
		this();
		this.value = value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}

	@Override
	public DataType getNewInstance() {
		return new CraterBooleanInstance();
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		return new CraterNull();
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		this.describeIndent(builder, depth);
		builder.append(this.value);
	}
}
