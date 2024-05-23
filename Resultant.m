classdef Resultant
        properties
            F=[],Fx=0,Fy=0,theta=[],Res,Restheta,Fux,Fuy
        end
        methods
            function CalcRes(self)
                n = input('Enter number of forces:');
                for i=1:n
                    self.F(i) = input('Enter magnitude of force:');
                    self.theta(i) = (input('Enter angle of force with x axis in degrees:'));
                    self.theta(i) = deg2rad(self.theta(i));
                    self.Fx = self.Fx + (self.F(i)*cos(self.theta(i)));
                    self.Fy = self.Fy + (self.F(i)*sin(self.theta(i)));
                    self.Res = hypot(self.Fx , self.Fy); 
                    self.Restheta = atan(self.Fy/self.Fx);
                end
                fprintf('The value of resultant is %.2f\n',self.Res);
                fprintf('The angle of resultant with x axis is %.2f',rad2deg(self.Restheta));
            end
            function UnknownForce(self)
                n = input('Enter number of forces:');
                fprintf('Let unknown force be force%d\n',n)
                self.Res = input('Enter magnitude of resultant force:');
                    self.Restheta = input('Enter angle of resultant force with x axis in degrees:');
                    self.Restheta = deg2rad(self.Restheta);
                for i=1:(n-1)
                    self.F(i) = input('Enter magnitude of force:');
                    self.theta(i) = input('Enter angle of force with x axis in degrees:');
                    self.theta(i) = deg2rad(self.theta(i));
                    self.Fx = self.Fx + self.F(i)*cos(self.theta(i));
                    self.Fy = self.Fy + self.F(i)*sin(self.theta(i));
                end
                self.Fux = self.Res*cos(self.Restheta)-self.Fx;
                self.Fuy = self.Res*sin(self.Restheta)-self.Fy;
                fprintf('Magnitude of unknown force is %.2f\n',hypot(self.Fux,self.Fuy));
                fprintf('Angle of unknown force with x axis is %.2f',rad2deg(atan(self.Fuy/self.Fux)));
            end
           
        end
end