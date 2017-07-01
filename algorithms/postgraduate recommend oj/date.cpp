//YYYYMMDD  日期的差值 以及
#include<iostream>
#include<cstdlib>
#include<iomanip>
#include<string>
#include<cmath>
using namespace std;
bool leap_year(int year)
{
	return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
}
//获得某时间到1年1月1日的时间
int get_days(int year, int month, int day)
{
	int month_day[13] = { 0,31,28,31,30,31,30,31,31,30,31,30,31 };
	month_day[2] += leap_year(year);
	int day_count = 0;
	for (int y = 1; y < year; y++)
	{
		day_count += 365 + leap_year(y);
	}
	for (int m = 1; m < month; m++)
	{
		day_count += month_day[m];
	}
	day_count += day;
	return day_count;
}
//给定某年的第几天，打印具体日期
void detail_date(int year, int daynum)
{
	int month_day[13] = { 0,31,28,31,30,31,30,31,31,30,31,30,31 };
	month_day[2] += leap_year(year);
	int month = 1;
	//int m = 1;
	while (month <= 12)
	{
		if(daynum>month_day[month])
		{
			daynum -= month_day[month];
			++month;
		}
		else 
		{
			break;
		}
		//month++;
	}
	cout << setw(4) << setfill('0') << year;
	cout << "-";
	cout << setw(2) << setfill('0') << month;
	cout << "-";
	cout << setw(2) << setfill('0') << daynum << endl;
	//cout << year << "-" << month << "-" << daynum << endl;
}
//main
void testgetdays()
{
	string date1, date2;
	while (cin >> date1 >> date2) {
		int year1 = atoi(date1.substr(0, 4).c_str());
		int month1 = atoi(date1.substr(4, 2).c_str());
		int day1 = atoi(date1.substr(6, 2).c_str());
		int year2 = atoi(date2.substr(0, 4).c_str());
		int month2 = atoi(date2.substr(4, 2).c_str());
		int day2 = atoi(date2.substr(6, 2).c_str());

		int day_count1 = get_days(year1, month1, day1);
		int day_count2 = get_days(year2, month2, day2);

		cout << abs(day_count1 - day_count2) + 1 << endl;
	}
}
void testdetail()
{
	int y, d;
	while (cin >> y >> d) 
	{
		detail_date(y, d);
	}
}