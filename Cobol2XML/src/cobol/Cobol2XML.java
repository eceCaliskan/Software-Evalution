/*
 * @(#)Cobol2XML.java	 0.1.0

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

import XMLWriter.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parse.*;
import parse.tokens.*;

public class Cobol2XML {
	
	/**
	 * Recognise some basic constructs in a COBOL source code file.
	 * And then produce a well-formed XML file with the data identified
	 * 
	 * First command line parameter must be path to cobol source file, such as
	 * "C:\\Users\\<your user name>\\git\\cobol2xml\\base.cbl"
	 * 
	 * Or, when you know exactly where the repository is located and have the file in the right place, simply
	 * "base.cbl"
	 * 
	 * The quotation marks are required
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Cobol2XML V0.1.0");

		XMLPayload xmlp = new XMLPayload();
		
		/* The first command line paprameter is used to get the cobol source file namee
		 * In case you are not sure if you are pointing toward the right file, print out the filename
		 * like this...
		 *
		 * System.out.println("arg[0]" + args[0]);
		 */
		
		/*
		 * A rather crude approach is to hard code the filename for the cobol source file, like this
		 * InputStream is = new FileInputStream("C:\\Users\\sgs442\\eclipse-workspace\\CobolParser1\\base.cbl");
		 */
		InputStream is = new FileInputStream("/Users/ececaliskan/git/Software-Evalutionfinal/Cobol2XML/cobol.cbl");
		BufferedReader r = 	new BufferedReader(new InputStreamReader(is));

		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		
		
		// Look through source code file line by line
		while (true) {
			// throws IOException
			String s = r.readLine();
			if (s == null) {
				break;
			}
			            
			
			String line1="";
			String line2="";
			//to deal with the remarks part
			if(s.contains("remarks") )  {
				line1 =r.readLine(); //first line is space
				while(!line1.contains(".") ||  !line1.contains("")) {
				line2 =r.readLine(); //second line actual remark starts
				line1 +=  line2.trim() + " ";       // to all the lines together
			
				}
			s=s+line1;	
			s=s.replaceAll("\"", "");
			}
		
			if(s.contains("main-logic."))  {
				line1 =r.readLine(); //first line is space
				if(!line1.contains(".") || !line1.contains("")) {
				line2 =r.readLine(); //second line actual remark starts
			    line1 += line2  ;       // to all the lines together
				}
			s="main-logic."+line1;	 
			}
			
			
					

			
			if(  s.contains("decimal-to-base")    ) {
			  if(!line1.contains("perform "))
			      line1 =r.readLine(); //first line is space
			      line2 += line1;
			      line1 =r.readLine(); //first line is space
			      line2 += line1;
			      line1 =r.readLine(); //first line is space
			      line2 += line1;
			      s=s+line2;	
			}
			
			if(  s.contains("divide")   ) {
				   line1 =r.readLine(); //first line is space
				   s=s+line1;	
			}
			
	
			if(  s.contains("search")   ) {
			line1=r.readLine();
			while(!line1.contains("end_search")) {
				line2 +=line1;
				line1=r.readLine();
			}
			 s=s+line2;
		
		   }
			
			if(  s.equals("       search all hex_table          at end               continue          when hex_value( hex_idx ) is = entry_char(ind:1)               move dec_value( hex_idx) to rest_divide")){
				
				s= "dd"+s;
					
				
				}
			
			if(s.contains("if")) {
				line1 =r.readLine(); //first line is space
				while(!line1.contains(".") ||  !line1.contains("")) {
				line2 =r.readLine(); //second line actual remark starts
				line1 +=  line2.trim() + " ";       // to all the lines together
			
				}
			s=s+line1;	
			s=s.replaceAll("\"", "");
			}
				
			
			
			
			
			t.setString(s);
			Assembly in = new TokenAssembly(t);
			Assembly out = p.bestMatch(in);
			Cobol c = new Cobol();
			
			
			c = (Cobol) out.getTarget();
			
			if(c != null)
				
				xmlp.addElements(c); 
			
		}
		xmlp.writeFile("/Users/ececaliskan/git/Software-Evalutionfinal/Cobol2XML/ output.xml");
		r.close();
		Cobol c2 =new Cobol();
		String s1= c2.getCommentLine();
		
	
		}
		
	}
	
	



