import java.util.ArrayList;


public class AddFun {
	public AddFun(){
		
	}
	
	public static int addFun(ArrayList<Integer> a){
		int sum=0;
		for(int i=0;i<a.size();i++){
			sum+=a.get(i);
		}
		return sum;
	}

}
