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
	Element cobolname; 
	Element cobolname2; 
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
		
	
		
		String statement = c.getStatement(); 
		
		if (statement!= null ) {
			
			this.addProgramLogicSection(c.getStatement()); //System.out.println("Got Section");
			
			// Add contents of procedure division
			} else {
			//Element e = this.addMainLogic();
			//System.out.println("Comment Line null");
			}
		
String search = c.getSearch(); 
		
		if (search!= null ) {
			
			this.addSearch(c.getSearch()); //System.out.println("Got Section");
			
			// Add contents of procedure division
			} else {
			//Element e = this.addMainLogic();
			//System.out.println("Comment Line null");
			}

		
String subtraction = c.getSubtraction(); 
		
		if (subtraction!= null ) {
			
			this.addSubtraction(c.getSubtraction()); //System.out.println("Got Section");
			
			// Add contents of procedure division
			} else {
			//Element e = this.addMainLogic();
			//System.out.println("Comment Line null");
			}		
		
		
		String mainLogic = c.getMainLogic(); 
		if (mainLogic != null ) {
			this.addProgramLogicSection2( c.getMainLogic()); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
				
			//Element e = this.addMainLogic();
			//System.out.println("Comment Line null");
			}
		
		
		String divide = c.getDivide(); 
		if (divide != null ) {
			this.addDivide( c.getDivide()); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
				
			//Element e = this.addMainLogic();
			//System.out.println("Comment Line null");
			}

	
		
			
		
		
		
		
		String decimalToBase = c.getDecimalToBase(); 
		if (decimalToBase != null) {
			this.addProgram_decimalToBase(decimalToBase); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
		
		
		String performUntil = c.getPerform(); 
		if (performUntil != null) {
			this.addProgram_PerformUntil(performUntil); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
		
		
		
		String commentLine = c.getCommentLine(); if (commentLine != null) {
			this.addCommentLineElement( commentLine ); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
		
		
		String ifStatement = c.getIfStatement(); 
		if (ifStatement != null) {
			this.addIfStatement( ifStatement ); 
			//System.out.println("Got Section");
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
			String constantName = c.getConstantName(); if (constantName != null) {
			this.addConstantValueElement( constantName, c.getConstantValue(), c.getLineNumber() ); //System.out.println("Got Section");
			// Add contents of procedure division
			} else {
			//System.out.println("Comment Line null");
			}
			
			String remarks = c.getRemarks(); 
			if (remarks != null) {
				this.addProgram_remark(remarks); //System.out.println("Got Section");
				// Add contents of procedure division
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
	cobolname= doc.createElement("main-logic");
 		rootElement.appendChild(cobolname);
		//return cobolname;
 		
 	}
 	

 	
 	
 	
 	
 	
 void addProgramLogicSection(String string) {
 		
 		Element constID = doc.createElement("statement"); 
 		constID.appendChild(doc.createTextNode(string));
 		//rootElement.appendChild(constID);
 		
			cobolname.appendChild(constID);
		
		
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
		//  Division element
		if(stringElement != null) {
			cobolname2= doc.createElement("decimal-to-base");
			cobolname2.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname2);
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
	
	void addProgram_remark(String stringElement) {
		//  Program ID element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("remarks");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}

	
void addDivide(String stringElement) {
		
		if(stringElement != null) {
	 		Element constID2 = doc.createElement("divide"); 
	 		constID2.appendChild(doc.createTextNode(stringElement));
	 		//rootElement.appendChild(constID);
	 		constID.appendChild(constID2);
	}}



void addSearch(String stringElement) {
	
	if(stringElement != null) {
 		Element constID2 = doc.createElement("search"); 
 		constID2.appendChild(doc.createTextNode(stringElement));
 		//rootElement.appendChild(constID);
 		constID.appendChild(constID2);
}}
	
	void addProgram_PerformUntil(String stringElement) {
		
		if(stringElement != null) {
	 		 constID = doc.createElement("perform"); 
	 		Attr attrType2 =doc.createAttribute("until" ); attrType2.setValue( stringElement); constID.setAttributeNode(attrType2); cobolname.appendChild(constID);
	 		//constID.appendChild(doc.createTextNode(stringElement));
	 		//rootElement.appendChild(constID);
	 		cobolname2.appendChild(constID);
	}}
	
void addIfStatement(String stringElement) {
		
		if(stringElement != null) {
	 		 constID = doc.createElement("if-statement"); 
	 		//Attr attrType2 =doc.createAttribute("until" ); attrType2.setValue( stringElement); constID.setAttributeNode(attrType2); cobolname.appendChild(constID);
	 		constID.appendChild(doc.createTextNode(stringElement));
	 		//rootElement.appendChild(constID);
	 		cobolname2.appendChild(constID);
	}}
	
	
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
	

}
