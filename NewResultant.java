
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
            case 2:
                OneUnknownForce ouf = new OneUnknownForce() ;
                ouf.getOneUnknownFroce () ;
                break ;
            case 3:
                TwoUnknownForce tuf = new TwoUnknownForce() ;
                tuf.getTwoUnknownForces() ;
                break ;
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
        char slope ;
        double h =0 , v = 0 ;
        for(int i = 0 ; i < n ; i++)
        {
            System.out.println("Force " + (i+1)) ;
            System.out.println("Choose the Quadrant the force is present in") ;
            System.out.println("Either 1, 2, 3 or 4") ;
            quadrant = sc.nextInt();
            System.out.println("Enter the magnitude of the force") ;
            magnitude[i] = sc.nextDouble() ;
            System.out.println("If instead of angle there is slope present \nenter S \nIf not then enter n ") ;
            slope = sc.next().charAt(0);
            if(slope == 's' || slope == 'S' )
            {
                System.out.println("Enter the horizontal lenght") ;
                h=sc.nextDouble();
                System.out.println("Enter the vertical length") ;
                v = sc.nextDouble();
                if( quadrant ==1)
                {
                    angle[i] = Math.atan(v/h) ;
                }
                if( quadrant ==2)
                {
                    angle[i] = Math.PI - Math.atan(v/h) ;
                }
                if( quadrant ==3)
                {
                    angle[i] = Math.PI + Math.atan(v/h) ;
                }
                if( quadrant ==4)
                {
                    angle[i] = (2*Math.PI) - Math.atan(v/h) ;
                }
                
            }
            else
            {
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
                    angle[i] = (2*Math.PI) - angle[i] ;
                }
            }

            fx += (magnitude[i]) * ((Math.cos(angle[i]))) ;
            fy += (magnitude[i]) * ((Math.sin(angle[i]))) ;
            
        }
        
        double resultantAngle = Math.toDegrees(Math.atan(fy/fx)) ;
        double resultantMagnitude = Math.hypot(fx , fy) ;
        if ( fx < 0 && fy < 0 )
        {
            resultantAngle = 0 - resultantAngle ;
        }
    
        System.out.format("The resultant magnitude is : %.2f" , resultantMagnitude) ;
        System.out.println("") ;
        System.out.format("The resultant angle is : %.2f" , resultantAngle) ;
        
        

        
    }
}

class OneUnknownForce
{
    void getOneUnknownFroce()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter the number of forces apart from the unknown force and resultant");
        int n = sc.nextInt() ;
        double[] angle = new double[n] ;
        double[] magnitude = new double[n] ;
        int quadrant= 0 ;
        double fx =0 , fy = 0  ;
        char slope ;
        double h =0 , v = 0 ;
        for(int i = 0 ; i < n ; i++)
        {
            System.out.println("Force " + (i+1)) ;
            System.out.println("Choose the Quadrant the force is present in") ;
            System.out.println("Either 1, 2, 3 or 4") ;
            quadrant = sc.nextInt();
            System.out.println("Enter the magnitude of the force") ;
            magnitude[i] = sc.nextDouble() ;
            System.out.println("If instead of angle there is slope present \nenter S \nIf not then enter n ") ;
            slope = sc.next().charAt(0);
            if(slope == 's' || slope == 'S' )
            {
                System.out.println("Enter the horizontal lenght") ;
                h=sc.nextDouble();
                System.out.println("Enter the vertical length") ;
                v = sc.nextDouble();
                if( quadrant ==1)
                {
                    angle[i] = Math.atan(v/h) ;
                }
                if( quadrant ==2)
                {
                    angle[i] = Math.PI - Math.atan(v/h) ;
                }
                if( quadrant ==3)
                {
                    angle[i] = Math.PI + Math.atan(v/h) ;
                }
                if( quadrant ==4)
                {
                    angle[i] = (2*Math.PI) - Math.atan(v/h) ;
                }
                
            }
            else
            {
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
                    angle[i] = (2*Math.PI) - angle[i] ;
                }
            }

            fx += (magnitude[i]) * ((Math.cos(angle[i]))) ;
            fy += (magnitude[i]) * ((Math.sin(angle[i]))) ;
        }
        System.out.println("Enter the details for the Resultant") ;
        double resMagnitude = 0 , resAngle = 0;
        double resX = 0 , resY =0 ;
        char resSlope ;
        double resH = 0 , resV = 0 ;
        int resQuadrant ;
        System.out.println("Choose the Quadrant the force is present in") ;
        resQuadrant = sc.nextInt();
        System.out.println("Enter the magnitude of the force") ;
        resMagnitude = sc.nextDouble() ;
        System.out.println("If instead of angle there is slope present \nenter S \nIf not then enter n ") ;
        resSlope = sc.next().charAt(0);
        if(resSlope == 's' || resSlope == 'S' )
        {
            System.out.println("Enter the horizontal lenght") ;
            resH=sc.nextDouble();
            System.out.println("Enter the vertical length") ;
            resV = sc.nextDouble();
            if( resQuadrant ==1)
            {
                resAngle = Math.atan(resV/resH) ;
            }
            if( resQuadrant ==2)
            {
                resAngle = Math.PI - Math.atan(resV/resH) ;
            }
            if( resQuadrant ==3)
            {
                resAngle = Math.PI + Math.atan(resV/resH) ;
            }
            if( resQuadrant ==4)
            {
                resAngle = (2*Math.PI) - Math.atan(resV/resH) ;
            }
            
        }
        else
        {
            System.out.println("Enter the angle of the force with the respective x axis of the quadrant") ;
             resAngle= Math.toRadians(sc.nextDouble()) ;
        
            if(resQuadrant == 1)
            {
                resAngle = resAngle ;
            }
            if(resQuadrant == 2)
            {
                resAngle = (Math.PI) - resAngle ;
            }
            if(resQuadrant == 3)
            {
                resAngle = Math.PI + resAngle ;
            }
            if(resQuadrant == 4)
            resAngle = (2*Math.PI) - resAngle ;
        }
        resX = (resMagnitude) * ((Math.cos(resAngle))) ;
        resY = (resMagnitude) * ((Math.sin(resAngle))) ;

        double uFX = resX - fx ;
        double uFY = resY - fy ;
        double uAngle = Math.toDegrees(Math.atan(uFY/uFX)) ;
        double uMagnitude = Math.hypot(uFX , uFY) ;
        
        if (uFX < 0 && Ufy < 0)
        {
            uAngle = 0 - uAngle ;
        }

        System.out.println("The magnitude of the unknown force is :") ;
        System.out.format("%.2f",uMagnitude) ;
        System.out.println(" The angle of the unknow force is :") ;
        System.out.format("%.2f",uAngle) ;


    }
}

class TwoUnknownForce
{
    void getTwoUnknownForces()
    {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter the number of forces apart from the unknown force and resultant");
        int n = sc.nextInt() ;
        double[] angle = new double[n] ;
        double[] magnitude = new double[n] ;
        int quadrant= 0 ;
        double fx =0 , fy = 0  ;
        char slope ;
        double h =0 , v = 0 ;
        for(int i = 0 ; i < n ; i++)
        {
            System.out.println("Force " + (i+1)) ;
            System.out.println("Choose the Quadrant the force is present in") ;
            System.out.println("Either 1, 2, 3 or 4") ;
            quadrant = sc.nextInt();
            System.out.println("Enter the magnitude of the force") ;
            magnitude[i] = sc.nextDouble() ;
            System.out.println("If instead of angle there is slope present \nenter S \nIf not then enter n ") ;
            slope = sc.next().charAt(0);
            if(slope == 's' || slope == 'S' )
            {
                System.out.println("Enter the horizontal lenght") ;
                h=sc.nextDouble();
                System.out.println("Enter the vertical length") ;
                v = sc.nextDouble();
                if( quadrant ==1)
                {
                    angle[i] = Math.atan(v/h) ;
                }
                if( quadrant ==2)
                {
                    angle[i] = Math.PI - Math.atan(v/h) ;
                }
                if( quadrant ==3)
                {
                    angle[i] = Math.PI + Math.atan(v/h) ;
                }
                if( quadrant ==4)
                {
                    angle[i] = (2*Math.PI) - Math.atan(v/h) ;
                }
                
            }
            else
            {
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
                    angle[i] = (2*Math.PI) - angle[i] ;
                }
            }

            fx += (magnitude[i]) * ((Math.cos(angle[i]))) ;
            fy += (magnitude[i]) * ((Math.sin(angle[i]))) ;
        }

        System.out.println("Enter the details for the Resultant") ;
        double resMagnitude = 0 , resAngle = 0;
        double resX = 0 , resY =0 ;
        char resSlope ;
        double resH = 0 , resV = 0 ;
        int resQuadrant ;
        System.out.println("Choose the Quadrant the force is present in") ;
        resQuadrant = sc.nextInt();
        System.out.println("Enter the magnitude of the force") ;
        resMagnitude = sc.nextDouble() ;
        System.out.println("If instead of angle there is slope present \nenter S \nIf not then enter n ") ;
        resSlope = sc.next().charAt(0);
        if(resSlope == 's' || resSlope == 'S' )
        {
            System.out.println("Enter the horizontal lenght") ;
            resH=sc.nextDouble();
            System.out.println("Enter the vertical length") ;
            resV = sc.nextDouble();
            if( resQuadrant ==1)
            {
                resAngle = Math.atan(resV/resH) ;
            }
            if( resQuadrant ==2)
            {
                resAngle = Math.PI - Math.atan(resV/resH) ;
            }
            if( resQuadrant ==3)
            {
                resAngle = Math.PI + Math.atan(resV/resH) ;
            }
            if( resQuadrant ==4)
            {
                resAngle = (2*Math.PI) - Math.atan(resV/resH) ;
            }
            
        }
        else
        {
            System.out.println("Enter the angle of the force with the respective x axis of the quadrant") ;
             resAngle= Math.toRadians(sc.nextDouble()) ;
        
            if(resQuadrant == 1)
            {
                resAngle = resAngle ;
            }
            if(resQuadrant == 2)
            {
                resAngle = (Math.PI) - resAngle ;
            }
            if(resQuadrant == 3)
            {
                resAngle = Math.PI + resAngle ;
            }
            if(resQuadrant == 4)
            resAngle = (2*Math.PI) - resAngle ;
        }
        resX = (resMagnitude) * ((Math.cos(resAngle))) ;
        resY = (resMagnitude) * ((Math.sin(resAngle))) ;

        System.out.println("Enter the information of the unknown forces") ;
    
        double[] unknownForce = new double[2] ;
        for( int i = 0 ; i < 2 ; i++)
        {
            System.out.println("Enter the angle of force" + (i+1));
            unknownForce[i] = sc.nextDouble() ;
        }
        double unknownForceAngleA=0 , unknownForceAngleB=0 ;
        int counter =0;
        int axis=0 ;
        for(int i = 0 ; i< 2 ;i++)
        {
            if(unknownForce[i] == 0 ||  unknownForce[i] == 180 )
            {
                unknownForceAngleA = Math.toRadians(unknownForce[i]) ;
                axis = 1;
            }
            if( unknownForce[i] == 90 || unknownForce[i] == 270 )
            {
                unknownForceAngleA = Math.toRadians(unknownForce[i]) ;
                axis = 2;
            }
            else
            {
                unknownForceAngleB = Math.toRadians(unknownForce[i]) ;
                counter++;
            }
        }
        if (counter > 1)
        {
            System.out.println("Cannot calculate for this specific case") ;
        }
        else
        {
            if ( axis ==1 )
            {
                double unknownForceB = (resY - fy)/Math.sin(unknownForceAngleB) ;
                double unknownForceA = (resX - (fx+unknownForceB*Math.cos(unknownForceAngleB)))/Math.cos(unknownForceAngleA) ;
                System.out.println("The unknown forces are :");
                System.out.format("Force A : %.2f" , unknownForceA ,"with angle %.2f ", unknownForceAngleA);
                System.out.format("Force B : %.2f" , unknownForceB, "with angle %.2f ", unknownForceAngleB);
            }
            if ( axis == 2)

            {
                double unknownForceB = (resX - fx)/Math.cos(unknownForceAngleB) ;
                double unknownForceA = (resY - (fy+unknownForceB*Math.sin(unknownForceAngleB)))/Math.sin(unknownForceAngleA) ;
                System.out.println("The unknown forces are :");
                System.out.format("Force A : %.2f" , unknownForceA +"with angle %.2f ", unknownForceAngleA);
                System.out.format("Force B : %.2f" , unknownForceB +"with angle %.2f ", unknownForceAngleB);
            }
        }
            
    }
}
