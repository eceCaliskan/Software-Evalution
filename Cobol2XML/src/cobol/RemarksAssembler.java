package cobol;


import parse.*;

import parse.tokens.*;
public class RemarksAssembler extends Assembler{

	public RemarksAssembler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		
		
		
	   if(t.sval() != null) {
				c.setRemarks(t.sval().trim()+ defaultDelimiter()+ a.remainder(defaultDelimiter()) );
				a.setTarget(c); }
			}

		public String defaultDelimiter() {
			String delimiter = " ";
			return delimiter; 
			}

		
		}
		
	


