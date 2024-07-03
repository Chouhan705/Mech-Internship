clc;
clear;
% Taking input of velocity-time graph
x=input('Enter the x-axis values enclosed in square brackets separated by commas ');
y=input('Enter the x-axis values enclosed in square brackets separated by commas ');
% Plotting velocity-time graph
figure;
plot(x,y);
xlabel('Time');
ylabel('Velocity');
title('Velocity-Time Graph');
grid on
n=length(x)-1;
A=0;apoint=A;
% Calculating acceleration on given points
for i=1:n
    A=(y(i+1)-y(i))/(x(i+1)-x(i));
    apoint=[apoint,A];
end
fprintf('Values of acceleration are \n');
disp(apoint);
%Calculating values for uniform acceleration
q=length(x)+n;
X=zeros(1,q);
A=zeros(1,q);
j=1;
for i=1:length(apoint)
    if i==1
        X(j) = x(i);
        X(j+1) = x(i);
        A(j) = apoint(i);
    elseif i==length(apoint)
        X(j) = x(i);
        A(j-1) = apoint(i);
        A(j) = apoint(i);
    else
        X(j) = x(i);
        X(j+1) = x(i);
        A(j-1) = apoint(i);
        A(j) = apoint(i);
    end
    j = j+2;
end
% Plotting acceleration-time graph
figure;
plot(X,A,'o','MarkerSize',10,'DisplayName','Original Points');
hold on;
plot(X,A,'r--','LineWidth',1.5,'DisplayName','Fitted Curve');
xlabel('Time');
ylabel('Acceleration');
title('Acceleration-Time Graph');
grid on
hold off;
S=0;spoint=S;
% Calculating displacement on given points
for i=1:n
    a=x(i:i+1);
    b=y(i:i+1);
    S=S+trapz(a,b);
    spoint=[spoint,S];
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
