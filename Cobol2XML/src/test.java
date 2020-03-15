import static org.junit.Assert.*;


import java.util.Stack;

import org.junit.Test;

import XMLWriter.XMLPayload;
import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.Token;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;
import parse.Assembly;

public class test {

	@Test
	public void testDivisionId() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("data division.");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getDivisionName(), "data");

}

@Test
public void testSectionStorage() {
Tokenizer t = CobolParser.tokenizer();
Parser p = CobolParser.start();
t.setString("working-storage section.");
Assembly in = new TokenAssembly(t); 
Assembly out = p.bestMatch(in);
Cobol c = new Cobol();
c = (Cobol) out.getTarget();
assertEquals(c.getSectionName(), "working-storage");
}



@Test
public void testProgram_id() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("program-id.  base.");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getProgram_ID(), "base");

}

@Test
public void testDayWritten() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("date-written.  07-mar-1995 - mb.");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getDayDateWritten(), 7);

}

@Test
public void testMonthWritten() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("date-written.  07-mar-1995 - mb.");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getMonthDateWritten(), "mar");

}
@Test
public void testYearWritten() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("date-written.  07-mar-1995 - mb.");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getYearDateWritten(), 1995);

}
@Test
public void testCommentLine() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString( 
			"***---  convert from decimal to base system\n" 
			);
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getCommentLine(), "convert from decimal to base system");

}
@Test
public void testCostant() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString(" 88  base_2                          value 2.");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getConstantName(), "base_2");
}

@Test
public void testCobol() {
	Cobol c = new Cobol();
	c.setCommentLine("convert from decimal to base system");
	String commentLine1 = c.getCommentLine();
	assertEquals(commentLine1 , "convert from decimal to base system");
}

@Test
public void testCobolParser2() {
	Cobol c = new Cobol();
	Cobol b = (Cobol) c.clone();
	assertEquals(c,b);
}

@Test
public void testXMLPayload() {
	XMLPayload m = new XMLPayload();
	Cobol c = new Cobol();
	c.setCommentLine("bbbb");
	m.addElements(c);
	assertEquals(c.getCommentLine(), "bbbb");
}


}









