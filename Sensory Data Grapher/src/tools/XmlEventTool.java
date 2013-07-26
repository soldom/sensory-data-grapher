package tools;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * This is class presents functions to ease the use of StAX
 * 
 *
 */
public abstract class XmlEventTool {
	/**
	 * Creates a XML node in the XMLEventWriter
	 * 
	 * @param ew 	XMLEventWriter
	 * @param name 	name of the node
	 * @param value value of the node
	 * @throws XMLStreamException
	 */
	public static void createNode(XMLEventWriter ew, String name, String value) throws XMLStreamException {
		createNode(ew, name, value, 0, false);
	}
	
	/**
	 * Creates a optionally formatted (with tabs and line feeds) a XML node in a XMLEventWriter
	 * 
	 * @param ew 	XMLEventWriter
	 * @param name 	name of the node
	 * @param value value of the node
	 * @param level number of tabs
	 * @param newLine 	if true, a line feed follows the node
	 * @throws XMLStreamException
	 */
	public static void createNode(XMLEventWriter ew, String name, String value, int level, boolean newLine) throws XMLStreamException {
		// Create EventFactory
		XMLEventFactory ef = XMLEventFactory.newInstance();
	    // Add tabs
		XMLEvent tab = ef.createDTD("\t");
	    for (int i = 0; i < level; i++) {ew.add(tab);}
	    // Create Start node
	    ew.add(ef.createStartElement("", "", name));
	    // Create Content
	    ew.add(ef.createCharacters(value));
	    // Create End node
	    ew.add(ef.createEndElement("", "", name));
	    // Add line feed
	    if(newLine) {ew.add(ef.createDTD("\n"));}
	}
	
	
	/**
	 * Creates a start element (opening tag) in a XMLEventWriter.
	 * 
	 * @param ew 	XMLEventWriter
	 * @param name 	name of the start element
	 * @param level number of tabs
	 * @param newLine 	if true, a line feed follows the start element
	 * @throws XMLStreamException
	 */
	public static void createStartElement(XMLEventWriter ew, String name, int level, boolean newLine) throws XMLStreamException{
		// Create EventFactory
		XMLEventFactory ef = XMLEventFactory.newInstance();
	    // Add tabs
		XMLEvent tab = ef.createDTD("\t");
	    for (int i = 0; i < level; i++) {ew.add(tab);}
	    // Create Start node
	    ew.add(ef.createStartElement("", "", name));
	    // Add line feed
	    if(newLine) {ew.add(ef.createDTD("\n"));}
	}
	
	/**
	 * Creates a end element (closing tag) in a XMLEventWriter.
	 * 
	 * @param ew 	XMLEventWriter
	 * @param name 	name of the end element
	 * @param level number of tabs
	 * @param newLine if true, a line feed follows the start element
	 * @throws XMLStreamException
	 */
	public static void createEndElement(XMLEventWriter ew, String name, int level, boolean newLine) throws XMLStreamException {
		// Create EventFactory
		XMLEventFactory ef = XMLEventFactory.newInstance();
	    // Add tabs
		XMLEvent tab = ef.createDTD("\t");
	    for (int i = 0; i < level; i++) {ew.add(tab);}
	    // Create End node
	    ew.add(ef.createEndElement("", "", name));
	    // Add line feed
	    if(newLine) {ew.add(ef.createDTD("\n"));}
	}
	
	/**
	 * Creates characters in a XMLEventWriter.
	 * 
	 * @param ew 	XMLEventWriter
	 * @param characters 	characters to be created
	 * @throws XMLStreamException
	 * @throws FactoryConfigurationError
	 */
	public static void createCharacters(XMLEventWriter ew, String characters) throws XMLStreamException, FactoryConfigurationError {
		ew.add(XMLEventFactory.newInstance().createCharacters(characters));
	}
	
}