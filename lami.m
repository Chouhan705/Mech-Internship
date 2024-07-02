function Lami
    disp('Choose the system to solve');
    disp('1. One Force System');
    disp('2. Two Force System');
    disp('3. Loads');
    choice = input('Enter your choice: ');

    switch choice
        case 1
            OneForceSystem();
        case 2
            TwoForceSystem();
        case 3
            Loads();
        otherwise
            disp('Invalid Input');
    end
end

function OneForceSystem()
    ForceValues = lami();
    UnknownForce1 = ForceValues(1);
    UnknownForce2 = ForceValues(2);
    fprintf('Unknown Force one = %.2f N\n', UnknownForce1);
    fprintf('Unknown Force two = %.2f N\n', UnknownForce2);
end

function TwoForceSystem()
    ForceValues = lami();
    UnknownForce1 = ForceValues(1);
    UnknownForce2 = ForceValues(2);
    fprintf('Unknown Force one = %.2f N\n', UnknownForce1);
    fprintf('Unknown Force two = %.2f N\n', UnknownForce2);
    disp('Use these values for the second System of Forces.');
    ForceValues2 = lami();
    UnknownForce3 = ForceValues2(1);
    UnknownForce4 = ForceValues2(2);
    fprintf('Unknown Force three = %.2f N\n', UnknownForce3);
    fprintf('Unknown Force four = %.2f N\n', UnknownForce4);
end

function ForceValues = lami()
    knownForceMagnitude = input('Enter the Magnitude of the known force: ');
    knownForceAngle = deg2rad(input('Enter the Angle opposite to the known force: '));
    unknownAngle1 = deg2rad(input('Enter the Angle opposite to the unknown force 1: '));
    unknownAngle2 = deg2rad(input('Enter the Angle opposite to the unknown force 2: '));

    unknownForce1Magnitude = (knownForceMagnitude * sin(unknownAngle1)) / sin(knownForceAngle);
    unknownForce2Magnitude = (knownForceMagnitude * sin(unknownAngle2)) / sin(knownForceAngle);

    if (knownForceAngle + unknownAngle1 + unknownAngle2 ~= 2 * pi)
        disp('The sum of the angles is not equal to 360 degrees');
        disp('Re-check the angles entered');
        error('Invalid angles');
    end

    ForceValues = [unknownForce1Magnitude, unknownForce2Magnitude];
end

function Loads()
    values = InputMoment();
    FX = values(1);
    FY = values(2);
    MomentDueToForce = values(3);
    LoadValues = InputLoad();
    LX = LoadValues(1);
    LY = LoadValues(2);
    MomentDueToLoad = LoadValues(3);
    TotalFX = FX + LX;
    TotalFY = FY + LY;
    
    choice = input('If any extra moments are present enter 1 else 0: ');
    ExtraMoment = 0;
    if choice == 1
        ExtraMoment = InputExtraMoments();
    end
    TotalMoment = MomentDueToForce + MomentDueToLoad + ExtraMoment;

    if TotalFX < 0
        fprintf('Total FX: %.2f N Leftwards\n', abs(TotalFX));
    elseif TotalFX > 0
        fprintf('Total FX: %.2f N Rightwards\n', TotalFX);
    else
        disp('All forces cancel out in the X Direction');
    end

    if TotalFY < 0
        fprintf('Total FY: %.2f N Downwards\n', abs(TotalFY));
    elseif TotalFY > 0
        fprintf('Total FY: %.2f N Upwards\n', TotalFY);
    else
        disp('All forces cancel out in the Y Direction');
    end

    if TotalMoment < 0
        fprintf('Total Moment: %.2f N/m Clockwise\n', TotalMoment);
    elseif TotalMoment > 0
        fprintf('Total Moment: %.2f N/m Anti-Clockwise\n', TotalMoment);
    else
        fprintf('Total Moment: %.2f N/m\n', TotalMoment);
    end
end

function Fvalues = InputForce()
    clc;
    n = input('Enter the number of known forces: ');
    FX = 0;
    FY = 0;
    for i = 1:n
        clc;
        fprintf('Enter the information of force %d\n', i);
        quadrant = InputQuadrant();
        magnitude = InputMagnitude();
        disp('Select A for angle or S for slope');
        inputChoice = input('', 's');
        switch inputChoice
            case {'A', 'a'}
                angle = InputAngle(quadrant);
            case {'S', 's'}
                angle = InputSlope(quadrant);
            otherwise
                disp('Invalid Input');
        end
        FX = FX + CalcFX(magnitude, angle);
        FY = FY + CalcFY(magnitude, angle);
    end
    Fvalues = [FX, FY];
end

function quadrant = InputQuadrant()
    disp('Select the quadrant the force is present in:');
    disp('1. Quadrant 1');
    disp('2. Quadrant 2');
    disp('3. Quadrant 3');
    disp('4. Quadrant 4');
    quadrant = input('');
end

function magnitude = InputMagnitude()
    magnitude = input('Enter the magnitude of force: ');
end

function angle = InputAngle(quadrant)
    angle = input('Enter angle between the force and the respective X-Axis: ');
    switch quadrant
        case 1
            angle = deg2rad(angle);
        case 2
            angle = pi - deg2rad(angle);
        case 3
            angle = pi + deg2rad(angle);
        case 4
            angle = 2 * pi - deg2rad(angle);
    end
end

function angle = InputSlope(quadrant)
    horizontal = input('Enter the horizontal length for the slope: ');
    vertical = input('Enter the vertical length for the slope: ');
    angle = atan2(vertical, horizontal);
    switch quadrant
        case 2
            angle = pi - angle;
        case 3
            angle = pi + angle;
        case 4
            angle = 2 * pi - angle;
    end
end

function Finalvalues = InputMoment()
    clc;
    n = input('Enter the number of known forces: ');
    FX = 0;
    FY = 0;
    Moment = 0;
    for i = 1:n
        clc;
        fprintf('Enter the information of force %d\n', i);
        Inclination = input('Enter 1 if the force is inclined else 0: ');
        if Inclination == 1
            quadrant = InputQuadrant();
            magnitude = InputMagnitude();
            disp('Select A for angle or S for slope');
            inputChoice = input('', 's');
            switch inputChoice
                case {'A', 'a'}
                    angle = InputAngle(quadrant);
                case {'S', 's'}
                    angle = InputSlope(quadrant);
                otherwise
                    disp('Invalid Input');
            end
        else
            magnitude = InputMagnitude();
            disp('Choose the orientation of the force');
            disp('1. Upwards');
            disp('2. Downwards');
            direction = input('');
            if direction == 1
                angle = pi / 2;
            else
                angle = 3 * pi / 2;
            end
        end
        FX = FX + CalcFX(magnitude, angle);
        FY = FY + CalcFY(magnitude, angle);

        distance = input('Enter the distance from the reference point: ');
        disp('Select 1 for Moment acting Anticlockwise or 2 for Clockwise');
        direction = input('');
        Moment = Moment + CalcMoment(magnitude, distance, direction);
    end
    Finalvalues = [FX, FY, Moment];
end

function Moment = InputExtraMoments()
    clc;
    n = input('Enter the number of moments: ');
    Moment = 0;
    for i = 1:n
        magnitude = input(sprintf('Enter the Magnitude of the Moment %d: ', i));
        disp('Select 1 for Moment acting Anticlockwise or 2 for Clockwise');
        direction = input('');
        switch direction
            case 1
                Moment = Moment + magnitude;
            case 2
                Moment = Moment - magnitude;
            otherwise
                disp('Invalid direction');
        end
    end
end

function Moment = CalcMoment(magnitude, distance, direction)
    if direction == 1
        Moment = magnitude * distance;
    else
        Moment = -magnitude * distance;
    end
end

function FinalValues = InputLoad()
    n = input('Enter the number of loads: ');
    LoadX = 0;
    LoadY = 0;
    LoadMoment = 0;
    for i = 1:n
        clc;
        fprintf('Enter the information of Load %d\n', i);
        LoadWeight = input('Enter the weight of the Load: ');
        disp('Choose the orientation of the load');
        disp('1. X-Direction');
        disp('2. Y-Direction');
        disp('3. Inclined');
        direction = input('');
        switch direction
            case 1
                LoadX = LoadX + LoadWeight;
            case 2
                LoadY = LoadY + LoadWeight;
            case 3
                InclinedForce = lami();
                LoadX = LoadX + InclinedForce(1);
                LoadY = LoadY + InclinedForce(2);
            otherwise
                disp('Invalid choice');
        end

        distance = input('Enter the distance from the reference point: ');
        disp('Select 1 for Moment acting Anticlockwise or 2 for Clockwise');
        direction = input('');
        LoadMoment = LoadMoment + CalcMoment(LoadWeight, distance, direction);
    end
    FinalValues = [LoadX, LoadY, LoadMoment];
end

function FX = CalcFX(magnitude, angle)
    FX = magnitude * cos(angle);
end

function FY = CalcFY(magnitude, angle)
    FY = magnitude * sin(angle);
end
