package model;

import java.text.ParseException;

public enum LineType {
	DOTTED,
	LINE;
	
	public static LineType parse(String name) throws ParseException {
		for(LineType u : LineType.values()) {
			if(u.toString().equals(name)) {
				return u;
			}
		}
		throw new ParseException("Could not parse LineType", 0);
	}
}
