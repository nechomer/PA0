package basicInterpreter;

public class PrintCmd extends Cmd {
	
	private Exp exp;
	
	public PrintCmd(Exp exp) {
		super(PRINT_CMD);
		this.exp = exp;
	}

}
