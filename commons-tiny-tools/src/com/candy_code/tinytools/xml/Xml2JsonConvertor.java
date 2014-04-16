package com.candy_code.tinytools.xml;

import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.xml.XMLSerializer;

/**
 * Description: Xml2JsonConvertor is used to convert a xml string to a json string.
 * @version 1.0  Apr 16, 2014 1:59:33 PM create by Candy Code(CandyCodeJ@gmail.com)
 * @depends json-lib ,commons-lang,commons-logging,commons-beanutils,commons-collections,xdom and ezmorph library.
 */
public class Xml2JsonConvertor
{
	/**
	 * Description:  convert a xml string to a json string.
	 * @Version1.0 Apr 16, 2014 2:01:33 PM create by Candy Code (CandyCodeJ@gmail.com)
	 * @param xmlStr
	 * @return
	 */
	public static String convert(String xmlStr){
		if(xmlStr == null || xmlStr.length() == 0){
			throw new JSONException("xml is null");
		}
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xmlStr);
		return json.toString();
	}
}
