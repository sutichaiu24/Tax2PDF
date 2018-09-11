package com.sutichai24;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;

public class readpdf {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		PdfReader reader = new PdfReader("C:\\keeptaxes\\f1040ez.pdf");
		AcroFields form = reader.getAcroFields();
		HashMap fields = (HashMap) form.getFields();
		String key;
		for (Iterator i = fields.keySet().iterator(); i.hasNext(); ) {
		key = (String) i.next();
		System.out.print(key + ": ");
		switch(form.getFieldType(key)) {
		case AcroFields.FIELD_TYPE_CHECKBOX:
		System.out.println("Checkbox");
		break;
		case AcroFields.FIELD_TYPE_COMBO:
		System.out.println("Combobox");
		break;
		case AcroFields.FIELD_TYPE_LIST:
		System.out.println("List");
		break;
		case AcroFields.FIELD_TYPE_NONE:
		System.out.println("None");
		break;
		case AcroFields.FIELD_TYPE_PUSHBUTTON:
		System.out.println("Pushbutton");
		break;
		case AcroFields.FIELD_TYPE_RADIOBUTTON:
		System.out.println("Radiobutton");
		break;
		case AcroFields.FIELD_TYPE_SIGNATURE:
		System.out.println("Signature");
		break;
		case AcroFields.FIELD_TYPE_TEXT:
		System.out.println("Text");
		break;
		default:
		System.out.println("?");
		}
		}

	}

}
