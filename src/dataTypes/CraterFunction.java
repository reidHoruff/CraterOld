package dataTypes;

import java.util.ArrayList;

import craterObjects.Action;
import craterObjects.OperatorType;
import craterObjects.VariableMap;

public class CraterFunction extends DataType {
	
	protected final static String TYPE = "CraterFunction";

	protected VariableMap parentVariableSet;
	protected ArrayList<Action> actionList;
	protected ArrayList<String> argumentNames;
	protected boolean functionReturnFlag;
	protected DataType assertedValue;
	protected String functionName;

	public CraterFunction(String name) {
		
		super(TYPE);

		this.functionName = name;
		this.actionList = new ArrayList<Action>();
		this.argumentNames = new ArrayList<String>();

		this.parentVariableSet = null;

		this.functionReturnFlag = false;
		this.assertedValue = new CraterNull();

	}

	public CraterFunction(String name, VariableMap vars) {
		this(name);

		this.parentVariableSet = vars;

		this.parentVariableSet.setVariable(name, this);
	}

	/* clone */
	private CraterFunction(CraterFunction clone) {
		super(TYPE);

		this.functionName = clone.functionName;
		this.actionList = clone.actionList;
		this.argumentNames = clone.argumentNames;

		this.parentVariableSet = clone.parentVariableSet;

		this.functionReturnFlag = false;
		this.assertedValue = new CraterNull();
	}

	@Override
	public DataType getNewInstance() {
		return new CraterFunction(this);
	}

	@Override
	public DataType clone() {
		System.out
				.println("Shouldn'y have to create new function isntance in CraterFunction.clone()");
		return new CraterFunction(this);
	}

	public void setParentVariableSet(VariableMap vars) {
		this.parentVariableSet = vars;
	}

	public VariableMap getParentVariableSet() {
		return parentVariableSet;
	}

	public void addAction(Action action) {
		actionList.add(action);
	}

	public ArrayList<Action> getActionList() {
		return actionList;
	}

	public void addArgumentName(String name) {
		argumentNames.add(name);
	}

	public ArrayList<String> getArgumentNames() {
		return argumentNames;
	}

	public DataType assertValue(DataType value) {
		assertedValue = value;
		return assertedValue;
	}

	public DataType getAssertedValue() {
		return assertedValue;
	}

	public void setReturnFlag() {
		functionReturnFlag = true;
	}

	public boolean isReturned() {
		return functionReturnFlag;
	}

	public String getArgumentName(int index) {
		if (index > argumentNames.size() - 1) {
			return null;
		}

		return argumentNames.get(index);
	}

	public DataType execute(CraterFunction callingFunction,
			VariableMap parentVars, ArrayList<Action> callArguments) {

		CraterFunction functionInstance = (CraterFunction)this.getNewInstance();
		VariableMap tempVariables = new VariableMap(this.parentVariableSet);

		/* set argument values executed in the scope of the calling function */
		for (int x = 0; x < functionInstance.argumentNames.size(); x++) {
			String argumentName = functionInstance.getArgumentName(x);

			if (argumentName != null) {
				tempVariables.setVariable(argumentName, callArguments.get(x)
						.act(callingFunction, parentVars));
			}
		}

		/* execute all contained actions */
		ArrayList<Action> actionList = functionInstance.getActionList();

		actionsLoop: for (Action action : actionList) {
			action.act(functionInstance, tempVariables);

			if (functionInstance.isReturned()) {
				break actionsLoop;
			}
		}

		return functionInstance.getAssertedValue();
	}

	public String typeString() {
		return "function";
	}

	@Override
	public String toString() {
		return "<CraterFunction " + functionName + ">";
	}

	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append("function ");
		builder.append(functionName);
		builder.append("(");
		String argNames = argumentNames.toString();

		builder.append(argNames.substring(1, argNames.length() - 1));
		builder.append("){\n");

		for (Action action : actionList) {
			action.describe(builder, depth + 1);
			builder.append('\n');
		}

		describeIndent(builder, depth);
		builder.append("}");
	}

	@Override
	public DataType getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataType operate(OperatorType operator, DataType operand) {
		// TODO Auto-generated method stub
		return null;
	}
}
