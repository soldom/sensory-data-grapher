package db;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import javax.xml.stream.XMLStreamException;

import model.DataSet;
import model.DataSetConfig;
import model.DataSetEntry;
import model.GrapherPlot;
import model.LineColor;
import model.LineItem;
import model.LineType;

/**
 *  This is a dummy implementation for testing
 *
 */
public class GrapherPlotDAOdummy implements GrapherPlotDAO {

	private String xmlTestStr;
	
	public GrapherPlotDAOdummy() {
		xmlTestStr = 
				"<?xml version=\"1.0\"?>\n" +
				"<!--This was created by a dummy implementation-->\n" +
				"<grapherPlot>\n" +
					"\t<xMin>1.0<xMin>\n" +
					"\t<xMax>2.0<xMax>\n" +
					"\t<yMin>3.0<yMin>\n" +
					"\t<yMax>4.0<yMax>\n" +
					"\t<config>\n" +
					"\t</config>\n" +
					"\t<dataSet>\n" +
					"\t</dataSet>\n" +
					"\t<dataSet>\n" +
					"\t</dataSet>\n" +
				"</grapherPlot>";
	}
	
	@Override
	public void saveToFile(String fileName, GrapherPlot grapherPlot)
			throws FileNotFoundException, XMLStreamException {
		try {
		BufferedWriter writer = new BufferedWriter( new FileWriter(fileName));
		writer.write(xmlTestStr);
		writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getString(GrapherPlot grapherPlot) throws XMLStreamException {
		return xmlTestStr;
	}

	@Override
	public GrapherPlot readInFile(String fileName)
			throws FileNotFoundException, XMLStreamException {
		GrapherPlot gp = new GrapherPlot();
		
		gp.setXmin(1.0);
		gp.setXmax(2.0);
		gp.setYmin(3.0);
		gp.setYmax(4.0);
		gp.setxAutoSet(true);
		gp.setyAutoSet(true);
		
		int n = 200;
		
		// data set 1
			DataSet ds1 = new DataSet();
			for(int i = 0; i<=n; i++) {
				DataSetEntry entry = new DataSetEntry((double) i, Math.sin(i*2*Math.PI/n));
				ds1.addEntry(entry);
		
			}
			
			DataSetConfig ds1Conf = new DataSetConfig();
			ds1Conf.setLegend("Sinus");
			ds1Conf.setLineColor(LineColor.BLUE);
			ds1Conf.setLineItem(LineItem.POINT);
			ds1Conf.setLineType(LineType.DOTTED);
			
			ds1.setConfig(ds1Conf);
			
			gp.setDataSet1(ds1);
			
		//dataSet 2
			DataSet ds2  = new DataSet();
			for(int i = 0; i<=n; i++) {
				DataSetEntry entry = new DataSetEntry((double) i, ((double) i)/n);
				ds2.addEntry(entry);
			}
			DataSetConfig ds2Conf = new DataSetConfig();
			ds2Conf.setLegend("Gerade");
			ds2Conf.setLineColor(LineColor.RED);
			ds2Conf.setLineItem(LineItem.POINT);
			ds2Conf.setLineType(LineType.LINE);
			
			ds2.setConfig(ds2Conf);
			
			gp.setDataSet2(ds2);
		
		return gp;
	}

}
