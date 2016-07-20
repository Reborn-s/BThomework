import java.util.ArrayList;


public class Zone {
	public String zoneName;
	public String capacity;
	public String serverIndex;
	
	public Zone(String zonename,String capacity,String indexString){
		this.zoneName=zonename;
		this.capacity=capacity;
		this.serverIndex=indexString;
	}

	public Zone() {
		// TODO Auto-generated constructor stub
	}
	
	
	//得到zoneList
	public void getZoneList(ArrayList<Zone> a, Zone z){
		String listZoneName;
		boolean flag = false;             //记录s是否和list里面的元素重复的标志
		if(a.size()==0){                  //如果list大小为0，则直接添加元素
			a.add(z);
		}else{                                       //如果list大小不为0，则过滤掉重复元素再添加
			for(int i=0;i<a.size();i++){
				listZoneName=a.get(i).zoneName;
				if(!listZoneName.equals(z.zoneName)){
					flag=true;
				}
				else{
					flag=false;
				}
			}
			if(flag==true){
				a.add(z);
			}
		}
	}
}
