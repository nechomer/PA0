

public abstract class Cmd {
	
	public static final int IF_CMD = 1;
	public static final int GOTO_CMD = 2;
	public static final int PRINT_CMD = 3;
	public static final int ASSIGN_CMD = 4;
	
	private int type;
	protected int currentLineNumber;
	
	public int getType() {
		return type;
	}

	public int getCurrentLineNumber() {
		return currentLineNumber;
	}

	public Cmd(int type, int currentLineNumber) {
		this.type = type;
		this.currentLineNumber = currentLineNumber;
	}
	
}
