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


@Test
public void testRemarksSection() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString(
			"    remarks. \n" + 
			"\n" + 
			"     This program convert a value of a generic system base to a numeric value                                \n" + 
			"     and viceversa." 
			);
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getRemarks(), "This program convert a value of a generic system base to a numeric value and viceversa .");
}

@Test
public void testMainLogic() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("main-logic. "+

		    "display window erase");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getMainLogic(), "display window erase");
}

//TESTING THE STATEMENT SECTION

@Test
public void testBaseStatements() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString( "display \"Base:  \" no");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getStatement(), "display \"Base:  \" no");
}


@Test
public void testAcceptStatements() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString( "accept current_base convert");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getStatement(), "accept current_base convert");
}

@Test
public void testPerformStatements() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString( "perform base-to-decimal thru base-to-decimal-ex");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getStatement(), "perform base-to-decimal thru base-to-decimal-ex");
}

//TESTING DECIMAL TO BASE SECTION

@Test
public void testCDecimalToBase() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString( "decimal-to-base.    move entry_number  to w_number    move spaces        to entry_char    move 16    to ind");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getDecimalToBase(), "move entry_number to w_number move spaces to entry_char move 16.0 to ind");
	}


@Test
public void testDecimalToBasePerform() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("perform until w_number < current_base");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getPerform(), "until w_number < current_base");
	}



@Test
public void testDecimalToBaseDivide() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("       divide current_base into w_number giving w_number              remainder rest_divide");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getDivide(), "current_base into w_number giving w_number remainder rest_divide");
	}

@Test
public void testDecimalToBaseSearch() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("search all hex_table          at end               continue          when dec_value( hex_idx ) is = rest_divide               move hex_value( hex_idx) to entry_char(ind:1)");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getSearch(), "all hex_table at end continue when dec_value ( hex_idx ) is = rest_divide move hex_value ( hex_idx ) to entry_char ( ind : 1.0 )");
}

@Test
public void testDecimalToBaseSubstraction() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("subtract 1 from ind");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getSubtraction(), "subtract 1.0 from ind");
	}

@Test
public void testDecimalToBaseIf() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("    if w_number not = 0");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getIfStatement(), "w_number not = 0.0");
	}

@Test
public void testDecimalToBaseSearch2() {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	t.setString("       search all hex_table          at end               continue          when dec_value( hex_idx ) is = w_number               move hex_value( hex_idx) to entry_char(ind:1)");
	Assembly in = new TokenAssembly(t); Assembly out = p.bestMatch(in);
	Cobol c = new Cobol();
	c = (Cobol) out.getTarget();
	assertEquals(c.getSearch(), "all hex_table at end continue when dec_value ( hex_idx ) is = w_number move hex_value ( hex_idx ) to entry_char ( ind : 1.0 )");
}
}


