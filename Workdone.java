import java.util.*;
import java.lang.*;
import java.text.*;

public class Workdone
{
  public static void main(String[] args) 
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("KINDLY ENTER THE VALUE OF ALL THE QUANTITIES IN SI UNITS\n");
    System.out.println("Choose the system you wish to operate on\n 1.Weight&Friction force\n 2.Spring force");
    int op = sc.nextInt();
    switch(op)
    {
      case 1:
      System.out.println("Choose the operation");
      System.out.println("1: Plane Surface");
      System.out.println("2: Inclined Surface");
      int surface = sc.nextInt();
      System.out.println("Choose if a force is present");
      System.out.println("1: Yes");
      System.out.println("0: No");
      int force = sc.nextInt();
      
      System.out.println("Choose a operation to perform");
      System.out.println("1: Find Distance");
      System.out.println("2: Find Velocity");
      int operation = sc.nextInt();
      if(operation == 1)
      {
        CalcDistance(surface,force);
      }
      if(operation == 2)
      {
        CalcVelocity(surface,force);
      }
      break;  

      case 2:
      Springforce();

    }
  }
  public static void CalcVelocity(int surface, int force )
  {
    // initial velocity is always assumed to be zero seeing no sum asks to calculate initial velocity
    double g = 9.81;
    Scanner sc = new Scanner(System.in);
    double[] values = InputVelocity();
    double mass = values[0];
    double friction = values[1];
    double distance = values[2];
    if(surface ==1 )
    {
        if(force==1)
        {
            System.out.println("Enter the magnitude of force:");
            double magnitude = sc.nextDouble();
            System.out.println("Enter the angle of force from positve x-axis:");
            double angle = Math.toRadians(sc.nextDouble());
            double normal = (mass*g)-magnitude*Math.sin(angle);                               
            double workdone = (magnitude*Math.cos(angle)*distance) - (friction*normal*distance);
            System.out.println("The normal force is " +normal+ " N");
            System.out.println("The work done is " +workdone+ " J");
            double veloctiy = Math.sqrt((2*workdone)/mass);
            System.out.println("The velocity of the block is " + veloctiy+ "m/s");
        }
        if(force==0)
        {
            double normal = (mass*g);
            double workdone = -(friction*normal*distance);
            System.out.println("The normal force is " +normal+ " N");
            System.out.println("The work done is " +workdone+ " J");
            double veloctiy = Math.sqrt((2*workdone)/mass);
            System.out.println("The velocity of the block is " + veloctiy+ "m/s");
        }
    }
    if(surface==2)
    {
        if(force==1)
        {
            System.out.println("Enter the angle of inclination:");
            double theta = Math.toRadians(sc.nextDouble());
            System.out.println("Enter the value of force:");
            double magnitude = sc.nextDouble();
            System.out.println("Enter the angle of force from positve X-axis (not with the inclined plane):");
            double angle = Math.toRadians(sc.nextDouble());
            double normal = mass*g*Math.cos(theta)-(magnitude*Math.sin(angle));
            double workdone = (mass*g*Math.sin(theta)*distance) + (magnitude*Math.cos(angle)*distance) - (friction*normal*distance);     //+-force or +-force(cos/sin) depending on where the force is acting (humne yaha include nahi kiya) 
            System.out.println("The normal force is " +normal+ " N");
            System.out.println("The work done is " +workdone+ " J");
            double veloctiy = Math.sqrt((2*workdone)/mass);
            System.out.println("The velocity of the block is " + veloctiy+ "m/s");
        }
        if(force==0)
        {
            System.out.println("Enter the angle of inclination:");
            double theta = Math.toRadians(sc.nextDouble());
            double normal = mass*9.81*Math.cos(theta);
            double workdone = (mass*9.81*Math.sin(theta)*distance) - (friction*normal*distance);
            System.out.println("The normal force is " +normal+ " N");
            System.out.println("The work done is " +workdone+ " J");
            double veloctiy = Math.sqrt((2*workdone)/mass);
            System.out.println("The velocity of the block is " + veloctiy+ "m/s");
        }
    }
  }
  public static double[] InputVelocity()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass: ");
    double mass = sc.nextDouble();
    System.out.println("Enter the frictional constant:");
    double friction = sc.nextDouble();
    System.out.println("Enter the distance:");
    double distance = sc.nextDouble();
    double[] values = new double[3];
    values[0] = mass;
    values[1] = friction;
    values[2] = distance;
    return values;
  }

  public static void CalcDistance(int surface, int force)
  {
    double g = 9.81;
    Scanner sc = new Scanner(System.in);
    double[] values = InputDistance();
    double mass = values[0];
    double friction = values[1];
    double InitialVelocity = values[2];
    double FinalVelocity = values[3];

    if(surface==1)
    {
        if(force==1)
        {
          System.out.println("Enter the magnitude of force:");
          double magnitude = sc.nextDouble();
          System.out.println("Enter the angle of force from positve x-axis:");
          double angle = Math.toRadians(sc.nextDouble());
          double normal = mass*g*Math.cos(angle);
          double workdone = (magnitude*Math.cos(angle)) + (mass*g*Math.sin(angle)) - (friction*normal);     //+-force or +-force(cos/sin) depending on where the force is acting
          System.out.println("The normal force is " +normal+ " N");
          System.out.println("The work done is " +workdone+ " J");
    
          double KineticEnergy1 = 0.5*mass*Math.pow(InitialVelocity, 2);
          double KineticEnergy2 = 0.5*mass*Math.pow(FinalVelocity, 2);
    
          double Distance = (KineticEnergy2 - KineticEnergy1)/workdone;
          System.out.println("The distance travelled by the block is "+(Distance)+" m");
        }   
    
        if(force==0)
        {
          System.out.println("Enter the angle of inclination:");
          double theta = Math.toRadians(sc.nextDouble());
          double normal = mass*g*Math.cos(theta);
          double workdone = (mass*g*Math.sin(theta)) - (friction*normal);     
          System.out.println("The normal force is " +normal+ " N");
          System.out.println("The work done is " +workdone+ " J");
    
          double KineticEnergy1 = 0.5*mass*Math.pow(InitialVelocity, 2);
          double KineticEnergy2 = 0.5*mass*Math.pow(FinalVelocity, 2);
    
          double Distance = (KineticEnergy2 - KineticEnergy1)/workdone;
          System.out.println("The distance travelled by the block is "+(Distance)+" m");
        }   
    } 
    
    if(surface==2)
    {
        if(force == 1)
        {
          System.out.println("Enter the magnitude of force:");
          double magnitude = sc.nextDouble();
          System.out.println("Enter the angle of force from positve x-axis:");
          double angle = Math.toRadians(sc.nextDouble());
          System.out.println("Enter the angle of inclination:");
          double theta = Math.toRadians(sc.nextDouble());
          double normal =(mass*g)-magnitude*Math.sin(angle);                                                  
          double workdone = (magnitude*Math.cos(angle)) + (mass*g*Math.sin(theta)) - (friction*normal);     //+-force or +-force(cos/sin) depending on where the force is acting
          System.out.println("The normal force is " +normal+ " N");
          System.out.println("The work done is " +workdone+ " J");

          double KineticEnergy1 = 0.5*mass*Math.pow(InitialVelocity, 2);
          double KineticEnergy2 = 0.5*mass*Math.pow(FinalVelocity, 2);
    
          double Distance = (KineticEnergy2 - KineticEnergy1)/workdone;
          System.out.println("The distance travelled by the block is "+(Distance)+" m");
        }   
    
        if(force == 0)
        {
          System.out.println("Enter the angle of inclination:");
          double theta = Math.toRadians(sc.nextDouble());
          double normal =mass*g*Math.cos(theta);
          System.out.println("If the force is along the direction of velocity enter 1 else enter 2");
          double Input = sc.nextDouble();
          double WorkDone ;
          if(Input==1)
          {
            WorkDone = (mass*9.81*Math.sin(theta)) - (friction*normal);
          }
          else 
          {
            WorkDone = -(mass*9.81*Math.sin(theta)) - (friction*normal);
          }
          double KineticEnergy1 = 0.5*mass*Math.pow(InitialVelocity, 2);
          double KineticEnergy2 = 0.5*mass*Math.pow(FinalVelocity, 2);
    
          double Distance = (KineticEnergy2 - KineticEnergy1)/WorkDone;
          System.out.println("The distance travelled by the block is "+(Distance)+" m");
        }
    }
  }  
  public static double[] InputDistance()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass: ");
    double mass = sc.nextDouble();
    System.out.println("Enter the frictional constant:");
    double friction = sc.nextDouble();
    System.out.println("Enter the initial velocity:");
    double InitialVelocity = sc.nextInt();
    System.out.println("Enter the final velocity:");
    double FinalVelocity = sc.nextInt();
    double[] values = new double[4];
    values[0] = mass;
    values[1] = friction;
    values[2] = InitialVelocity;
    values[3] = FinalVelocity;
    return values;
  }


public static void Springforce()
    {
      double WorkDone, FinalVelocity;
      double g = 9.81;
      Scanner sc  = new Scanner(System.in);
      
      System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass: ");
      double mass = sc.nextDouble();
      System.out.println("Enter the height/displacement:");
      double h = sc.nextDouble();
      System.out.println("Enter the value of x1 and x2:");
      double x1 = sc.nextDouble();
      double x2 = sc.nextDouble();
      System.out.println("Enter the value of k:");
      double k = sc.nextDouble();
      System.out.println("Enter 1 if force is present else enter 0");
      double value = sc.nextDouble();
      if(value==1)
      {
        System.out.println("Enter the magnitude of force:");
        double force = sc.nextDouble();
        System.out.println("Enter the angle of force from positive direction of x-axis:");
        double angle = Math.toRadians(sc.nextDouble());
        force = force*Math.sin(angle)*h;
        WorkDone = force - mass*g*h + 0.5*k*(Math.pow(x1, 2) - Math.pow(x2, 2));
      }
      else
      {
        WorkDone = mass*g*h + 0.5*k*(Math.pow(x1, 2) - Math.pow(x2, 2));
        FinalVelocity = Math.sqrt(2*WorkDone/mass);
        System.out.println("The final velocity is "+(FinalVelocity)+ "m/s");
      }
    }
}
