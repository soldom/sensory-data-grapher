package db;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import tools.XmlEventTool;

import model.DataSet;
import model.DataSetConfig;
import model.DataSetEntry;
import model.GrapherPlot;
import model.LineColor;
import model.LineItem;
import model.LineType;

/**
 * This class reads an XML-File and returns a GrapherPlot object
 * or generates XML from a GrapherPlot object and stores the XML
 * in a file or a string.
 *
 */
public class GrapherPlotDAOxml implements GrapherPlotDAO {
	
	private GrapherPlot grapherPlot = null;
	private String fileName = null;

	@Override
	public void saveToFile(String file, GrapherPlot grapherPlot) throws FileNotFoundException, XMLStreamException {
		saveToFile(file, grapherPlot, true, true);
	}
	
	/**
	 * A GrapherPlot object will be turned into XML (optionally with tabs and
	 * line feeds) and saved into a file.
	 * 
	 * @param file	The file name, into which the XML will be stored
	 * @param grapherPlot	The GrapherPlot object, from which XML gets generated
	 * @param tabbed If true, then the file will be formatted with tabs and line feeds
	 * @param header	If true, than a header will be printed
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public void saveToFile(String file, GrapherPlot grapherPlot, boolean tabbed, boolean header) throws XMLStreamException, FileNotFoundException {
		this.grapherPlot = grapherPlot;
		// Create OutputFactory, EventViewer and EventFactory
		XMLOutputFactory of = XMLOutputFactory.newInstance(); 
		FileOutputStream stream = new FileOutputStream(file);
		XMLEventWriter ew = of.createXMLEventWriter(stream);;
		writeToEventWriter(ew, tabbed, header);
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String getString(GrapherPlot grapherPlot) throws XMLStreamException {
		return getString(grapherPlot, true, true);
	}
	
	/**
	 * A GrapherPlot object will be turned into XML (optionally with tabs and
	 * line feeds) and return as a string.
	 * 
	 * @param grapherPlot	The GrapherPlot object, from which XML gets generated
	 * @param tabbed If true, then the file will be formatted with tabs and line feeds
	 * @param header	If true, than a header will be printed
	 * @return grapherPlot as a XML string
	 * @throws XMLStreamException
	 */
	public String getString(GrapherPlot grapherPlot, boolean tabbed, boolean header) throws XMLStreamException {
		this.grapherPlot = grapherPlot;
		XMLOutputFactory of = XMLOutputFactory.newInstance();
		StringWriter sw = new StringWriter();
		XMLEventWriter ew = of.createXMLEventWriter(sw);
		writeToEventWriter(ew, tabbed, header);
		String string = sw.toString();
		try {
			sw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	
	/**
	 * Writes the member object grapherPlot to a XMLEventWriter
	 * 
	 * @param eventWriter XMLEventWriter
	 * @param tabbed if ture, tabs and line feeds will be used
	 * @param header	if true, a header will be printed
	 * @throws XMLStreamException
	 */
	private void writeToEventWriter(XMLEventWriter eventWriter, boolean tabbed, boolean header) throws XMLStreamException {
		XMLEventFactory ef = XMLEventFactory.newInstance();
		
		// Create line feed 
		XMLEvent lf = ef.createDTD("\n");

		if(header) {
			// Opening tag
			eventWriter.add(ef.createStartDocument());
			// Create comment
			if(tabbed) eventWriter.add(lf);
			String comment = "XML generated by the Sensory Data Grapher";
			eventWriter.add(ef.createComment(comment));
		}
		if (tabbed) eventWriter.add(lf);

		//###################################################################
		// Converting grapherPlot to XML
		boolean newLine = tabbed;
		int level = 0;
		//Write root start element
		XmlEventTool.createStartElement(eventWriter, XmlTags.GRAPHERPLOT.toString(), level, newLine);
			if(tabbed) level++;
			//xmin
			XmlEventTool.createNode(eventWriter, XmlTags.XMIN.toString(), grapherPlot.getXmin().toString(), level, newLine);
			//xmax
			XmlEventTool.createNode(eventWriter, XmlTags.XMAX.toString(), grapherPlot.getXmax().toString(), level, newLine);
			//ymin
			XmlEventTool.createNode(eventWriter, XmlTags.YMIN.toString(), grapherPlot.getYmin().toString(), level, newLine);
			//ymax
			XmlEventTool.createNode(eventWriter, XmlTags.YMAX.toString(), grapherPlot.getYmax().toString(), level, newLine);
			//getxAutoSet
			XmlEventTool.createNode(eventWriter, XmlTags.XAUTO.toString(), grapherPlot.getxAutoSet().toString(), level, newLine);
			//getyAutoSet
			XmlEventTool.createNode(eventWriter, XmlTags.YAUTO.toString(), grapherPlot.getyAutoSet().toString(), level, newLine);
			//dataSet1
			writeDataSet(eventWriter, grapherPlot.getDataSet1(), level, tabbed);
			//dataSet2
			writeDataSet(eventWriter, grapherPlot.getDataSet2(), level, tabbed);
			if(tabbed) level--;
		//Write root end element
		XmlEventTool.createEndElement(eventWriter, XmlTags.GRAPHERPLOT.toString(), level, newLine);
		// finished grapherPlot to XML
		//###################################################################
		
		//Write end of document
		if(header) eventWriter.add(ef.createEndDocument());
		eventWriter.flush();
		eventWriter.close();
	}
	
	private void writeDataSet(XMLEventWriter eventWriter, DataSet ds, int level, boolean tabbed) throws XMLStreamException {
		boolean newLine = tabbed;
		if(ds != null) {
			XmlEventTool.createStartElement(eventWriter, XmlTags.DATASET.toString(), level, newLine);
			if(tabbed) level++;
				//config
				DataSetConfig conf = ds.getConfig();
				XmlEventTool.createStartElement(eventWriter, XmlTags.CONFIG.toString(), level, newLine);
				if(tabbed) level++;
					XmlEventTool.createNode(eventWriter, XmlTags.LEGEND.toString(), conf.getLegend(), level, newLine);
					XmlEventTool.createNode(eventWriter, XmlTags.LINECOLOR.toString(), conf.getLineColor().toString(), level, newLine);
					XmlEventTool.createNode(eventWriter, XmlTags.LINEITEM.toString(), conf.getLineItem().toString(), level, newLine);
					XmlEventTool.createNode(eventWriter, XmlTags.LINETYPE.toString(), conf.getLineType().toString(), level, newLine);
				if(tabbed) level--;
				XmlEventTool.createEndElement(eventWriter, XmlTags.CONFIG.toString(), level, newLine);
				//Entries
				XmlEventTool.createStartElement(eventWriter, XmlTags.DATASETENTRIES.toString(), level, newLine);
				if(tabbed) level++;
					List<DataSetEntry> entries = ds.getEntries();
					Iterator<DataSetEntry> it = entries.iterator();
					while(it.hasNext()) {
						DataSetEntry entry = it.next();
						XmlEventTool.createStartElement(eventWriter, XmlTags.DATASETENTRY.toString(), level, false);
						XmlEventTool.createNode(eventWriter, XmlTags.X.toString(), entry.getX().toString(), 0, false);
						XmlEventTool.createNode(eventWriter, XmlTags.Y.toString(), entry.getY().toString(), 0, false);
						XmlEventTool.createEndElement(eventWriter, XmlTags.DATASETENTRY.toString(), 0, newLine);
					}
				if(tabbed) level--;
				XmlEventTool.createEndElement(eventWriter, XmlTags.DATASETENTRIES.toString(), level, newLine);
			if(tabbed) level--;
			XmlEventTool.createEndElement(eventWriter, XmlTags.DATASET.toString(), level, newLine);
			}
	}
	
	@Override
	public GrapherPlot readInFile(String fileName) throws FileNotFoundException, XMLStreamException {
		this.fileName = fileName;
		// First create a new XMLInputFactory
	    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	    // Setup a new eventReader
	    InputStream in = new FileInputStream(this.fileName);
	    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
 
	    XMLEvent event = eventReader.nextEvent();
    	
    	if(event.isStartDocument()) {
    		event = eventReader.nextEvent();
    	} else {
    		throw new XMLStreamException("No XML header found");
    	}
    	
    	event = eventReader.nextEvent();
    	
    	GrapherPlot gp = null;
    	if(event.isStartElement()) {
    		grapherPlot = null;
    		if (event.asStartElement().getName().getLocalPart() == XmlTags.GRAPHERPLOT.toString()) {
    			//System.out.println(event.asStartElement().getName().getLocalPart());
    			gp = new GrapherPlot();
    			eventReader.nextEvent();
    		} else {
    			throw new XMLStreamException("Did not find root element " + XmlTags.GRAPHERPLOT.toString());
    		}
    	}
    	
    	Double xmin = null;
    	Double ymin = null;
    	Double xmax = null;
    	Double ymax = null;
    	Boolean xAutoSet = null;
    	Boolean yAutoSet = null;
    	DataSet ds = null;
    	LinkedList<DataSet> dsList = new LinkedList<DataSet>();
    	
    	while(eventReader.hasNext()) {
    		event = eventReader.nextEvent();
    		
    		if(event.isStartElement()) {
    			String localPart = event.asStartElement().getName().getLocalPart();
    			if(localPart.equals(XmlTags.XMIN.toString())) {
    				event = eventReader.nextEvent();
    				if(event.isCharacters()) {
	    				try {
	    				xmin = Double.parseDouble(event.asCharacters().getData());
	    				} catch (NumberFormatException e) {
	    					throw new XMLStreamException("Could not parse xMin data.", event.getLocation(), e);
	    				}
    				}
    				continue;
    			}
    			if(localPart.equals(XmlTags.XMAX.toString())) {
    				event = eventReader.nextEvent();
    				if(event.isCharacters()) {
	    				try {
	    				xmax = Double.parseDouble(event.asCharacters().getData());
	    				} catch (NumberFormatException e) {
	    					throw new XMLStreamException("Could not parse xMax data.", event.getLocation(), e);
	    				}
    				}
    				continue;
    			}
    			if(localPart.equals(XmlTags.YMIN.toString())) {
    				event = eventReader.nextEvent();
    				if(event.isCharacters()) {
	    				try {
	    				ymin = Double.parseDouble(event.asCharacters().getData());
	    				} catch (NumberFormatException e) {
	    					throw new XMLStreamException("Could not parse yMin data.", event.getLocation(), e);
	    				}
    				}
    				continue;
    			}
    			if(localPart.equals(XmlTags.YMAX.toString())) {
    				event = eventReader.nextEvent();
    				if(event.isCharacters()) {
	    				try {
	    				ymax = Double.parseDouble(event.asCharacters().getData());
	    				} catch (NumberFormatException e) {
	    					throw new XMLStreamException("Could not parse yMax data.", event.getLocation(), e);
	    				}
    				}
    				continue;
    			}
    			if(localPart.equals(XmlTags.XAUTO.toString())) {
    				event = eventReader.nextEvent();
    				if(event.isCharacters()) {
    					xAutoSet = Boolean.parseBoolean(event.asCharacters().getData());
    				}
    				continue;
    			}
    			if(localPart.equals(XmlTags.YAUTO.toString())) {
    				event = eventReader.nextEvent();
    				if(event.isCharacters()) {
    					yAutoSet = Boolean.parseBoolean(event.asCharacters().getData());
    				}
    				continue;
    			}
    			// Parsing dataset
    			//****************
    			if(localPart.equals(XmlTags.DATASET.toString())) {
    				ds = new DataSet();
    				while(eventReader.hasNext()) {
    					event = eventReader.nextEvent();
    					if(event.isStartElement()) {
    						localPart = event.asStartElement().getName().getLocalPart();
    		    			// Parsing config
    		    			//****************
    		    			if(localPart.equals(XmlTags.CONFIG.toString())) {
    		    				DataSetConfig conf = new DataSetConfig();
    		    				while(eventReader.hasNext()) {
    		    					event = eventReader.nextEvent();
    		    					if(event.isStartElement()) {
    		    						localPart = event.asStartElement().getName().getLocalPart();
    		    						if(localPart.equals(XmlTags.LEGEND.toString())) {
    		    							event = eventReader.nextEvent();
    		    							if(event.isCharacters()) {
    		    								String legend = event.asCharacters().getData();
    	    		    						conf.setLegend(legend);
    	    		    					}
    		    							continue;
    		    						}
    		    						if(localPart.equals(XmlTags.LINECOLOR.toString())) {
    		    							event = eventReader.nextEvent();
    		    							if(event.isCharacters()) {
    		    								try {
    		    									LineColor lineColor = LineColor.parse(event.asCharacters().getData());
    		    									conf.setLineColor(lineColor);
    		    								} catch (ParseException e) {
    		    									throw new XMLStreamException("Could not parse line color.", event.getLocation(), e);
    		    								}
    	    		    					}
    		    							continue;
    		    						}
    		    						if(localPart.equals(XmlTags.LINEITEM.toString())) {
    		    							event = eventReader.nextEvent();
    		    							if(event.isCharacters()) {
    		    								try {
    		    									LineItem lineItem = LineItem.parse(event.asCharacters().getData());
    		    									conf.setLineItem(lineItem);
    		    								} catch (ParseException e) {
    		    									throw new XMLStreamException("Could not parse line item.", event.getLocation(), e);
    		    								}
    	    		    					}
    		    							continue;
    		    						}
    		    						if(localPart.equals(XmlTags.LINETYPE.toString())) {
    		    							event = eventReader.nextEvent();
    		    							if(event.isCharacters()) {
    		    								try {
    		    									LineType lineType = LineType.parse(event.asCharacters().getData());
    		    									conf.setLineType(lineType);
    		    								} catch (ParseException e) {
    		    									throw new XMLStreamException("Could not parse line type.", event.getLocation(), e);
    		    								}
    	    		    					}
    		    							continue;
    		    						}
    		    					}
    		    					if(event.isEndElement()) {
    		    						if(event.asEndElement().getName().getLocalPart().equals(XmlTags.CONFIG.toString())) {
    		    							ds.setConfig(conf);
    		    							break;
    		    						}
    		    					}
    		    				}
    		    				continue;
    		    			}
    		    			// finished parsing config
    		    			//****************
    		    			
    		    			// Parsing data entries
    		    			//****************
    		    			if(localPart.equals(XmlTags.DATASETENTRIES.toString())) {
    		    				List<DataSetEntry> entries = Collections.synchronizedList(new ArrayList<DataSetEntry>());
    		    				while(eventReader.hasNext()) {
    		    					event = eventReader.nextEvent();
    		    					if(event.isStartElement()) {
    		    						localPart = event.asStartElement().getName().getLocalPart();
    		    						if(localPart.equals(XmlTags.DATASETENTRY.toString())) {
    		    		    				Double x = null;
    		    		    				Double y = null;
    		    		    				while(eventReader.hasNext()) {
    		    		    					event = eventReader.nextEvent();
    		    		    					if(event.isStartElement()) {
    		    		    						localPart = event.asStartElement().getName().getLocalPart();
    		    		    						if(localPart.equals(XmlTags.X.toString())) {
    		    		    							event = eventReader.nextEvent();
    		    		    							if(event.isCharacters()) {
    		    		    								try {
    		    		    				    				x = Double.parseDouble(event.asCharacters().getData());
    		    		    				    			} catch (NumberFormatException e) {
    		    		    				    				throw new XMLStreamException("Could not parse x data.", event.getLocation(), e);
    		    		    				    			}
    		    		    							}
    		    		    							continue;
    		    		    						}
    		    		    						if(localPart.equals(XmlTags.Y.toString())) {
    		    		    							event = eventReader.nextEvent();
    		    		    							if(event.isCharacters()) {
    		    		    								try {
    		    		    				    				y = Double.parseDouble(event.asCharacters().getData());
    		    		    				    			} catch (NumberFormatException e) {
    		    		    				    				throw new XMLStreamException("Could not parse y data.", event.getLocation(), e);
    		    		    				    			}
    		    		    							}
    		    		    							continue;
    		    		    						}
    		    		    					}
    		    		    					if(event.isEndElement()) {
    		    		    						if(event.asEndElement().getName().getLocalPart().equals(XmlTags.DATASETENTRY.toString())) {
    		    		    							DataSetEntry entry = new DataSetEntry(x, y);
    		    		    							entries.add(entry);
    		    		    							break;
    		    		    						}
    		    		    					}
    		    		    				}
    		    						}
    		    					}
    		    					if(event.isEndElement()) {
    		    						if(event.asEndElement().getName().getLocalPart().equals(XmlTags.DATASETENTRIES.toString())) {
    		    							ds.setEntries(entries);
    		    							break;
    		    						}
    		    					}
    		    				}
    		    				continue;
    		    			}
    		    			// finished parsing data entries
    		    			//**************
    					}
    					
    					if (event.isEndElement()) {
    						localPart = event.asEndElement().getName().getLocalPart();
    						if(localPart.equals(XmlTags.DATASET.toString())) {
    	        				dsList.add(ds);
    	        				break;
    	        			}
    					}
	        			
    				continue;
    				}
    			}
    			// finished parsing dataset
    			//****************
    		}
    		
    		//detect end tags, add staged content
    		if(event.isEndElement()) {
    			String localPart = event.asEndElement().getName().getLocalPart();
    			if(localPart.equals(XmlTags.XMIN.toString())) {
    				gp.setXmin(xmin);
    				continue;
    			}
    			if(localPart.equals(XmlTags.XMAX.toString())) {
    				gp.setXmax(xmax);
    				continue;
    			}
    			if(localPart.equals(XmlTags.YMIN.toString())) {
    				gp.setYmin(ymin);
    				continue;
    			}
    			if(localPart.equals(XmlTags.YMAX.toString())) {
    				gp.setYmax(ymax);
    				continue;
    			}
    			if(localPart.equals(XmlTags.XAUTO.toString())) {
    				gp.setxAutoSet(xAutoSet);
    				continue;
    			}
    			if(localPart.equals(XmlTags.YAUTO.toString())) {
    				gp.setyAutoSet(yAutoSet);
    				continue;
    			}
    			if(localPart.equals(XmlTags.GRAPHERPLOT.toString())) {
    				try {
    					gp.setDataSet1(dsList.remove());
    				} catch (NoSuchElementException e) {
    					//ignore
    				}
    				try {
    					gp.setDataSet2(dsList.remove());
    				} catch (NoSuchElementException e) {
    					//ignore
    				}
    				
    				grapherPlot = gp;
    				break;
    			}
    			
    		}

    	}
    	
		return grapherPlot;
		
	}
}
