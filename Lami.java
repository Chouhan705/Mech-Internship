import java.util.*;
import java.lang.*;
import java.text.*;

class Lami
{
    public static void main (String[] args)
    {
        System.out.println("Choose the system to solve");
        System.out.println("1. One Force System");
        System.out.println("2. Two Force System");
        System.out.println("3.Loads");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
                OneForceSystem();
                break;
            case 2:
                TwoForceSystem();
                break;
            case 3:
                Loads();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }
    public static void OneForceSystem()
    {
        double ForceValues[] = lami();
        double UnknownForce1 = ForceValues[0];
        double UnknownForce2 = ForceValues[1];
        System.out.println("Unknown Force one = "+ UnknownForce1 + "N");
        System.out.println("Unknown Force two = "+ UnknownForce2 + "N");
    }
    public static void TwoForceSystem()
    {
        double ForceValues[] = lami();
        double UnknownForce1 = ForceValues[0];
        double UnknownForce2 = ForceValues[1];
        System.out.println("Unknown Force one = "+ UnknownForce1 + "N");
        System.out.println("Unknown Force two = "+ UnknownForce2 + "N");
        System.out.println("Use these values for the second System of Forces.");
        double ForceValues2[] = lami();
        double UnknownForce3 = ForceValues2[0];
        double UnknownForce4 = ForceValues2[1];
        System.out.println("Unknown Force three = "+ UnknownForce3 + "N");
        System.out.println("Unknown Force four = "+ UnknownForce4 + "N");
    }
    public static double[] lami()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Magnitude of the known force");
        double  knownForceMagnitude = sc.nextDouble();
        System.out.println("Enter the Angle opposite to the known force");
        double  knownForceAngle = Math.toRadians(sc.nextDouble());
        System.out.println("Enter the Angle opposite to the unknown force 1");
        double  unknownAngle1 = Math.toRadians(sc.nextDouble());
        System.out.println("Enter the Angle opposite to the unknown force 2");
        double  unknownAngle2 = Math.toRadians(sc.nextDouble());

        double unknownForce1Magnitude = (knownForceMagnitude*Math.sin(unknownAngle1))/Math.sin(knownForceAngle);
        double unknownForce2Magnitude = (knownForceMagnitude*Math.sin(unknownAngle2))/Math.sin(knownForceAngle);

        if(knownForceAngle+unknownAngle1+unknownAngle2!= (2*Math.PI))
        {
            System.out.println("The sum of the angles is not equal to 360 degrees");
            System.out.println("Re-check the angles entered");
            System.exit(0);
        }

        double[] ForceValues = new double[2];
        ForceValues[0] = unknownForce1Magnitude;
        ForceValues[1] = unknownForce2Magnitude;
        return ForceValues;
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
                int direction = sc.nextInt();
                if(direction == 1)
                {
                    angle = Math.PI/2 ;
                }
                if(direction == 2)
                {
                    angle = (3*Math.PI)/2 ;
                }
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
            System.out.println("3 for Uniformly Varrying Load");
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
                System.out.println("Enter the distance from the refrence point");
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
                System.out.println("Enter the distance of the closer side from the refrence point");
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
                System.out.println("1: Slanted toward the refrence point");
                System.out.println("2: Slanted away from thr refrence point");
                orientation = sc.nextInt();
                System.out.println("Enter the magnitude of the Load");
                magnitude = sc.nextDouble();
                System.out.println("Enter the length of applied Load");
                length = sc.nextDouble();
                System.out.println("Enter the distance of the closer side from the refrence point");
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
                System.out.println("1: Slanted toward the refrence point");
                System.out.println("2: Slanted away from thr refrence point");
                orientation = sc.nextInt();
                System.out.println("Enter the smaller magnitude of the Load");
                smallermagnitude = sc.nextDouble();
                System.out.println("Enter the bigger magnitude of the Load");
                biggermagnitude = sc.nextDouble();
                System.out.println("Enter the length of applied Load");
                length = sc.nextDouble();
                System.out.println("Enter the distance of the closer side from the refrence point");
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
