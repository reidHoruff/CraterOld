package craterObjects;

import java.util.*;


import dataTypes.CraterFunction;
import dataTypes.CraterNull;
import dataTypes.DataType;

public class VariableMap{

	private HashMap<String, DataType> variableMap;

	private ArrayList<VariableMap> superSets;

	public VariableMap() {
		super();

		superSets = new ArrayList<VariableMap>();
		variableMap = new HashMap<String, DataType>();
	}

	public VariableMap(VariableMap set) {
		this();
		superSets.add(set);
	}

	public void addSuperSet(VariableMap varset) {
		superSets.add(varset);
	}

	public boolean hasImmediateVariable(String name) {
		return this.variableMap.containsKey(name);
	}

	public DataType getImmediateVariable(String name) {
		return this.variableMap.get(name);
	}

	public DataType setImmediateVariable(String name, DataType data) {
		if (data == null) {
			this.variableMap.put(name, new CraterNull());
			return new CraterNull();
		}
		else {

			if (data instanceof CraterFunction) {
				((CraterFunction) data).setParentVariableSet(this);
			}

			this.variableMap.put(name, data);
			return data;
		}
	}

	public boolean hasVariable(String name) {
		if (this.hasImmediateVariable(name)) {
			return true;
		}

		for (VariableMap superSet : superSets) {
			if (superSet.hasVariable(name)) {
				return true;
			}
		}

		return false;
	}

	public DataType setVariable(String name, DataType data) {

		if (this.hasImmediateVariable(name)) {
			return this.setImmediateVariable(name, data);
		}

		for (VariableMap superSet : superSets) {
			if (superSet.hasVariable(name)) {
				return superSet.setVariable(name, data);
			}
		}

		return this.setImmediateVariable(name, data);
	}

	public DataType getVariable(String name) {

		if (this.hasImmediateVariable(name)) {
			return this.getImmediateVariable(name);
		}

		for (VariableMap superSet : superSets) {
			if (superSet.hasVariable(name)) {
				return superSet.getVariable(name);
			}
		}

		return new CraterNull();
	}

	@Override
	public VariableMap clone() {
		
		VariableMap branch = new VariableMap();
		
		for (String key : this.variableMap.keySet()) {
			branch.setImmediateVariable(key, this.variableMap.get(key).clone());
		}

		for (VariableMap superSet : this.superSets) {
			branch.addSuperSet(superSet.clone());
		}
		
		return branch;
	}
}
