package model;

public class GrapherPlot  {
	
	private Double xmin;
	private Double xmax;
	private Double ymin;
	private Double ymax;
	private Boolean xAutoSet;
	private Boolean yAutoSet;
	private DataSet dataSet1;
	private DataSet dataSet2;
	

	public GrapherPlot() {
		xmin = 0.0;
		xmax = 0.0;
		ymin = 0.0;
		ymax = 0.0;
		dataSet1 = null;
		dataSet2 = null;
		xAutoSet = true;
		yAutoSet = true;
	}
	

	public GrapherPlot(Double xmin, Double xmax, Double ymin, Double ymax, DataSet dataSet1) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.dataSet1 = dataSet1;
		this.dataSet2 = null;
	}
	

	public GrapherPlot(Double xmin, Double xmax, Double ymin, Double ymax, DataSet dataSet1, DataSet dataSet2) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.dataSet1 = dataSet1;
		this.dataSet2 = dataSet2;
	}

	public Double getXmin() {
		return xmin;
	}

	public void setXmin(Double xmin) {
		if (xmin == null) xmin = 0.0;
		this.xmin = xmin;
	}

	public Double getXmax() {
		return xmax;
	}

	public void setXmax(Double xmax) {
		if (xmax == null) xmax = 0.0;
		this.xmax = xmax;
	}

	public Double getYmin() {
		return ymin;
	}

	public void setYmin(Double ymin) {
		if (ymin == null) ymin = 0.0;
		this.ymin = ymin;
	}

	public Double getYmax() {
		return ymax;
	}

	public void setYmax(Double ymax) {
		if (ymax == null) ymax = 0.0;
		this.ymax = ymax;
	}

	public DataSet getDataSet1() {
		return dataSet1;
	}

	public void setDataSet1(DataSet dataSet1) {
		this.dataSet1 = dataSet1;
	}

	public DataSet getDataSet2() {
		return dataSet2;
	}

	public void setDataSet2(DataSet dataSet2) {
		this.dataSet2 = dataSet2;
	}
	

	public Boolean getxAutoSet() {
		return xAutoSet;
	}

	public void setxAutoSet(Boolean xAutoSet) {
		if (xAutoSet == null) xAutoSet = true;
		this.xAutoSet = xAutoSet;
	}

	public Boolean getyAutoSet() {
		return yAutoSet;
	}

	public void setyAutoSet(Boolean yAutoSet) {
		if (yAutoSet == null) yAutoSet = true;
		this.yAutoSet = yAutoSet;
	}

	
}
