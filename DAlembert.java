import java.util.*;
public class DAlembert
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n KINDLY ENTER THE VALUE OF ALL THE QUANTITIES IN SI UNITS\n");
        System.out.println("Select the option number you wish to operate on.\n 1.Block System\n 2.Pulley System");
        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
            System.out.println("Choose what you want find:");
            System.out.println("1. Force");
            System.out.println("2. Acceleration");
            int quantity = sc.nextInt();
            if(quantity==1)
            {
                force();
                break;
            }

            System.out.println("Choose whether it is an/a..");
            System.out.println("1. One Block system");
            System.out.println("2. Multiple Block system");
            int system = sc.nextInt();
      
            if(system==1)
            {
                oneBlock();
            }
            if (system==2)
            {
                multipleBlock();
            }
            break;

            case 2:
            Pulleysystem();
            break;

            default:
            System.out.println("Invalid choice");
            break;
        }
    }

    public static void Formula()
    {
        double u,v,s,t,a;
        Scanner sc = new Scanner(System.in);
        int formula = sc.nextInt();
        switch(formula)
        {
            case 1:
            System.out.println("Enter the value of the following quantities in this order....[intial velocity, acceleration & time]");
             u = sc.nextDouble();
             a = sc.nextDouble();
             t = sc.nextDouble();
             v = u+(a*t);
            System.out.println("The final velocity(v) is "+(v)+ " m/s");
            break;

            case 2:
            System.out.println("Enter the value of the following quantities in this order....[intial velocity, acceleration & distance]");
            u = sc.nextDouble();
            a = sc.nextDouble();
            s = sc.nextDouble();
            v = (Math.pow(u, 2)) + (2*a*s);
            System.out.println("The final velocity(v) is "+(v)+ " m/s");
            break;

            case 3:
            System.out.println("Enter the value of the following quantities in this order....[intial velocity, acceleration & time]");
            u = sc.nextDouble();
            a = sc.nextDouble();
            t = sc.nextDouble();
            s = ((u*t) + (0.5*a*Math.pow(t, 2)));
            System.out.println("The distance travelled(s) is "+(s)+ " m");
            break;

            case 4:
            System.out.println("Enter the value of the following quantities in this order....[final velocity, initial velocity & acceleration]");
            v = sc.nextDouble();
            u = sc.nextDouble();
            a = sc.nextDouble();
            s = (Math.pow(v,2) - Math.pow(u, 2))/(2*a);
            System.out.println("The distance travelled(s) is "+(s)+ " m");
            break;

            case 5:
            System.out.println("Enter the value of the following quantities in this order....[final velocity, initial velocity & acceleration]");
            v = sc.nextDouble();
            u = sc.nextDouble();
            a = sc.nextDouble();
            t = ((v-u)/a);
            System.out.println("The time taken is "+(t)+ " s");
            break;

            case 6:
            System.out.println("Enter the value of the following quantities in this order....[final velocity, initial velocity & distance]");
            v = sc.nextDouble();
            u = sc.nextDouble();
            s = sc.nextDouble();
            a = (Math.pow(v,2) - Math.pow(u, 2)/2*s);
            System.out.println("The acceleration is "+(a)+ " m/s^2");
            break;

            case 7:
            System.out.println("Enter the value of the following quantities in this order....[final velocity, initial velocity & time]");
            v = sc.nextDouble();
            u = sc.nextDouble();
            t = sc.nextDouble();
            a = ((v-u)/t);
            System.out.println("The acceleration is "+(a)+ " m/s^2");
            break;
        }
    }
        public static void force()
        {
            double g = 9.81;
            Scanner sc  = new Scanner(System.in);
            System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass:)");
            double mass = sc.nextDouble();
            System.out.println("Enter the value of frictional constant:");
            double mu = sc.nextDouble();
            System.out.println("Enter the angle(in degrees) of force from positive x-axis:");
            double theta = Math.toRadians(sc.nextDouble());
            System.out.println("Enter the acceleration:");
            double acceleration = sc.nextDouble();
            double F = ((mass*acceleration) + (mu*mass*g))/(Math.cos(theta) + mu*Math.sin(theta));                  // giving the right answer when in the denominator (sign = + & theta =330) & (sign = - & theta =30)
            System.out.println("The force acting on the block is "+(F));
        }

        public static void oneBlock()
        {
            Scanner sc  = new Scanner(System.in);
            System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass:)");
            double mass = sc.nextDouble();
            System.out.println("Enter the value of frictional constant:");
            double mu = sc.nextDouble();
            System.out.println("Enter the angle of inclination (in degrees):");
            double theta = Math.toRadians(sc.nextDouble());
            double acceleration = 9.81*(Math.sin(theta)- mu*Math.cos(theta));
            System.out.println("The acceleration of the block is "+(acceleration));

            System.out.println("\n Choose what else you want to find and the formula to find it\n 1. v=u+at\n 2. v^2 = u^2 + 2as\n 3. s = ut + 1/2at^2\n 4. s = (v^2-u^2)/2a\n 5. t = (v-u)/a\n 6. a = (v^2-u^2)/2s\n 7. a = (v-u)/t");
            Formula();
            System.out.println("Do you wish to find some other physical quantitty too?\n If yes enter 1 else enter 0");
            double wish = sc.nextDouble();
            if (wish==1)
            {
                System.out.println("Choose what else you want to find and the formula to find it\n 1. v=u+at\n 2. v^2 = u^2 + 2as\n 3. s = ut + 1/2at^2\n 4. s = (v^2-u^2)/2a\n 5. t = (v-u)/a\n 6. a = (v^2-u^2)/2s\n 7. a = (v-u)/t");
                Formula();
            }
            if (wish==0)
            {
                System.out.println("okay :)");
            }
        }

        public static void multipleBlock()
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of blocks:");
            int n = sc.nextInt();  
            for(int i=1; i<=n; i++)
            {
                System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass:)");
                double mass = sc.nextDouble();
                System.out.println("Enter the value of frictional constant:");
                double mu = sc.nextDouble();
                System.out.println("Enter the angle of inclination (in degrees):");
                double theta = Math.toRadians(sc.nextDouble());
                double acceleration = 9.81*(Math.sin(theta)- mu*Math.cos(theta));
                System.out.println("The acceleration of the block is "+(acceleration));
            }
            System.out.println("\n Choose what else you want to find and the formula to find it\n 1. v=u+at\n 2. v^2 = u^2 + 2as\n 3. s = ut + 1/2at^2\n 4. s = (v^2-u^2)/2a\n 5. t = (v-u)/a\n 6. a = (v^2-u^2)/2s\n 7. a = (v-u)/t");
            Formula();
            System.out.println("Do you wish to find some other physical quantitty too?\n If yes enter 1 else enter 0");
            double wish = sc.nextDouble();
            if (wish==1)
            {
                System.out.println("Choose what else you want to find and the formula to find it\n 1. v=u+at\n 2. v^2 = u^2 + 2as\n 3. s = ut + 1/2at^2\n 4. s = (v^2-u^2)/2a\n 5. t = (v-u)/a\n 6. a = (v^2-u^2)/2s\n 7. a = (v-u)/t");
                Formula();
            }
            if (wish==0)
            {
                System.out.println("okay :)");
            }
        }


    public static void Pulleysystem() 
    {
        double g = 9.81;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Choose what you want to find and the formula to find it\n 1. v=u+at\n 2. v^2 = u^2 + 2as\n 3. s = ut + 1/2at^2\n 4. s = (v^2-u^2)/2a\n 5. t = (v-u)/a\n 6. a = (v^2-u^2)/2s\n 7. a = (v-u)/t");
        Formula();
        System.out.println("Enter the mass of the block (if weight is given kindly divide it by 9.81 to obtain the mass:)");
        double mass = sc.nextDouble();
        System.out.println("Enter the acceleration:");
        double acceleration = sc.nextDouble();
        double Tension = (mass*g)-(mass*acceleration);
        System.out.println("The tension in the string is "+(Tension)+ " N");
    }
}
