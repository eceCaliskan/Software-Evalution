/*
 * @(#)XMLPayload.java	 0.1.0
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
 */package XMLWriter;

import cobol.*;


import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLPayload {
	 
	Document doc;
	Element rootElement;
	Element programLogic; 
	Element decimalToBase; 
	Element constID;
	
	public XMLPayload() {
		try {
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = 
		            dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();
		
		 // root element
        rootElement = doc.createElement("cobol");
        doc.appendChild(rootElement);
        
		
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}
	
	
	
	public void addElements(Cobol c) {
		/*
		 *  add sectionName element
		 */		
		String sectionName = c.getSectionName();
		
		if (sectionName != null ) {
		
			this.addSectionElement( sectionName );
			//System.out.println("Got Section");
			// Add contents of procedure division
		} else {
			//System.out.println("Section Name null");
		}
		
	
		/*
		 * add statement element
		 */
		String statement = c.getStatement(); 
		if (statement!= null ) {
	         this.addProgramLogicSection(c.getStatement()); //returns the statement
			// Add contents of statement
			} else {
			//the section is not found
			}
		

		/*
		 * add search element
		 */
		String search = c.getSearch(); 
		if (search!= null ) {
			this.addSearch(c.getSearch()); //returns search statement;
			// Add contents of search 
			} else {
			//System.out.println("Comment Line null");
			}

		

		/*
		 * add subtraction element
		 */
		String subtraction = c.getSubtraction(); 
		if (subtraction!= null ) {
			this.addSubtraction(c.getSubtraction()); //return subtraction;
			// Add contents of subtraction part
			} else {
			//System.out.println("Comment Line null");
			}		
		
		/*
		 * add main-logic element
		 */
		String mainLogic = c.getMainLogic(); 
		if (mainLogic != null ) {
			this.addProgramLogicSection2(c.getMainLogic()); //return mainLogic section
			// Add contents of main logic
			} else {
			//System.out.println("Comment Line null");
			}
		
		
		/*
		 * add divide element
		 */
		String divide = c.getDivide(); 
		if (divide != null ) {
			this.addDivide( c.getDivide()); //return divide section;
			// Add contents of divide
			} else {
			//System.out.println("Comment Line null");
			}

	
		/*
		 * add decimal-to-base element
		 */
		String decimalToBase = c.getDecimalToBase(); 
		if (decimalToBase != null) {
			this.addProgram_decimalToBase(decimalToBase); //return decimal-to-base;
			// Add contents of decimal-to-base
			} else {
			//System.out.println("Comment Line null");
			}
		
		/*
		 * add perform-until element
		 */
		String performUntil = c.getPerform(); 
		if (performUntil != null) {
			this.addProgram_PerformUntil(performUntil); //return perform-until;
			// Add contents of perform until
			} else {
			//System.out.println("Comment Line null");
			}
		
		
		/*
		 * add commentLine element
		 */
		String commentLine = c.getCommentLine(); if (commentLine != null) {
			this.addCommentLineElement( commentLine ); //return commentLine element;
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
		
		/*
		 * add if element
		 */
		String ifStatement = c.getIfStatement(); 
		if (ifStatement != null) {
			this.addIfStatement( ifStatement ); //return if statement
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
		
		/*
		 *  add divisionName element
		 */		
		String divisionName = c.getDivisionName();
		if (divisionName != null) {
			this.addDivisionElement( divisionName );
			//System.out.println("Got Section");
			// Add contents of procedure division
		} else {
			//System.out.println("Division Name null");
		}
		
		
	 	/*
		 *  add commentLine element
		 */
	    String constantName = c.getConstantName(); 
		if (constantName != null) {
			this.addConstantValueElement( constantName, c.getConstantValue(), c.getLineNumber() ); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
			
		
		/*
		 *  add remarks element
		 */
		String remarks = c.getRemarks(); 
		if (remarks != null) {
				this.addProgram_remark(remarks); //return remarks section;
				// Add contents of remarks
				} else {
				//System.out.println("Comment Line null");
				}
		
		
		/*
		 *  add ProgramID element
		 */		
		String programIDName = c.getProgram_ID();
		if (programIDName != null) {
			this.addProgram_IDElement( programIDName );
			//System.out.println("Got Section");
			// Add contents of procedure division
		} else {
			//System.out.println("Section Name null");
		}
		
		/*
		 *  add DateWritten element
		 */	
		// DayDateWritten
		int dayDateWritten = c.getDayDateWritten();
		if(dayDateWritten != 0) {
			this.addDayDateWrittenElement( dayDateWritten );
		}
		
		//MonthDateWritten
		String monthDateWritten = c.getMonthDateWritten();
		if (monthDateWritten != null) {
			this.addMonthDateWrittenElement( monthDateWritten );
			//System.out.println("Got Section");
			// Add contents of procedure division
		} else {
			//System.out.println("Section Name null");
		}

		// YearDateWritten
		int yearDateWritten = c.getYearDateWritten();
		if(yearDateWritten != 0) {
			this.addYearDateWrittenElement( yearDateWritten );
		}

	}
	
	
	
	
	void addProgram_IDElement(String stringElement) {
		//  Program ID element
		if(stringElement != null) {
			Element cobolname = doc.createElement("Program-ID");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
 

 	void addProgramLogicSection2(String string) {
 		//main-logic element
	    programLogic= doc.createElement("main-logic");
 		rootElement.appendChild(programLogic);
	}
 	

 	void addProgramLogicSection(String string) {
 		//statement element inside main-logic section
 		Element constID = doc.createElement("statement"); 
 		constID.appendChild(doc.createTextNode(string));
 		programLogic.appendChild(constID);
	}
 	
 	
 	void addCommentLineElement(String stringElement) {
		//  Comment Line element
		if(stringElement != null) {
			Element cobolname = doc.createElement("comment");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
 	
 	void addSectionElement(String stringElement) {
		//  Section element
		if(stringElement != null) {
			Element cobolname = doc.createElement("section");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
 	
 	void addDivisionElement(String stringElement) {
		//  Division element
		if(stringElement != null) {
			Element cobolname = doc.createElement("division");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
	
 	
	
 	void addSubtraction(String stringElement) {
		//  Division element
 		Element constID2 = doc.createElement("subtraction"); 
 		constID2.appendChild(doc.createTextNode(stringElement));
 		//rootElement.appendChild(constID);
 		constID.appendChild(constID2);
		}
	
 	
	void addDayDateWrittenElement(int intElement) {
		//  DayDateWritten element
		if(intElement != 0) {
			Element cobolname = doc.createElement("day-date-written");
			String s = "" + intElement;
			cobolname.appendChild(doc.createTextNode(s));
			rootElement.appendChild(cobolname);
		}
	}
	
	
	void addProgram_decimalToBase(String stringElement) {
		//  Decimal-to-base element
		if(stringElement != null) {
			decimalToBase= doc.createElement("decimal-to-base");
			decimalToBase.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(decimalToBase);
		}
	}


	void addMonthDateWrittenElement(String stringElement) {
		//  MonthWritten element
		if(stringElement != null) {
			Element cobolname = doc.createElement("month-date-written");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}

	
	void addYearDateWrittenElement(int intElement) {
		//  YearDateWritten element
		if(intElement != 0) {
			Element cobolname = doc.createElement("year-date-written");
			String s = "" + intElement;
			cobolname.appendChild(doc.createTextNode(s));
			rootElement.appendChild(cobolname);
		}
	}
	
	
	
	void addProgram_remark(String stringElement) {
		//  Program ID element
		if(stringElement != null) {
			Element cobolname = doc.createElement("remarks");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}

	

	void addDivide(String stringElement) {
		//divide element
		if(stringElement != null) {
	 		Element constID2 = doc.createElement("divide"); 
	 		constID2.appendChild(doc.createTextNode(stringElement));
	 		//rootElement.appendChild(constID);
	 		constID.appendChild(constID2);
	
		}
	}




	void addSearch(String stringElement) {
		//search element inside decimal-to-base
	    if(stringElement != null) {
 		    Element constID2 = doc.createElement("search"); 
 		    constID2.appendChild(doc.createTextNode(stringElement));
 		    //rootElement.appendChild(constID);
 		    constID.appendChild(constID2);

	    }
	 }



	void addProgram_PerformUntil(String stringElement) {
		//perform element
		if(stringElement != null) {
	   	    constID = doc.createElement("perform"); 
	 		Attr attrType2 =doc.createAttribute("until" ); attrType2.setValue( stringElement); constID.setAttributeNode(attrType2); programLogic.appendChild(constID);
	 		//constID.appendChild(doc.createTextNode(stringElement));
	 		//rootElement.appendChild(constID);
	 		decimalToBase.appendChild(constID);
		}
	}
	

	void addIfStatement(String stringElement) {
		//if statement element
		if(stringElement != null) {
	 		 constID = doc.createElement("if-statement"); 
	 		//Attr attrType2 =doc.createAttribute("until" ); attrType2.setValue( stringElement); constID.setAttributeNode(attrType2); cobolname.appendChild(constID);
	 		constID.appendChild(doc.createTextNode(stringElement));
	 		//rootElement.appendChild(constID);
	 		decimalToBase.appendChild(constID);
	    }
	}
	
	
	void addConstantValueElement(String constantName, double constantValue, int lineNumber) {
	     //  Program ID element
	if(constantName != null) {
	Element cobolname = doc.createElement("Constant");
	           // insert name of constant into XML file
	Element constID = doc.createElement("Constant"); Attr attrType2 =doc.createAttribute("Name" ); attrType2.setValue( constantName ); constID.setAttributeNode(attrType2); cobolname.appendChild(constID);
	           // insert line number of constant into XML file
	Element lineID = doc.createElement(constantName); Attr attrType = doc.createAttribute("Line_Number" ); attrType.setValue( Integer.toString(lineNumber) ); lineID.setAttributeNode(attrType); cobolname.appendChild(lineID);
	           // insert value of constant into XML file
	Element constantID = doc.createElement(constantName); Attr attrType1 =doc.createAttribute("Value" ); attrType1.setValue( Double.toString(constantValue) ); constantID.setAttributeNode(attrType1); cobolname.appendChild(constantID);
	           rootElement.appendChild(cobolname);
	     }
	

	}
	
	
	public void writeFile(String filename) {
		try {
		// write the content into xml file
		// System.out.println("WriteFile Filename: " + filename);
        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer =
        transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        
        StreamResult result =
                new StreamResult(new File(filename));
        transformer.transform(source, result);
        
        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
	}

}
