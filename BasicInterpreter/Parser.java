import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class Parser {
	
	//<line # in file, error code>
	public static HashMap<Integer, Integer> lineErrorMap = new HashMap<Integer, Integer>();
	private Lexer lex;
	private static int maxLineIdx = 0;
	private static int currentLineNum = 0;
	
	public Parser(Lexer lex) {
		this.lex = lex;
	}
	
	public void parseLine(Lexer lex) {
		currentLineNum++;
		
		Token token = lex.nextToken();
		if (token.getType() != Token.Num) {
			Parser.setErrCode(currentLineNum, 1);
			return;
		}
		
		Main.lineNumberingMap.put(token.getNum(), currentLineNum);
		
		if (maxLineIdx >= token.getNum()) Parser.setErrCode(currentLineNum, 3);
		
		maxLineIdx = token.getNum();
		
		if (!Parser.checkSpace(currentLineNum, lex)) return;
		
		token = lex.nextToken();
		if (!(token.getType() == Token.Symbol && token.getchar() == ':')) {
			Parser.setErrCode(currentLineNum, 1);
			return;
		}
		
		if (!Parser.checkSpace(currentLineNum, lex))  return;
		
		token = lex.nextToken();
		if (token.getType() != Token.Cmd && token.getType() != Token.Var) {
			Parser.setErrCode(currentLineNum, 1);
			return;
		}
		
		Cmd currentCmd = null;
		
		if (token.getType() == Token.Cmd) {
			switch (token.getNum()) {
				case (Cmd.IF_CMD) : {currentCmd = new IfCmd(currentLineNum, lex); break; }
				case (Cmd.GOTO_CMD) : {currentCmd = new GOTOCmd(currentLineNum, lex); break; }
				case (Cmd.PRINT_CMD) : {currentCmd = new PrintCmd(currentLineNum, lex); break; }
			}
		} else {
			currentCmd = new AssignCmd(currentLineNum, lex, token.getchar());
		}
		
		if (lineErrorMap.containsKey(currentLineNum)) return;
		
		if (!Parser.checkSpace(currentLineNum, lex)) return;

		token = lex.nextToken();
		if (token.getType() != Token.Eol) {
			Parser.setErrCode(currentLineNum, 1);
			return;
		}
		
		Main.linesByRealNumbering.put(currentLineNum, currentCmd);
		
	}
	
	public boolean parseProgram() {
		Token token = lex.nextToken();
		while (token.getType() != Token.Eof) {
			lex.lastToken();
			parseLine(lex);
			lex.nextLine();
			token = lex.nextToken();
		}
		
		Iterator<Entry<Integer, Cmd>> iter = Main.linesByRealNumbering.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, Cmd> entry = iter.next();
			if (entry.getValue().getType() == Cmd.GOTO_CMD) {
				int gotoTarget = Main.lineNumberingMap.get(((GOTOCmd) entry.getValue()).getLineNumber());
				if (!Main.linesByRealNumbering.containsKey(gotoTarget)) {
					Parser.setErrCode(entry.getKey(), 2);
				}
			}
		}
		
		for (int i = 1; i<=currentLineNum; i++) {
			if (lineErrorMap.containsKey(i)) {
				Printer.PrintError(i, lineErrorMap.get(i));
			}
		}
		
		return lineErrorMap.isEmpty();
	}
	
	public static void setErrCode(int currentLineNumber, int errCode) {
		if (lineErrorMap.containsKey(currentLineNumber)) {
			if (lineErrorMap.get(currentLineNumber) > errCode) {
				lineErrorMap.put(currentLineNumber, errCode);
			} 
		} else lineErrorMap.put(currentLineNumber, errCode);
	}
	
	public static boolean checkSpace(int currentLineNmuber, Lexer lex){
		Token token = lex.nextToken();
		if (!(token.getType() == Token.Symbol && token.getchar() == ' ')) { 
			Parser.setErrCode(currentLineNmuber, 1);
			return false;
		}
		return true;
	}
}
