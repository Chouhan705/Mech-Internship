import java.util.*;
import java.lang.*;
import java.text.*;

class NewResultant
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
                BasicResultant br = new BasicResultant() ;
                br.getResultant() ;
                break ;
            /*case 2:
                OneUnknownForce ouf = new OneUnknownForce() ;
                ouf.getResultant() ;
                break ;
            case 3:
                TwoUnknownForce tuf = new TwoUnknownForce() ;
                tuf.getResultant() ;
                break ;*/
            default:
                System.out.println("Invalid Choice") ;
                break ;
        }
    }
}

class BasicResultant
{

    
    void getResultant()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter the number of forces") ;
        int n = sc.nextInt() ;
        double[] angle = new double[n] ;
        double[] magnitude = new double[n] ;
        int quadrant= 0 ;
        double fx =0 , fy = 0  ;
        
        for(int i = 0 ; i < n ; i++)
        {
            System.out.println("Force " + (i+1)) ;
            System.out.println("Choose the Quadrant the force is present in") ;
            System.out.println("Either 1, 2, 3 or 4") ;
            quadrant = sc.nextInt();
            System.out.println("Enter the magnitude of the force") ;
            magnitude[i] = sc.nextDouble() ;

            System.out.println("Enter the angle of the force with the respective x axis of the quadrant") ;
            angle[i] = Math.toRadians(sc.nextDouble()) ;
            if(quadrant == 1)
            {
                angle[i] = angle[i] ;
            }
            if(quadrant == 2)
            {
                angle[i] = (Math.PI) - angle[i] ;
            }
            if(quadrant == 3)
            {
                angle[i] = Math.PI + angle[i] ;
            }
            if(quadrant == 4)
            {
                angle[i] = 2*(Math.PI) - angle[i] ;
            }
            

            fx += (magnitude[i]) * ((Math.cos(angle[i]))) ;
            fy += (magnitude[i]) * ((Math.sin(angle[i]))) ;
            
        }
        
        double resultantAngle = Math.toDegrees(Math.atan(fy/fx)) ;
        double resultantMagnitude = Math.hypot(fx , fy) ;
    
        System.out.format("The resultant angle is %.2f" , resultantAngle) ;
        System.out.println("") ;
        System.out.format("The resultant magnitude is %.2f" , resultantMagnitude) ;

        
    }
}