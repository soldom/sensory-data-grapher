package view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.LineColor;
import model.LineType;
import model.Model;

import controller.Controller;

import java.io.*;
import java.util.Observable;

public class DataSetPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static String rbBlue = "blue";
	private static String rbGreen = "green";
	private static String rbRed = "red";
	private static String rbDot = "dot";
	private static String rbLine = "line";

	// Dateset 1
	private JLabel lbDataSet1 = new JLabel("Dataset 1:");
	private JLabel lbDataSet1Path = new JLabel("Path:");
	private JTextField txtDataSet1Source = new JTextField("Source");
	private JButton btAddDataSet1 = new JButton("Source");
	private JLabel lbLegenData1 = new JLabel("Legend:");
	private JTextField txtLegendData1 = new JTextField("Dataset1");

	private JRadioButton rbDataSet1ColorBlue = new JRadioButton(rbBlue, true);
	private JRadioButton rbDataSet1ColorGreen = new JRadioButton(rbGreen);
	private JRadioButton rbDataSet1ColorRed = new JRadioButton(rbRed);
	private JLabel lbDateSet1LineType = new JLabel("Linetype:");
	private JRadioButton rbDataSet1LineTypeDot = new JRadioButton(rbDot, true);
	private JRadioButton rbDataSet1LineTypeLine = new JRadioButton(rbLine);

	ButtonGroup gpDataSet1Color = new ButtonGroup();
	ButtonGroup gpDataSet1LineType = new ButtonGroup();

	// Dataset 2
	private JLabel lbDataSet2 = new JLabel("Dataset 2:");
	private JLabel lbDataSet2Path = new JLabel("Path:");
	private JTextField txtDataSet2Source = new JTextField("Source");
	private JButton btAddDataSet2 = new JButton("Source");
	private JLabel lbLegenData2 = new JLabel("Legend:");
	private JTextField txtLegendData2 = new JTextField("Dataset2");

	private JRadioButton rbDataSet2ColorBlue = new JRadioButton(rbBlue, true);
	private JRadioButton rbDataSet2ColorGreen = new JRadioButton(rbGreen);
	private JRadioButton rbDataSet2ColorRed = new JRadioButton(rbRed);
	private JLabel lbDateSet2LineType = new JLabel("Linetype:");
	private JRadioButton rbDataSet2LineTypeDot = new JRadioButton(rbDot, true);
	private JRadioButton rbDataSet2LineTypeLine = new JRadioButton(rbLine);

	ButtonGroup gpDataSet2Color = new ButtonGroup();
	ButtonGroup gpDataSet2LineType = new ButtonGroup();

	private JLabel lbEmpty = new JLabel();

	public File dataSetFile1;
	public File dataSetFile2;

	private JFileChooser fc = new JFileChooser();
	private JFileChooser fc2 = new JFileChooser();
	private Controller controller;

	public DataSetPanel(Controller controller) {

		this.controller = controller;

		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fc2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		setLayout(new GridBagLayout());
		setBorder(new TitledBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED, Color.white, new Color(165, 163, 151)),
				" Datasets "));

		gpDataSet1Color.add(rbDataSet1ColorBlue);
		gpDataSet1Color.add(rbDataSet1ColorGreen);
		gpDataSet1Color.add(rbDataSet1ColorRed);
		gpDataSet2Color.add(rbDataSet2ColorBlue);
		gpDataSet2Color.add(rbDataSet2ColorGreen);
		gpDataSet2Color.add(rbDataSet2ColorRed);

		gpDataSet1LineType.add(rbDataSet1LineTypeDot);
		gpDataSet1LineType.add(rbDataSet1LineTypeLine);
		gpDataSet2LineType.add(rbDataSet2LineTypeDot);
		gpDataSet2LineType.add(rbDataSet2LineTypeLine);

		btAddDataSet1.addActionListener(this);
		rbDataSet1ColorBlue.addActionListener(this);
		rbDataSet1ColorGreen.addActionListener(this);
		rbDataSet1ColorRed.addActionListener(this);
		rbDataSet1LineTypeDot.addActionListener(this);
		rbDataSet1LineTypeLine.addActionListener(this);
		btAddDataSet2.addActionListener(this);
		rbDataSet2ColorBlue.addActionListener(this);
		rbDataSet2ColorGreen.addActionListener(this);
		rbDataSet2ColorRed.addActionListener(this);
		rbDataSet2LineTypeDot.addActionListener(this);
		rbDataSet2LineTypeLine.addActionListener(this);

		// Dataset 1
		add(lbDataSet1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbDataSet1Path, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtDataSet1Source, new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(btAddDataSet1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		add(lbLegenData1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtLegendData1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet1ColorBlue, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet1ColorGreen, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet1ColorRed, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbDateSet1LineType, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet1LineTypeDot, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet1LineTypeLine, new GridBagConstraints(1, 6, 1, 1, 0.0,
				0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

		// Dataset 2
		add(lbDataSet2, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbDataSet2Path, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtDataSet2Source, new GridBagConstraints(0, 9, 2, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(btAddDataSet2, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbLegenData2, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtLegendData2, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet2ColorBlue, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet2ColorGreen, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet2ColorRed, new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbDateSet2LineType, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet2LineTypeDot, new GridBagConstraints(0, 13, 1, 1, 0.0,
				0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(rbDataSet2LineTypeLine, new GridBagConstraints(1, 13, 1, 1, 0.0,
				0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbEmpty, new GridBagConstraints(4, 0, 0, 13, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rbDataSet1ColorBlue) {
			controller.setDataSet1LineColor(LineColor.BLUE);
		}
		if (e.getSource() == rbDataSet1ColorGreen) {
			controller.setDataSet1LineColor(LineColor.GREEN);
		}
		if (e.getSource() == rbDataSet1ColorRed) {
			controller.setDataSet1LineColor(LineColor.RED);
		}
		if (e.getSource() == rbDataSet1LineTypeDot) {
			controller.setDataSet1LineType(LineType.DOTTED);
		}
		if (e.getSource() == rbDataSet1LineTypeLine) {
			controller.setDataSet1LineType(LineType.LINE);
		}
		if (e.getSource() == btAddDataSet1) {
			int dataSet1 = fc.showOpenDialog(DataSetPanel.this);
			if (dataSet1 == JFileChooser.APPROVE_OPTION) {
				File dataSetFile1 = fc.getSelectedFile();
				txtDataSet1Source.setText(dataSetFile1.getPath());
				
				
				txtDataSet1Source.setText(dataSetFile1.getPath());
				controller.setDataSet1Txt(dataSetFile1.getPath());

			}
		}
		
		if (e.getSource() == btAddDataSet2) {
			int dataSet2 = fc2.showOpenDialog(DataSetPanel.this);
			if (dataSet2 == JFileChooser.APPROVE_OPTION) {
				dataSetFile2 = fc2.getSelectedFile();
				txtDataSet2Source.setText(dataSetFile2.getPath());
				controller.setDataSet2Txt(dataSetFile2.getPath());
			}
		}
		if (e.getSource() == rbDataSet2ColorBlue) {
			controller.setDataSet2LineColor(LineColor.BLUE);
		}
		if (e.getSource() == rbDataSet2ColorGreen) {
			controller.setDataSet2LineColor(LineColor.GREEN);
		}
		if (e.getSource() == rbDataSet2ColorRed) {
			controller.setDataSet2LineColor(LineColor.RED);
		}
		if (e.getSource() == rbDataSet2LineTypeDot) {
			controller.setDataSet2LineType(LineType.DOTTED);
		}
		if (e.getSource() == rbDataSet2LineTypeLine) {
			controller.setDataSet2LineType(LineType.LINE);
		}
	}

	public void update(Observable obs, Object arg) {
		Model model = (Model) obs;
		if (model.getDataSet1() != null) {
			txtLegendData1.setText(model.getDataSet1().getConfig().getLegend());
			switch (model.getDataSet1().getConfig().getLineColor()) {
			case BLUE:
				rbDataSet1ColorBlue.setSelected(true);
				break;
			case GREEN:
				rbDataSet1ColorGreen.setSelected(true);
				break;
			case RED:
				rbDataSet1ColorRed.setSelected(true);
				break;
			default:
				break;
			}
			;
		}
		if (model.getDataSet2() != null) {
			txtLegendData2.setText(model.getDataSet2().getConfig().getLegend());
			switch (model.getDataSet2().getConfig().getLineColor()) {
			case BLUE:
				rbDataSet2ColorBlue.setSelected(true);
				break;
			case GREEN:
				rbDataSet2ColorGreen.setSelected(true);
				break;
			case RED:
				rbDataSet2ColorRed.setSelected(true);
				break;
			default:
				break;
			}
			;
		}
	}
}
