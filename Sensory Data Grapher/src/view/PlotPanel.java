package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.LineType;
import model.Model;
import model.DataSetEntry;

public class PlotPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	int[] data = { 25, 60, 42, 75 };
	final int PAD = 20;
	private List<DataSetEntry> entries = null;

	private double xmin = 1, xmax = 1, ymin = 1, ymax = 1;
	private LineType lineType;
	private Color colorDataSet1;
	private List<DataSetEntry> entries2;
	private LineType lineType2;
	private Color colorDataSet2;

	public PlotPanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED, Color.white, new Color(165, 163, 151)),
				" Graphical View "));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		g2.drawLine(PAD, PAD, PAD, h - PAD);
		g2.drawLine(PAD, h - PAD, w - PAD, h - PAD);
		double xScale = (w - 2 * PAD) / (xmax - xmin);
		double yScale = (h - 2 * PAD) / (ymax - ymin);
		// The origin location.
		double x0 = PAD;
		double y0 = h - PAD;

		g2.setPaint(colorDataSet1);

		if (entries != null) {
			Iterator<DataSetEntry> iterator = entries.iterator();
			double xold = x0;
			double yold = y0;
			boolean firstDraw = true;

			while (iterator.hasNext()) {
				DataSetEntry entry = iterator.next();
				double xnow = (xScale * (entry.getX() - xmin));
				double ynow = (yScale * (entry.getY() - ymin));
				if ((entry.getX() > xmin) && (entry.getX() < xmax)
						&& (entry.getY() > ymin) && (entry.getY() < ymax)) {
					if (firstDraw) {
						firstDraw = false;
					} else {
						switch (lineType) {
						case DOTTED:
							g2.fillOval((int) (x0 + xnow - 2),
									(int) (y0 - ynow - 2), 4, 4);
							break;
						case LINE:
							g2.drawLine((int) (x0 + xold), (int) (y0 - yold),
									(int) (x0 + xnow), (int) (y0 - ynow));
							break;
						}
					}
				}

				xold = xnow;
				yold = ynow;
			}
		}

		g2.setPaint(colorDataSet2);

		if (entries2 != null) {
			Iterator<DataSetEntry> iterator = entries2.iterator();
			double xold = x0;
			double yold = y0;
			boolean firstDraw = true;

			while (iterator.hasNext()) {
				DataSetEntry entry = iterator.next();
				double xnow = (xScale * (entry.getX() - xmin));
				double ynow = (yScale * (entry.getY() - ymin));
				if ((entry.getX() > xmin) && (entry.getX() < xmax)
						&& (entry.getY() > ymin) && (entry.getY() < ymax)) {
					if (firstDraw) {
						firstDraw = false;
					} else {
						switch (lineType2) {
						case DOTTED:
							g2.fillOval((int) (x0 + xnow - 2),
									(int) (y0 - ynow - 2), 4, 4);
							break;
						case LINE:
							g2.drawLine((int) (x0 + xold), (int) (y0 - yold),
									(int) (x0 + xnow), (int) (y0 - ynow));
							break;
						}
					}
				}

				xold = xnow;
				yold = ynow;
			}
		}
	}

	public void update(Observable obs, Object arg) {
		Model model = (Model) obs;
		if (model.getXmax() != null) {
			xmin = model.getXmin().doubleValue();
			xmax = model.getXmax().doubleValue();
			ymin = model.getYmin().doubleValue();
			ymax = model.getYmax().doubleValue();
		}

		if (model.getDataSet1() != null) {
			if (model.getDataSet1().getEntries() != null) {
				entries = ((Model) obs).getDataSet1().getEntries();

			}
			if (model.getDataSet1().getConfig() != null) {
				lineType = model.getDataSet1().getConfig().getLineType();
				colorDataSet1 = model.getDataSet1().getConfig().getLineColor()
						.toColor();
			}
		}

		if (model.getDataSet2() != null) {
			if (model.getDataSet2().getEntries() != null) {
				entries2 = ((Model) obs).getDataSet2().getEntries();

			}
			if (model.getDataSet2().getConfig() != null) {
				lineType2 = model.getDataSet2().getConfig().getLineType();
				colorDataSet2 = model.getDataSet2().getConfig().getLineColor()
						.toColor();
			}
		}

	}
}
