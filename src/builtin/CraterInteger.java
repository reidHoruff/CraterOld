package builtin;

import craterObjects.OperatorType;
import dataTypes.CraterClass;
import dataTypes.CraterNull;
import dataTypes.DataType;
import dataTypes.NativeClass;

public class CraterInteger extends NativeClass {

	protected static CraterBoolean instance = null;

	protected final static String NAME = "CraterInteger";

	protected CraterInteger() {
		super(NAME);
	}

	/* singleton */
	public static CraterClass getInstance() {

		if (instance == null) {
			instance = new CraterBoolean();
		}

		return instance;
	}

	@Override
	public DataType getNewInstance() {
		return new CraterIntegerInstance();
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		return new CraterNull();
	}
}
