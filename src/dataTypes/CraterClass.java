package dataTypes;

import craterObjects.OperatorType;
import craterObjects.VariableMap;

public abstract class CraterClass extends DataType {

	protected String className;

	public VariableMap staticData;
	public VariableMap dynamicData;

	public CraterClass(String type, String className) {

		super(type);

		this.className = className;
		
		this.staticData = new VariableMap();
		this.dynamicData = new VariableMap();
	}

	public final String getClassName() {
		return this.className;
	}

	public DataType getAttribute(String name){
		return this.staticData.getVariable(name);
	}
	
	public DataType clone(){
		return this;
	}

	public abstract DataType operate(OperatorType operator, DataType operand);
}
