set(groot, 'defaulttextinterpreter', 'latex');
set(groot, 'defaultAxesTickLabelInterpreter', 'latex');
set(groot, 'defaultLegendInterpreter', 'latex');
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
close all;

% time
t = 1:1000;
% amplitude
a = 1;
% phase
f1 = 150;
f2 = 100;
% source signal x1
S(1,:) = 1 * sin(2*pi*t*1/f1);
% source signal x2
S(2,:) = 1 * sin(2*pi*t*1/f2);


% mixing matrix N:N where having 2 mixed signals N = 2;
A = [1, 2; 4, -1];

% Mixing Source Signals
X = A*S;

figure
subplot(2,1,1)
plot(S(1,:), S(2,:), 'b.')
subplot(2,1,2)
plot(X(1,:), X(2,:), 'b.')