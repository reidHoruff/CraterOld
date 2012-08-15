package craterObjects;

import craterActions.ControlStructure;
import dataTypes.CraterFunction;
import dataTypes.DataType;

public abstract class Action extends CraterObject {

	protected ControlStructure parentControlStructure;
	
	public Action(){
		super();
		parentControlStructure = null;
	}
	
	public Action(ControlStructure parent){
		super();
		this.parentControlStructure = parent;
	}

	public void setParentControlStructure(ControlStructure parent) {
		this.parentControlStructure = parent;
	}

	public ControlStructure getParentControlStructure() {
		return this.parentControlStructure;
	}

	public final DataType act(VariableMap paretnSet) {
		return act(null, paretnSet);
	}

	public abstract DataType act(CraterFunction parentFunction,
			VariableMap parentVariables);
}
