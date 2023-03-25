// No functions are initially set as the main function
// Change one function name to run it

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <set>

using namespace std;
// Underweight, Overweight, Obese
int dts[2][3][3] = {
	{
	{8, 21, 26},
	{11, 23, 29},
	{13, 25, 31}
},
{
	{21, 33, 39},
	{23, 35, 41},
	{25, 38, 43}
}
};

int BAI()
{
	cout << "Enter your height in cm: ";
	double height; cin >> height;
	height /= 100;
	cout << "\nEnter your hip circumference in cm: ";
	int hip; cin >> hip;
	cout << "\nEnter your gender(1: female; 0: male): ";
	int gender; cin >> gender;
	cout << "\nEnter your age: ";
	int age; cin >> age;
	cout << "\n";

	// Classify Age
	if (age >= 20 && age < 40) age = 0;
	else if (age < 60) age = 1;
	else if (age < 80) age = 2;

	// Calculate percentage
	double percent{ hip / pow(height, 1.5) - 18 };
	printf("The percentage is %.1f ", percent);
	
	// Check classification

	if (percent < dts[gender][age][0])
		cout << "\nYou are underweight";
	else if (percent > dts[gender][age][2])
		cout << "\nYou are obese";
	else if (percent > dts[gender][age][1])
		cout << "\nYou are overweight";
	else
		cout << "\nYour are healthy";

	cout << ".\n";
	return 0;
}

const int N{ 26 };
int singer()
{
	vector<int> votes;
	for (int i = 0; i < N; i++) votes.push_back(0);
	cout << "Enter the votes: ";
	string s; cin >> s;

	// Add votes
	for (char i : s) votes[i - 65]++;

	vector<int> top; // Array of indexes
	top.push_back(0);
	// Check how many numbers are the same
	for (int i = 1; i < N; i++)
	{
		if (votes[i] > votes[top[0]])
		{
			top.clear();
			top.push_back(i);
		}
		else if (votes[i] == votes[top[0]]) top.push_back(i);
	}

	// Check if only one winner
	if (top.size() == 1)
		cout << "The Winner is singer " << static_cast<char> (top[0]+65) << " with " << votes[top[0]] << " votes.\n";
	else {
		cout << "\nSinger ";
		for (int i = 0; i < top.size(); i++)
		{
			if (i == top.size() - 1) cout << static_cast<char> (top[i]+65) << " ";
			else cout << static_cast<char> (top[i]+65) << ", ";
		}

		cout << "tied with " << votes[top[0]] << " votes each.";
	}
	return 0;
}

char grid[4][4] = {
	{'*', '*', '*', '*'},
	{'*', ' ', ' ', '*'},
	{'*', ' ', ' ', '*'},
	{'*', '*', '*', '*'}
};
int magnify()
{
	cout << "Enter the magnify factor: \n";
	int factor; cin >> factor;
	cout << "\n";

	for (int j = 0; j < 4; j++)
	{
		for (int n = 0; n < factor; n++)
		{
			for (int i = 0; i < 4; i++)
			{
				for (int m = 0; m < factor; m++) cout << grid[j][i];
			}
			cout << "\n";
		}
	}
	cout << "\n";
	return 0;
}

int addition()
{
	// Generate 10 unique numbers
	set<int> numbers;
	while(numbers.size() != 10)
	{
		numbers.insert(rand() % (50 - 0 + 1) + 0);
	}
	cout << "\n";

	// Output as individuals
	for (int i : numbers) cout << i << " ";
	cout << "\n";

	// Turn into string
	string s{ "" };
	for (int i: numbers)
	{
		s = s.append(to_string(i));
	}
	cout << s << "\n";

	int sum{ 0 };
	for (auto it = numbers.begin(); it != numbers.end(); advance(it, 2))
	{
		cout << *it << "+";
		sum += *it;
	}

	cout << "\b \b = " << sum << "\n";

	return 0;
}

int main()
{
	addition();
	return 0;
}