package com.candy_code.tinytools.xml;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlFormator
{
	public static String format(Document doc) throws Exception
	{
		StringWriter out = null;
		try
		{
			OutputFormat formate = OutputFormat.createPrettyPrint();
			out = new StringWriter();
			XMLWriter writer = new XMLWriter(out, formate);
			writer.write(doc);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			out.close();
		}
		return out.toString();
	}
	public static String format(String str) throws Exception
	{
		StringWriter out = null;
		Document doc = null;
		try
		{
			doc = DocumentHelper.parseText(str);
		}catch(DocumentException e){
			throw new Exception("The input string is invalid xml."+e.getMessage());
		}
		try{
			if(doc == null){
				throw new IOException("Invalid xml String");
			}
			OutputFormat formate = OutputFormat.createPrettyPrint();
			formate.setNewLineAfterDeclaration(false);
			
			out = new StringWriter();
			XMLWriter writer = new XMLWriter(out, formate);
			writer.write(doc);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if(out != null){
				out.close();
			}
		}
		return out.toString();
	}
	public static void main(String[] args) throws Exception
	{
		System.out.println(format("<msc><input/></msc>"));
	}
}
