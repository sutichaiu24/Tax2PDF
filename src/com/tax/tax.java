package com.tax;

import java.io.*; 
import java.util.Iterator;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class tax 
{
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws IOException, DocumentException 
	{   System.out.println(System.getProperty("user.dir"));
		InputStream inp = new FileInputStream("tax.xls");
	    //InputStream inp = new FileInputStream("workbook.xlsx");
       
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
	    HSSFSheet sheet = wb.getSheetAt(0);
	   // HSSFRow  row = sheet.getRow(2);
	     
	   
	    for (  Iterator rit = sheet.rowIterator(); rit.hasNext(); )
	    {			
	    	       
	    	 HSSFRow row = (HSSFRow)rit.next();
	    	 PdfReader reader = new PdfReader("f1040NR/f1040ez.pdf");
		     HSSFCell cell1 = row.getCell(3);
		     HSSFCell cell2 = row.getCell(4);
		 	 PdfStamper stamper = new PdfStamper(reader,new FileOutputStream("f1040NR_Result/" + cell1.getRichStringCellValue().toString()+"_"+ cell2.getRichStringCellValue().toString()+".pdf"));
		    
	    	                       
	        
			for (Iterator cit = row.cellIterator(); cit.hasNext(); )
			{
				HSSFCell cell = (HSSFCell)cit.next();
				AcroFields form = stamper.getAcroFields();
				
				// Do something here
				 switch(cell.getCellType())
				  {      case  HSSFCell.CELL_TYPE_STRING:
				         System.out.println(cell.getRichStringCellValue().getString());
				        
				         
				         switch (cell.getColumnIndex())
				          { 
				                  
				            case 3 :
				            form.setField("f1_01(0)", cell.getRichStringCellValue().toString());  
				            break ;
				            case 4 :
				            form.setField("f1_02(0)", cell.getRichStringCellValue().toString());
				            break; 
				            case 5 :
				            form.setField("f1_03(0)", cell.getRichStringCellValue().toString());
				            break ;
				            case 32:
					        form.setField("f2_07(0)",(cell.getRichStringCellValue().toString()));
					        break;
				            case 34 :
				            form.setField("f2_09(0)",(cell.getRichStringCellValue().toString()));
				            break;
				           
				          }			      
				     	   
				     	   break;
				          case  HSSFCell.CELL_TYPE_NUMERIC:
				        	  
				        	  switch (cell.getColumnIndex())
				        	  {
				                case 35 :
					            form.setField("f2_15(0)",(Integer.toString((int)cell.getNumericCellValue())));
					            break;
					            case 36 :
						        form.setField("f2_16(0)",(Integer.toString((int)cell.getNumericCellValue())));
						        break;
					            case 37 :
						        form.setField("f2_17(0)",(Integer.toString((int)cell.getNumericCellValue())));
						        break;
				        	  }
				            
				        	System.out.println(cell.getNumericCellValue());
				          case HSSFCell.CELL_TYPE_FORMULA:
				          {
				        	  switch (cell.getColumnIndex())
				        	 {
				        	  case 13:				        		  
				        	  form.setField("f1_10(0)", Integer.toString((int)(cell.getNumericCellValue()))) ;
					          break ;  
				        	  case 15:
				        	  form.setField("f1_18(0)", Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 16:
				        	  form.setField("f1_24(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 17:
				        	  form.setField("f1_26(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 18 :
				        	  form.setField("f1_28(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 20:
				        	  form.setField("f1_32(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 21:
				              form.setField("f1_34(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 22:
				              form.setField("f1_38(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 24:
				        	  form.setField("f1_40(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 25:
				              form.setField("f1_46(0)",Integer.toString((int)(cell.getNumericCellValue())));
				        	  case 26:
				              form.setField("f1_48(0)",Integer.toString((int)(cell.getNumericCellValue())));
				              case 27:
				              form.setField("f1_50(0)",Integer.toString((int)(cell.getNumericCellValue())));	
				              ;	  
				        	 }
					          
				        	System.out.println (Math.round(cell.getNumericCellValue()))  ;
				        	
				       //   Fill PDF field 	
				        	
				            break;  
				          }

				          default:
				            System.out.println();

				     }
				 
		     
			 }
			 stamper.close(); 
	     }
	    
	 }
	
}	   



		

	


