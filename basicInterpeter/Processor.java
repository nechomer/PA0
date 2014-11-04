package basicInterpreter;

import java.util.HashMap;

public class Processor {

	public Processor() {
	}
	
	public void process(HashMap<Integer, Cmd> parsedProgram, HashMap<String, Integer> variables, int startingAt) {
		
		int currentLine = startingAt;
		while(true) {
			//extract next line
			Cmd currentCmd = parsedProgram.get(currentLine);
			
		}
		
	}

}
