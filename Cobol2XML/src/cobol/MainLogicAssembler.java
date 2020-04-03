package cobol;

import parse.*;
import parse.tokens.*;

public class MainLogicAssembler extends Assembler {

	public MainLogicAssembler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void workOn(Assembly a) {
		// TODO Auto-generated method stub
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		
		

	    c.setMainLogic(t.sval().trim()+ defaultDelimiter() + a.remainder(defaultDelimiter() )); //System.out.println("Token string[4]: " +
	    c.getMainLogic() ;
		
			
			
		
		     
		     a.setTarget(c);

	}
	

	public String defaultDelimiter() {
		String delimiter = " ";
		return delimiter; 
		}
}


