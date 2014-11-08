
public class Lexer {
	
    int currentPos = 0;
    int previousPos = 0;
    char buffer[];
	public Lexer(String data) {
		
		buffer = data.toCharArray();
		currentPos = 0;
	}
	
    /** Return true if  char is whitespace. */
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
    
	public Token nextToken() {
		
		
        if (currentPos >= buffer.length)
            return new Token(Token.Error);

        previousPos = currentPos;
        
        if (isSpace(buffer[currentPos])) {
        	
        	if(currentPos >0 && isSpace(buffer[currentPos-1]))
        		return new Token(Token.Error);
        	if(currentPos+1 <buffer.length && isSpace(buffer[currentPos+1]))
        		return new Token(Token.Error);
        	
        	currentPos++;
        }
		
        switch (buffer[currentPos]) {
        
	        case '+' :
	        	
	            currentPos++;
	            return new Token(Token.BinOp, "+", BinOpExp.ADD);
	
	        case '-' :
	            currentPos++;
	            return new Token(Token.BinOp, "-", BinOpExp.SUB);
	
	        case '*' :
	            currentPos++;
	            return new Token(Token.BinOp, "*", BinOpExp.MUL);
	
	        case '/' :
	            currentPos++;
	            return new Token(Token.BinOp, "\\", BinOpExp.DIV);
	            
            case '<' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.BoolOp, "<=", IfCmd.LE);
                }
                currentPos++;
                return new Token(Token.BoolOp, "<", IfCmd.LT);

            case '>' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.BoolOp, ">=", IfCmd.GE);
                } 
                currentPos++;
                return new Token(Token.BoolOp, ">", IfCmd.GT);
                
            case ':' :
                if (buffer[currentPos+1] == '=') {
                    currentPos += 2;
                    return new Token(Token.Cmd, ":=", Cmd.ASSIGN_CMD);
                } 
                else
                	return new Token(Token.Error);
            
            case 'i' :
                if (currentPos+2 <= buffer.length && buffer[currentPos+1] == 'f' && buffer[currentPos+2] == '(' ) {
                    currentPos += 2;
                    return new Token(Token.Cmd, "if", Cmd.IF_CMD);
                }
                
            case 'g' :
                if (currentPos+4 <= buffer.length && buffer[currentPos+1] == 'o' && buffer[currentPos+2] == 't' && buffer[currentPos+3] == 'o' && buffer[currentPos+4] == ' ') {
                    currentPos += 4;
                    return new Token(Token.Cmd, "goto", Cmd.GOTO_CMD);
                } 
               
            case 'p' :
                if (currentPos+5 <= buffer.length && buffer[currentPos+1] == 'r' && buffer[currentPos+2] == 'i' && buffer[currentPos+3] == 'n' && buffer[currentPos+4] == 't' && buffer[currentPos+5] == '(') {
                    currentPos += 5;
                    return new Token(Token.Cmd, "print", Cmd.PRINT_CMD);
                } 
                
            case '(' :					
            case ')' :
            case ';' :
                return new Token(Token.Symbol);   
                
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
            	
            	return new Token(Token.Num,num,0);
            
           default: break;
        }
        
        if(isLetter(buffer[currentPos])) {
        	
        	StringBuffer sb = new StringBuffer();
	        
        	while (isLetter(buffer[currentPos])) {
	            sb.append(buffer[currentPos]);
	            currentPos++;
	        }
	        
	        return new Token(Token.Var,sb.toString(),0);
        }


        
        
        return new Token(Token.Error);
	}

}
