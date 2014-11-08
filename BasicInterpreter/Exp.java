

public abstract class Exp {
	
	public static final int VAR_EXP = 1;
	public static final int NUM_EXP = 2;
	public static final int BINARY_EXP = 3;

	public int getCurrentLineNumber() {
		return currentLineNumber;
	}

	int type;

	int  currentLineNumber;
	
	public Exp(int type, int currentLineNumber) {
		this.type = type;
		this.currentLineNumber =  currentLineNumber;
	}

	public abstract int evalExp();
	
	public abstract boolean valIsValid();
}
	