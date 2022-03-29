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

% plots for source signal
figure
subplot(3,2,1)
plot(S(1,:))
ax = gca;
ax.FontSize = 14;
title('Initial Source Signal s1')
grid; grid minor
subplot(3,2,2)
plot(S(2,:))
ax = gca;
ax.FontSize = 14;
title('Initial Source Signal s2')
grid; grid minor


% mixing matrix N:N where having 2 mixed signals N = 2;
A = [1, 2; 4, -1];

% Mixing Source Signals
X = A*S;

% plots for mixed signal
subplot(3,2,3)
plot(X(1,:))
title('Mixed signals x1')
grid; grid minor
ax = gca;
ax.FontSize = 14;
subplot(3,2,4)
plot(X(2,:))
title('Mixed signals x2')
grid; grid minor
ax = gca;
ax.FontSize = 14;

% using fastICA function to separate mixed signals
Z = fastICA(X, 2);

% plots for recovered signal
subplot(3,2,5)
plot(Z(1,:))
title('Recovered Signal s1')
grid; grid minor
ax = gca;
ax.FontSize = 14;
subplot(3,2,6)
plot(Z(2,:))
title('Recovered Signal s2')
grid; grid minor
ax = gca;
ax.FontSize = 14;