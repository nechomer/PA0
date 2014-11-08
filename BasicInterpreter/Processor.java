

import java.util.HashMap;

public class Processor {

	private int LastLine;
	private static final int interval = 10;
	
	public Processor(int LastLine) {
		this.LastLine = LastLine;
	}
	
	public void process(HashMap<Integer, Cmd> parsedProgram, HashMap<Character, Integer> variables, int startingAt) {
		
		Cmd currentCmd = null;
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
				currentCmd = parsedProgram.get(currentLine);
				isIfCmd = false;
			}
			
			cmdType = currentCmd.getType();
			
			switch(cmdType) {
			case Cmd.IF_CMD :
				ifCmd = (IfCmd) currentCmd;
				Boolean ifConditionValue = ifCmd.evalCondition();
				if (null == ifConditionValue) {
					break;
				}
				//evaluate Cmd
				isIfCmd = true;
				currentCmd = ((IfCmd)currentCmd).getNextCmd();
				currentLine +=interval;
				continue;
			case Cmd.GOTO_CMD :
				gotoCmd = (GOTOCmd) currentCmd;
				int lineNumber = gotoCmd.getLineNumber();
				currentLine = lineNumber;
				continue;
			case Cmd.ASSIGN_CMD :
				assignCmd = (AssignCmd) currentCmd;
				Character assignedVar = assignCmd.getVar();
				exp = assignCmd.getExp();
				expValue = exp.evalExp();
				if (null == expValue) {//runtime error
					break;
				}
				variables.put(assignedVar, expValue);
				currentLine +=interval;
				continue;
			case Cmd.PRINT_CMD :
				printCmd = (PrintCmd) currentCmd;
				exp = printCmd.getExp();
				expValue = exp.evalExp();
				if (null == expValue) {//runtime error
					break;
				}
				Printer.Print(expValue);
				currentLine +=interval;
				continue;
			default :
				break;
			}
		}	
	}
}
