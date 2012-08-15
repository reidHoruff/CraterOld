package dataTypes;

import craterObjects.OperatorType;

public class UserDefinedClassInstance extends ClassInstance {
	
	protected CraterClass spawnClass;

	protected final static String TYPE = "Object";

	public UserDefinedClassInstance(UserDefinedClass myClass) {

		super(TYPE, myClass);
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		return ((UserDefinedClass)spawnClass).dynamicOperations.execute(namespace, operator, operand);
	}

	@Override
	public String toString() {
		return TYPE;
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		// TODO implement UserdefinedClassInstance describe
	}

	@Override
	public DataType getNewInstance() {
		return spawnClass.getNewInstance();
	}
}
