

public class VarExp extends Exp {
	
	char name;
	
	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public VarExp(int currentLineNumber, Lexer lex) {
		super(VAR_EXP, currentLineNumber);
		parseEXP(this, lex);
	}
	
	private static void parseEXP(VarExp varExp, Lexer lex) {
		lex.lastToken();
		Token token = lex.nextToken();
		if (token.getType() != Token.Var) {
			Parser.setErrCode(varExp.getCurrentLineNumber(), 1);
			return;
		}
		
		varExp.setName(token.getchar());
	}
	
	public int evalExp() {
		if (!valIsValid()) {
			Printer.PrintError(getCurrentLineNumber(), 4);
			System.exit(0);
		}
		return Main.variables.get(name);
	}

	@Override
	public boolean valIsValid() {
		return Main.variables.containsKey(name);
	}
}
