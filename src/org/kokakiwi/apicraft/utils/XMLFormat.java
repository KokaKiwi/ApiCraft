package org.kokakiwi.apicraft.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMLFormat {
	public static String format(Object o)
	{
		return format(o, "response");
	}
	
	public static String format(Object o, String rootNodeName)
	{
		return format(o, rootNodeName, 0);
	}
	
	public static String format(Object o, String rootNodeName, int indent)
	{
		String response = "";
		response += addTabs(indent) + "<" + rootNodeName + ">\r\n";
		if(o instanceof Map)
		{
			Map<String, Object> datas = (Map<String, Object>) o;
			Iterator<String> i = datas.keySet().iterator();
			int counter = 0;
			while(i.hasNext())
			{
				counter++;
				String name = i.next();
				Object data = datas.get(name);
				if(data instanceof String)
				{
					response += addTabs(indent + 1) + "<" + name + ">" + (String) data + "</" + name + ">\r\n";
				}else {
					response += format(data, name, indent + 1);
				}
			}
		}else if(o instanceof List) {
			List<Object> datas = (List<Object>) o;
			Iterator i = datas.iterator();
			int counter = 0;
			while(i.hasNext())
			{
				counter++;
				Object data = i.next();
				if(data instanceof String)
				{
					response += addTabs(indent + 1) + "<" + rootNodeName + ">" + (String) data + "</" + rootNodeName + ">\r\n";
				}else{
					response += format(data, rootNodeName, indent + 1);
				}
			}
		}else {
			response += addTabs(indent + 1) + (String) o;
		}
		
		response += addTabs(indent) + "</" + rootNodeName + ">\r\n";
		
		return response;
	}

	private static String addTabs(int indent) {
		String response = "";
		if(indent > 0)
		{
			for(int k = 0; k < indent; k++)
			{
				response += "\t";
			}
		}
		return response;
	}
}
