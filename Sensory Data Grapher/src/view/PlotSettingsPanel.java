package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.xml.stream.XMLStreamException;

import model.Model;

import controller.Controller;

public class PlotSettingsPanel extends JPanel implements ActionListener,
		ItemListener {

	private static final long serialVersionUID = 1L;

	private Controller controller;

	private JLabel lbXaxis = new JLabel("X- Axis");
	private JLabel lbXfrom = new JLabel("from:");
	private JTextField txtXfrom = new JTextField("0");
	private JLabel lbXto = new JLabel("to:");
	private JTextField txtXto = new JTextField("50");
	private JLabel lbYaxis = new JLabel("Y- Axis");
	private JTextField txtYfrom = new JTextField("-5");
	private JLabel lbYfrom = new JLabel("from:");
	private JTextField txtYto = new JTextField("5");
	private JLabel lbYto = new JLabel("to:");
	private JCheckBox cbxXautoScale = new JCheckBox("Auto Scale", true);
	private JCheckBox cbxYautoScale = new JCheckBox("Auto Scale", true);
	private JButton btPlot = new JButton("Plot");
	private JButton btSaveAsXml = new JButton("Save as XML");
	private JLabel lbEmpty = new JLabel();

	private JFileChooser fs = new JFileChooser();

	public PlotSettingsPanel(Controller controller) {
		setLayout(new GridBagLayout());
		
		setBorder(new TitledBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED, Color.white, new Color(165, 163, 151)),
				" Plot Settings "));

		this.controller = controller;

		btPlot.addActionListener(this);
		btSaveAsXml.addActionListener(this);
		cbxXautoScale.addItemListener(this);
		cbxYautoScale.addItemListener(this);

		txtXfrom.setEnabled(false);
		txtXto.setEnabled(false);
		txtYfrom.setEnabled(false);
		txtYto.setEnabled(false);

		add(lbXaxis, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbXfrom, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtXfrom, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbXto, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtXto, new GridBagConstraints(5, 1, 2, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(cbxXautoScale, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbYaxis, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbYfrom, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtYfrom, new GridBagConstraints(1, 4, 3, 1, 1.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbYto, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(txtYto, new GridBagConstraints(5, 4, 2, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(cbxYautoScale, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(btPlot, new GridBagConstraints(6, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(btSaveAsXml, new GridBagConstraints(2, 6, 3, 1, 0.0, 0.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(lbEmpty, new GridBagConstraints(7, 0, 0, 7, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btPlot) {
			if(!cbxXautoScale.isSelected()) {
				controller.setxAutoSet(false);
				controller.setXmin(Double.parseDouble(txtXfrom.getText()));
				controller.setXmax(Double.parseDouble(txtXto.getText()));
			} else {
				controller.setxAutoSet(true);
			}
			if(!cbxYautoScale.isSelected()) {
				controller.setyAutoSet(false);
				controller.setYmin(Double.parseDouble(txtYfrom.getText()));
				controller.setYmax(Double.parseDouble(txtYto.getText()));
			} else {
				controller.setyAutoSet(true);
			}
			controller.setNotifyObserver();
		}
		if (e.getSource() == btSaveAsXml) {
			int dataSet = fs.showSaveDialog(PlotSettingsPanel.this);
			if (e.getSource() == btSaveAsXml) {
				if (dataSet == JFileChooser.APPROVE_OPTION) {
					File file = fs.getSelectedFile();
					System.out.println(file.getPath());
					try {
						controller.setSaveAsXml(file.getPath());
					} catch (FileNotFoundException | XMLStreamException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItemSelectable() == cbxXautoScale) {
			if (e.getStateChange() == 1) {
				txtXfrom.setEnabled(false);
				txtXto.setEnabled(false);
				controller.setxAutoSet(true);
			} else {
				txtXfrom.setEnabled(true);
				txtXto.setEnabled(true);
				controller.setxAutoSet(false);
				try {
					controller.setXmax(Double.parseDouble(txtXto.getText()));
				} catch (NumberFormatException e1) {
					System.out.println("Could not parse xto data");
					e1.printStackTrace();
				}
				try {
					controller.setXmin(Double.parseDouble(txtXfrom.getText()));
				} catch (NumberFormatException e1) {
					System.out.println("Could not parse xfrom data");
					e1.printStackTrace();
				}
			}
		}
		if (e.getItemSelectable() == cbxYautoScale) {
			if (e.getStateChange() == 1) {
				txtYfrom.setEnabled(false);
				txtYto.setEnabled(false);
				controller.setyAutoSet(true);

			} else {
				txtYfrom.setEnabled(true);
				txtYto.setEnabled(true);
				controller.setyAutoSet(false);
				try {
					controller.setXmax(Double.parseDouble(txtYto.getText()));
				} catch (NumberFormatException e1) {
					System.out.println("Could not parse yto data");
					e1.printStackTrace();
				}
				try {
					controller.setXmin(Double.parseDouble(txtYfrom.getText()));
				} catch (NumberFormatException e1) {
					System.out.println("Could not parse yfrom data");
					e1.printStackTrace();
				}
			}
		}
	}

	public void update(Observable obs, Object arg) {
		Model model = (Model) obs;
		if (model.getDataSet1() != null || model.getDataSet2() != null) {
			txtXfrom.setText(model.getXmin().toString());
			txtXto.setText(model.getXmax().toString());
			txtYfrom.setText(model.getYmin().toString());
			txtYto.setText(model.getYmax().toString());
			cbxXautoScale.setSelected(model.getxAutoSet());
			cbxYautoScale.setSelected(model.getyAutoSet());
		}
	}
}
