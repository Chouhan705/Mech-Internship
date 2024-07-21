function Joints
    clc;
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
    disp('Choose the System of Joints');
    disp('1. Roller and Hinge');
    disp('2. 2 Roller');
    disp('3. 2 Hinge [also works for 1 hinge and an inclined roller]');
    system = input('');
    switch system
        case 1
            Roller_Hinge(TotalFY, TotalFX, TotalMoment);
        case 2
            TwoRoller(TotalFY, TotalFX, TotalMoment);
        case 3
            TwoHinge(TotalFY, TotalFX, TotalMoment);
        otherwise
            disp('Invalid System of Joints');
    end
end

function values = InputMoment()
    clc;
    n = input('Enter the number of known forces: ');
    FX = 0;
    FY = 0;
    Moment = 0;
    angle = 0;
    Inclination = 2;
    for i = 1:n
        clc;
        fprintf('Enter the information of force %d\n', i);
        Inclination = input('Enter 1 if the force is inclined else 0: ');
        if Inclination == 1
            quadrant = InputQuadrant();
            magnitude = InputMagnitude();
            inputType = input('Select A for angle or S for slope: ', 's');
            switch inputType
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
            disp('3. Rightwards');
            disp('4. Leftwards');
            direction = input('');
            if direction == 1
                angle = pi/2;
            elseif direction == 2
                angle = 3*pi/2;
            elseif direction == 3
                angle = 0;
            elseif direction == 4
                angle = pi;
            end
        end
        FX = FX + CalcFX(magnitude, angle);
        FY = FY + CalcFY(magnitude, angle);
        
        distance = input('Enter the distance from the reference point: ');
        disp('Choose the direction of moment caused by the force');
        disp('1. Anti-clockwise');
        disp('2. Clockwise');
        disp('0. If the Force is passing through the point');
        direction = input('');
        Moment = Moment + CalcMoment(magnitude, distance, direction);
    end
    values = [FX, FY, Moment];
end

function Moment = InputExtraMoments()
    clc;
    n = input('Enter the number of moments: ');
    Moment = 0;
    for i = 1:n
        magnitude = input(['Enter the Magnitude of the Moment ', num2str(i), ': ']);
        direction = input('Select 1 for Moment acting Anticlockwise or 2 for Clockwise: ');
        if direction == 1
            Moment = Moment + magnitude;
        elseif direction == 2
            Moment = Moment - magnitude;
        else
            disp('Invalid direction');
        end
    end
end

function values = InputLoad()
    n = input('Enter the number of loads: ');
    Load = zeros(1, 100);
    Distance = zeros(1, 100);
    Direction = zeros(1, 100);
    for i = 1:n
        clc;
        disp('Select the type of load');
        disp('1 for Point Load');
        disp('2 for Uniform Distributed Load');
        disp('3 for Uniformly Varying Load');
        disp('4 for Combined UDL and UVL');
        choice = input('');
        switch choice
            case 1
                Load(i) = input('Enter the magnitude of the Point Load: ');
                Distance(i) = input('Enter the distance from the reference point: ');
                disp('Choose the direction of Potential Moment');
                disp('1: Anticlockwise');
                disp('2: Clockwise');
                Direction(i) = input('');
            case 2
                magnitude = input('Enter the magnitude of the Load: ');
                length = input('Enter the length of applied Load: ');
                distance = input('Enter the distance of the closer side from the reference point: ');
                disp('Choose the direction of Potential Moment');
                disp('1: Anticlockwise');
                disp('2: Clockwise');
                Direction(i) = input('');
                Load(i) = magnitude * length;
                Distance(i) = distance + (length / 2);
            case 3
                disp('Choose the orientation of the UVL');
                disp('1: Slanted toward the reference point');
                disp('2: Slanted away from the reference point');
                orientation = input('');
                magnitude = input('Enter the magnitude of the Load: ');
                length = input('Enter the length of applied Load: ');
                distance = input('Enter the distance of the closer side from the reference point: ');
                disp('Choose the direction of Potential Moment');
                disp('1: Anticlockwise');
                disp('2: Clockwise');
                Direction(i) = input('');
                Load(i) = 0.5 * magnitude * length;
                if orientation == 1
                    Distance(i) = distance + (2 * length) / 3;
                elseif orientation == 2
                    Distance(i) = distance + length / 3;
                end
            case 4
                disp('Choose the orientation of the UVL');
                disp('1: Slanted toward the reference point');
                disp('2: Slanted away from the reference point');
                orientation = input('');
                smallermagnitude = input('Enter the smaller magnitude of the Load: ');
                biggermagnitude = input('Enter the bigger magnitude of the Load: ');
                length = input('Enter the length of applied Load: ');
                distance = input('Enter the distance of the closer side from the reference point: ');
                disp('Choose the direction of Potential Moment');
                disp('1: Anticlockwise');
                disp('2: Clockwise');
                Direction(i) = input('');
                Load(i) = smallermagnitude * length;
                Distance(i) = distance + (length / 2);
                
                Load(i + 1) = 0.5 * (biggermagnitude - smallermagnitude) * length;
                if orientation == 1
                    Distance(i + 1) = distance + (2 * length) / 3;
                elseif orientation == 2
                    Distance(i + 1) = distance + length / 3;
                end
                Direction(i + 1) = Direction(i);
                i = i + 1;
                n = n + 1;
            otherwise
                disp('Invalid Input');
        end
    end
    
    LX = 0;
    LY = 0;
    for i = 1:n
        LY = LY + CalcFY(Load(i), deg2rad(270));
    end
    
    MomentDueToLoad = 0;
    for i = 1:n
        MomentDueToLoad = MomentDueToLoad + CalcMoment(Load(i), Distance(i), Direction(i));
    end
    
    values = [LX, LY, MomentDueToLoad];
end

function Roller_Hinge(FY, FX, Moment)
    clc;
    disp('Using the Hinge Joint as reference point');
    RollerValues = InputJoint('Roller');
    RollerDistance = RollerValues(1);
    RollerOrientation = RollerValues(2);
    HingeValues = InputJoint('Hinge');
    HingeDistance = HingeValues(1);
    HingeOrientation = HingeValues(2);
    
    ReactionValues = simultaneousEqnSolver(RollerDistance, HingeDistance, -Moment, 1, 1, -FY);
    RollerReaction = ReactionValues(1);
    HingeReactionFY = ReactionValues(2);
    HingeReactionFX = -FX;
    HingeReaction = hypot(HingeReactionFX, HingeReactionFY);
    
    disp(['Roller Reaction: ', num2str(RollerReaction), ' N']);
    disp(['Hinge Reaction: ', num2str(HingeReaction), ' N']);
    disp('"Approx Values"');
end

function TwoRoller(FY, FX, Moment)
    clc;
    Roller1Values = InputJoint('Roller');
    Roller1Distance = Roller1Values(1);
    Roller1Orientation = Roller1Values(2);
    Roller2Values = InputJoint('Roller');
    Roller2Distance = Roller2Values(1);
    Roller2Orientation = Roller2Values(2);
    
    ReactionValues = simultaneousEqnSolver(Roller1Distance, Roller2Distance, -Moment, 1, 1, -FY);
    Roller1Reaction = ReactionValues(1);
    Roller2Reaction = ReactionValues(2);
    
    disp(['Roller 1 Reaction: ', num2str(Roller1Reaction), ' N']);
    disp(['Roller 2 Reaction: ', num2str(Roller2Reaction), ' N']);
    disp('"Approx Values"');
end

function TwoHinge(FY, FX, Moment)
    clc;
    Hinge1Values = InputJoint('Hinge');
    Hinge1Distance = Hinge1Values(1);
    Hinge1Orientation = Hinge1Values(2);
    Hinge2Values = InputJoint('Hinge');
    Hinge2Distance = Hinge2Values(1);
    Hinge2Orientation = Hinge2Values(2);
    
    ReactionValues = simultaneousEqnSolver(Hinge1Distance, Hinge2Distance, -Moment, 1, 1, -FY);
    Hinge1Reaction = ReactionValues(1);
    Hinge2Reaction = ReactionValues(2);
    
    disp(['Hinge 1 Reaction: ', num2str(Hinge1Reaction), ' N']);
    disp(['Hinge 2 Reaction: ', num2str(Hinge2Reaction), ' N']);
    disp('"Approx Values"');
end

function distance = InputDistance()
    distance = input('Enter the distance from the reference point: ');
end

function magnitude = InputMagnitude()
    magnitude = input('Enter the magnitude of the force: ');
end

function angle = InputAngle(quadrant)
    angle = input('Enter the value of the angle in degrees: ');
    switch quadrant
        case 1
            angle = angle;
        case 2
            angle = 180 - angle;
        case 3
            angle = 180 + angle;
        case 4
            angle = 360 - angle;
        otherwise
            disp('Invalid Quadrant');
    end
end

function angle = InputSlope(quadrant)
    SlopeRatio = zeros(2, 1);
    SlopeRatio(1) = input('Enter the value of X Component: ');
    SlopeRatio(2) = input('Enter the value of Y Component: ');
    switch quadrant
        case 1
            angle = atan(SlopeRatio(2) / SlopeRatio(1));
        case 2
            angle = pi - atan(SlopeRatio(2) / SlopeRatio(1));
        case 3
            angle = pi + atan(SlopeRatio(2) / SlopeRatio(1));
        case 4
            angle = 2*pi - atan(SlopeRatio(2) / SlopeRatio(1));
        otherwise
            disp('Invalid Quadrant');
    end
end

function quadrant = InputQuadrant()
    disp('Choose the Quadrant of the angle:');
    disp('1: First Quadrant');
    disp('2: Second Quadrant');
    disp('3: Third Quadrant');
    disp('4: Fourth Quadrant');
    quadrant = input('');
end

function values = InputJoint(jointType)
    clc;
    fprintf('Enter the Information of %s joint\n', jointType);
    distance = InputDistance();
    disp('Choose the orientation of the joint');
    disp('1. Horizontal');
    disp('2. Vertical');
    orientation = input('');
    values = [distance, orientation];
end

function fx = CalcFX(magnitude, angle)
    fx = magnitude * cos(angle);
end

function fy = CalcFY(magnitude, angle)
    fy = magnitude * sin(angle);
end

function moment = CalcMoment(magnitude, distance, direction)
    if direction == 1
        moment = magnitude * distance;
    elseif direction == 2
        moment = -magnitude * distance;
    else
        moment = 0;
    end
end

function solutions = simultaneousEqnSolver(coeff1, coeff2, rhs1, coeff3, coeff4, rhs2)
    A = [coeff1, coeff2; coeff3, coeff4];
    B = [rhs1; rhs2];
    solutions = linsolve(A, B);
end
