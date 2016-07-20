import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Expression {
	String expression;
	static LinkedList<String> funcStack=new LinkedList<String>();
	static LinkedList<String> dataStack=new LinkedList<String>();
	
	public Expression(String inputExpression){
		this.expression=inputExpression;
	}
	
	public void getStack(String expr){
		String temString = "";
		for(int i=0;i<expr.length();i++){
			char temChar=expr.charAt(i);
			if(temChar!='('&&temChar!=')'&&temChar!=','){
				temString=temString+temChar;
			}
			if(temChar=='('){
				this.dataStack.push("(");
				if(!temString.equals("")){
					this.funcStack.push(temString);
					temString="";
				}
			}else if(temChar==','){
				if(!temString.equals("")){
					this.dataStack.push(temString);
					temString="";
				}
			}else if(temChar==')'){
				if(!temString.equals("")){
					this.dataStack.push(temString);
					this.dataStack.push(")");
					temString="";
				}else{
					this.dataStack.push(")");
				}
			}
		}
	}
	
	/*
	 * 字符表达式的正确性我打算用正则表达式来进行判断，但是写出来的正则表达式在编译的时候报错。。我解决不了，希望老师能
	 * 帮助我找出解决错误的办法！
	 */
	public boolean isValidExpression(String input){
		if(input.equals(null)){
			return false;
		}else{
			Pattern p=Pattern.compile("f[1-9]+((?<o>\\()([a-z]+,|0,|[1-9]\\d*,|f[1-9]+))+(?<-o>\\))+");    //正则表达式
			Matcher m=p.matcher(input);
			return m.matches();
		}
	}
	
	
	
	public int calculate(HashMap<String, Integer> m){
		
		/*
		if(!this.isValidExpression(this.expression)){
			System.out.println("The expression input is not valid!");
			System.exit(1);
		}
		*/
		
		String newExpression="";
		char c;
		//将字符串中所有的未知数替换成数字
		for(int i=0;i<this.expression.length();i++){
			c=this.expression.charAt(i);
			if(m.containsKey(Character.toString(c))){
				newExpression=newExpression+m.get(Character.toString(c)).toString();
			}else{
				newExpression=newExpression+c;
			}
		}
		getStack(newExpression);
		int result=ExpressionBuilder.caculate(this.funcStack, this.dataStack);
		return result;
	}
	
}
