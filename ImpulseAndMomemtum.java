import java.util.*;

public class ImpulseAndMomentum 
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("KINDLY ENTER THE VALUE OF ALL THE QUANTITIES IN SI UNITS\n");
        System.out.println("Choose the type of impact\n 1.Perfectly Elastic\n 2.Perfectly Plastic");
        int impact = sc.nextInt();
        switch(impact)
        {
            case 1:
            System.out.println("Choose the line of impact");
            System.out.println("1. x-axis");
            System.out.println("2. y-axis");
            int line = sc.nextInt();
            if (line==1)
            {
                LineofImpactisXaxis();
            }
            if (line==2)
            {
                LineofImpactisYaxis();
            }
           
            break;

            case 2:
            System.out.println("Enter m1, m2, u1 & u2:");
            double m1 = sc.nextDouble();
            double m2 = sc.nextDouble();
            double u1 = sc.nextDouble();
            double u2 = sc.nextDouble();
            double v = (m1*u1 + m2*u2)/(m1+m2);
            System.out.println("The common velocity is "+(v)+ " m/s\n");
            System.out.println("Enter 1 if you want to find distance by work-energy principle else enter 0");
            int choice = sc.nextInt();
            if(choice==1)
            {
                System.out.println("Enter mass:");
                double mass = sc.nextDouble();
                System.out.println("Enter frictional constant:");
                double mu = sc.nextDouble();
                double workdone = -mu*mass*9.81;
                double KineticEnergy1 = -0.5*mass*Math.pow(v, 2);                                   //Ke1=0
                double distance = KineticEnergy1/workdone;
                System.out.println("The distance travelled is "+(distance)+ " m"); 
            }
            else
            {
                break;
            }  
        }
    }   
        public static void LineofImpactisXaxis()
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter m1");
            double mass1 = sc.nextDouble();
            System.out.println("Enter m2");
            double mass2 = sc.nextDouble();
            System.out.println("Enter u1(only magnitude)");
            double u1 = sc.nextDouble();
            System.out.println("Enter u2(only magnitude)");
            double u2 = sc.nextDouble();
            System.out.println("Enter the angle of sphere 1 with respect to positive x-axis");
            double angle1 = Math.toRadians(sc.nextDouble());
            System.out.println("Enter the angle of sphere 2 with respect to positive x-axis");
            double angle2 = Math.toRadians(sc.nextDouble());
            System.out.println("Enter Coefficient of restitution");
            double e = sc.nextDouble();
            //M1(U1) +M2(U2) = M1(V1)+M2(V2)
            //E=(V2-V1)/U1-U2

            // M1(V1) + M2(V2) = M1(U1) +M2(U2) 
            //V1 + -V2 = -E(U1-U2)
            double CoefX1 = mass1;
            double CoefY1 = mass2 ;
            double CoefX2 = 1;
            double CoefY2 = -1 ;
            double Const1 = (mass1*(u1*Math.cos(angle1)))+(mass2*(u2*Math.cos(angle2)));
            double Const2 = -(e*((u1*Math.cos(angle1))-(u2*Math.cos(angle2))));
            double[] Answer = SimultaneusEqnSolver(CoefX1,CoefY1,Const1,CoefX2,CoefY2,Const2);
            double v1x = Answer[0];
            double v2x = Answer[1];
            double v1y = u1*Math.sin(angle1);
            double v2y = u2*Math.sin(angle2);
            double v1 = Math.hypot(v1x, v1y);
            double v2 = Math.hypot(v2x, v2y);
            double v1angle = Math.atan(v1y/v1x);
            double v2angle = Math.atan(v2y/v2x);
            System.out.println("V1 is : "+v1+" with an angle of : "+Math.toDegrees(v1angle));
            System.out.println("V2 is : "+v2+" with an angle of : "+Math.toDegrees(v2angle));

            double initialKE = ((0.5*mass1*Math.pow(u1, 2))+(0.5*mass2*Math.pow(u2, 2)));
            double finalKE = ((0.5*mass1*Math.pow(v1, 2))+(0.5*mass2*Math.pow(v2, 2)));
            double percentLossinKE = ((initialKE - finalKE)/initialKE)*100;
            System.out.println("The % loss in KE is "+(percentLossinKE));
        }

        public static void LineofImpactisYaxis()
        {
            double u1;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter 1 if you want to calculate u1 else enter 0");                                                            //usually u2=v2=0 
            int option = sc.nextInt();
            if(option==1)
            {
                System.out.println("Enter the height");
                double h = sc.nextDouble();
                u1 = Math.sqrt(2*9.81*h);
            }
            else
            {
                System.out.println("Enter u1(only magnitude)");
                u1 = sc.nextDouble();
            }
            System.out.println("Enter u2(only magnitude)");
            double u2 = sc.nextDouble();
            System.out.println("Enter Coefficient of restitution");
            double e = sc.nextDouble();
            System.out.println("Enter the angle of velocity with respect to positive x-axis");
            double angle = Math.toRadians(sc.nextDouble());
            double v1y = -e*(u1*Math.sin(angle)-u2);
            double v1x = u1*Math.cos(angle);
            double v1 = Math.hypot(v1x, v1y);
            double v1angle = Math.atan(v1y/v1x);
            System.out.println("V1 is : "+v1+" with an angle of : "+Math.toDegrees(v1angle));
        }
    

    public static double[] SimultaneusEqnSolver(double CoefX1 , double CoefY1 , double Const1 , double CoefX2 , double CoefY2 , double Const2)
    {
        //Coefx1(x) + Coefy1(y) = Const1
        //Coefx2(x) + Coefy2(y) = Const2
        double[] ResultantValues = new double[2];
        double x = (Const1*CoefY2 - Const2*CoefY1)/(CoefX1*CoefY2 - CoefX2*CoefY1);
        double y = (Const1*CoefX2 - Const2*CoefX1)/(CoefX2*CoefY1 - CoefX1*CoefY2);
        ResultantValues[0] = x ;
        ResultantValues[1] = y ;
        return ResultantValues ;
    }
}
