
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public final class Methods {
    //created as a static to be used in static main of DSSIM.
    static XYSeriesCollection data;
    //now this public method has all the essential arguments needed. at least at these early stages
    public Methods(double stock1, double stock2, double stock3, double t0, double tf, double step, String choice) {
        
        //double [] y0 = new double[]{stock1, stock2, stock3};
        
        //data still works the same. or it should be. 
        //data = rk4(t0,tf,step,y0);
        double [] y0 = new double[]{stock1, stock2, stock3};
        if("rk4".equals(choice)){
            data = rk4(t0,tf,step,y0);
            
        }
        else if("rk2".equals(choice)){
            data = rk2(t0,tf,step,y0);
        }
        else{
            
            //eulers
            data = eulers(t0,tf,step,y0);
        }
    }
    //dont know what this is set up for -Taylor
    //public void ChooseMethod(double stock1, double stock2, double stock3, double t0, double tf, double step, String choice){
        //double [] y0 = new double[]{stock1, stock2, stock3};
        //if("rk4".equals(choice)){
            //data = rk4(t0,tf,step,y0);
            
        //}
        //else if("rk2".equals(choice)){
            //data = rk2(t0,tf,step,y0);
        //}
        //else{
            //this is where you would put the option for euler's method
            //eulers not implemented yet.
            //data = eulers(t0,tf,step,y0);
        //}
   //}
    public XYSeriesCollection returnData(){
        return data;
    }
    //anything below this line I didn't see a reason to change. And having a seperate class
    //for this method would make everything a headache. 
    public XYSeriesCollection rk4 (double t0, double tF, double step, double[] y0){
        
        double numSteps = (tF-t0)/step;
        double t = t0;
        double[] tempy = new double[3];
        double[] dydt = new double[3];
        double[] k1 = new double[3];
        double[] k2 = new double[3];
        double[] k3 = new double[3];
        double[] k4 = new double[3];

        final XYSeries series = new XYSeries("Susceptable");
        final XYSeries series2 = new XYSeries("Infected");
        final XYSeries series3 = new XYSeries("Recoverd");
        final XYSeriesCollection data = new XYSeriesCollection();
        
        final WriteFile file = new WriteFile("C:\\Users\\TJ\\Documents\\test.txt", true);
        try{ file.writeToFile("Time;" + "Y Values" ); } catch(Exception e){}
        
        series.add(0, y0[0]);
        series2.add(0, y0[1]);
        series3.add(0, y0[2]);
          
        


             //now you should save or write y[i] (i=0,1,2,3) to file. Next iteration of for loop will
             //overwrite it.
              
        for(int n = 0; n < numSteps; n++){
             t = t0 + (n+1)*step;

             //Let's find k1:
             dydt = rhs(t,y0);
             for(int i=0; i<3; i++){
               k1[i] = step*dydt[i];
             }

             //next let's find k2:
             for(int i=0;i<3;i++)
               tempy[i] = y0[i] + k1[i]/2;
             dydt = rhs(t+step/2, tempy);
             for(int i=0; i<3; i++){
               k2[i] = step*dydt[i];
             }

             //next let's find k3:
             for(int i=0;i<3;i++)
               tempy[i] = y0[i] + k2[i]/2;
             dydt = rhs(t+step/2, tempy);
             for(int i=0; i<3; i++){
               k3[i] = step*dydt[i];
             }

             //next let's find k4:
             for(int i=0;i<3;i++)
               tempy[i] = y0[i] + k3[i];
             dydt = rhs(t+step, tempy);
             for(int i=0; i<3; i++){
               k4[i] = step*dydt[i];
             }

             //now we update y
             for(int i=0; i<3; i++)
               y0[i] = y0[i] + (k1[i] + 2*k2[i] + 2*k3[i] + k4[i])/6;

    
             //System.out.println(y0[0] + " " + y0[1] + " " + y0[2]);
             int x = n+1;
             series.add(x, y0[0]);
             series2.add(x, y0[1]);
             series3.add(x, y0[2]);
             
             try{
             file.writeToFile(x + ";" + y0[0]);
             file.writeToFile(x + ";" + y0[1]);
             file.writeToFile(x + ";" + y0[2]);
             }catch(Exception e){ }
             
             
        }
        data.addSeries(series);
        data.addSeries(series2);
        data.addSeries(series3);
        
        return data;   
    }

    public XYSeriesCollection rk2 (double t0, double tF, double step, double[] y0){
        
        double numSteps = (tF-t0)/step;
        double t = t0;
        double[] tempy = new double[3];
        double[] dydt = new double[3];
        double[] k1 = new double[3];
        double[] k2 = new double[3];

        final XYSeries series = new XYSeries("Susceptable");
        final XYSeries series2 = new XYSeries("Infected");
        final XYSeries series3 = new XYSeries("Recoverd");
        final XYSeriesCollection data = new XYSeriesCollection();
        
        final WriteFile file = new WriteFile("C:\\Users\\TJ\\Documents\\test.txt", true);
        try{ file.writeToFile("Time;" + "Y Values" ); } catch(Exception e){}
        
        series.add(0, y0[0]);
        series2.add(0, y0[1]);
        series3.add(0, y0[2]);
          
        


             //now you should save or write y[i] (i=0,1,2,3) to file. Next iteration of for loop will
             //overwrite it.
         for(int n = 0; n < numSteps; n++){
             t = t0 + (n+1)*step;

             //Let's find k1:
             dydt = rhs(t,y0);
		//the i is the length of the array list that could be used. Instead of hardcoded number of stocks	
             for(int i=0; i<3; i++){
               k1[i] = step*dydt[i];
             }

             //next let's find k2:
             for(int i=0;i<3;i++)
               tempy[i] = y0[i] + k1[i];
             dydt = rhs(t+step, tempy);
             for(int i=0; i<3; i++){
               k2[i] = step*dydt[i];
             }

             //now we update y
             for(int i=0; i<3; i++)
               y0[i] = y0[i] + (k1[i] + k2[i] )/2;
    
             //System.out.println(y0[0] + " " + y0[1] + " " + y0[2]);
             int x = n+1;
             series.add(x, y0[0]);
             series2.add(x, y0[1]);
             series3.add(x, y0[2]);
             
             try{
             file.writeToFile(x + ";" + y0[0]);
             file.writeToFile(x + ";" + y0[1]);
             file.writeToFile(x + ";" + y0[2]);
             }catch(Exception e){ }
             
             
        }
        data.addSeries(series);
        data.addSeries(series2);
        data.addSeries(series3);
        
        return data;   
    }
    public XYSeriesCollection eulers (double t0, double tF, double step, double[] y0){
        
        double numSteps = (tF-t0)/step;
        double t = t0;
        double[] tempy = new double[3];
        double[] dydt = new double[3];
        double[] k1 = new double[3];
        double[] k2 = new double[3];

        final XYSeries series = new XYSeries("Susceptable");
        final XYSeries series2 = new XYSeries("Infected");
        final XYSeries series3 = new XYSeries("Recoverd");
        final XYSeriesCollection data = new XYSeriesCollection();
        
        final WriteFile file = new WriteFile("C:\\Users\\TJ\\Documents\\test.txt", true);
        try{ file.writeToFile("Time;" + "Y Values" ); } catch(Exception e){}
        
        series.add(0, y0[0]);
        series2.add(0, y0[1]);
        series3.add(0, y0[2]);
          
        


             //now you should save or write y[i] (i=0,1,2,3) to file. Next iteration of for loop will
             //overwrite it.
         for(int n = 0; n < numSteps; n++){
             t = t0 + (n+1)*step;

             //Let's find k1:
             dydt = rhs(t,y0);
		//the i is the length of the array list that could be used. Instead of hardcoded number of stocks	
             for(int i=0; i<3; i++){
               k1[i] = step*dydt[i];
             }

            

             //now we update y
             for(int i=0; i<3; i++)
               y0[i] = y0[i] + k1[i];
    
             //System.out.println(y0[0] + " " + y0[1] + " " + y0[2]);
             int x = n+1;
             series.add(x, y0[0]);
             series2.add(x, y0[1]);
             series3.add(x, y0[2]);
             
             try{
             file.writeToFile(x + ";" + y0[0]);
             file.writeToFile(x + ";" + y0[1]);
             file.writeToFile(x + ";" + y0[2]);
             }catch(Exception e){ }
             
             
        }
        data.addSeries(series);
        data.addSeries(series2);
        data.addSeries(series3);
        
        return data;   
    }

    double[] rhs(double t, double[] y){

      double[] ret = new double[3];

      ret[0] = -0.002*y[0]*y[1];
      ret[1] =  0.002*y[0]*y[1] - 0.5*y[1];
      ret[2] = 0.5*y[1];

      return ret;
    } 
}
