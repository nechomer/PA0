

import java.util.HashMap;

public class Processor {

	private int LastLine;
	private static final int interval = 1;
	
	public Processor(int LastLine) {
		this.LastLine = LastLine;
	}
	
	public void process(int startingAt) {
		
		Cmd currentCmd;
		Integer expValue;
		int cmdType;
		Exp exp;
		IfCmd ifCmd;
		GOTOCmd gotoCmd;
		AssignCmd assignCmd;
		PrintCmd printCmd;
		boolean isIfCmd = false;
		
		
		int currentLine = startingAt;
		
		while(currentLine <= LastLine) {
			if (!isIfCmd)  {
				//extract next line
				currentCmd = Main.linesByRealNumbering.get(currentLine);
			}
			isIfCmd = false;
			
			cmdType = currentCmd.getType();
			
			switch(cmdType) {
			case Cmd.IF_CMD :
				ifCmd = (IfCmd) currentCmd;
				boolean ifConditionValue = ifCmd.evalCondition();
				if (false == ifConditionValue) { //condition false go to next line
					currentLine +=interval;
				} else {//condition is true, evaluate Cmd
					isIfCmd = true;
					currentCmd = currentCmd.getCmd();
				}
				break;
			case Cmd.GOTO_CMD :
				gotoCmd = (GotoCmd) currentCmd;
				int imgLineNumber = GotoCmd.getLineNumber();
				//get real line number from imaginary
				int realLineNumber = Main.lineNumberingMap.get(imgLineNumber);
				currentLine = realLineNumber;
				break;
			case Cmd.ASSIGN_CMD :
				assignCmd = (AssignCmd) currentCmd;
				Character assignedVar = assignCmd.getVar();
				exp = assignCmd.getExp();
				expValue = exp.evalExp();
				Main.variables.put(assignedVar, expValue);
				currentLine +=interval;
				break;
			case Cmd.PRINT_CMD :
				printCmd = (PrintCmd) currentCmd;
				exp = printCmd.getExp();
				expValue = exp.evalExp();
				Printer.Print(expValue);
				currentLine +=interval;
				break;
			default :
				break;
			}
		}	
	}
}
