package model;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

	private GrapherPlot grapherPlot = new GrapherPlot();

	public void setXmin(Double xmin) {
		grapherPlot.setXmin(xmin);
	}

	public void setXmax(Double xmax) {
		grapherPlot.setXmax(xmax);
	}

	public void setYmin(Double ymin) {
		grapherPlot.setYmin(ymin);
	}

	public void setYmax(Double ymax) {
		grapherPlot.setYmax(ymax);
	}


	public void setxAutoSet(Boolean xAutoSet) {
		grapherPlot.setxAutoSet(xAutoSet);
		if(xAutoSet) {
			findXautoValues();
		}
	}

	public void setyAutoSet(Boolean yAutoSet) {
		grapherPlot.setyAutoSet(yAutoSet);
		if(yAutoSet) {
			findYautoValues();
		}
	}

	public void setDataSet2(DataSet dataSet2) {
		grapherPlot.setDataSet2(dataSet2);
	}

	public void setDataSet1(DataSet dataSet1) {
		grapherPlot.setDataSet1(dataSet1);
	}

	public void setGrapherPlot(GrapherPlot gp) {
		this.grapherPlot = gp;
		notifyObservers();
	}

	/* @Override */
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}

	public Double getXmin() {
		return grapherPlot.getXmin();
	}

	public Double getXmax() {
		return grapherPlot.getXmax();
	}

	public Double getYmin() {
		return grapherPlot.getYmin();
	}

	public Double getYmax() {
		return grapherPlot.getYmax();
	}

	public DataSet getDataSet1() {

		return grapherPlot.getDataSet1();
	}

	public DataSet getDataSet2() {
		return grapherPlot.getDataSet2();
	}

	public Boolean getxAutoSet() {
		return grapherPlot.getxAutoSet();
	}

	public Boolean getyAutoSet() {
		return grapherPlot.getyAutoSet();
	}

	public void setDataSet1LineType(LineType lineType) {
		grapherPlot.getDataSet1().getConfig().setLineType(lineType);
	}

	public void setDataSet1LineColor(LineColor lineColor) {
		grapherPlot.getDataSet1().getConfig().setLineColor(lineColor);
	}

	public void setDataSet2LineColor(LineColor lineColor) {
		grapherPlot.getDataSet2().getConfig().setLineColor(lineColor);

	}

	public void setDataSet2LineType(LineType lineType) {
		grapherPlot.getDataSet2().getConfig().setLineType(lineType);

	}
	
	public void findXautoValues() {
		
		Double xmin = Double.MAX_VALUE;
		Double xmax = Double.MIN_VALUE;
		
		if(grapherPlot.getDataSet1() != null) {
			if(grapherPlot.getDataSet1().getEntries() != null) {
				List<DataSetEntry> entries = grapherPlot.getDataSet1().getEntries();
				Iterator<DataSetEntry> iterator = entries.iterator();
				while(iterator.hasNext()) {
					DataSetEntry entry = iterator.next();
					if(entry.getX() > xmax)
						xmax = entry.getX();
					if(entry.getX() < xmin)
						xmin = entry.getX();
				}
			}
		}
		if(grapherPlot.getDataSet2() != null) {
			if(grapherPlot.getDataSet2().getEntries() != null) {
				List<DataSetEntry> entries = grapherPlot.getDataSet2().getEntries();
				Iterator<DataSetEntry> iterator = entries.iterator();
				while(iterator.hasNext()) {
					DataSetEntry entry = iterator.next();
					if(entry.getX() > xmax)
						xmax = entry.getX();
					if(entry.getX() < xmin)
						xmin = entry.getX();
				}
			}
		}
		grapherPlot.setXmax(xmax);
		grapherPlot.setXmin(xmin);

	}
	
	public void findYautoValues() {
		Double ymin = Double.MAX_VALUE;
		Double ymax = Double.MIN_VALUE;
		
		if(grapherPlot.getDataSet1() != null) {
			if(grapherPlot.getDataSet1().getEntries() != null) {
				List<DataSetEntry> entries = grapherPlot.getDataSet1().getEntries();
				Iterator<DataSetEntry> iterator = entries.iterator();
				while(iterator.hasNext()) {
					DataSetEntry entry = iterator.next();
					if(entry.getY() > ymax)
						ymax = entry.getY();
					if(entry.getY() < ymin)
						ymin = entry.getY();
				}
			}
		}
		if(grapherPlot.getDataSet2() != null) {
			if(grapherPlot.getDataSet2().getEntries() != null) {
				List<DataSetEntry> entries = grapherPlot.getDataSet2().getEntries();
				Iterator<DataSetEntry> iterator = entries.iterator();
				while(iterator.hasNext()) {
					DataSetEntry entry = iterator.next();
					if(entry.getY() > ymax)
						ymax = entry.getY();
					if(entry.getY() < ymin)
						ymin = entry.getY();
				}
			}
		}
		
		grapherPlot.setYmax(ymax);
		grapherPlot.setYmin(ymin);

	}

	public void setNotifyObserver() {
		notifyObservers();

	}

	public GrapherPlot getGrapherPlot() {
		return grapherPlot;
	}

}
