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
        System.out.println("2. One Unknown Force") ;
        System.out.println("3. Two Unknown Forces") ;
        System.out.println("4. Lami's Theorem");
        System.out.println("5. Moment of Parallel Force System");
        System.out.println("6. Moment of General force System on a Beam") ;
        System.out.println("7. Moment of General force System on a Plane") ;
        System.out.println("8. Joints and Loads");
        System.out.println("9. Loads Resultant+Moment ");
        int choice = sc.nextInt() ;
        switch(choice)
        {
            case 1:
                getResultant() ;
                break ;
            case 2:
                getOneUnknownForce () ;
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
                case 8:
                Joints();
                break ;
                case 9:
                Loads();
                break;
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
            System.out.println("All the forces cancel-out each other");
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
    public static void getOneUnknownForce()
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
        double[] SimulSolution = simultaneousEqnSolver(CoefX1, CoefY1, Const1, CoefX2, CoefY2, Const2);
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
            System.out.println("Enter the information of Force " + (i+1));
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
        System.out.println("Select the quadrant the force is present in : ");
        System.out.println("[Once the base of the force is at origin ]");
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
        System.out.println("Enter the magnitude of force :");
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
    
    public static double[] simultaneousEqnSolver(double CoefX1 , double CoefY1 , double Const1 , double CoefX2 , double CoefY2 , double Const2)
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
        System.out.println("");
        System.out.println("To use again enter 0 else any other number");
        int choice = sc.nextInt();
        if(choice == 0)
        {
            LamiAgain(knownForceMagnitude, unknownForce1Magnitude, unknownForce2Magnitude);
        }
        

    }
    public static void LamiAgain(double force1 , double force2 , double force3)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Choose the force to use again");
        System.out.println("1. Previous Known Force ");
        System.out.println("2. Previous Unknown Force 1");
        System.out.println("3. Previous Unknown Force 2");
        int ReusedForce = sc.nextInt();
        double knownForceMagnitude = 0 ;
        switch(ReusedForce)
        {
            case 1:
            knownForceMagnitude = force1;
            break;
            case 2:
            knownForceMagnitude = force2;
            break;
            case 3:
            knownForceMagnitude = force3;
            break;
            default:
            System.out.println("Invalid Input");
            break;
        }
        System.out.println("Enter the Angle opposite to the known force");
        double knownForceAngle = Math.toRadians(sc.nextDouble());
        System.out.println("Enter the Angle opposite to the unknown force 1");
        double unknownAngle1 = Math.toRadians(sc.nextDouble());
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
        System.out.println("");
        System.out.println("To use again enter 0 else any other number");
        int choice = sc.nextInt();
        if(choice == 0)
        {
            LamiAgain(knownForceMagnitude, unknownForce1Magnitude, unknownForce2Magnitude);
        }
    }
    public static void Loads()
    {
        Scanner sc = new Scanner(System.in) ;
        double[] values = InputMoment();
        double FX = values[0];
        double FY = values[1];
        double MomentDueToForce = values[2];
        double[] LoadValues = InputLoad();
        double LX = LoadValues[0];
        double LY = LoadValues[1];
        double MomentDueToLoad = LoadValues[2];
        double TotalFX = FX + LX;
        double TotalFY = FY + LY;
        System.out.println("If any extra moments are present enter 1 else 0");
        int choice = sc.nextInt();
        double ExtraMoment = 0 ;
        if(choice == 1)
        {
            ExtraMoment = InputExtraMoments();
        }
        double TotalMoment = MomentDueToForce + MomentDueToLoad + ExtraMoment ;
        if(TotalFX < 0)
        {
            System.out.println("Total FX : "+(0-TotalFX)+" N Leftwards");
        }
        if(TotalFX > 0)
        {
            System.out.println("Total FX : "+(TotalFX)+" N Rightwards");
        }
        if(TotalFX==0)
        {
            System.out.println("All forces cancel out in the X Direction");
        }
        if(TotalFY < 0)
        {
            System.out.println("Total FY : "+(0-TotalFY)+" N Downwards");
        }
        if(TotalFY > 0)
        {
            System.out.println("Total FY : "+TotalFY+" N Upwards");
        }
        if(TotalFY==0)
        {
            System.out.println("All forces cancel out in the Y Direction");
        }
        if(TotalMoment < 0)
        {
            System.out.println("Total Moment : "+TotalMoment+" N/m Clockwise");
        }
        if(TotalMoment > 0)
        {
            System.out.println("Total Moment : "+TotalMoment+" N/m Anti-Clockwise");
        }
        if(TotalMoment == 0)
        {
            System.out.println("Total Moment : "+TotalMoment+" N/m");
        }

    }
    public static void MomentGeneralPlane()
    {
        Scanner sc = new Scanner(System.in);
        double[] Finalvalues = InputGeneralMoment();
        double FX = Finalvalues[0];
        double FY = Finalvalues[1];
        double MomentDueToForce = Finalvalues[2];
        System.out.println(FX + " + " +FY+" + "+MomentDueToForce);
        //System.out.println("\033[H\033[2J");
        System.out.print("Enter 1 if extra moments are present else enter 0: ");
        int e = sc.nextInt();
        double ExtraMoment = 0 ;
        if (e == 1) 
        {
            ExtraMoment = InputExtraMoments();
        }
        System.out.println(ExtraMoment);
        double TotalMoment = MomentDueToForce + ExtraMoment ;
        System.out.printf("Resultant force is %.2f\n", Math.hypot(FX , FY));
        System.out.printf("With an angle %.2f\n" , Math.toDegrees(Math.atan(FY/FX)));
        System.out.printf("Total moment is %.2f\n", TotalMoment);
        if (FX == 0 && FY == 0) {
            System.out.println("No forces are present");
        } else {
            System.out.printf("Distance of the force from reference point is %.2f\n", TotalMoment/(FX*FY));
        }

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
            System.out.printf("The distance of the force from reference point is %.2f\n", Distance);
        
            if(TotalMoment > 0)
            {
                System.out.println("The Moment is in Anti Clockwise direction");
                System.out.printf("Total moment is %.2f\n", TotalMoment);
            }
            if(TotalMoment < 0)
            {
                System.out.println("The Moment is in Clockwise direction");
                TotalMoment = 0 - TotalMoment;
                System.out.printf("Total moment is %.2f\n", TotalMoment);
            }
            
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
        int Inclination = 2;
        for( int i = 0 ; i < n ; i++)
        {
            System.out.println("\033[H\033[2J");
            System.out.println("Enter the information of force" + (i+1));
            System.out.println("Enter 1 if the force is inclined else 0");
            Inclination = sc.nextInt();
            double magnitude;
            if(Inclination == 1)
            {
                int quadrant = InputQuadrant();
                magnitude = InputMagnitude();            
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
            }
            else
            {
                magnitude = InputMagnitude();
                System.out.println("Choose the orientation of the force");
                System.out.println("1. Upwards");
                System.out.println("2. Downwards");
                System.out.println("3. Rightwards");
                System.out.println("4. Leftwards");
                int direction = sc.nextInt();
                if(direction == 1)
                {
                    angle = Math.PI/2 ;
                }
                if(direction == 2)
                {
                    angle = (3*Math.PI)/2 ;
                }
                if(direction == 3)
                {
                    angle = 0;  
                }
                if(direction == 4)
                {
                    angle = Math.PI ;
                }
            FX += CalcFX(magnitude , angle);
            FY += CalcFY(magnitude , angle);

            System.out.println("Enter the distance from the reference point");
            double distance = sc.nextDouble() ;
            System.out.println("Choose the direction of moment caused by the force");
            System.out.println("1. Anti-clockwise");
            System.out.println("2. Clockwise");
            System.out.println("0. If the Force is passing through the point");
            direction = sc.nextInt() ;
            Moment += CalcMoment(magnitude , distance , direction);
            }
        }
        double[] Finalvalues = new double[3];
        Finalvalues[0] = FX ;
        Finalvalues[1] = FY ;
        Finalvalues[2] = Moment ;
        return Finalvalues ;
    }
    public static double[] InputGeneralMoment()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Enter the number of known forces :");
        int n = sc.nextInt() ;
        double FX=0 , FY=0 ;
        double Moment = 0 ;
        double angle =0 ;
        int Inclination = 2;
        for( int i = 0 ; i < n ; i++)
        {
            System.out.println("\033[H\033[2J");
            System.out.println("Enter the information of force" + (i+1));
            System.out.println("Enter 1 if the force is inclined else 0");
            Inclination = sc.nextInt();
            double magnitude;
            double distance;
            if(Inclination == 1)
            {
                int quadrant = InputQuadrant();
                magnitude = InputMagnitude();            
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
                System.out.println("Enter the Horizontal distance from the reference point");
                System.out.println("[0 If the force is passing through the point]");
                double distanceX = sc.nextDouble() ;
                System.out.println("Enter the Vertical distance from the reference point");
                System.out.println("[0 If the force is passing through the point]");
                double distanceY = sc.nextDouble() ;
                distance = Math.hypot(distanceX,distanceY);
            }
            else
            {
                
                magnitude = InputMagnitude();
                System.out.println("Choose the orientation of the force");
                System.out.println("1. Upwards");
                System.out.println("2. Downwards");
                System.out.println("3. Rightwards");
                System.out.println("4. Leftwards");
                int direction = sc.nextInt();
                if(direction == 1)
                {
                    angle = Math.PI/2 ;
                }
                if(direction == 2)
                {
                    angle = (3*Math.PI)/2 ;
                }
                if(direction == 3)
                {
                    angle = 0;  
                }
                if(direction == 4)
                {
                    angle = Math.PI ;
                }
                System.out.println("Enter the Perpendicular Distance from the Reference Point");
                System.out.println("[0 If the force is passing through the point]");
                distance = sc.nextDouble() ;
            }
            FX += CalcFX(magnitude , angle);
            FY += CalcFY(magnitude , angle);
            System.out.println("Choose the direction of moment caused by the force");
            System.out.println("1. Anti-clockwise");
            System.out.println("2. Clockwise");
            System.out.println("0. If the force is passing through the point");
            direction = sc.nextInt();
            Moment += CalcMoment(magnitude, distance, direction);
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
        else if(direction == 2)
        {
            double Moment = -magnitude*distance;
            return Moment ;
        }
        else
        return 0 ;
    }
    public static void Joints()
    {
        Scanner sc = new Scanner(System.in) ;
        double[] values = InputMoment();
        double FX = values[0];
        double FY = values[1];
        double MomentDueToForce = values[2];
        double[] LoadValues = InputLoad();
        double LX = LoadValues[0];
        double LY = LoadValues[1];
        double MomentDueToLoad = LoadValues[2];
        double TotalFX = FX + LX;
        double TotalFY = FY + LY;
        System.out.println("If any extra moments are present enter 1 else 0");
        int choice = sc.nextInt();
        double ExtraMoment = 0 ;
        if(choice == 1)
        {
            ExtraMoment = InputExtraMoments();
        }
        double TotalMoment = MomentDueToForce + MomentDueToLoad + ExtraMoment ;
        System.out.println("Choose the System of Joints");
        System.out.println("1. Roller and Hinge");
        System.out.println("2. 2 Roller");
        System.out.println("3. 2 Hinge [also works for 1 hinge and an inclined roller]");
        int system = sc.nextInt();
        switch(system)
        {
            case 1:
            Roller_Hinge(TotalFY ,  TotalFX , TotalMoment);
            break;
            case 2:
            TwoRoller(TotalFY , TotalFX , TotalMoment);
            break;
            case 3:
            TwoHinge(TotalFY , TotalFX , TotalMoment);
            break;
            default:
            System.out.println("Invalid System of Joints");
            break;
        }

    }
    public static void Roller_Hinge(double FY , double FX , double Moment)
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Using the Hinge Joint as reference point");
        double[] RollerValues = InputJoint("Roller");
        double RollerDistance = RollerValues[0];
        double RollerOrientation = RollerValues[1];
        double[] HingeValues = InputJoint("Hinge");
        double HingeDistance = HingeValues[0];
        double HingeOrientation = HingeValues[1];
        
        double[] ReactionValues = simultaneousEqnSolver(RollerDistance,HingeDistance,(0-Moment),1,1,(0-FY));
        double RollerReaction = ReactionValues[0];
        double HingeReactionFY = ReactionValues[1];
        double HingeReactionFX = 0 - FX;
        double HingeReaction = Math.hypot(HingeReactionFX, HingeReactionFY);

        System.out.println("Roller Reaction: " + RollerReaction + " N");
        System.out.println("Hinge Reaction: " + HingeReaction + " N");
        System.out.println("\"Aproxx Values\"");

    }
    public static void TwoRoller(double FY , double FX , double Moment)
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        double[] Roller1Values = InputJoint("Roller");
        double Roller1Distance = Roller1Values[0];
        double Roller1Orientation = Roller1Values[1];
        double[] Roller2Values = InputJoint("Roller");
        double Roller2Distance = Roller2Values[0];
        double Roller2Orientation = Roller2Values[1];
        
        double[] ReactionValues = simultaneousEqnSolver(Roller1Distance,Roller2Distance,(0-Moment),1,1,(0-FY));
        double Roller1Reaction = ReactionValues[0];
        double Roller2Reaction = ReactionValues[1];

        System.out.println("Roller 1 Reaction : " +Roller1Reaction+ " N");
        System.out.println("Roller 2 Reaction : " + Roller2Reaction+ " N");
        System.out.println("\"Aproxx Values\"");
    }
    public static void TwoHinge(double FY , double FX , double Moment)
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("\033[H\033[2J");
        System.out.println("Hinge Joint 1");
        double[] Hinge1Values = InputJoint("Hinge");
        double Hinge1Distance = Hinge1Values[0];
        double Hinge1Orientation = Hinge1Values[1];
        System.out.println("Hinge Joint 2");
        double[] Hinge2Values = InputJoint("Hinge");
        double Hinge2Distance = Hinge2Values[0];
        double Hinge2Orientation = Hinge1Values[1];

        double[] ReactionValues = simultaneousEqnSolver(Hinge1Distance,Hinge2Distance,(0-Moment),1,1,(0-FY));
        double Hinge1Reaction = ReactionValues[0];
        double Hinge2Reaction = ReactionValues[1];

        System.out.println("Hinge 1 Reaction : " + Hinge1Reaction+ " N");
        System.out.println("Hinge 2 Reaction : " + Hinge2Reaction+ " N");
        System.out.println("\"Approx Values\"");
        System.out.println("IF USED FOR INCLINED ROLLER ENTER 1");
        int inclinedRoller = sc.nextInt();
        if(inclinedRoller == 1)
        {
            System.out.println("Select the hinge used as the inclined roller");
            System.out.println("1. Hinge 1");
            System.out.println("2. Hinge 2");
            int hinge = sc.nextInt();
            double roller= 0;
            if(hinge == 1)
            {
                roller = Hinge1Reaction;
            }
            if(hinge == 2)
            {
                roller = Hinge2Reaction;
            }
            System.out.println("Enter the angle of inclination with the positive y-axis");
            double angle = sc.nextDouble();
            roller = roller *Math.cos(angle);
            System.out.println("Roller Reaction : " + roller + " N");
        }
    }
    public static double[] InputJoint(String name)
    {
        Scanner sc = new Scanner(System.in) ;
        double distance=0;
        double orientation=0;
        switch(name)
        {
            case "Roller":  
            System.out.println("Enter the Distance of the Roller from the reference Point");
            System.out.println("Enter 0 if it is reference point");
            distance = sc.nextDouble() ;
            System.out.println("Select the orientation of moment caused by the joint");
            System.out.println("1. Anti-Clockwise");
            System.out.println("2.Clockwise");
            System.out.println("0. If joint is at reference point");
            orientation = sc.nextDouble();
            break;
            case "Hinge":
            System.out.println("Enter the Distance of the Hinge from the reference Point");
            System.out.println("Enter 0 if it is reference point");
            distance = sc.nextDouble() ;
            System.out.println("Select the orientation of moment caused by the joint");
            System.out.println("1. Anti-Clockwise");
            System.out.println("2.Clockwise");
            System.out.println("0. If joint is at reference point");
            orientation = sc.nextDouble();
            break;
            case "Fixed":
            System.out.println("Enter the Distance of the Fixed from the reference Point");
            System.out.println("Enter 0 if it is reference point");  
            distance = sc.nextDouble() ;
            System.out.println("Select the orientation of moment caused by the joint");
            System.out.println("1. Anti-Clockwise");
            System.out.println("2.Clockwise");
            System.out.println("0. If joint is at reference point");
            orientation = sc.nextDouble();
            break;
            default:
            System.out.println("Invalid Joint");
            break;
        }

        double[] JointValues = new double[2];
        JointValues[0] = distance;
        JointValues[1] = orientation;
        return JointValues;
    }


    public static double[] InputLoad()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter the number of loads");
        int n = sc.nextInt() ;
        double[] Load = new double[100];
        double[] Distance = new double[100];
        int[] Direction = new int[100];
        for(int i = 0 ; i < n ; i++)
        {
            System.out.println("\033[H\033[2J");
            System.out.println("Select the type of load");
            System.out.println("1 for Point Load");
            System.out.println("2 for Uniform Distributed Load");
            System.out.println("3 for Uniformly Varying Load");
            System.out.println("4 for Combined UDL and UVL");
            int choice = sc.nextInt();
            double magnitude;
            double length;
            double smallermagnitude;
            double biggermagnitude;
            double distance;
            int orientation ;
            switch(choice)
            {
                case 1:
                System.out.println("Enter the magnitude of the Point Load");
                Load[i] = sc.nextDouble();
                System.out.println("Enter the distance from the reference point");
                Distance[i]= sc.nextDouble();
                System.out.println("Choose the direction of Potential Moment");
                System.out.println("1: Anticlockwise");
                System.out.println("2: Clockwise");
                Direction[i] = sc.nextInt();
                break;

                case 2:
                System.out.println("Enter the magnitude of the Load");
                magnitude = sc.nextDouble();
                System.out.println("Enter the length of applied Load");
                length = sc.nextDouble();
                System.out.println("Enter the distance of the closer side from the reference point");
                distance = sc.nextDouble();
                System.out.println("Choose the direction of Potential Moment");
                System.out.println("1: Anticlockwise");
                System.out.println("2: Clockwise");
                Direction[i] = sc.nextInt();
                Load[i] = magnitude*length;
                Distance[i] = distance + (length/2);
                break;

                case 3:
                System.out.println("Choose the orientation of the UVL");
                System.out.println("1: Slanted toward the reference point");
                System.out.println("2: Slanted away from thr reference point");
                orientation = sc.nextInt();
                System.out.println("Enter the magnitude of the Load");
                magnitude = sc.nextDouble();
                System.out.println("Enter the length of applied Load");
                length = sc.nextDouble();
                System.out.println("Enter the distance of the closer side from the reference point");
                distance = sc.nextDouble();
                System.out.println("Choose the direction of Potential Moment");
                System.out.println("1: Anticlockwise");
                System.out.println("2: Clockwise");
                Direction[i] = sc.nextInt();
                Load[i] = (0.5*magnitude*length);
                if(orientation ==1)
                {
                    Distance[i] = distance + ((2*length)/3);
                }
                if(orientation == 2)
                {
                    Distance[i] = distance + (length/3);
                }
                break;

                case 4:
                System.out.println("Choose the orientation of the UVL");
                System.out.println("1: Slanted toward the reference point");
                System.out.println("2: Slanted away from thr reference point");
                orientation = sc.nextInt();
                System.out.println("Enter the smaller magnitude of the Load");
                smallermagnitude = sc.nextDouble();
                System.out.println("Enter the bigger magnitude of the Load");
                biggermagnitude = sc.nextDouble();
                System.out.println("Enter the length of applied Load");
                length = sc.nextDouble();
                System.out.println("Enter the distance of the closer side from the reference point");
                distance = sc.nextDouble();
                System.out.println("Choose the direction of Potential Moment");
                System.out.println("1: Anticlockwise");
                System.out.println("2: Clockwise");
                Direction[i] = sc.nextInt();
                Load[i] = smallermagnitude*length;
                Distance[i] = distance + (length/2);

                Load[i+1] = (0.5*(biggermagnitude-smallermagnitude)*length);
                if(orientation ==1)
                {
                    Distance[i+1] = distance + ((2*length)/3);
                }
                if(orientation == 2)
                {
                    Distance[i+1] = distance + (length/3);
                }
                Direction[i+1]= Direction[i];
                i++;
                n++;
                break;
                default:
                System.out.println("Invalid Input");
                break;
            }
        }
        double LX = 0;
        double LY = 0;
        for(int i = 0 ; i < n ; i++)
        {
            LY += CalcFY(Load[i], Math.toRadians(270));
        }
        double MomentDueToLoad = 0 ;
        for(int i = 0 ; i < n ; i++)
        {
            MomentDueToLoad += CalcMoment(Load[i] , Distance[i] , Direction[i]);
        }

        double[] FinalValues = new double[3];
        FinalValues[0] = LX;
        FinalValues[1] = LY;
        FinalValues[2] = MomentDueToLoad;
        return FinalValues;
    }

}
