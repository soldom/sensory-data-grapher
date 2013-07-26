package db;

/**
 * 
 * This enumeration defines the XML tag names.
 *
 */
public enum XmlTags {
	
	//Root
	GRAPHERPLOT("grapherPlot"),
	
	//> Members
	XMIN("xMin"),
	XMAX("xMax"),
	YMIN("yMin"),
	YMAX("yMax"),
	XAUTO("xAutoSet"),
	YAUTO("yAutoSet"),
	DATASET("dataSet"),
	//>>DataSet
		CONFIG("config"),
			LEGEND("legend"),
			LINETYPE("lineType"),
			LINEITEM("lineItem"),
			LINECOLOR("lineColor"),
		DATASETENTRIES("dataSetEntries"),
			DATASETENTRY("entry"),
				Y("y"),
				X("x"),
	;
	
	
	private String name;
	
	XmlTags(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
}