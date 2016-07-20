
public class PlayerNum {
	public String curPlayerNum;
	public String maxPlayerNum;
	
	public PlayerNum(String curNum,String maxNume){
		this.curPlayerNum=curNum;
		this.maxPlayerNum=maxNume;
	}
	public String getCapacity(String curPlayerNume,String maxPlayerNume){
		double p=Double.parseDouble(curPlayerNume)/Double.parseDouble(maxPlayerNume);
		int capacity = 0;
		if(p>=0&&p<0.1){
			capacity=1;
		}
		else if(p>=0.1&&p<0.3){
			capacity=2;
		}
		else if(p>=0.3&&p<0.4){
			capacity=3;
		}
		else if(p>=0.4&&p<0.5){
			capacity=4;
		}
		else if(p>=0.5&&p<0.7){
			capacity=5;
		}
		else if(p>=0.7&&p<0.8){
			capacity=6;
		}
		else if(p>=0.8&&p<1){
			capacity=7;
		}
		return Integer.toString(capacity);
	}

}
