package model;

public class DataSetConfig {
	private String legend;
	private LineType lineType;
	private LineItem lineItem;
	private LineColor lineColor;
	
	public DataSetConfig() {
		legend = null;
		lineType = LineType.DOTTED;
		lineItem = LineItem.POINT;
		lineColor = LineColor.BLUE;
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}

	public LineType getLineType() {
		return lineType;
	}

	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}

	public LineItem getLineItem() {
		return lineItem;
	}

	public void setLineItem(LineItem lineItem) {
		this.lineItem = lineItem;
	}

	public LineColor getLineColor() {
		return lineColor;
	}

	public void setLineColor(LineColor lineColor) {
		this.lineColor = lineColor;
	}

}
