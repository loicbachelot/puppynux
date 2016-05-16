package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import puppynux.rg.AI.Agent;
import puppynux.rg.AI.Consciousness;
import puppynux.rg.AI.QMatrix;
import puppynux.rg.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

/**
 * Created by niamor on 5/16/16.
 */
public class Charts_AWT extends JDialog {

    public Charts_AWT(String applicationTitle, HashMap<String, QMatrix> memory, HashMap<String, Integer> actionMap) {
        super();
        setTitle(applicationTitle);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("test closing charts");
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.err.println("test charts closed");
            }
        });
        JPanel panel = new JPanel();

        JFreeChart chart = ChartFactory.createPieChart(
                "Total actions",  // chart title
                PieChart_AWT.createDataset(actionMap),        // data
                true,           // include legend
                true,
                false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        panel.add(chartPanel);

        if (GameEngine.getInstance().isLiving()) {
            Consciousness agent = GameEngine.getInstance().getAiManager().getAgent();
            chart = ChartFactory.createBarChart(
                    agent.getSubplacePosition(),
                    "State",
                    "Reward",
                    BarChart_AWT.createDataset(agent.getQ()),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            panel.add(chartPanel);
        }

        setContentPane(panel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}
