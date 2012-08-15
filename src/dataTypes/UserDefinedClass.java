package dataTypes;

import craterObjects.OperationMap;
import craterObjects.OperatorType;

public class UserDefinedClass extends CraterClass {

	protected final static String TYPE = "UserDefinedClass";

	public OperationMap staticOperations;
	public OperationMap dynamicOperations;

	public UserDefinedClass(String name) {

		super(TYPE, name);

		this.dynamicOperations = new OperationMap();
		this.staticOperations = new OperationMap();
	}

	public UserDefinedClass(String name, UserDefinedClass parent) {

		this(name);

		this.staticData.addSuperSet(parent.staticData);
		this.dynamicData.addSuperSet(parent.dynamicData);

		this.staticOperations.setSuperSet(parent.staticOperations);
		this.dynamicOperations.setSuperSet(parent.dynamicOperations);
	}

	public void addStaticData(String name, DataType data) {
		this.staticData.setImmediateVariable(name, data);
	}

	public void addDynamicData(String name, DataType data) {
		this.dynamicData.setImmediateVariable(name, data);
	}

	public void setStaticOperator(OperatorType operator, CraterFunction function) {
		this.staticOperations.setImmediateFunction(operator, function);
	}

	public void setDynamicOperator(OperatorType operator,
			CraterFunction function) {
		this.dynamicOperations.setImmediateFunction(operator, function);
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		return this.staticOperations.execute(staticData, operator, operand);
	}

	@Override
	public DataType getNewInstance() {
		return new UserDefinedClassInstance(this);
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		builder.append(this.getClassName());
		builder.append("{");
		//
		builder.append("}");
	}

	@Override
	public String toString() {
		return this.type + " " + this.className;
	}
}
