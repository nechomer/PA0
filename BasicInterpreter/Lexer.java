import java.io.BufferedReader;
import java.io.IOException;



public class Lexer {
	
    int currentPos = 0;
    int previousPos = 0;
    int eof = 0;
    BufferedReader br;
    String line;
    char buffer[];
    
	public Lexer(BufferedReader br) {
		
		this.br = br;
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer = line.toCharArray();
		currentPos = 0;
	}
	
    static boolean isSpace(char c) {
        return ((c == ' ') || (c == '\t'));
    }   
    
    static boolean isDigit(char c) {
        return ((c >= '0') && (c <= '9'));
    }
    
    static boolean isLetter(char c) {
        return ((c >= 'a') && (c <= 'z'));
    }
    
    public void lastToken() {
        if (currentPos != previousPos) {
            currentPos = previousPos;
        }
    }

    public boolean nextLine () {
    	
    	try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if( line != null ) {
    		buffer = line.toCharArray();
			currentPos = 0;
			return true;
    	}
    	else {
    		eof = 1;
    		return false;
    	}
    		
    }
	public Token nextToken() {
		
		if (eof == 1)
			return new Token(Token.Eof);
		
        if (currentPos >= buffer.length)
            return new Token(Token.Error);

        previousPos = currentPos;
        
        if (isSpace(buffer[currentPos]))
        	return new Token(Token.Symbol, buffer[currentPos++]);
		
        switch (buffer[currentPos]) {
        
	        case '+' :
	        	
	            currentPos++;
	            return new Token(Token.BinOp, BinOpExp.ADD);
	
	        case '-' :
	            currentPos++;
	            return new Token(Token.BinOp, BinOpExp.SUB);
	
	        case '*' :
	            currentPos++;
	            return new Token(Token.BinOp, BinOpExp.MUL);
	
	        case '/' :
	            currentPos++;
	            return new Token(Token.BinOp, BinOpExp.DIV);
	            
            case '<' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.BoolOp, IfCmd.LE);
                }
                currentPos++;
                return new Token(Token.BoolOp, IfCmd.LT);

            case '>' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.BoolOp, IfCmd.GE);
                } 
                currentPos++;
                return new Token(Token.BoolOp, IfCmd.GT);
            case '!' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.BoolOp, IfCmd.NEQ);
                } 
                currentPos++;
                return new Token(Token.Error);
                
            case '=' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.BoolOp, IfCmd.EQ);
                } 
                currentPos++;
                return new Token(Token.Error);   
                
            case ':' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.Cmd, Cmd.ASSIGN_CMD);
                } 
                return new Token(Token.Symbol, buffer[currentPos++]);
            
            case 'i' :
                if (currentPos+1 <= buffer.length && buffer[currentPos+1] == 'f') {
                    currentPos += 2;
                    return new Token(Token.Cmd, Cmd.IF_CMD);
                }
                
            case 'g' :
                if (currentPos+3 <= buffer.length && buffer[currentPos+1] == 'o' && buffer[currentPos+2] == 't' && buffer[currentPos+3] == 'o') {
                    currentPos += 4;
                    return new Token(Token.Cmd, Cmd.GOTO_CMD);
                } 
               
            case 'p' :
                if (currentPos+4 <= buffer.length && buffer[currentPos+1] == 'r' && buffer[currentPos+2] == 'i' && buffer[currentPos+3] == 'n' && buffer[currentPos+4] == 't') {
                    currentPos += 5;
                    return new Token(Token.Cmd, Cmd.PRINT_CMD);
                } 
                
            case '(' :					
            case ')' :
                return new Token(Token.Symbol, buffer[currentPos++]);
                
            case ';' :
                if (currentPos+1 == buffer.length) {
					
                	if(nextLine())
						return new Token(Token.Eol);
					else
						return new Token(Token.Eof);
                } 
                else 
                	return new Token(Token.Error);
                
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            	
            	int num = 0;
            	while ( currentPos+1 <= buffer.length && isDigit(buffer[currentPos]) ) 
            		num = (num*10) + (buffer[currentPos++] - '0');
            	
            	return new Token(Token.Num,num);
            
           default: break;
        }
        
        if(isLetter(buffer[currentPos])) 
	        return new Token(Token.Var,buffer[currentPos++]);
        
        currentPos++;
        return new Token(Token.Error);
        
	}

}
