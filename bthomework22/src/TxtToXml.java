import org.w3c.dom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
public class TxtToXml {

	/**
	 * @param args
	 */
	
	//定义装有Server对象的serverList和装有Zone对象的zoneList。
	static ArrayList<Server> serverList= new ArrayList<Server>();
	static ArrayList<Zone> zoneList= new ArrayList<Zone>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getXmlContent();
		transform();
	}
	
	//读取文件
	public static BufferedReader readFile(String fileName) throws FileNotFoundException{
		File file=new File(fileName);
		BufferedReader br=new BufferedReader(new FileReader(file));
		return br;
	}
	
	//得到装有Server对象的serverList和装有Zone对象的zoneList
	public static void getXmlContent() throws Exception{
		
		//读取新文件
		BufferedReader zoneBr=readFile("zoneReplaced.config");
		String zoneLine;
		
		//定义新文件中域名的list，其中装有属于ZoneReplaced的对象
		ArrayList<NewZone> newZoneList= new ArrayList<NewZone>();
		
		//得到不包含重复元素的newZoneList,在得到这个集合的过程中就已经过滤掉了第三项不为1的元素
		while((zoneLine=zoneBr.readLine())!=null){
			String zoneTemp[]=zoneLine.split("\t");
			NewZone newZone= new NewZone(zoneTemp[0],zoneTemp[1],zoneTemp[2]);
			newZone.getNewZoneList(newZoneList, newZone);
		}
		
		//读取旧文件
		BufferedReader br=readFile("zone.config");
		
		//读取旧文件得到的每一行
		String line;
		while((line=br.readLine())!=null){
			
			//用tab键作为间隔取得每个词组成的数组temp
			String temp[]=line.split("\t");
			
			//temp[3]是curPlayerNum,temp[4]是maxPlayerNum，并且得到capacity
			PlayerNum playerNum=new PlayerNum(temp[3], temp[4]);
			String capacity=playerNum.getCapacity(playerNum.curPlayerNum, playerNum.maxPlayerNum);
			
			//将zone和ip存储到map中,以便于获得serverIndex
			ServerMap serverMap= new ServerMap();
			serverMap.key=temp[0];
			serverMap.value=temp[1];
			serverMap.put(temp[0], temp[1]);
			
			//得到serverList
			int lastServerIndex=0;                      //serverList里最后一个元素的索引
			Server lastServer=new Server();            //通过索引得到list中存储的处于最后一个位置的对象
			Server server = new Server(temp[1],temp[1],temp[2]);                  //temp[1]是ip,temp[2]是port
			server.getServerList(serverList, newZoneList, server);
			
			//得到zoneList
			String serverIndex=null;
			int lastZoneIndex=0; 
			Zone lastZone=new Zone();          //通过索引得到list中存储的处于最后一个位置的对象
			if(!(line.startsWith("$"))){                                 //过滤"$"开头的行
				Zone zone = new Zone(temp[0],capacity,serverIndex);                           //temp[0]是zone
				zone.getZoneList(zoneList, zone);
                //得到serverIndex
				lastZoneIndex=zoneList.size()-1;
				lastZone=zoneList.get(lastZoneIndex);
				serverIndex=serverMap.getServerIndex(serverList,serverMap);
				lastZone.serverIndex=serverIndex;
			}
		}
	}
	
	public static void transform() throws Exception{
		
		//创建dom对象和其下的根元素以及子节点。
		DocumentBuilderFactory factory=DocumentBuilderFactory .newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document= builder.newDocument();
		
		//创建根节点
		Element root= (Element)document.createElement("root");
		
		//创建servers子节点和其子节点
		Element servers=(Element)document.createElement("servers");
		Iterator itServer= serverList.iterator();
		Server xmlServer= new Server();
		while(itServer.hasNext()){
			xmlServer=(Server) itServer.next();
			Element serverElement=(Element)document.createElement("server");
			serverElement.appendChild(document.createTextNode(xmlServer.newZone+"/"+xmlServer.port));
			servers.appendChild(serverElement);
		}
		
		//创建zones子节点和其子节点
		Element zones=(Element)document.createElement("zones");
		Iterator itZone= zoneList.iterator();
		Zone xmlZone=new Zone();
		while(itZone.hasNext()){
			xmlZone=(Zone) itZone.next();
			Element zoneElement=(Element)document.createElement("zone");
			zoneElement.setAttribute("name", xmlZone.zoneName);
			zoneElement.setAttribute("serverindex", xmlZone.serverIndex);
			zoneElement.setAttribute("capacity", xmlZone.capacity);
			zones.appendChild(zoneElement);
		}

		//将节点全部放入root中
		root.appendChild(servers);
		root.appendChild(zones);
		document.appendChild(root);
		OutputFile(document);
	}
		
	public static void OutputFile(Document document) throws Exception{
		//输出成xml文件
		TransformerFactory transFactory=TransformerFactory.newInstance();
		Transformer transformer=transFactory.newTransformer();
		DOMSource source=new DOMSource(document);
		File zoneXmlInfo= new File("zoneXmlInfo.xml");
		FileOutputStream os= new FileOutputStream(zoneXmlInfo);
		StreamResult result = new StreamResult(os);
		transformer.transform(source, result);
	}
}
