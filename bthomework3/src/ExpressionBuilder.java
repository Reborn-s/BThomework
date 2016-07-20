import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class ExpressionBuilder {
	static HashMap<String,Object> funcToOperate=new HashMap<String, Object>();
	
	public static int caculate(LinkedList<String> funcStack,LinkedList<String> dataStack){
		LinkedList<String> temStack = new LinkedList<String>();  //����ջ����װ����ջ��ջ��Ԫ��
		ArrayList<Integer> number = new ArrayList<Integer>();
		HashMap map= new HashMap();
		Object oprates=new Object();
		int result=0;
		for(int i=0;i<dataStack.size();i++){
			if(!dataStack.get(i).equals("(")){                  //�������������
				temStack.push(dataStack.get(i));
			}else{
				while(!temStack.peek().equals(")")){            //�����������
					number.add(Integer.parseInt(temStack.poll()));//poll()�����ȷ�����ȡ����pop()����ȡ��
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
