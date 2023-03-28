import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import CalApp.*;
import CalApp.calculatorPackage.DivisionByZero;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import static java.lang.System.out;

public class CalculatorClient {

    static calculator calImpl;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) {
        double result=0.0,num1=0.0,num2=0.0;

        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "Calculator";
            calImpl = calculatorHelper.narrow(ncRef.resolve_str(name));

            //System.out.println(calculatorImpl);
            int ch=1;

       while(ch!=0)
       {
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("0. Exit");

        BufferedReader in1=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("enter your choice:");
        ch=Integer.parseInt(in1.readLine());
        
        if(ch==0)
          break;

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("enter number1:");
        num1=Double.parseDouble(in.readLine());

        System.out.println("enter number2:");
        num2=Double.parseDouble(in.readLine());

        switch(ch)
        {
          case 1:
              result=calImpl.addition(num1,num2);
              break;

          case 2:
              result=calImpl.subtraction(num1,num2);
              break;

          case 3:
              result=calImpl.multiplication(num1,num2);
              break;

          case 4:
              try {
              result=calImpl.division(num1,num2);
              } catch (DivisionByZero de) {
                out.println("Division by zero!!!");
               }
              break;
        }
              System.out.println("result is:"+result);

        }

      }


            
         catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    //static float getFloat(String number) throws Exception {
       // out.print(number + ": ");
       // return Float.parseFloat(br.readLine());
   // }
}