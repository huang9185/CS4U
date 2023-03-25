#include <bits/stdc++.h>

using namespace std;

int main()
{
	int flooring, R, C;
	cin >> flooring >> R >> C;

	bool visited[25][25];

	// C
	for (int j = 0; j < 25; ++j)
	{
		// R
		for (int i = 0; i < 25; ++i)
		{
			// Out of range
			if (i >= C || j >= R)
			{
				visited[i][j] = true;
				continue;
			}
			char tmp;
			cin >> tmp;
			// If wall
			if (tmp == 73) visited[i][j] = true;
			else visited[i][j] = false;
		}
	}

	vector<int> a{ 1, 0, -1, 0 };
	vector<int> b{ 0, -1, 0, 1 };
	vector<int> sizes;

	string line;

	for (int j = 0; j < R; ++j)
	{
		for (int i = 0; i < C; ++i)
		{
			if (!visited[i][j])
			{
				int space{ 1 };
				stack<pair<int, int>> cells;
				cells.push(make_pair(i, j));
				visited[i][j] = true;
				pair<int, int> current;
				while (!cells.empty())
				{
					current = cells.top();
					cells.pop();
					for (int q = 0; q < 4; ++q)
					{
						int nextx = current.first + a[q];
						int nexty = current.second + b[q];
						// If out of scope
						if (nextx < 0 || nextx >= C || nexty < 0 || nexty >= R) continue;
						// If match
						else if (!visited[nextx][nexty])
						{
							cells.push(make_pair(nextx, nexty));
							visited[nextx][nexty] = true;
							space++;
						}
					}
				}
				if (space != 0) sizes.push_back(space);
			}
		}
	}

	// In ascending order
	sort(sizes.begin(), sizes.end());
	reverse(sizes.begin(), sizes.end());
	int roomFilled{ 0 };
	for (int size : sizes)
	{
		if (flooring >= size)
		{
			roomFilled++;
			flooring -= size;
		}
		else break;
	}

	string first{ " rooms, " };
	string second{ " square metre(s) left over" };

	if (roomFilled == 1) first = " room, ";
	cout << roomFilled << first << flooring << second;

	return 0;
}