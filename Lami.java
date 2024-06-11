import java.util.*;
import java.lang.*;
import java.text.*;

class Lami
{
    public static void main (String[] args)
    {


    }
    public static void OneForceSystem()
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
        double unknownForce1Magnitude = (knownForceMagnitude*Math.sin(unknownAngle1))/Math.sin(knownForceAngle);
        double unknownForce2Magnitude = (knownForceMagnitude*Math.sin(unknownAngle2))/Math.sin(knownForceAngle);
        System.out.println("The unknown force 1 is :");
        System.out.format("%.2f", unknownForce1Magnitude);
        System.out.println("");
        System.out.println("The unknown force 2 is :");
        System.out.format("%.2f", unknownForce2Magnitude);
    }
    public static void TwoForceSystem()
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
        System.out.println("The unknown force 1 is :");
        System.out.format("%.2f", unknownForce1Magnitude);
        System.out.println("");
        System.out.println("The unknown force 2 is :");
        System.out.format("%.2f", unknownForce2Magnitude);
    }
}