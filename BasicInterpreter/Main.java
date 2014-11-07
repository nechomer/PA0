import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Main {

	public static HashMap<Integer, Integer> lineNumberingMap;
	public static HashMap<Integer, Cmd> linesByRealNumbering;
	public static HashMap<Character, Integer> variables;
	
	public static void main(String[] args) {
		
		lineNumberingMap = new HashMap<Integer, Integer>();
		linesByRealNumbering = new HashMap<Integer, Cmd>();
		variables = new HashMap<Character, Integer>();
		
		String filePath = args[0];//need to check correct argument
		
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader(filePath));
			
		} catch (FileNotFoundException e) {
			//do nothing
			return;
		}
		
		
		//initialize tokenizer
		Lexer lex = new Lexer(br);
		
		//checks, to be deleted
		tokenizerCheker(lex);
		
		//parse program
		boolean isProgramParsed;
		closeBr(br);
		if (isProgramParsed) {
			//execute program
			Processor processor = new Processor(linesByRealNumbering.size());
		}
	}
	
	public static void tokenizerCheker(Lexer lex) {
		
		Token t;
		
		System.out.println("The Tokens Are: ");
		while (lex.hasNext()) {
			t = lex.nextToken();
			System.out.println(t.toString());
		}
	}

	static void closeBr(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e1) {
		}
	}
}
