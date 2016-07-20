import java.util.ArrayList;

public class NewZone {
	public String ip;
	public String newZone;
	public String num;
	
	public NewZone(String ipString,String zoneString,String numString){
		this.ip=ipString;
		this.newZone=zoneString;
		this.num=numString;
	}
	
	//µÃµ½NewZoneList
	public void getNewZoneList(ArrayList<NewZone> a, NewZone n){
		String listIp;
		boolean flag = false;
		if(a.size()==0){
			a.add(n);
		}else {
			for(int i=0;i<a.size();i++){
				if(n.num.equals(Integer.toString(1))){
					listIp=a.get(i).ip;
					if(!listIp.equals(n.ip)){
						flag=true;
					}
					else{
						flag=false;
					}
				}
			}
		}
		if(flag==true){
			a.add(n);
		}
	}
}
 