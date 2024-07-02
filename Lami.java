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

        double[] ForceValues = new double[2];
        ForceValues[0] = unknownForce1Magnitude;
        ForceValues[1] = unknownForce2Magnitude;
        return ForceValues;
    }
}
