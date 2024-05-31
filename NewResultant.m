classdef NewResultant
    properties
        option,Force,F,Fx=0,Fy=0,theta,Theta,quadrant,n,values,Res,ResCell,Restheta,Fux,Fuy
    end
    
    methods
        function CalcRes(self)
            self.n = input('\nEnter number of forces:');
            fprintf(['\nEnter magnitude, angle w.r.t x-axis and quandrant of all forces\n' ...
                'Please enter details in following format:[Magnitude,Angle,Quadrant]\n' ...
                'Enclosed in square brackets and seperated by commas.\n']);
            for i=1:self.n
                fprintf('\nEnter force%d:',i);
                self.Force = input('');
                ForceCell = num2cell(self.Force);
                [self.F,self.theta,self.quadrant] = ForceCell{:};                
                switch self.quadrant
                        case 1
                            self.theta = self.theta;
                        case 2
                            self.theta = 180 - self.theta;
                        case 3
                            self.theta = 180 + self.theta;
                        case 4
                            self.theta = 270 + self.theta;
                        otherwise
                            disp('\nEnter a valid quadrant number!!\n');
                end
                self.theta = deg2rad(self.theta);
                self.Fx = self.Fx + self.F*cos(self.theta);
                self.Fy = self.Fy + self.F*sin(self.theta);
            end
            self.Res = hypot(self.Fx,self.Fy);
            self.Restheta = rad2deg(atan(self.Fy/self.Fx));
            fprintf('\nThe magnitude of Resultant is %.2f\n', self.Res);
            fprintf('The angle of resultant with x axis is %.2f\n', self.Restheta);
        end
        function UnknownForce(self)
                self.n = input('Enter number of forces:');
                fprintf('\nLet unknown force be force%d\n',self.n);
                fprintf(['\nEnter magnitude, angle w.r.t x-axis and quandrant of all forces\n' ...
                'Please enter details in following format:[Magnitude,Angle,Quadrant]\n' ...
                'Enclosed in square brackets and seperated by commas.\n\n']);
                fprintf('Enter resultant force:');
                self.Res = input('');
                self.ResCell = num2cell(self.Res);
                [self.Res,self.Restheta,self.quadrant] = self.ResCell{:};
                switch self.quadrant
                        case 1
                            self.Restheta = self.Restheta;
                        case 2
                            self.Restheta = 180 - self.Restheta;
                        case 3
                            self.Restheta = 180 + self.Restheta;
                        case 4
                            self.Restheta = 270 + self.Restheta;
                        otherwise
                            disp('\nEnter a valid quadrant number!!\n');
                end
                fprintf('\nNow enter details of known forces.\n');
                for i=1:(self.n-1)
                    fprintf('Enter force%d:',i);
                    self.Force = input('');
                    ForceCell = num2cell(self.Force);
                    [self.F,self.theta,self.quadrant] = ForceCell{:};                
                    switch self.quadrant
                        case 1
                            self.theta = self.theta;
                        case 2
                            self.theta = 180 - self.theta;
                        case 3
                            self.theta = 180 + self.theta;
                        case 4
                            self.theta = 270 + self.theta;
                        otherwise
                            disp('\nEnter a valid quadrant number!!\n');
                            
                    end
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
    end
end