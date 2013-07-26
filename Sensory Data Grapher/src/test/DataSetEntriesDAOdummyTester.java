package test;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import model.GrapherPlot;
import db.GrapherPlotDAO;
import db.GrapherPlotDAOdummy;

public class DataSetEntriesDAOdummyTester {

	
	public static void main(String[] args) {
		String file = System.getProperty("user.home") + "/Desktop/DummyTest.xml";
		GrapherPlotDAO gpDAO = new GrapherPlotDAOdummy();
		GrapherPlot gp = new GrapherPlot();
		try {
			gpDAO.saveToFile(file, gp);
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
