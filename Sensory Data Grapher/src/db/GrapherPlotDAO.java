package db;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import model.GrapherPlot;

public interface GrapherPlotDAO {

	/**
	 * 
	 * Saves all information of a GrapherPlot object to a file.
	 * 
	 * @param fileName name of the file
	 * @param grapherPlot	the GrapherPot to be saved
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public void saveToFile(String fileName, GrapherPlot grapherPlot) throws FileNotFoundException, XMLStreamException;
	
	
	/**
	 * 
	 * Returns all information of a GrapherPlot object in a string.
	 * 
	 * @param grapherPlot
	 * @return
	 * @throws XMLStreamException
	 */
	public String getString(GrapherPlot grapherPlot) throws XMLStreamException;
	
	
	/**
	 * 
	 * Reads in a file and returns a GrapherPlot object filled with the
	 * information found in the file.
	 * 
	 * @param fileName file name of the file to be read
	 * @return	Returns a GrapherPlot object
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public GrapherPlot readInFile(String fileName) throws FileNotFoundException, XMLStreamException;
}
