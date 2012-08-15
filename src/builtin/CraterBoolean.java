package builtin;

import craterObjects.OperatorType;
import dataTypes.CraterClass;
import dataTypes.DataType;
import dataTypes.NativeClass;

public class CraterBoolean extends NativeClass {

	protected static CraterBoolean instance = null;

	protected final static String NAME = "CraterBoolean";

	protected CraterBoolean() {
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
		return new CraterBooleanInstance();
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		// TODO Auto-generated method stub
		return null;
	}
}
