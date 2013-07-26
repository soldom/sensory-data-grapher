package model;

import java.text.ParseException;

public enum LineItem {
	CIRCLE,
	POINT,
	CROSS;
	
	public static LineItem parse(String name) throws ParseException {
		for(LineItem u : LineItem.values()) {
			if(u.toString().equals(name)) {
				return u;
			}
		}
		throw new ParseException("Could not parse LineItem", 0);
	}
}
