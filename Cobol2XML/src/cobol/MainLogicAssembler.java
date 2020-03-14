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
		
		
		
	   if(t.sval() != null) {
				c.setMainLogic(t.sval()+ defaultDelimiter() + a.remainder(defaultDelimiter()) );
				a.setTarget(c); }
			}

		public String defaultDelimiter() {
			String delimiter = "  ";
			return delimiter; 
			}

	}


