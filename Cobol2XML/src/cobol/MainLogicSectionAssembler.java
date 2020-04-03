package cobol;



import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;



	public class MainLogicSectionAssembler extends Assembler {
		/**
		 * Pop a string, and set the target SectionName to this
		 * string.
		 *
		 * @param   Assembly   the assembly to work on
		 */
		public void workOn(Assembly a) {
			Cobol c = new Cobol();
			Token t = (Token) a.pop();
			//c.setMainLogic(t.sval().trim()+ defaultDelimiter() + a.remainder(defaultDelimiter() ));
		   
		
			
		    c.setStatement(t.sval().trim()+ defaultDelimiter() + a.remainder(defaultDelimiter() )); //System.out.println("Token string[4]: " +
		    c.getStatement() ;
		   
		   
				
			
			     
			a.setTarget(c);
		}
		public String defaultDelimiter() {
			String delimiter = " ";
			return delimiter; 
			}
	}
		
	

