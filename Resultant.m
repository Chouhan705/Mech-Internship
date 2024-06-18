classdef Resultant
    properties
        Force,F,Fx=0,Fy=0,theta,quadrant,n,Res,Restheta,Fux,Fuy,Fu1,Fu2,Fu1theta,Fu2theta,Fu1quadrant,Fu2quadrant,direction;
    end
    methods (Static)
        function ConcurrentCoplanarSystem(self)
            self.n = input('\nEnter number of forces:');
            fprintf(['\nEnter magnitude, angle w.r.t x-axis and quandrant of all forces\n' ...
                'Please enter details in following format:[Magnitude,Angle,Quadrant]\n' ...
                'Enclosed in square brackets and seperated by commas.\n']);
            for i=1:self.n
                [self.F, self.theta, self.quadrant] = Tools.ForceInput(i);               
                self.theta = Tools.QuadCheck(self.quadrant, self.theta);
                self.theta = deg2rad(self.theta);
                self.Fx = self.Fx + self.F*cos(self.theta);
                self.Fy = self.Fy + self.F*sin(self.theta);
            end
            fprintf('\nThe magnitude of Resultant is %.2f\n', hypot(self.Fx,self.Fy));
            fprintf('The angle of resultant with x axis is %.2f\n', rad2deg(atan(self.Fy/self.Fx)));
        end
        function OneUnknownForceConcurrentCoplanarSystem(self)
                self.n = input('Enter number of forces:');
                fprintf('\nLet unknown force be force%d\n',self.n);
                fprintf(['\nEnter magnitude, angle w.r.t x-axis and quandrant of all forces\n' ...
                'Please enter details in following format:[Magnitude,Angle,Quadrant]\n' ...
                'Enclosed in square brackets and seperated by commas.\n\n']);
                fprintf('Now enter resultant force');
                [self.Res, self.Restheta, self.quadrant] = Tools.ForceInput(1);
                self.Restheta = Tools.QuadCheck(self.quadrant, self.Restheta);
                fprintf('\nNow enter details of known forces.\n');
                for i=1:(self.n-1)
                    [self.F,self.theta,self.quadrant] = Tools.ForceInput(i);                
                    self.theta = Tools.QuadCheck(self.quadrant, self.theta);
                    self.theta = deg2rad(self.theta);
                    self.Fx = self.Fx + self.F*cos(self.theta);
                    self.Fy = self.Fy + self.F*sin(self.theta);
                end
                self.Restheta = deg2rad(self.Restheta);
                self.Fux = self.Res*cos(self.Restheta)-self.Fx;
                self.Fuy = self.Res*sin(self.Restheta)-self.Fy;
                fprintf('\nMagnitude of unknown force is %.2f\n',hypot(self.Fux,self.Fuy));
                fprintf('Angle of unknown force with x axis is %.2f',rad2deg(atan(self.Fuy/self.Fux)));
        end
        function TwoUnknownForcesConcurrentCoplanarSystem(self)
                self.n = input('Enter number of forces:');
                fprintf('\nLet unknown force be force%d\n',self.n);
                fprintf(['\nEnter magnitude, angle w.r.t x-axis and quandrant of all forces\n' ...
                'Please enter details in following format:[Magnitude,Angle,Quadrant]\n' ...
                'Enclosed in square brackets and seperated by commas.\n\n']);
                fprintf('Now enter resultant force');
                [self.Res, self.Restheta, self.quadrant] = Tools.ForceInput(1);
                self.Restheta = Tools.QuadCheck(self.quadrant, self.Restheta);
                self.Restheta = deg2rad(self.Restheta);
                fprintf('\nNow enter details of known forces.\n');
                for i=1:(self.n-2)
                    [self.F,self.theta,self.quadrant] = Tools.ForceInput(i);                
                    self.theta = Tools.QuadCheck(self.quadrant, self.theta);
                    self.theta = deg2rad(self.theta);
                    self.Fx = self.Fx + self.F*cos(self.theta);
                    self.Fy = self.Fy + self.F*sin(self.theta);
                end
                fprintf('Now enter the two unknown forces.\nEnter magnitude of unknown force as 0');
                [self.Fu1, self.Fu1theta, self.Fu1quadrant] = Tools.ForceInput(1);
                [self.Fu2, self.Fu2theta, self.Fu2quadrant] = Tools.ForceInput(2);
                self.Fu1theta = Tools.QuadCheck(self.Fu1quadrant, self.Fu1theta);
                self.Fu2theta = Tools.QuadCheck(self.Fu2quadrant, self.Fu2theta);
                self.Fu1theta = deg2rad(self.Fu1theta);
                self.Fu2theta = deg2rad(self.Fu2theta);
                syms x y
                eqn1 = x*cos(self.Fu1theta) + y*cos(self.Fu2theta) == (self.Res*cos(self.Restheta) - self.Fx);
                eqn2 = x*sin(self.Fu1theta) + y*sin(self.Fu2theta) == (self.Res*sin(self.Restheta) - self.Fy);
                [sol] = Tools.SimultaneousEqn(eqn1,eqn2);
                fprintf('The magnitude of Unknown Force A and B is %.2f and %.2f respectively\n',sol(1),sol(2));
        end
        function ParallelCoplanarSystem(self)
            self.n = input('Enter number of forces:');
            fprintf(['\nEnter magnitude and direction of all forces\n' ...
                'Please enter details in following format:[Magnitude,Direction]\n' ...
                'Enclosed in square brackets and seperated by commas.\n\n' ...
                'Accepted value:\n' ...
                'Direction : 1 if force heading upwards else 0 if downwards.\n']);
            for i=1:self.n
                [self.F,self.direction] = Tools.TwoVariableInput(i);
                switch direction
                    case 1
                        self.Res = self.Res + self.F;
                    case 0
                        self.Res = self.Res - self.F;
                    otherwise
                        fprintf('Enter valid direction.');
                end
            end
            fprintf('The resultant of the force system is %.2f',self.Res);
        end
        function OneUnknownParallelForceSystem(self)
            self.n = input('Enter the number of forces:');
            fprintf(['\nEnter magnitude and direction of all forces\n' ...
                'Please enter details in following format:[Magnitude,Direction]\n' ...
                'Enclosed in square brackets and seperated by commas.\n\n' ...
                'Accepted value:\n' ...
                'Direction : 1 if force heading upwards else 0 if downwards.\n']);
            fprintf('\nEnter value of resultant:')
            [self.Res, self.direction] = Tools.TwoVariableInput(1);
            if self.direction == 0
                self.Res = -1*self.Res;
            end
            fprintf('Enter the values of known forces:');
            self.Fy = 0;
            for i=1:(self.n-1)
                [self.F,self.direction] = Tools.TwoVariableInput(i);
                switch self.direction
                    case 1
                        self.Fy = self.Fy + self.F;
                    case 0
                        self.Fy = self.Fy - self.F;
                    otherwise
                        fprintf('Enter valid direction.');
                end
            end
            fprintf('\nThe magnitude of unknown force is %.2f',self.Res-self.Fy);
        end
        function GeneralForceSystem(self)
            self.n = input('\nEnter number of forces:');
            fprintf(['\nEnter magnitude, angle w.r.t x-axis and quandrant of all forces\n' ...
                'Please enter details in following format:[Magnitude,Angle,Quadrant]\n' ...
                'Enclosed in square brackets and seperated by commas.\n']);
            for i=1:self.n
                [self.F, self.theta, self.quadrant] = Tools.ForceInput(i);               
                self.theta = Tools.QuadCheck(self.quadrant, self.theta);
                self.theta = deg2rad(self.theta);
                self.Fx = self.Fx + self.F*cos(self.theta);
                self.Fy = self.Fy + self.F*sin(self.theta);
            end
            fprintf('\nThe magnitude of Resultant is %.2f\n', hypot(self.Fx,self.Fy));
            fprintf('The angle of resultant with x axis is %.2f\n', rad2deg(atan(self.Fy/self.Fx)));
        end
    end
end
