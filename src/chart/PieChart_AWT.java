package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import puppynux.rg.AI.AgentLoader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niamor on 5/15/16.
 */
public class PieChart_AWT extends JDialog {
    public PieChart_AWT(String title, HashMap<String, Integer> actionMap)
    {
        super();
        setTitle(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("test closing piechart");
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.err.println("test piechart closed");
            }
        });
        JFreeChart chart = ChartFactory.createPieChart(
                "Total actions",  // chart title
                createDataset(actionMap),        // data
                true,           // include legend
                true,
                false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
    public static PieDataset createDataset(HashMap<String, Integer> actionMap)
    {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for (HashMap.Entry<String, Integer> entry :
                actionMap.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        return dataset;
    }

    public static void main(String[ ] args)
    {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("src/resources/backup/test.dat"))));
            AgentLoader loader = (AgentLoader) ois.readObject();

            PieChart_AWT chart = new PieChart_AWT("Mobile Sales", loader.getActionMap());
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
