package com.candy_code.tinytools.xml;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.candy_code.tinytools.shell.UnixExecutor;

/**
 * Description: XmlFormator is used to format the messy xml to be tidy.It will use a fixed format.
 * XmlFormator depends on dom4j library.
 * @version 1.0  Apr 16, 2014 1:42:37 PM create by Candy Code(CandyCodeJ@gmail.com)
 */
public class XmlFormator
{
	/**
	 * Description: format a org.dom4j.Document object to a tidy xml string. 
	 * @Version1.0 Apr 16, 2014 1:51:10 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param doc
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * Description: format a xml string to a tidy xml string. 
	 * @Version1.0 Apr 16, 2014 1:52:22 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param str
	 * @return
	 * @throws Exception
	 */
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
}
