package view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.xml.stream.XMLStreamException;
import controller.Controller;

import java.io.*;

public class XmlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private Controller controller;
	
	private JLabel lbDataSet1 = new JLabel("XML:");
	private JLabel lbDataSet1Path = new JLabel("Path:");
	private JTextField txtXMLSource = new JTextField("Source");
	private JButton btAddXML = new JButton("Load");
	private JLabel lbEmpty = new JLabel();

	private JFileChooser fc = new JFileChooser();

	public XmlPanel(Controller controller) {
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		setLayout(new GridBagLayout());
		// setBackground(Color.lightGray);
		setBorder(new TitledBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED, Color.white, new Color(165, 163, 151)),
				" Load XML "));
		
		this.controller = controller;

		btAddXML.addActionListener(this);
		add(lbDataSet1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbDataSet1Path, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtXMLSource, new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(btAddXML, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbEmpty, new GridBagConstraints(4, 0, 0, 5, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int dataSet = fc.showOpenDialog(XmlPanel.this);
		if (e.getSource() == btAddXML) {
			if (dataSet == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				txtXMLSource.setText(file.getPath());
				try {
					controller.setGrapherPlotXML(file.getPath());
				} catch (FileNotFoundException | XMLStreamException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
}
