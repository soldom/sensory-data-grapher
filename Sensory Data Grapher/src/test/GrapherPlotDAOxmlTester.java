package test;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import db.GrapherPlotDAO;
import db.GrapherPlotDAOxml;
import model.GrapherPlot;

public class GrapherPlotDAOxmlTester {

	public static void main(String[] args) {
		try {
			GrapherPlotDAO realDao = new GrapherPlotDAOxml();
			
			String file = System.getProperty("user.home") + "/Desktop/XmlTest.xml";
			
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			System.out.println("-------------------------------------------------");
			
			GrapherPlot gpRead = realDao.readInFile(file);
			String str2 = realDao.getString(gpRead);
			System.out.println(str2);
			
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

	}

}
