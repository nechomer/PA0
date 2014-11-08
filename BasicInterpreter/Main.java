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
			e.printStackTrace();
			return;
		}
		
		
		//initialize tokenizer
		Lexer lex = new Lexer(br);
		
		//checks, to be deleted
		//tokenizerCheker(lex);
		
		//parse program
		Parser parser = new Parser(lex);
		boolean isProgramParsed = parser.parseProgram();
		closeBr(br);
		if (isProgramParsed) {
			//execute program
			Processor processor = new Processor(linesByRealNumbering.size());
			processor.process(1);
		}
	}
	
	public static void tokenizerCheker(Lexer lex) {
		
		Token t;
		
		System.out.println("The Tokens Are: ");
		t = lex.nextToken();
		while (t.getType()!=Token.Eof) {
			System.out.println(t.typeString());
			t = lex.nextToken();
		}
	}

	static void closeBr(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e1) {
		}
	}
}
