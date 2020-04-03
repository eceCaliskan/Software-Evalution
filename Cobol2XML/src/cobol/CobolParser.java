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
		a.add( ProgramID() );
		a.add( DivisionName() );
		a.add(  MainLogicSection());
	    a.add(Remarks());
	    a.add(StatementSection());
	    a.add(StatementSection2());
	    a.add(StatementSection3());
		a.add( SectionName() );
	    a.add( DateWritten() );
	    a.add(DecimalToBase());
	    a.add( DecimalToBaseSearch());
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
	
	//this method is to create the remarks part
	protected Parser Remarks() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("remarks") );
		s.add(new Symbol('.').discard());	
		s.add(new Word().setAssembler(new RemarksAssembler()));
		return s;
		
	}
	
	//this method is to create the main logic part
	protected Parser MainLogicSection() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("main-logic"));
		s.add(new Symbol('.').discard());
			s.add(new Word() );
			//s.add(new Word() );
		s.setAssembler(new MainLogicAssembler());
	return s;
		
	}

	
	protected Parser StatementSection() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("display"));
	   //  s.add(new Word() );
	   //  s.add(new Word() );
		s.setAssembler(new MainLogicSectionAssembler());
	return s;
		
	}
	
	protected Parser StatementSection2() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("accept"));
	    // s.add(new Word() );
	   //  s.add(new Word() );
		s.setAssembler(new MainLogicSectionAssembler());
	return s;
		
	}
	
	protected Parser DecimalToBasePerform() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("perform"));
	     s.add(new CaselessLiteral("until"));
	   //  s.add(new Word() );
		s.setAssembler(new PerformUntilAssembler());
	return s;
		
	}
	
	
	protected Parser StatementSection3() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("perform"));
	    //s.add(new Word() );
	   //  s.add(new Word() );
		s.setAssembler(new MainLogicSectionAssembler());
	return s;
		
	}
	
//	protected Parser DecimalToBaseMove() {
//		Sequence s = new Sequence();
//		s.add(new CaselessLiteral("move"));
		
   // s.add(new Word() );
//	     s.add(new Word() );
//		 s.setAssembler(new PerformUntilAssembler() );
//	     return s;
		
//	}
	
	protected Parser DecimalToBase() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("decimal-to-base") );
		s.add(new Symbol('.').discard());	
		 s.add(new Word() );
		s.setAssembler(new DecimalToBaseAssembler());
		return s;
    }
	
	protected Parser DecimalToBaseDivide() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("divide") );
		 s.add(new Word() );
		s.setAssembler(new DesimalToBaseDivideAssembler());
		return s;
	}
	
	protected Parser DecimalToBaseSearch() {
		 Sequence s = new Sequence();
		 s.add(new CaselessLiteral("search") );
		 s.add(new Word() );
		 s.setAssembler(new DesimalToBaseSearchAssembler());
		return s;
	}
	protected Parser DecimalToBaseSubtract() {
		 Sequence s = new Sequence();
		 s.add(new CaselessLiteral("subtract") );
		 s.setAssembler(new DesimalToBaseSubtractAssembler());
		 return s;
	}
	
	protected Parser DecimalToBaseIfStatement() {
		 Sequence s = new Sequence();
		 s.add(new CaselessLiteral("if") );
		 s.setAssembler(new DesimalToBaseIfAssembler());
		 return s;
	}
	
	
	
}

