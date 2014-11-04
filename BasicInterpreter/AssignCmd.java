package basicInterpreter;

public class AssignCmd extends Cmd {

	char var;
	
	public char getVar() {
		return var;
	}

	public void setVar(char var) {
		this.var = var;
	}

	public Exp getExp() {
		return exp;
	}

	public void setExp(Exp exp) {
		this.exp = exp;
	}

	Exp exp;
	
	public AssignCmd(int currentLineNumber, Lexer lex, char var, Exp exp) {
		super(ASSIGN_CMD, currentLineNumber);
		this.exp = exp;
		this.var = var;
		parseCMD(this, lex);
	}
	
	private static void parseCMD(AssignCmd assignCMD, Lexer lex) {
		Token token = lex.getNextToken();
		if (token != ":=") {
			
		}
	}

}
