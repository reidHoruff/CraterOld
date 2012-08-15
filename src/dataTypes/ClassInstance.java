package dataTypes;

import craterObjects.VariableMap;

public abstract class ClassInstance extends DataType {

	protected VariableMap namespace;
	
	public ClassInstance(String type, CraterClass sClass) {

		super(type);

		namespace = new VariableMap();
		this.namespace.addSuperSet(sClass.dynamicData);
		this.namespace.addSuperSet(sClass.staticData);
	}
	
	public DataType clone(){
		return this;
	}
	
	public abstract DataType getNewInstance();

	public DataType getAttribute(String name) {
		return namespace.getVariable(name);
	}
}
