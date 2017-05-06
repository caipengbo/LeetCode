# C输入输出 #
----------
## scanf ##
    int scanf(“格式控制符号串type”,地址列表)
如果在“格式控制”字符串中除了格式说明以外还有其他字符，则在输入数据时在对应位置应输入与这些字符相同的字符。

> 当无输入时，会返回EOF（-1），记得使用EOF作为判断，否则会使程序陷入死循环。

格式控制(type)

    %d	输入十进制整数
    %f或e	输入实型数(用小数形式或指数形式)
    %c	输入单个字符
    %s	输入字符串
    %o	输入八进制整数
    %x	输入十六进制整数
    %u	输入无符号十进制整数

## printf ##

    int printf(“format”, list)
> 返回被打印的字符数目

#####format

    %[flags][width][.prec][F|N|h|l]type

#####flag

符号 | 作用
----|------
减号|左对齐，右边填充空格(默认右对齐)
加号	|在数字前增加符号 + 或 -
数字零|将输出的前面补上0，直到占满指定列宽为止（不可以搭配使用“-”）
空格|输出值为正时加上空格，为负时加上负号
井号|type是o、x、X时，增加前缀0、0x、0Xtype是e、E、f、g、G时，一定使用小数点type是g、G时，尾部的0保留

#####width  
n(n=1,2,3,4,5,6...)： 宽度至少为n位，不够以空格填充。

#####.prec 
精度

    #include<cstdio>
    int main()
    {
       float a,b;
       while(scanf("%f%f",&a,&b)!=EOF) {
    printf("%5.2f\n",a+b);
       }
    }

##gets函数

<stdio.h>的 gets函数,获得一行字符串(可以获取空格)

	char * gets ( char * str );
	char s[100];
	char* ss;
	ss = gets(s);
	printf("The s is %s\n",s);
	printf("The ss is %s\n",s);

### stdio.h及cstdio的区别

在C语言中，stdio.h 头文件是主要的。而在后来的C++语言中，C只是C++的一个子集，且C++中，已不推荐再用C的类库，但为了对已有代码的保护，还是对原来的头文件支持。
   cstdio是c++从C的stdio.h继承来的，在前面加C同时不要H后缀，在C++环境当然是选用前者，两者内容都一样，只是cstdio头文件中定义的名字被定义在命名空间std中。使用后者就会带来额外的负担，需要区分哪些是标准库明是C++特有的，哪些是继承过来的！！所以在C++中要尽量避免C风格的出现！！！

#C++输入输出

----------

# 输入cin
    #include<iostream>
    #include<string>
    using namespace std;
    int main()
    {
       char ch[20];
       char* a = new char[5];
       cin>>a;
       cin>>ch;  //不可char* ch, cin>>ch;  cin>>引用
       cout<<ch;
       cout<<a<<endl;
    }
## getline ##
输入一行串getline

> <string>里面的全局函数getline

    string s;
    getline(cin,s);
    cout<<"The string is--"<<s<<"--And the size is "<<s.size()<<endl;
## cin.get

cin.get获取特殊字符
cin 是一个istream对象

    #include<iostream>
    using namespace std;
    int main()
    {
       char ch[20];
       char c;
       int i = 0;
       while(c = cin.get())
       {
       if(c == '\n') break;
       ch[i] = c;
       i++;
       }
       ch[i]='\0';
       cout<<"size:"<<i<<endl;
       cout<<ch<<endl;
    }

##输出格式控制

###\<iomanip>

控制符：


控制符|作 用
---|---|
dec|设置数值的基数为10
hex|设置数值的基数为16
oct|设置数值的基数为8
setfill(c)|设置填充字符c，c可以是字符常量或字符变量
setprecision(n)|设置浮点数的精度为n位。在以一般十进制小数形式输出时，n代表有效数字。在以fixed(固定小数位数)形式和 scientific(指数)形式输出时，n为小数位数
setw(n)|设置字段宽度为n位
setiosflags( ios::fixed)|设置浮点数以固定的小数位数显示
setiosftags( ios::scientific)|设置浮点数以科学记数法(即指数形式)显示
setiosflags( ios::left)|输出数据左对齐
setiosflags( ios::right)|输出数据右对齐
setiosflags( ios::skipws)|忽略前导的空格
setiosflags( ios::uppercase)|数据以十六进制形式输出时字母以大写表示
setiosflags( ios::lowercase)|数据以十六进制形式输出时宇母以小写表示
setiosflags(ios::showpos)|输出正数时给出“+”号

    cout<<a;  输出： 123.456
    cout<<setprecision(9)<<a;  输出： 123.456789
    cout<<setprecision(6);  恢复默认格式(精度为6)
    cout<< setiosflags(ios∷fixed);  输出： 123.456789
    cout<<setiosflags(ios∷fixed)<<setprecision(8)<<a;  输出： 123.45678901
    cout<<setiosflags(ios∷scientific)<<a;  输出： 1.234568e+02
    cout<<setiosflags(ios∷scientific)<<setprecision(4)<<a;  输出： 1.2346e02

###流对象成员函数

一般的C++输出，使用此便够用了

流成员函数|与之作用相同的控制符|作用
-----|-------|-------
precision(n)|setprecision(n)|设置实数的精度为n位
width(n)|setw(n)|设置字段宽度为n位
fill(c)|setfill(c)|设置填充宇符c
setf()|setiosflags()|设置输出格式状态，括号中应给出格式状态，内容与控制符setiosflags括号中的内容相同，如表13.5所示
unsetf()|resetioflags()|终止已设置的输出格式状态，在括号中应指定内容

与setf配套使用的格式标志

格式标志|作用
-----|-------
ios::left|输出数据在本域宽范围内向左对齐
ios::right|输出数据在本域宽范围内向右对齐
ios::internal|数值的符号位在域宽内左对齐，数值右对齐，中间由填充字符填充
ios::dec|设置整数的基数为10
ios::oct|设置整数的基数为8
ios::hex|设置整数的基数为16
ios::showbase|强制输出整数的基数(八进制数以0打头，十六进制数以0x打头)
ios::showpoint|强制输出浮点数的小点和尾数0
ios::uppercase|在以科学记数法格式E和以十六进制输出字母时以大写表示
ios::showpos|对正数显示“+”号
ios::scientific|浮点数以科学记数法格式输出
ios::fixed|浮点数以定点格式(小数形式)输出
ios::unitbuf|每次输出之后刷新所有的流
ios::stdio|每次输出之后清除stdout, stderr

    cout.width(12);   //指定域宽为
    cout.setf(ios::showpos);  //正数输出“+”号
    cout.setf(ios::internal); //数符出现在左侧
    cout.precision(6);//保留位小数
    cout<<pi<<endl;   //输出pi,注意数符“+”的位置

