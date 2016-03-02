

/*
 * The MIT License
 *
 * Copyright 2016 Lord Commander.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author Lord Commander
 */

import org.jfree.data.xy.XYDatasetTableModel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.io.*;


public class DSSIM {
    public static void main(String[] args) {
        //now the initial stock values are outside they can be changed. Here they are set 
        //to the default we've played with before
        double inistock1 = 750;
        double inistock2 = 1;
        double inistock3 = 0;
        //window is made from the window class in the project. It runs the windows you created.
        //there are return methods for specific things but they may be redundant.
        Windows window = new Windows();
        window.graphOrChart();
        String viewChoice = window.viewChoice();
        window.graphLabels();
        String XTitle = window.returnXTitle();
        String YTitle = window.returnYTitle();
        String graphTitle = window.returnGraphTitle();
        window.initialVariables();
        window.methodChoice();
        String choice = window.returnMethodChoice();
        //you had dialogue boxes using "showConfirmDialog". This returns an int value
        //which is supposed to be handy for knowing what option was selected. I was guessing
        //that 0 would be the yes option. Could be wrong.
        if (window.option == 0){
            //instance of rungekutta to be run if the last dialogue is yes. Now rungekutta
            //has input arguments which will be handy later. 
            Methods rungeKuttaFour = new Methods(inistock1, inistock2, inistock3, window.t0, window.tf, window.step, choice);
            //data is created as an instance here.
            XYSeriesCollection data = rungeKuttaFour.returnData();
            
            if ("chart".equals(viewChoice)){
                final GraphThis graph = new GraphThis("DSSIM Graph", data, graphTitle, XTitle, YTitle);
                graph.pack();
                RefineryUtilities.centerFrameOnScreen(graph);
                graph.setVisible(true);
            }
            if("table".equals(viewChoice)){
                InsertFileDataToJTable blah = new InsertFileDataToJTable();
                blah.readFileToTable();
            }
        }        
    }
}
