package org.kokakiwi.apicraft.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlainFormat {
	
	public static String format(Object o)
	{
		String response = "";
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
					response += (String) data;
				}else {
					response += format(data);
				}
				if(counter < datas.size())
					response += ",";
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
					response += (String) data;
				}else{
					response += format(data);
				}
				if(counter < datas.size())
					response += ",";
			}
		}else {
			response = (String) o;
		}
		return response;
	}
}
