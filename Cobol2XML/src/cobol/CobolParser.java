/*
 * @(#)CobolParser.java	 0.1.0

 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
package cobol;

import parse.Alternation;

import parse.Repetition;
import parse.Empty;
import parse.Parser;
import parse.Sequence;
import parse.tokens.CaselessLiteral;
import parse.tokens.Literal;
import parse.tokens.Num;
import parse.tokens.Symbol;
import parse.tokens.Tokenizer;
import parse.tokens.Word;

public class CobolParser {
	/**
	 * Return a parser that will recognize the selected COBOL source code constructs:
	 * 
	 * 
	 * This parser creates a <code>COBOL</code> object
	 * as an assembly's target.
	 *
	 * @return a parser that will recognize and build a 
	 *         <object>COBOL</object> from a source code file.
	 */
	public Parser cobol() {
		Alternation a = new Alternation();
		 a.add( constantValue() );
		 a.add( commentLine() );
		
	
		Symbol fullstop = new Symbol('.');
		fullstop.discard();
		a.add(ProgramID() );
		a.add(DivisionName() );
		a.add(MainLogicSection());
	    a.add(Remarks());
	    a.add(StatementSection());
	    a.add(StatementSection2());
	    a.add(StatementSection3());
	    a.add(StatementSection4());
		a.add(SectionName() );
	    a.add(DateWritten() );
	    a.add(DecimalToBase());
	    a.add(DecimalToBaseSearch());
	    a.add(DecimalToBasePerform());
	    a.add(DecimalToBaseDivide());
	    a.add(DecimalToBaseSubtract());
	    a.add(DecimalToBaseIfStatement() );
		a.add(new Empty());
		return a;
	}
	
	/*              
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word;
	 *
	 */
	protected Parser ProgramID() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("program-id") );
		s.add(new Symbol('.').discard());	
		s.add(new Word().setAssembler(new Program_idAssembler()));
		return s;
	}



	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    <divisionName> division;
	 *
	 */
	protected Parser DivisionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new DivisionAssembler()));
		s.add(new CaselessLiteral("division") );
		s.add(new Symbol('.').discard());
		return s;
	}
	
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word;
	 *
	 */
	protected Parser SectionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new SectionNameAssembler()));
		s.add(new CaselessLiteral("section") );
		s.add(new Symbol('.').discard());	

		return s;
	}
	

	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    working-storage section;
	 *
	 */
	protected Parser DateWritten() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("date-written") );
		s.add(new Symbol('.').discard());
		s.add(new Num());
		s.add(new Symbol('-').discard());

		//This next Word actually contains month and year (which are extracted in DateAssembler
		s.add(new Word());
		s.add(new Symbol('-').discard());
		s.add(new Word().discard());
		s.add(new Symbol('.').discard());
		s.setAssembler(new DateAssembler());
		
		return s;
	}


	/**
	 * Return the primary parser for this class -- cobol().
	 *
	 * @return the primary parser for this class -- cobol()
	 */
	public static Parser start() {
		return new CobolParser().cobol();
	}

	/**
	 * Returns a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol's grammar.
	 *
	 * @return a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol grammar.
	 */
	public static Tokenizer tokenizer() {
		Tokenizer t = new Tokenizer();
		t.wordState().setWordChars(' ', ' ', false);
		return t;
	}

	/*
	 * Return a parser that will recognize the grammar:
	 *
	 *    <line number> <contstant name> "value" <constant value>.
	 *
	 */
	protected Parser constantValue() { 
		//System.out.println("constantValue()"); 
		Sequence s = new Sequence();
	s.add(new Num() );
	s.add(new Word() );
	s.add(new CaselessLiteral("value") ); 
	s.add(new Num() );
	s.setAssembler(new ConstantValueAssembler()); return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    ***---comment line section;
	 *
	 */
	protected Parser commentLine() { //System.out.println("commentLine()");
		Sequence s = new Sequence();
		s.add(new Symbol("*"));
		s.add(new Symbol("*"));
		s.add(new Symbol("*"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Word().setAssembler(new CommentLineAssembler()) ); //s.setAssembler(new CommentLineAssembler());
		return s;
		}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    remarks.
	 *    
	 *           remarks line
	 *
	 */
	protected Parser Remarks() {
		Sequence s = new Sequence(); //create a new sequence
		s.add(new CaselessLiteral("remarks") ); //find the remarks keyword
		s.add(new Symbol('.').discard());	    //remove . 
		s.add(new Word().setAssembler(new RemarksAssembler())); //add as a new word to 
		//the subclass of the Assembler
		return s;
	}
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    main-logic.
	 *    
	 *           
	 *
	 */
	protected Parser MainLogicSection() {
		Sequence s = new Sequence();//create a new sequence
		s.add(new CaselessLiteral("main-logic"));//find the main-logic keyword
		s.add(new Symbol('.').discard()); //remove . 
	    s.setAssembler(new MainLogicAssembler());//add as a new word to 
		//the subclass of the Assembler
	return s;
		
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    display ............
	 *    
	 *           
	 *
	 */
	protected Parser StatementSection() {
		Sequence s = new Sequence();   //create a new sequence
		s.add(new CaselessLiteral("display"));  //find the main-logic keyword
	    s.setAssembler(new MainLogicSectionAssembler());//add as a new word to 
		//the subclass of the Assembler
	return s;
		
	}
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    accept ............
	 *    
	 *           
	 *
	 */
	protected Parser StatementSection2() {
		Sequence s = new Sequence();    //create a new sequence
		s.add(new CaselessLiteral("accept"));   //find the accept keyword
	    s.setAssembler(new MainLogicSectionAssembler());  //add as a new word to 
		//the subclass of the Assembler
	   
	return s;
    }
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    perform base-to-decimal ............
	 *    
	 *           
	 *
	 */
	protected Parser StatementSection3() {
		Sequence s = new Sequence();    //create a new sequence
		s.add(new CaselessLiteral("perform"));  //find the perform keyword
		s.add(new CaselessLiteral("base-to-decimal"));//find the keyword
	    s.setAssembler(new MainLogicSectionAssembler());//add as a new word to 
		//the subclass of the Assembler
	return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    perform decimal-to-base ............
	 *    
	 *           
	 *
	 */
	protected Parser StatementSection4() {
		Sequence s = new Sequence();   //create a new sequence
		s.add(new CaselessLiteral("perform"));//find the perform keyword
		s.add(new CaselessLiteral("decimal-to-base"));//find the keyword
	    s.setAssembler(new MainLogicSectionAssembler());//add as a new word to 
		//the subclass of the Assembler
	return s;
	}
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    decimal-to-base...
	 *    move..... move...... move......
	 *    
	 *           
	 *
	 */
    protected Parser DecimalToBase() {
		Sequence s = new Sequence(); //create a new sequence
		s.add(new CaselessLiteral("decimal-to-base") );//find the decimal-to-base keyword
		s.add(new Symbol('.').discard());	//discarding . 
		s.add(new Word() );                 //adding a new word to the sequence
		s.setAssembler(new DecimalToBaseAssembler());//setting 
		//the subclass of the Assembler
		return s;
    }
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    perform until ............
	 *    
	 *           
	 *
	 */
	protected Parser DecimalToBasePerform() {
		Sequence s = new Sequence();     //create a new sequence
		s.add(new CaselessLiteral("perform"));//find the perform keyword
	    s.add(new CaselessLiteral("until"));//find the until keyword
		s.setAssembler(new PerformUntilAssembler());//setting 
		//the subclass of the Assembler
	return s;
		
	}
	
	
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    divide...............
	 *    
	 *           
	 *
	 */
	protected Parser DecimalToBaseDivide() {
		Sequence s = new Sequence();    //create a new sequence
		s.add(new CaselessLiteral("divide") );//find the divide keyword
		s.add(new Word() ); //adding a new word to the sequence
		s.setAssembler(new DesimalToBaseDivideAssembler());//setting 
		//the subclass of the Assembler
		return s;
	}
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    search...............
	 *    
	 *           
	 *
	 */
	protected Parser DecimalToBaseSearch() {
		 Sequence s = new Sequence();      //create a new sequence
		 s.add(new CaselessLiteral("search") );//find the search keyword
		 s.add(new Word() );//adding a new word to the sequence
		 s.setAssembler(new DesimalToBaseSearchAssembler());//setting 
			//the subclass of the Assembler
		return s;
	}
	
	
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    subtract...............
	 *    
	 *           
	 *
	 */
	protected Parser DecimalToBaseSubtract() {
		 Sequence s = new Sequence();//create a new sequence
		 s.add(new CaselessLiteral("subtract") );//find the subtract keyword
		 s.setAssembler(new DesimalToBaseSubtractAssembler());//setting 
			//the subclass of the Assembler
		 return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    if...............
	 *    
	 *           
	 *
	 */
	protected Parser DecimalToBaseIfStatement() {
		 Sequence s = new Sequence();//create a new sequence
		 s.add(new CaselessLiteral("if") );//find the if keyword
		 s.add(new Word() );//adding a new word to the sequence
		 s.setAssembler(new DesimalToBaseIfAssembler());//setting 
			//the subclass of the Assembler
		 return s;
	}
	
	
	
}

