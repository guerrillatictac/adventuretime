
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.data.xy.XYDatasetTableModel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lord Commander
 */
public class Windows {
    String graphTitle;
    String XTitle;
    String YTitle;
    String choice;
    String viewChoice;
    double t0;
    double tf;
    double step;
    int option;
    
    public Windows(){
    
//        JTextField graphTitleField = new JTextField(25);
//        JTextField XTitleField = new JTextField(25);
//        JTextField YTitleField = new JTextField(25);
//        JPanel graphLabels = new JPanel();
//        graphLabels.setLayout(new BoxLayout(graphLabels, BoxLayout.PAGE_AXIS));
//        graphLabels.add(new JLabel("Please enter the graph's title."));
//        graphLabels.add(graphTitleField);
//        graphLabels.add(new JLabel("Please enter the X axis title."));
//        graphLabels.add(XTitleField);
//        graphLabels.add(new JLabel("Please enter the Y axis title."));
//        graphLabels.add(YTitleField);
//        JOptionPane.showConfirmDialog(null, graphLabels);
//        graphTitle = graphTitleField.getText();
//        XTitle = XTitleField.getText();
//        YTitle = YTitleField.getText();

//        JTextField timeZeroField = new JTextField(25);
//        JTextField timeFinalField = new JTextField(25);
//        JTextField timeStepField = new JTextField(25);
//        JPanel timeVariables = new JPanel();
//        timeVariables.setLayout(new BoxLayout(timeVariables, BoxLayout.PAGE_AXIS));
//        timeVariables.add(new JLabel("Please enter the Start time, or Time Zero."));
//        timeVariables.add(timeZeroField);
//        timeVariables.add(new JLabel("Please enter the EndTime, or Time Final."));
//        timeVariables.add(timeFinalField);
//        timeVariables.add(new JLabel("Please enter the Time Step."));
//        timeVariables.add(timeStepField);
//        //here is where I was mentioning that showconfirmDialog returns an int. 
//        int option = JOptionPane.showConfirmDialog(null, timeVariables);
//        String sTimeZero =timeZeroField.getText();
//        String sTimeFinal=timeFinalField.getText();
//        String sTimeStep=timeStepField.getText();
//        t0 = Double.parseDouble(sTimeZero);
//        tf = Double.parseDouble(sTimeFinal);
//        step = Double.parseDouble(sTimeStep);
        
//        JRadioButton rk4Button = new JRadioButton("Runge-Kutta Fourth Order");
//        JRadioButton rk2Button = new JRadioButton("Runge-Kutta Second Order");
//        JRadioButton eulersButton = new JRadioButton("Euler's Method");
//        JPanel numericalMethod = new JPanel();
//        numericalMethod.setLayout(new BoxLayout(numericalMethod, BoxLayout.PAGE_AXIS));
//        numericalMethod.add(rk4Button);
//        numericalMethod.add(rk2Button);
//        numericalMethod.add(eulersButton);
//        JOptionPane.showConfirmDialog(null, numericalMethod);
//        if(rk4Button.isSelected()){
//            choice = "rk4";
//        }
//        if(rk2Button.isSelected()){
//            choice = "rk2";
//        }
//        if(eulersButton.isSelected()){
//            choice = "eulers";
//        }
        
        
        
    }
    
    public void methodChoice(){
        JRadioButton rk4Button = new JRadioButton("Runge-Kutta Fourth Order");
        JRadioButton rk2Button = new JRadioButton("Runge-Kutta Second Order");
        JRadioButton eulersButton = new JRadioButton("Euler's Method");
        JPanel numericalMethod = new JPanel();
        numericalMethod.setLayout(new BoxLayout(numericalMethod, BoxLayout.PAGE_AXIS));
        numericalMethod.add(rk4Button);
        numericalMethod.add(rk2Button);
        numericalMethod.add(eulersButton);
        JOptionPane.showConfirmDialog(null, numericalMethod);
        if(rk4Button.isSelected()){
            choice = "rk4";
        }
        if(rk2Button.isSelected()){
            choice = "rk2";
        }
        if(eulersButton.isSelected()){
            choice = "eulers";
        }
    }
    
    public void initialVariables(){
        JTextField timeZeroField = new JTextField(25);
        JTextField timeFinalField = new JTextField(25);
        JTextField timeStepField = new JTextField(25);
        JPanel timeVariables = new JPanel();
        timeVariables.setLayout(new BoxLayout(timeVariables, BoxLayout.PAGE_AXIS));
        timeVariables.add(new JLabel("Please enter the Start time, or Time Zero."));
        timeVariables.add(timeZeroField);
        timeVariables.add(new JLabel("Please enter the EndTime, or Time Final."));
        timeVariables.add(timeFinalField);
        timeVariables.add(new JLabel("Please enter the Time Step."));
        timeVariables.add(timeStepField);
        //here is where I was mentioning that showconfirmDialog returns an int. 
        int option = JOptionPane.showConfirmDialog(null, timeVariables);
        String sTimeZero =timeZeroField.getText();
        String sTimeFinal=timeFinalField.getText();
        String sTimeStep=timeStepField.getText();
        t0 = Double.parseDouble(sTimeZero);
        tf = Double.parseDouble(sTimeFinal);
        step = Double.parseDouble(sTimeStep);
        
    }
    
     public void graphLabels(){
        JTextField graphTitleField = new JTextField(25);
        JTextField XTitleField = new JTextField(25);
        JTextField YTitleField = new JTextField(25);
        JPanel graphLabels = new JPanel();
        graphLabels.setLayout(new BoxLayout(graphLabels, BoxLayout.PAGE_AXIS));
        graphLabels.add(new JLabel("Please enter the graph's title."));
        graphLabels.add(graphTitleField);
        graphLabels.add(new JLabel("Please enter the X axis title."));
        graphLabels.add(XTitleField);
        graphLabels.add(new JLabel("Please enter the Y axis title."));
        graphLabels.add(YTitleField);
        JOptionPane.showConfirmDialog(null, graphLabels);
        graphTitle = graphTitleField.getText();
        XTitle = XTitleField.getText();
        YTitle = YTitleField.getText();
     }
    
//    public void graphOrChart(){
//        JButton graphChoice = new JButton("Graph");
//        JButton tableChoice = new JButton("Table");
//        JPanel view = new JPanel();
//        view.add(new JLabel("Would you like to see data in a table or graph?"));
//        view.add(graphChoice);
//        view.add(tableChoice);
//        JFrame frame = new JFrame("Table");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setContentPane(view);
//        frame.setSize(350,100);
//        frame.setVisible(true);
//        
//        graphChoice.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//             viewChoice = "chart";
//         }          
//      });
//        
//        tableChoice.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//             viewChoice = "table";
//         }          
//      });
//    }
     
    public void graphOrChart(){
        final DefaultComboBoxModel choices = new DefaultComboBoxModel();
        choices.addElement("Chart");
        choices.addElement("Table");
        final JComboBox choiceCombo = new JComboBox(choices);    
        choiceCombo.setSelectedIndex(0);
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Please choose how you want to view the data    "));
        controlPanel.add(choiceCombo);
        JOptionPane.showConfirmDialog(null, controlPanel);
        if (choiceCombo.getSelectedIndex() == 0) {
            viewChoice = "chart";
        }else{
            viewChoice = "table";
        }
    }
    
    public String returnGraphTitle(){
        return graphTitle;
    }
    public String viewChoice(){
        return viewChoice;
    }
    public String returnXTitle(){
        return XTitle;
    }
    public String returnYTitle(){
        return YTitle;
    }
    public String returnMethodChoice(){
        return choice;
    }
}
