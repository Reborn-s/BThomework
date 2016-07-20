import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class CaculateExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExpressionBuilder builder=new ExpressionBuilder();
		
		builder.addFunc("f1", new MultipleFun());
		builder.addFunc("f2", new AddFun());
		
		Expression expression =builder.buildExpression("f1(x,f2(x,y,z))");
		
		HashMap<String,Integer> params=new HashMap<String, Integer>();
		params.put("x", 4);
		params.put("y", 5);
		params.put("z", 7);
		
		int value=expression.calculate(params);
		System.out.println(value);
			
	}

}
