package com.tax;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class f4853 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws DocumentException, IOException 	{
		InputStream inp = new FileInputStream("C:\\keeptaxes\\tax.xls");
	    //InputStream inp = new FileInputStream("workbook.xlsx");
       
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
	    HSSFSheet sheet = wb.getSheetAt(0);
	   // HSSFRow  row = sheet.getRow(2);
	     
	   
	    for (  Iterator rit = sheet.rowIterator(); rit.hasNext(); )
	    {			
	    	       
	    	 HSSFRow row = (HSSFRow)rit.next();
	    	 PdfReader reader = new PdfReader("C:\\keeptaxes\\f4852\\f4852.pdf");
		     HSSFCell cell1 = row.getCell(3);
		     HSSFCell cell2 = row.getCell(4);
		 	 PdfStamper stamper = new PdfStamper(reader,new FileOutputStream("C:\\keeptaxes\\f4852\\" + cell1.getRichStringCellValue().toString()+"_"+ cell2.getRichStringCellValue().toString()+"4852"+".pdf")); 
		    
	    	                       
	        
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
				            form.setField("f1_04(0)", cell.getRichStringCellValue().toString()); 
				  //          form.setField("f1_01(0)", form.getField("f1_01(0)")+" " + cell.getRichStringCellValue().toString());
				            break ;
				            case 5 :
				            form.setField("f1_02(0)", cell.getRichStringCellValue().toString());
				            case 46 :
				            form.setField("f1_06(0)", cell.getRichStringCellValue().toString());
				            break ;
				            case 50 :
					        form.setField("f1_15(0)", cell.getRichStringCellValue().toString());
					        break ;
				            
				           
				            
				           
				           
				          }			      
				     	   
				     	   break;
				          case  HSSFCell.CELL_TYPE_NUMERIC:
				        	 
				        	  
				            
				        	System.out.println(cell.getNumericCellValue());
				          case HSSFCell.CELL_TYPE_FORMULA:
				          {
				        	  switch (cell.getColumnIndex())
				        	 {
				        	  case 13 :
						            form.setField("f1_08(0)",Double.toString((cell.getNumericCellValue()))) ;
						      break ; 
				        	  case 24 :
				        		    form.setField("f1_13(0)",Double.toString((cell.getNumericCellValue())));
				              break ;
				        	  case 17 :
				        		    form.setField("f1_14(0)",Double.toString((cell.getNumericCellValue())));	    
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
