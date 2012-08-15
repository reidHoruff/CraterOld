package dataTypes;

import craterObjects.CraterObject;
import craterObjects.OperatorType;

public abstract class DataType extends CraterObject {

	protected String type;

	public DataType(String type) {
		this.type = type;
	}

	public final String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return this.type;
	}

	public abstract DataType clone();

	public abstract DataType getNewInstance();

	public abstract DataType getAttribute(String name);

	public abstract DataType operate(OperatorType operator, DataType operand);

}
