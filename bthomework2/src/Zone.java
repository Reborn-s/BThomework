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
	
	
	//�õ�zoneList
	public void getZoneList(ArrayList<Zone> a, Zone z){
		String listZoneName;
		boolean flag = false;             //��¼s�Ƿ��list�����Ԫ���ظ��ı�־
		if(a.size()==0){                  //���list��СΪ0����ֱ�����Ԫ��
			a.add(z);
		}else{                                       //���list��С��Ϊ0������˵��ظ�Ԫ�������
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
