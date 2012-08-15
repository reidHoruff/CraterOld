package craterObjects;

/*
 * This is the base class of everything, bow down.
 */

public abstract class CraterObject {

	public abstract String toString();

	public abstract void describe(StringBuilder builder, int depth);

	protected void describeIndent(StringBuilder builder, int depth) {
		for (int x = 0; x < depth; x++) {
			builder.append('\t');
		}
	}
}
