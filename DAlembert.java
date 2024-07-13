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
            System.out.println("Choose the operation to perform");
            System.out.println("1. Find tension with One Block");
            System.out.println("2. Find tension with Two blocks");
            int rope = sc.nextInt();
            if(rope==1)
            {
                Pulleysystem();
            }
            else if(rope == 2)
            {
                TwoBlocksWithRope();
            }
            else
            {
                System.out.println("Invalid choice");
            }
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
    public static void TwoBlocksWithRope()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the mass of the block force is applied on( gravitational too ) [divide by 9.81 if weight is given]");
        double mass1 = sc.nextDouble();
        System.out.println("Enter the mass of the second block[divide by 9.81 if weight is given]");
        double mass2 = sc.nextDouble();
        System.out.println("Enter the frictional constant (if none enter 1)");
        double mu = sc.nextDouble();
        System.out.println("Choose the arrangement of the blocks");
        System.out.println("1. On the same plane");
        System.out.println("2. Hanging on the sides of a pulley");
        int arrangement = sc.nextInt();
        switch(arrangement)
        {
            case 1:
            System.out.println("Enter the force applied");
            double force = sc.nextDouble();
            double normal1 = (mass1*9.81);
            double normal2 = (mass2*9.81);
            // eq1 force - t - mu*N1 = m1(a)
            // eq2 t-mu*N2 = m2(a)

            // m1(a) + t = F - mu*N1
            // m2(a) - t = - mu*N2
            double Coefx1=mass1;
            double Coefy1 = 1;
            double Const1 = force - mu*normal1;
            double Coefx2 = mass2;
            double Coefy2 = -1;
            double Const2 = - mu*normal2;
            double[] Answer = SimultaneusEqnSolver(Coefx1,Coefy1,Const1,Coefx2,Coefy2,Const2);
            double Acceleration = Answer[0];
            double Tension = Answer[1];
            System.out.printf("The acceleration of the blocks is %.2f ",(Acceleration));
            System.out.printf("m/s^2");
            System.out.println();
            System.out.printf("The tension in the string is %.2f",(Tension));
            System.out.printf("N");
            break;

            case 2:
            double normal = mass2*9.81;
            // m1(a) + t = m1*g
            // m2(a) - t = - mu*N
            Coefx1=mass1;
            Coefy1 = 1;
            Const1 = mass1*9.81;
            Coefx2 = mass2;
            Coefy2 = -1;
            Const2 = - mu*normal;
            Answer = SimultaneusEqnSolver(Coefx1,Coefy1,Const1,Coefx2,Coefy2,Const2);
            Acceleration = Answer[0];
            Tension = Answer[1];
            System.out.printf("The acceleration of the blocks is %.2f ",(Acceleration));
            System.out.printf("m/s^2");
            System.out.println();
            System.out.printf("The tension in the string is %.2f",(Tension));
            System.out.printf("N");
            break;

            default:
            System.out.println("Invalid choice");
            break;
        }
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
