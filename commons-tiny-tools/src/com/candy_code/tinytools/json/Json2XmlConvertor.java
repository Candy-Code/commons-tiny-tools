package com.candy_code.tinytools.json;

import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * Description: Xml2JsonConvertor is used to convert a json string to a xml string.
 * @version 1.0  Apr 16, 2014 1:59:33 PM create by Candy Code(CandyCodeJ@gmail.com)
 * @depends json-lib ,commons-lang,commons-logging,commons-beanutils,commons-collections,xdom and ezmorph library.
 */
public class Json2XmlConvertor
{
	/**
	 * Description: convert json string to xml string. 
	 * @Version1.0 Apr 16, 2014 4:40:37 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param jsonStr
	 * @return
	 */
	public static String convert(String jsonStr){
		if(jsonStr == null || jsonStr.length() == 0){
			throw new JSONException("json is null");
		}
		JSON jsonObj = JSONSerializer.toJSON(jsonStr);
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.write(jsonObj);
	}
}
