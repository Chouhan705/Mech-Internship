import java.util.*;
import java.lang.*;
import java.text.*;

public class Joints
{
    public static void main(String[] args)
    {
        Joints();
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
}