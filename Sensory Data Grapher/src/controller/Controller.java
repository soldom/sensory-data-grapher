package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import db.DataSetEntriesDAO;
import db.GrapherPlotDAO;
import model.DataSet;
import model.DataSetEntry;
import model.GrapherPlot;
import model.LineColor;
import model.LineType;
import model.Model;

public class Controller {

	private GrapherPlotDAO grapherPlotDAO;
	private DataSetEntriesDAO dataSetEntriesDAO;
	private Model model; 
	

	public void setXmin(Double xmin) {
		model.setXmin(xmin);
	}
	public Double getXmin() {
		return model.getXmin();
	}

	public void setXmax(Double xmax) {
		model.setXmax(xmax);
	}
	
	public Double getXmax() {
		return model.getXmax();
	}

	public void setYmin(Double ymin) {
		model.setYmin(ymin);
	}
	public Double getYmin() {
		return model.getYmin();
	}

	public void setYmax(Double ymax) {
		model.setYmax(ymax);
	}
	public Double getYmax() {
		return model.getYmax();
	}

	public void setDataSet1(DataSet dataSet1) {
		model.setDataSet1(dataSet1);
	}
	public DataSet getDataSet1() {
		return model.getDataSet1();
	}

	public void setDataSet2(DataSet dataSet2) {
		model.setDataSet2(dataSet2);
	}
	public DataSet getDataSet2() {
		return model.getDataSet2();
	}
	
	public void setxAutoSet(Boolean xAutoSet) {
		model.setxAutoSet(xAutoSet);
	}
	public Boolean getxAutoSet() {
		return model.getxAutoSet();
	}

	public void setyAutoSet(Boolean yAutoSet) {
		model.setyAutoSet(yAutoSet);
	}
	public Boolean getyAutoSet() {
		return model.getyAutoSet();
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setGrapherPlotXML(String string) throws FileNotFoundException, XMLStreamException {
		GrapherPlot gp = grapherPlotDAO.readInFile(string);
		model.setGrapherPlot(gp);
	}

	public void setDAO(GrapherPlotDAO grapherPlotDAO,
			DataSetEntriesDAO dataSetEntriesDAO) {
		this.grapherPlotDAO = grapherPlotDAO;
		this.dataSetEntriesDAO = dataSetEntriesDAO;
		
	}
	public void setDataSet1LineType(LineType lineType) {
		model.setDataSet1LineType(lineType);
		
	}
	public void setDataSet1LineColor(LineColor lineColor) {
		model.setDataSet1LineColor(lineColor);
		
	}
	public void setNotifyObserver() {
		model.setNotifyObserver();
		
	}
	public void setDataSet2LineColor(LineColor lineColor) {
		model.setDataSet2LineColor(lineColor);
		
	}
	public void setDataSet2LineType(LineType lineType) {
		model.setDataSet2LineType(lineType);		
	}
	public void setSaveAsXml(String string) throws FileNotFoundException, XMLStreamException {
		grapherPlotDAO.saveToFile(string, model.getGrapherPlot());
	}
	public void setDataSet2Txt(String path) {
		try {
			List<DataSetEntry> entries2 = dataSetEntriesDAO.getDataSetEntries(path);
			System.out.println(path);
			if(model.getGrapherPlot() == null) {
				model.setGrapherPlot(new GrapherPlot());
		}
			model.setDataSet2(new DataSet());
			model.getDataSet2().setEntries(entries2);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
	public void setDataSet1Txt(String path) {
		try {
			List<DataSetEntry> entries1 = dataSetEntriesDAO.getDataSetEntries(path);
			System.out.println(path);
			if(model.getGrapherPlot() == null) {
				model.setGrapherPlot(new GrapherPlot());
				System.out.println("neue?");
				}

			model.setDataSet1(new DataSet());
			model.getDataSet1().setEntries(entries1);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Model getModel() {
		return model;
	}
}
