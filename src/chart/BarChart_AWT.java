package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import puppynux.rg.AI.AgentLoader;
import puppynux.rg.AI.QMatrix;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;

/**
 * Created by niamor on 5/15/16.
 */
public class BarChart_AWT extends JDialog {

    public BarChart_AWT(String applicationTitle, HashMap<String, QMatrix> memory) {
        super();
        setTitle(applicationTitle);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("test closing barchart");
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.err.println("test barchart closed");
            }
        });
        JPanel panel = new JPanel();
        for (HashMap.Entry entry :
                memory.entrySet()) {
            JFreeChart barChart = ChartFactory.createBarChart(
                    (String)entry.getKey(),
                    "State",
                    "Reward",
                    createDataset((QMatrix)entry.getValue()),
                    PlotOrientation.VERTICAL,
                    true, true, false);
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            panel.add(chartPanel);
        }

        setContentPane(panel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    public static CategoryDataset createDataset(QMatrix Q) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < 16; i++) {
            HashMap<puppynux.rg.AI.actions.Action, Double> map = Q.getStateActions(i);
            if (map == null || map.isEmpty()) {
                continue;
            }
            for (HashMap.Entry<puppynux.rg.AI.actions.Action, Double> entry :
                    map.entrySet()) {
                if (entry.getValue() == 0) {
                    continue;
                }
                dataset.addValue(entry.getValue(), entry.getKey().toString(), String.valueOf(i));
            }
        }

        return dataset;
    }
    public static void main(String[] args) {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("src/resources/backup/test.dat"))));
            AgentLoader loader = (AgentLoader) ois.readObject();

            BarChart_AWT chart = new BarChart_AWT("Car Usage Statistics",
                    loader.getMemory());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}