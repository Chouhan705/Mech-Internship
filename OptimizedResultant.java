import java.util.*;
import java.lang.*;
import java.text.*;

class OptimizedResultant
{
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Choose the operation to perform") ;
        System.out.println("1. Basic Resultant") ;
        System.out.println("2. One Unknown Foce") ;
        System.out.println("3. Two Unknown Forces") ;
        int choice = sc.nextInt() ;
        switch(choice)
        {
            case 1:
                getResultant() ;
                break ;
            case 2:
                getOneUnknownFroce () ;
                break ;
            case 3:
                getTwoUnknownForces() ;
                break ;
            default:
                System.out.println("Invalid Choice") ;
                break ;
        }
    }
    public static void getResultant()
    {
        double[] Fvalues = InputForce();
        double FX = Fvalues[0] ;
        double FY = Fvalues[1] ;
        double ResultantMagnitude = Math.hypot(FX , FY) ;
        double ResultantAngle = Math.atan(FY/FX) ;
        if( FX < 0 && FY < 0 )
        {
            ResultantAngle = 0 - ResultantAngle ;
        }
        if(ResultantMagnitude == 0)
        {
            System.out.println("");  
            System.out.println("All the forces cancel-out eachother");
        }
        else
        {
            System.out.println("");
            System.out.print("The Magnitude of the Resultant is : ");
            System.out.format("%.2f" , ResultantMagnitude);
            System.out.println(" Newtons / Kilo Newtons");
            System.out.print("The Angle of the Resultant is : ");
            System.out.format("%.2f" , Math.toDegrees(ResultantAngle));
            System.out.println(" Degrees");
        }
    }
    public static void getOneUnknownFroce()
    {
        double[] Fvalues = InputForce();
        double FX = Fvalues[0] ;
        double FY = Fvalues[1] ;
        double[] ResultantValues = InputResultant();
        double ResultantMagnitude = ResultantValues[0] ;
        double ResultantAngle = ResultantValues[1] ;
        double ResultantX = ResultantMagnitude * Math.cos(ResultantAngle) ;
        double ResultantY = ResultantMagnitude * Math.sin(ResultantAngle) ;
        double UnknownForceMagnitude = Math.hypot(ResultantX - FX , ResultantY - FY) ;
        double UnknownForceAngle = Math.atan((ResultantY - FY)/ (ResultantX - FX)) ;
        System.out.println("The Magnitude of the unknown force is :");
        System.out.format("%.2f" , UnknownForceMagnitude);
        System.out.println();
        System.out.println("The Angle of the unknown force is :");
        System.out.format("%.2f" , Math.toDegrees(UnknownForceAngle));

    }
    public static void getTwoUnknownForces()
    {
        Scanner     sc = new Scanner(System.in);
        double[] Fvalues = InputForce();
        double FX = Fvalues[0] ;
        double FY = Fvalues[1] ;
        double[] ResultantValues = InputResultant();
        double ResultantMagnitude = ResultantValues[0] ;
        double ResultantAngle = ResultantValues[1] ;
        double ResultantX = ResultantMagnitude * Math.cos(ResultantAngle) ;
        double ResultantY = ResultantMagnitude * Math.sin(ResultantAngle) ;
        System.out.println("Enter the information of the unknown forces") ;
    
        double[] UnknownForce = new double[2] ;
        for( int i = 0 ; i < 2 ; i++)
        {
            System.out.println("Enter the angle of force" + (i+1));
            UnknownForce[i] = sc.nextDouble() ;
        }
        double UnknownForceAngleA=0 , UnknownForceAngleB=0 ;
        int counter =0;
        int axis=0 ;
        for(int i = 0 ; i< 2 ;i++)
        {
            if(UnknownForce[i] == 0 ||  UnknownForce[i] == 180 )
            {
                UnknownForceAngleA = Math.toRadians(UnknownForce[i]) ;
                axis = 1;
            }
            if( UnknownForce[i] == 90 || UnknownForce[i] == 270 )
            {
                UnknownForceAngleA = Math.toRadians(UnknownForce[i]) ;
                axis = 2;
            }
            else
            {
                UnknownForceAngleB = Math.toRadians(UnknownForce[i]) ;
                counter++;
            }
        }
        if (counter > 2)
        {
            System.out.println("Cannot calculate for this specific case") ;
        }
        else
        {
            if ( axis ==1 )
            {
                double UnknownForceB = (ResultantY - FY)/Math.sin(UnknownForceAngleB) ;
                double UnknownForceA = (ResultantX - (FX+UnknownForceB*Math.cos(UnknownForceAngleB)))/Math.cos(UnknownForceAngleA) ;
                System.out.println("The unknown forces are :");
                System.out.format("Force A : %.2f" , UnknownForceA ,"with angle %.2f ", UnknownForceAngleA);
                System.out.format("Force B : %.2f" , UnknownForceB, "with angle %.2f ", UnknownForceAngleB);
            }
            if ( axis == 2)

            {
                double UnknownForceB = (ResultantX - FX)/Math.cos(UnknownForceAngleB) ;
                double UnknownForceA = (ResultantY - (FY+UnknownForceB*Math.sin(UnknownForceAngleB)))/Math.sin(UnknownForceAngleA) ;
                System.out.println("The unknown forces are :");
                System.out.format("Force A : %.2f " , UnknownForceA ) ;
                System.out.format("with angle %.2f ", Math.toDegrees(UnknownForceAngleA)) ;
                System.out.println("") ;
                System.out.format("Force B : %.2f " , UnknownForceB );
                System.out.format("with angle %.2f ", Math.toDegrees(UnknownForceAngleB));
            }
        }

    }
    public static double[] InputForce()
    {
        
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the number of known forces :");
        int n = sc.nextInt() ;
        int[] forces = new int[n];
        double FX=0 , FY=0 ;
        double angle =0 ;
        for( int i = 0 ; i < n ; i++)
        {
            System.out.println("\033[H\033[2J");
            System.out.println("Enter the information of force" + (i+1));
            int quadrant = InputQuadrant();
            double magnitude = InputMagnitude();            
            System.out.println("Select A for angle or S for slope");
            char input = sc.next().charAt(0);
            switch (input)
            {
                case 'A':
                    angle = InputAngle(quadrant);
                    break;
                case 'S':
                    angle = InputSlope(quadrant);
                    break;
                case 'a':
                    angle = InputAngle(quadrant);
                    break;
                case 's':
                    angle = InputSlope(quadrant);
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            FX += CalcFX(magnitude , angle);
            FY += CalcFY(magnitude , angle);
        }
        double[] Fvalues = new double[2];
        Fvalues[0] = FX ;
        Fvalues[1] = FY ;
        return Fvalues ;
    }
    public static int InputQuadrant()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Select the quadrant the force is present in :");
        System.out.println("1. Quadrant 1") ;
        System.out.println("2. Quadrant 2") ;
        System.out.println("3. Quadrant 3") ;
        System.out.println("4. Quadrant 4") ;
        int quadrant = sc.nextInt() ;
        return quadrant ;
    }
    public static double InputMagnitude()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter ther magnitude of force :");
        double magnitude = sc.nextDouble() ;
        return magnitude ;
    }
    public static double InputAngle(int quadrant)
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter angle between the force and the respective X-Axis :");
        double angle = sc.nextDouble() ;
        if(quadrant == 1)
        {
            angle = Math.toRadians(angle) ;
        }
        if(quadrant == 2)
        {
            angle = Math.PI-Math.toRadians(angle) ;
        }
        if(quadrant == 3)
        {
            angle = Math.PI+Math.toRadians(angle) ;
        }
        if(quadrant == 4)
        {
            angle = (2*Math.PI)-Math.toRadians(angle) ;
        }
        return angle ;
    }
    public static double InputSlope(int quadrant)
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter the horizontal length for the slope :");
        double horizontal = sc.nextDouble() ;
        System.out.println("Enter the vertical length for the slope :");
        double vertical = sc.nextDouble() ;

        double angle = Math.atan(vertical/horizontal) ;
        if(quadrant == 1)
        {
            angle = (angle) ;
        }
        if(quadrant == 2)
        {
            angle = Math.PI - (angle) ;
        }
        if(quadrant == 3)
        {
            angle = Math.PI + (angle) ;
        }
        if(quadrant == 4)
        {
            angle = (2*Math.PI) - (angle) ;
        }
        return angle ;
    }
    public static double[] InputResultant()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the Magnitude of the resultant :");
        double ResultantMagnitude = sc.nextDouble() ;
        int quadrant = InputQuadrant();
        System.out.println("Select A for angle or S for slope");
        char input = sc.next().charAt(0);
        double ResultantAngle = 0 ;
        switch (input)
        {
            case 'A':
                ResultantAngle = InputAngle(quadrant);
                break;
            case 'S':
                ResultantAngle = InputSlope(quadrant);
                break;                
            case 'a':
                ResultantAngle = InputAngle(quadrant);
                break;
            case 's':
                ResultantAngle = InputSlope(quadrant);
                 break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        double[] ResultantValues = new double[2];
        ResultantValues[0] = ResultantMagnitude ;
        ResultantValues[1] = ResultantAngle ;
        return ResultantValues ;
    }
    public static double CalcFX(double magnitude , double angle)
    {
        double FX = magnitude*Math.cos(angle);
        return FX ;
    }
    public static double CalcFY(double magnitude , double angle)
    {
        double FY = magnitude*Math.sin(angle);
        return FY ;
    }    

}