package org.kokakiwi.apicraft.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlainFormat {
	public static String format(Object o)
	{
		if(o instanceof Map)
		{
			Map<String, Object> datas = (Map<String, Object>) o;
			Iterator<String> i = datas.keySet().iterator();
			String response = "";
			while(i.hasNext())
			{
				String name = i.next();
				Object data = datas.get(name);
				if(data instanceof String) {
					response += data;
				}else{
					response += PlainFormat.format(data);
				}
			}
			return response;
		}else if(o instanceof List) {
			List<Object> datas = (List<Object>) o;
			Iterator i = datas.iterator();
			String response = "";
			while(i.hasNext())
			{
				Object data = i.next();
				if(data instanceof String)
				{
					response += data;
				}else {
					response += PlainFormat.format(data);
				}
			}
			return response;
		}else {
			return (String) o;
		}
	}
}
