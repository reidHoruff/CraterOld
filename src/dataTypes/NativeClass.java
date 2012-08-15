package dataTypes;

public abstract class NativeClass extends CraterClass {

	/* singleton */

	protected final static String TYPE = "NativeClass";

	protected NativeClass(String className) {
		super(TYPE, className);
	}

	public abstract DataType getNewInstance();
	
	@Override
	public void describe(StringBuilder builder, int depth) {
		describeIndent(builder, depth);
		builder.append(this.className);
	}
}
