package main;

import builtin.CraterIntegerInstance;
import craterActions.*;
import craterObjects.*;
import dataTypes.*;

public class Main {

	static VariableMap variables;

	static public void main(String args[]) {

		variables = new VariableMap();

		functionTest();
		//logicTest();

		System.out.println(".end");
		
		//foo
	}

	public static void logicTest() {
		variables.setVariable("foo", new CraterIntegerInstance(0));

		Action act = new OperationAction(OperatorType.PLUS_EQUALS,
				new DerefrenceAction("foo"), new LiteralAction(4));
		
		System.out.println(act.act(variables));
	}

	public static void functionTest() {

		variables.setVariable("toast", new CraterIntegerInstance(0));

		CraterFunction function = new CraterFunction("blabla");
		function.addArgumentName("toast");
		function.addAction(new DebugPrintAction(new DerefrenceAction("toast")));
		variables.setVariable("blabla", function);

		FunctionCallAction caller = new FunctionCallAction(
				new DerefrenceAction("blabla"));
		caller.addArgument(new OperationAction(OperatorType.ADD,
				new DerefrenceAction("toast"), new LiteralAction(1)));

		BranchAction branch = new BranchAction(new OperationAction(
				OperatorType.EQUAL_TO, new DerefrenceAction("toast"),
				new LiteralAction(10)));

		function.addAction(branch);

		WhileLoopAction wtrue = new WhileLoopAction(new LiteralAction(true));
		wtrue.addAction(new ReturnAction(new DerefrenceAction("toast")));

		branch.addAction(wtrue);

		function.addAction(new ReturnAction(caller));

		FunctionCallAction caller3 = new FunctionCallAction(new LiteralAction(
				function));
		caller3.addArgument(new LiteralAction(0));
		caller3.act(variables);

		describe(function);
	}

	public static void describe(CraterObject object) {
		StringBuilder builder = new StringBuilder();
		object.describe(builder, 0);
		System.out.println(builder);
	}
}
