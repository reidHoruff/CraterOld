package craterObjects;

import java.util.HashMap;

import dataTypes.CraterFunction;
import dataTypes.DataType;

public class OperationMap {

	protected HashMap<OperatorType, CraterFunction> functionMap;

	protected OperationMap superSet;

	public OperationMap() {
		super();
		this.functionMap = new HashMap<OperatorType, CraterFunction>();
		this.superSet = null;
	}

	public OperationMap(OperationMap parent) {
		this();
		this.superSet = parent;
	}

	public void setSuperSet(OperationMap set) {
		this.superSet = set;
	}

	public boolean hasImmediateFunction(OperatorType operator) {
		return this.functionMap.containsKey(operator);
	}

	public CraterFunction getImmediateFunction(OperatorType operator) {
		return this.functionMap.get(operator);
	}

	public CraterFunction setImmediateFunction(OperatorType operator,
			CraterFunction function) {

		this.functionMap.put(operator, function);
		return function;
	}

	/* linked list search */
	public boolean hasFunction(OperatorType operator) {

		if (this.hasImmediateFunction(operator)) {
			return true;
		}
		else if (this.superSet != null) {
			return this.superSet.hasFunction(operator);
		}
		else {
			return false;
		}
	}

	public CraterFunction setFunction(OperatorType operator,
			CraterFunction function) {

		if (this.hasImmediateFunction(operator)) {
			return this.setImmediateFunction(operator, function);
		}
		else if (this.superSet != null && this.superSet.hasFunction(operator)) {
			return this.superSet.setFunction(operator, function);
		}
		else {
			return this.setImmediateFunction(operator, function);
		}
	}

	public CraterFunction getFunction(OperatorType operator) {

		if (this.hasImmediateFunction(operator)) {
			return this.getImmediateFunction(operator);
		}
		else if (this.superSet != null) {
			return this.superSet.getFunction(operator);
		}
		else {
			return null;
		}
	}

	public DataType execute(VariableMap parentVariableSet, OperatorType operator, DataType operand) {
		CraterFunction function = this.getFunction(operator);
		function.setParentVariableSet(parentVariableSet);
		//...//
		//TODO: finish OperationFunctionSetExecute
		return null;
	}
}
