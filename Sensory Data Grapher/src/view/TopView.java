package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.Controller;


public class TopView extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private PlotPanel jpPlotPanel;
	private XmlPanel jpXmlPanel;
	private DataSetPanel jpDataSetPanel;
	private PlotSettingsPanel jpPlotSettings;

	public TopView(Controller controller) {
		setTitle("Sensory Data Grapher");
		pack();
		setVisible(true);
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		
		setBackground(Color.lightGray);
		setLayout(new GridBagLayout());
		
		jpPlotPanel = new PlotPanel();
		jpXmlPanel = new XmlPanel(controller);
		jpDataSetPanel = new DataSetPanel(controller);
		jpPlotSettings = new PlotSettingsPanel(controller);
		
		add(jpDataSetPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(jpXmlPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		add(jpPlotPanel, new GridBagConstraints(1, 0, 2, 3, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		add(jpPlotSettings, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
	}
	
	public void update(Observable obs, Object arg) {
		jpPlotPanel.update(obs, arg);
		jpDataSetPanel.update(obs, arg);
		jpPlotSettings.update(obs, arg);
		repaint();
	}

//	public static void main(String args[]) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (Exception exception) {
//					exception.printStackTrace();
//				}
//				new TopView();
//			}
//		});
//	}
}
