import java.util.ArrayList;


public class Server {
	
	public String ip;
	public String port;
	public String newZone;
	
	public Server(String ip,String newZone,String port){
		this.ip=ip;
		this.newZone=newZone;
		this.port=port;
	}
	
	public Server() {
		// TODO Auto-generated constructor stub
	}
	
	//�õ�serverList
	public void getServerList(ArrayList<Server> a,  ArrayList<NewZone> b,Server s){
		String listIp;
		String listPort;
		boolean flag = false;             //��¼s�Ƿ��list�����Ԫ���ظ��ı�־
		if(a.size()==0){                  //���list��СΪ0����ֱ�����Ԫ��
			a.add(s);
			for(int i=0;i<b.size();i++){
				if(s.ip.equals(b.get(i).ip)){
					a.get(0).newZone=b.get(i).newZone;   //��������Ӧ��ip�����������滻��ԭ�е�newZone
				}
			}
		}else{
			for(int i=0;i<a.size();i++){
				listIp=a.get(i).ip;
				listPort=a.get(i).port;
			if(!listIp.equals(s.ip)||!listPort.equals(s.port)){
				flag=true;
			}
			else{
				flag=false;
			}
		}
		if(flag==true){
			a.add(s);
			for(int i=0;i<b.size();i++){
				if(s.ip.equals(b.get(i).ip)){
					a.get(0).newZone=b.get(i).newZone;   //��������Ӧ��ip�����������滻��ԭ�е�newZone
				}
			}
		}
		}
	}
}


	
