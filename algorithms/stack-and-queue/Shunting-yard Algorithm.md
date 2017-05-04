[Shunting-yard algorithm——调度场算法](https://en.wikipedia.org/wiki/Shunting-yard_algorithm "Shunting-yard algorithm")

----------
# 应用：将中缀表达式(Infix Notation)转化成后缀表达式(Postfix Notation) #


![](http://i.imgur.com/hjKEa2X.png)

Graphical illustration of algorithm, using a three way railroad junction. The input is processed one symbol at a time: if a variable or number is found, it is copied directly to the output a), c), e), h). If the symbol is an operator, it is pushed onto the operator stack b), d), f). If the operator's precedence is less than that of the operators at the top of the stack or the precedences are equal and the operator is left associative, then that operator is popped off the stack and added to the output g). Finally, any remaining operators are popped off the stack and added to the output i).

#操作步骤#

![](http://i.imgur.com/iOAj3jl.png)
	


