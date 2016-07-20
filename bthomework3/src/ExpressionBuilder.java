import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class ExpressionBuilder {
	static HashMap<String,Object> funcToOperate=new HashMap<String, Object>();
	
	public static int caculate(LinkedList<String> funcStack,LinkedList<String> dataStack){
		LinkedList<String> temStack = new LinkedList<String>();  //辅助栈，来装数据栈出栈的元素
		ArrayList<Integer> number = new ArrayList<Integer>();
		HashMap map= new HashMap();
		Object oprates=new Object();
		int result=0;
		for(int i=0;i<dataStack.size();i++){
			if(!dataStack.get(i).equals("(")){                  //如果不是左括号
				temStack.push(dataStack.get(i));
			}else{
				while(!temStack.peek().equals(")")){            //如果是左括号
					number.add(Integer.parseInt(temStack.poll()));//poll()才是先访问再取出，pop()就是取出
				}
				oprates=getOperate(funcStack.poll());
				map.put(number, oprates);
				if(oprates instanceof MultipleFun){
					result=MultipleFun.multipleFun(number);
				}else if(oprates instanceof AddFun){
					result=AddFun.addFun(number);
				}
				temStack.poll();
				temStack.push(Integer.toString(result));
				number=new ArrayList<Integer>();
			}
		}
		return result;
		
	}
	
	public Expression buildExpression(String input){
		Expression e= new Expression(input);
		return e;
	}
	
	public void addFunc(String func,Object operate){
		funcToOperate.put(func, operate);
	}
	
	public static Object getOperate(String funcName){
		Object o=funcToOperate.get(funcName);
		return o;
	}
	
	

}
