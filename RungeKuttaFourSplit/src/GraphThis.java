
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GraphThis extends ApplicationFrame {
    public GraphThis(final String title, XYSeriesCollection data, String graphTitle, String XTitle, String YTitle) {

        super(title);
        final JFreeChart chart = ChartFactory.createXYLineChart(
            graphTitle,
            XTitle, 
            YTitle, 
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );


        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(700, 470));
        JButton toTable = new JButton("View Table");
        chartPanel.add(toTable);
        setContentPane(chartPanel);
        
        toTable.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            InsertFileDataToJTable blah = new InsertFileDataToJTable();
            blah.readFileToTable();
         }          
      });

    }
}