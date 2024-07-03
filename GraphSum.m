classdef GraphSum
    properties
        x,y,n,V,vpoint,S,spoint,a,b,A,apoint,p,x_finer,y_fitted,X,q,j
    end
    
    methods
        function atgraph(static)
            % Taking input of acceleration-time graph
            fprintf('\nFinding Velocity-Time and Displacement-Time Graph from Acceleration-Time graph\n');
            static.x=input('Enter the x-axis values enclosed in square brackets separated by commas ');
            static.y=input('Enter the y-axis values enclosed in square brackets separated by commas ');
            % Plotting acceleration-time graph
            figure;
            plot(static.x,static.y,'o','MarkerSize',10,'DisplayName','Original Points');
            hold on
            plot(static.x,static.y,'DisplayName','Graph Lines');
            xlabel('Time');
            ylabel('Acceleration');
            title('Acceleration-Time Graph');
            hold off
            grid on
            static.n=length(static.x)-1;
            % Calculating velocity on given points
            static.V=0;static.vpoint=static.V;
            for i=1:static.n
                static.a=static.x(i:i+1);
                static.b=static.y(i:i+1);
                static.V=static.V+trapz(static.a,static.b);
                static.vpoint=[static.vpoint,static.V];
            end
            fprintf('Values of velocity are \n');
            disp(static.vpoint);
            % Plotting velocity-time graph
            figure;
            static.p = polyfit(static.x,static.vpoint,static.n);
            static.x_finer=linspace(min(static.x),max(static.x),100);
            static.y_fitted = polyval(static.p,static.x_finer);
            plot(static.x,static.vpoint,'o','MarkerSize',10,'DisplayName','Original Points');
            hold on;
            plot(static.x_finer,static.y_fitted,'r--','LineWidth',1,'DisplayName','Fitted Curve');
            xlabel('Time');
            ylabel('Velocity');
            title('Velocity-Time Graph');
            grid on
            hold off
            % Calculating displacement on given points
            static.S=0;static.spoint=static.S;
            for i=1:static.n
                if (static.y(i)<static.y(i+1) && static.y(i+1)==0 && static.y(i)<0)
                    static.S=static.S+(((static.x(i+1)-static.x(i))*(static.vpoint(i)-static.vpoint(i+1)))/3)+((static.x(i+1)-static.x(i))*static.vpoint(i+1));
                    static.spoint=[static.spoint,static.S];
                elseif (static.y(i)>static.y(i+1) && static.y(i+1)>=0)
                     static.S = static.S+((2*(static.x(i+1)-static.x(i))*(static.vpoint(i+1)-static.vpoint(i)))/3)+((static.x(i+1)-static.x(i))*static.vpoint(i));
                     static.spoint=[static.spoint,static.S];
                elseif (static.y(i)>static.y(i+1) && static.y(i+1)<0)
                    static.S =static.S+((2*(static.x(i+1)-static.x(i))*(static.vpoint(i)-static.vpoint(i+1)))/3)+((static.x(i+1)-static.x(i))*(static.vpoint(i)-static.vpoint(i+1)));
                    static.spoint=[static.spoint,static.S];
                elseif (static.y(i)<static.y(i+1) && static.y(i+1)>=0)
                    static.S = static.S+(((static.x(i+1)-static.x(i))*(static.vpoint(i+1)-static.vpoint(i)))/3)+((static.x(i+1)-static.x(i))*static.vpoint(i));
                    static.spoint=[static.spoint,static.S];
                elseif (static.y(i)==static.y(i+1))
                    static.S = static.S+((static.x(i+1)-static.x(i))*(static.vpoint(i+1)-static.vpoint(i)))/2+((static.x(i+1)-static.x(i))*static.vpoint(i));
                    static.spoint=[static.spoint,static.S];
                else
                    fprintf('\nNone of the condition matches');
                end
            end
            fprintf('Values of displacement are \n');
            disp(static.spoint);
            % Plotting displacement-time graph
            figure;
            static.p = polyfit(static.x,static.spoint,static.n);
            static.x_finer = linspace(min(static.x),max(static.x),100);
            static.y_fitted = polyval(static.p,static.x_finer);
            plot(static.x,static.spoint,'o','MarkerSize',10,'DisplayName','Original Points');
            hold on
            plot(static.x_finer,static.y_fitted,'r--','LineWidth',1,'DisplayName','Fitted Curve');
            xlabel('Time');
            ylabel('Displacement');
            title('Displacement-Time Graph');
            grid on
            hold off
        end
        function vtgraph(static)
            fprintf('\nFinding Acceleration-Time and Displacement-Time Graph from Velocity-Time graph\n')
            % Taking input of velocity-time graph
            static.x=input('Enter the x-axis values enclosed in square brackets separated by commas ');
            static.y=input('Enter the y-axis values enclosed in square brackets separated by commas ');
            % Plotting velocity-time graph
            figure;
            plot(static.x,static.y,'o','MarkerSize',10,'DisplayName','Original Points');
            hold on
            plot(static.x,static.y,'DisplayName','Graph Lines');
            xlabel('Time');
            ylabel('Velocity');
            title('Velocity-Time Graph');
            hold off
            grid on
            static.n=length(static.x)-1;
            % Calculating acceleration on given points
            static.A=0;static.apoint=static.A;
            for i=1:static.n
                static.A=(static.y(i+1)-static.y(i))/(static.x(i+1)-static.x(i));
                static.apoint=[static.apoint,static.A];
            end
            fprintf('Values of acceleration are \n');
            disp(static.apoint);
            static.q = length(static.x) + static.n;
            static.X=zeros(1,static.q);
            static.A=zeros(1,static.q);
            static.j=1;
            for i=1:length(static.apoint)
                if i==1
                    static.X(static.j)=static.x(i);
                    static.X(static.j+1) = static.x(i);
                    static.A(static.j) = static.apoint(i);
                elseif i==length(static.apoint)
                    static.X(static.j) = static.x(i);
                    static.A(static.j-1) = static.apoint(i);
                    static.A(static.j) = static.apoint(i);
                else
                    static.X(static.j) = static.x(i);
                    static.X(static.j+1) = static.x(i);
                    static.A(static.j-1) = static.apoint(i);
                    static.A(static.j) = static.apoint(i);
                end
                static.j=static.j+2;
            end
            % Plotting acceleration-time graph
            figure;
            plot(static.X,static.A,'o','MarkerSize',10,'DisplayName','Original Points');
            hold on;
            plot(static.X,static.A,'r--','LineWidth',1,'DisplayName','Fitted Curve');
            xlabel('Time');
            ylabel('Acceleration');
            title('Acceleration-Time Graph');
            grid on
            hold off
            static.S=0;static.spoint=static.S;
            % Calculating displacement on given points
            for i=1:static.n
                static.a=static.x(i:i+1);
                static.b=static.y(i:i+1);
                static.S=static.S+trapz(static.a,static.b);
                static.spoint=[static.spoint,static.S];
            end
            fprintf('Values of displacement are\n');
            disp(static.spoint);
            % Plotting displacement-time graph
            figure;
            static.p = polyfit(static.x,static.spoint,static.n);
            static.x_finer = linspace(min(static.x),max(static.x),100);
            static.y_fitted = polyval(static.p,static.x_finer);
            plot(static.x,static.spoint,'o','MarkerSize',10,'DisplayName','Original Points');
            hold on
            plot(static.x_finer,static.y_fitted,'r--','LineWidth',1,'DisplayName','Fitted Curve');
            xlabel('Time');
            ylabel('Displacement');
            title('Displacement-Time Graph');
            grid on
            hold off
        end
    end
end

