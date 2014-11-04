package basicInterpreter;

public class IfCmd extends Cmd {
	
	private char var1, var2;
	private int boolOP;
	private Cmd nextCmd;
	
	public char getVar1() {
		return var1;
	}

	public void setVar1(char var1) {
		this.var1 = var1;
	}

	public char getVar2() {
		return var2;
	}

	public void setVar2(char var2) {
		this.var2 = var2;
	}

	public int getBoolOP() {
		return boolOP;
	}

	public void setBoolOP(int boolOP) {
		this.boolOP = boolOP;
	}

	public Cmd getNextCmd() {
		return nextCmd;
	}

	public void setNextCmd(Cmd nextCmd) {
		this.nextCmd = nextCmd;
	}
	
	public IfCmd(char var1, char var2, int boolOP, Cmd nextCmd) {
		super(IF_CMD);
		this.var1 = var1;
		this.var2 = var2;
		this.boolOP = boolOP;
		this.nextCmd = nextCmd;
	}

}
