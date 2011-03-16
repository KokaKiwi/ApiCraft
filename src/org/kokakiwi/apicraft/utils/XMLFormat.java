package org.kokakiwi.apicraft.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMLFormat {
	public static String format(Object o)
	{
		return format(o, "response");
	}
	
	public static String format(Object o, String rootName)
	{
		if(o instanceof Map)
		{
			Map<String, Object> datas = (Map<String, Object>) o;
			Iterator<String> i = datas.keySet().iterator();
			String response = "<" + rootName + ">\n\r";
			while(i.hasNext())
			{
				String name = i.next();
				Object data = datas.get(name);
				if(data instanceof String) {
					response += "<" + name + ">" + data + "</" + name + ">\n\r";
				}else{
					response += XMLFormat.format(data, name);
				}
			}
			response += "</" + rootName + ">\n\r";
			return response;
		}else if(o instanceof List) {
			List<Object> datas = (List<Object>) o;
			Iterator<Object> i = datas.iterator();
			String response = "";
			while(i.hasNext())
			{
				Object data = i.next();
				if(data instanceof String)
				{
					response += "<" + rootName + ">" + data + "</" + rootName + ">\n\r";
				}else {
					response += XMLFormat.format(data);
				}
			}
			return response;
		}else {
			return (String) o;
		}
	}
}
