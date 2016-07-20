import java.util.ArrayList;


public class MultipleFun {
public MultipleFun(){
		
	}
	
	public static int multipleFun(ArrayList<Integer> a){
		int sum=1;
		for(int i=0;i<a.size();i++){
			sum*=a.get(i);
		}
		return sum;
	}
}
