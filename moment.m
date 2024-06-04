clc;
clear;
disp('Finding moment of the system');
n=input('Enter the number of forces ');
Fy=0;
M=0;
fprintf('RESULTANT FORCE');
for i=1:n
    fprintf('\nForce %d',i);
    mag=input('\n Enter the magnitude of force ');
    s=input('Enter 1 if force is heading upwards and 0 for downwards');
    if s==1
        Fy=Fy+mag;
    else 
        Fy=Fy-mag;
    end
end
fprintf('\nTOTAL MOMENTUM');
for i=1:n
    
    fprintf('\nForce %d',i);
    mag=input('\n Enter the magnitude of force ');
    d=double(input('Enter the distance of force from refernce point '));
    s=input('Enter 1 if moment is anticloclwise and 0 if clockwise ');
    if s==1
        M= M + (d*mag);
    else 
        M = M - (d*mag);
    end
end
e=input('\n Enter 1 if extra moments are present else enter 0 ');
if e==1
    m=input('Enter the number of moments ');
    for i=1:m
        fprintf('Moment %d',i);
        mo=input('Enter the magnitude of moment ');
        s=input('Enter 2 if moment is clockwise else enter 1 ');
        if s==2
            M = M - mo;
        else 
            M = M + mo;
        end
    end
end
fprintf('Resultant force is %d',Fy);
fprintf('\n Total moment is %.2f',M);
if Fy==0
    fprintf('\n No force is present, only moment is there');
else
    fprintf('\n Distance of the force from reference point is %.2f ',M/Fy);
end