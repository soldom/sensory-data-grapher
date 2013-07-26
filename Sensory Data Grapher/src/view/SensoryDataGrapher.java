package view;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.Controller;
import db.DataSetEntriesDAO;
import db.DataSetEntriesDAOtxtFile;
import db.GrapherPlotDAO;
import db.GrapherPlotDAOxml;
import model.Model;

public class SensoryDataGrapher extends JFrame {

	private static final long serialVersionUID = 1L;
	private GrapherPlotDAO grapherPlotDao = new GrapherPlotDAOxml();
	private DataSetEntriesDAO dataSetEntriesDao = new DataSetEntriesDAOtxtFile();
	private Controller controller = new Controller();
	private Model model = new Model();
	private TopView topView = new TopView(controller);


	public void init() {
		controller.setModel(model);
		controller.setDAO(grapherPlotDao, dataSetEntriesDao);
		model.addObserver(topView);
		getContentPane().setLayout(new GridBagLayout());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				SensoryDataGrapher frame = new SensoryDataGrapher();
				frame.init();
			}
		});

	}

}
