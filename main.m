clc;
result = NewResultant;
option = input('\nOperation Menu\n\n1. Find Resultant.\n2. Find 1 Unknown Force\nEnter operation number:');
switch option
    case 1
        result.CalcRes();
    case 2
        result.UnknownForce();
    otherwise
        fprintf('Enter a valid operation number!!');
end