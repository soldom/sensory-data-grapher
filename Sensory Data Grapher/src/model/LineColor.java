package model;

import java.awt.Color;
import java.text.ParseException;

public enum LineColor {
	GREEN("GREEN", Color.green),
	RED("RED", Color.red),
	BLUE("BLUE", Color.blue),
	BLACK("BLACK", Color.BLACK),
	;
	
	private Color color;
	private String name;
	
	public String toString() {
		return name;
	}
	
	LineColor(String name, Color color) {
		this.color = color;
		this.name = name;
	}
	
	public Color toColor() {
		return color;
	}
	public static LineColor parse(String name) throws ParseException {
		for(LineColor u : LineColor.values()) {
			if(u.toString().equals(name)) {
				return u;
			}
		}
		throw new ParseException("Could not parse LineColor", 0);
	}
	
}
