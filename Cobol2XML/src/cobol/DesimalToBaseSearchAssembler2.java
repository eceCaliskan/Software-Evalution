package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class DesimalToBaseSearchAssembler2 extends Assembler {

	public DesimalToBaseSearchAssembler2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void workOn(Assembly a) {
		// TODO Auto-generated method stub

		//System.out.println("commentLineAssembler");
		//System.out.println("Comment Line, remaining text: " + a.remainder(" "));
		Cobol c = new Cobol();
		Token t = (Token) a.pop(); // hopefully the token following the comment
		
		if(t.sval() != null) {
			c.setSearch2(t.sval().trim()+ defaultDelimiter() + a.remainder(defaultDelimiter()));
			a.setTarget(c); }
		}

	public String defaultDelimiter() {
		String delimiter = " ";
		return delimiter; 
		
	}
}