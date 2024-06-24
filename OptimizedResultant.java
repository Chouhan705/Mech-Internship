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
        System.out.println("4. Lami");
        System.out.println("5. Moment of Parallel Force System");
        System.out.println("6. Moment of General force System on a Beam") ;
        System.out.println("7. Moment of General force System on a Plane") ;
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
            case 4:
                Lami();
                break ;
            case 5:
                MomentParallel();
                break ;
            case 6:
                MomentBeamGeneral();
                break ;
            case 7:
                MomentGeneralPlane();
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
        double[] Fvalues = InputForce();
        double FX = Fvalues[0] ;
        double FY = Fvalues[1] ;
        double[] ResultantValues = InputResultant();
        double ResultantMagnitude = ResultantValues[0] ;
        double ResultantAngle = ResultantValues[1] ;
        double ResultantX = ResultantMagnitude * Math.cos(ResultantAngle) ;
        double ResultantY = ResultantMagnitude * Math.sin(ResultantAngle) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the information of the unknown forces") ;
        System.out.println("FORCE 1");
        int Quadrant1 = InputQuadrant();
        double angle1 = InputAngle(Quadrant1);
        System.out.println("\033[H\033[2J");
        System.out.println("FORCE 2");
        int Quadrant2 = InputQuadrant();
        double angle2 = InputAngle(Quadrant2);
        double CoefX1 = Math.cos(angle1);
        double CoefY1 = Math.cos(angle2);
        double CoefX2 = Math.sin(angle1);
        double CoefY2 = Math.sin(angle2);
        double Const1 = ResultantX - FX;
        double Const2 = ResultantY - FY;
        double[] SimulSolution = SimultaneusEqnSolver(CoefX1, CoefY1, Const1, CoefX2, CoefY2, Const2);
        double UnknownForce1 = SimulSolution[0];
        double UnknownForce2 = SimulSolution[1];

        System.out.println("\033[H\033[2J");
        System.out.println("The value of Unknown Force 1 is :");
        System.out.format("%.2f" , UnknownForce1);
        System.out.println();
        System.out.println("With angle : "+ Math.round(Math.toDegrees(angle1))+" From positive X-Axis");
        System.out.println();
        System.out.println("The value of Unknown Force 2 is :");
        System.out.format("%.2f" , UnknownForce2);
        System.out.println();
        System.out.println("With angle : "+ Math.round(Math.toDegrees(angle2))+" From positive X-Axis");

    }
    public static double[] InputForce()
    {
        
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the number of known forces :");
        int n = sc.nextInt() ;
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
            //angle = (angle) ;
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
    public static void Lami()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Magnitude of the known force");
        double  knownForceMagnitude = sc.nextDouble();
        System.out.println("Enter the Angle opposite to the known force");
        double  knownForceAngle = Math.toRadians(sc.nextDouble());
        System.out.println("Enter the Angle opposite to the unknown force 1");
        double  unknownAngle1 = Math.toRadians(sc.nextDouble());
        System.out.println("Enter the Angle opposite to the unknown force 2");
        double unknownAngle2 = Math.toRadians(sc.nextDouble());
        if(knownForceAngle+unknownAngle1+unknownAngle2!= (2*Math.PI))
        {
            System.out.println("The sum of the angles is not equal to 360 degrees");
            System.out.println("Re-check the angles entered");
            return ;
        }
        double unknownForce1Magnitude = (knownForceMagnitude*Math.sin(unknownAngle1))/Math.sin(knownForceAngle);
        double unknownForce2Magnitude = (knownForceMagnitude*Math.sin(unknownAngle2))/Math.sin(knownForceAngle);
        System.out.println("The unknown force 1 is :");
        System.out.format("%.2f", unknownForce1Magnitude);
        System.out.println("");
        System.out.println("The unknown force 2 is :");
        System.out.format("%.2f", unknownForce2Magnitude);

    }
    public static void MomentGeneralPlane()
    {

    }
    public static void MomentParallel()
    {
        Scanner sc = new Scanner(System.in);
        double[] Finalvalues = InputMoment();
        double FY = Finalvalues[1];
        double MomentDueToForce = Finalvalues[2];
        System.out.println("\033[H\033[2J");
        System.out.print("Enter 1 if extra moments are present else enter 0: ");
        int e = sc.nextInt();
        double ExtraMoment = 0 ;
        if (e == 1) 
        {
            ExtraMoment = InputExtraMoments();
        }

        double TotalMoment = MomentDueToForce + ExtraMoment ;

        System.out.printf("Resultant force is %.2f\n", FY);
        System.out.printf("Total moment is %.2f\n", TotalMoment);

        if (FY == 0) {
            System.out.println("No force is present, only moment is there");
        } else {
            System.out.printf("Distance of the force from reference point is %.2f\n", TotalMoment / FY);
        }

    }
    public static void MomentBeamGeneral()
    {
        Scanner sc = new Scanner(System.in);
        double[] values = InputMoment();
        double FX = values[0];
        double FY = values[1];
        double MomentDueToForce = values[2];
        System.out.println("If extra moments are present Enter 1 \nElse 0");
        int choice = sc.nextInt();
        double ExtraMoment = 0 ;
        if(choice == 1)
        {
            ExtraMoment = InputExtraMoments();
        }
        double TotalMoment = MomentDueToForce + ExtraMoment ;
        double Resultant = Math.hypot(FX , FY);
        double Distance = TotalMoment/Resultant ;
        if(Resultant == 0)
        {
            System.out.println("No force is present, only moment is there");
            System.out.printf("Total moment is %.2f\n", TotalMoment);
        }
        else
        {
        System.out.printf("Resultant force is %.2f\n", Resultant);
        System.out.printf("The distance of the force from refrence point is %.2f\n", Distance);
        System.out.printf("Total moment is %.2f\n", TotalMoment);
        }
    }
    public static double[] InputMoment()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the number of known forces :");
        int n = sc.nextInt() ;
        double FX=0 , FY=0 ;
        double Moment = 0 ;
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

            System.out.println("Enter the distance from the refrence point");
            double distance = sc.nextDouble() ;
            System.out.println("Select 1 for Moment acting Anticlockwise or 2 for Clockwise");
            int direction = sc.nextInt() ;
            Moment += CalcMoment(magnitude , distance , direction);
        }
        double[] Finalvalues = new double[3];
        Finalvalues[0] = FX ;
        Finalvalues[1] = FY ;
        Finalvalues[2] = Moment ;
        return Finalvalues ;
    }
    public static double InputExtraMoments()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the number of moments :");
        int n = sc.nextInt() ;
        double Moment = 0 ;
        int direction;
        for( int i = 0 ; i < n ; i++)
        {
            System.out.println("Enter the Magnitude of the Moment " + (i+1));
            double magnitude = sc.nextDouble() ;
            System.out.println("Select 1 for Moment acting Anticlockwise or 2 for Clockwise");
            direction = sc.nextInt() ;
            switch(direction)
            {
                case 1:
                Moment += magnitude ;
                break;
                case 2:
                Moment -= magnitude ;
                break;
                default :
                System.out.println("Invalid direction");
                break;
            }
        }
        return Moment ;
    }
    public static double CalcMoment(double magnitude , double distance , int direction)
    {
        if(direction == 1)
        {
            double Moment = magnitude*distance;
            return Moment ;
        }
        if(direction == 2)
        {
            double Moment = -magnitude*distance;
            return Moment ;
        }
        return 0 ;
    }
}
