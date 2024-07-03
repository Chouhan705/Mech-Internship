clc;
clear;
% Taking input of acceleration-time graph
x=input('Enter the x-axis values enclosed in square brackets separated by commas ');
y=input('Enter the y-axis values enclosed in square brackets separated by commas ');
% Plotting acceleration-time graph
figure;
plot(x,y,'o','MarkerSize',10,'DisplayName','Original Points');
hold on;
plot(x,y,'DisplayName','Graph Lines');
xlabel('Time');
ylabel('Acceleration');
title('Acceleration-Time Graph');
hold off;
grid on
n=length(x)-1;
% Calculating velocity on given points
V=0;vpoint=V;
for i=1:n
    a=x(i:i+1);
    b=y(i:i+1);
    V=V+trapz(a,b);
    vpoint=[vpoint,V];
end
fprintf('Values of velocity are \n');
disp(vpoint);
% Plotting velocity-time graph
figure;      
p = polyfit(x,vpoint,n);
x_finer = linspace(min(x),max(x),100);
y_fitted = polyval(p,x_finer);
plot(x,vpoint,'o','MarkerSize',10,'DisplayName','Original Points');
hold on;
plot(x_finer,y_fitted,'r--','LineWidth',1,'DisplayName','Fitted Curve');
xlabel('Time');
ylabel('Velocity');
title('Velocity-Time Graph');
grid on
hold off;
% Calculating displacement on given points
S=0;spoint=S;
for i=1:n
    if (y(i)<y(i+1) && y(i+1)==0 && y(i)<0)
        S = S+(((x(i+1)-x(i))*(vpoint(i)-vpoint(i+1)))/3)+((x(i+1)-x(i))*vpoint(i+1));
        spoint=[spoint,S];
    elseif (y(i)>y(i+1) && y(i+1)>=0)
        S = S+((2*(x(i+1)-x(i))*(vpoint(i+1)-vpoint(i)))/3)+((x(i+1)-x(i))*vpoint(i));
        spoint=[spoint,S];
    elseif (y(i)>y(i+1) && y(i+1)<0)
        S =S+((2*(x(i+1)-x(i))*(vpoint(i)-vpoint(i+1)))/3)+((x(i+1)-x(i))*(vpoint(i)-vpoint(i+1)));
        spoint=[spoint,S];
    elseif (y(i)<y(i+1) && y(i+1)>=0)
        S = S+(((x(i+1)-x(i))*(vpoint(i+1)-vpoint(i)))/3)+((x(i+1)-x(i))*vpoint(i));
        spoint=[spoint,S];
    elseif (y(i)==y(i+1))
        S = S+((x(i+1)-x(i))*(vpoint(i+1)-vpoint(i)))/2;
        spoint=[spoint,S];
    else
        fprintf('\nNone of the condition matches')
    end
end
fprintf('Values of displacement are \n');
disp(spoint);
% Plotting displacement-time graph
figure;
p = polyfit(x,spoint,n);
x_finer = linspace(min(x),max(x),100);
y_fitted = polyval(p,x_finer);
plot(x,spoint,'o','MarkerSize',10,'DisplayName','Original Points');
hold on
plot(x_finer,y_fitted,'r--','LineWidth',1.5,'DisplayName','Fitted Curve');
xlabel('Time');
ylabel('Displacement');
title('Displacement-Time Graph');
grid on
hold off