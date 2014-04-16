package com.candy_code.tinytools.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * Description: DynamicDocumentBuilder can help you build a dynamic xml.</br>
 * You can use the placeholder such as "{$key}" in the xml,</br>
 * and then use the function:setValue(String key,String value) to replace it with the value</br> 
 * which is the second argument of the function.</br>
 * @depends  commons-io ,dom4j library
 * @version 1.0  Apr 16, 2014 2:16:45 PM create by Candy Code(CandyCodeJ@gmail.com)
 */
public class DynamicDocumentBuilder {
	private String unmodified_content;
	private String current_content;
	private String charset = "UTF-8";
	public DynamicDocumentBuilder(){}
	public DynamicDocumentBuilder(Document doc){
		if(doc != null){
			init(doc.asXML());
		}
	}
	public String getCharset(){
		return charset;
	}
	public void setCharset(String charset){
		this.charset = charset;
	}
	public DynamicDocumentBuilder(String content){
		init(content);
	}
	private void init(String content){
		this.unmodified_content = content;
		this.current_content = content;
	}
	/**
	 * Description: This is one of the primary functions of DynamicDocumentBuilder.</br>
	 * It's used to replace the placeholder with the true value.</br>
	 * @Version1.0 Apr 16, 2014 4:20:17 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param key
	 * @param value
	 * @return
	 */
	public DynamicDocumentBuilder setValue(String key,String value){
		current_content = current_content.replaceAll("\\{\\$"+key+"\\}", value);
		return this;
	}
	/**
	 * Description: read from a string 
	 * @Version1.0 Apr 16, 2014 4:24:19 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param content
	 */
	public void readFromString(String content){
		init(content);
	}
	/**
	 * Description: read file from classpath 
	 * @Version1.0 Apr 16, 2014 3:41:49 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param res
	 * @throws FileNotFoundException
	 */
	public void readFromClassPathResource(String res) throws FileNotFoundException{
		InputStream in = DynamicDocumentBuilder.class.getClassLoader().getResourceAsStream(res);
		if(in == null) throw new FileNotFoundException();
		try {
			init(IOUtils.toString(in,charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Description: read file from the file system 
	 * @Version1.0 Apr 16, 2014 3:45:17 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param path
	 * @throws FileNotFoundException
	 */
	public void readFromSystemFile(String path) throws FileNotFoundException{
		File file = new File(path);
		if(!file.exists()) throw new FileNotFoundException();
		if(!file.exists()){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				init(IOUtils.toString(fis));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				IOUtils.closeQuietly(fis);
			}
		}
	}
	public String toString(){
		return current_content.replaceAll("\\{\\$[^\\}]*\\}", "");
	}
	/**
	 * Description: Reset the xml string to original state.</br>
	 * This operation will discard all of the changes.</br>
	 * @Version1.0 Apr 16, 2014 4:10:22 PM create by Candy Code (CandyCodeJ@gmail.com)
	 */
	public void reset(){
		current_content = unmodified_content;
	}
	public Document toDocument(){
		Document doc = null;
		if(current_content != null && current_content.length() > 0){
			try {
				doc = DocumentHelper.parseText(current_content);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}
}
