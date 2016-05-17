package feature_id_xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureID {
	
	private List<FeatureNode> srcNodes;
	
	// Դmap�ڵ�{ID:FeatureNode, ...}
	private Map<String, FeatureNode> srcNodesMap;
	
	// Ŀ��map�ڵ�{ID:FeatureNode, ...}
	private Map<String, FeatureNode> aimNodesMap;
	
	public FeatureID(List<FeatureNode> srcNodes){
		this.srcNodes = srcNodes;
		init();
	}
	
	/*
	 * @return: ��ʼ���Ƿ�ɹ�
	 */
	public boolean init(){
		if (!initSrcNodesMap()){
			return false;
		}
		
		if (!initAimNodesMap()){
			return false;
		}

		return true;
	}
	
	public String getBaseCn(String id){
		return aimNodesMap.get(id).cn;
	}
	
	public String getBaseEn(String id){
		return aimNodesMap.get(id).en;
	}
	
	private boolean initAimNodesMap(){
		aimNodesMap = new HashMap<String, FeatureNode>();
		
		for (FeatureNode node:srcNodes){
			String id = node.id;
			String baseId = findBaseID(id);
			aimNodesMap.put(id, srcNodesMap.get(baseId));
		}
		
		return true;
	}
	
	private boolean initSrcNodesMap(){
		srcNodesMap = new HashMap<String, FeatureNode>();
		for (FeatureNode node:srcNodes){
			String key = node.id;
			if (srcNodesMap.containsKey(key)){
				System.out.println("Warning: key duplicated: " + key);
				return false;
			}
			
			srcNodesMap.put(key, node);
		}
		return true;
	}
	
	/*
	 * ��ȡ����id����baseId
	 */
	private String findBaseID(String id){
		// ����ѭ��������
		List<String> mem = new ArrayList<String>();
		
		while(true){
			if (mem.contains(id)){
				System.out.println("Warning: baseId cycle: ");
				for (String s:mem){
					System.out.println("\n"+s);
				}
				return "";
			}
			
			FeatureNode baseNode = srcNodesMap.get(id);
			String baseId = baseNode.baseId;
			
			if ("".equals(baseId)){
				return baseNode.id;
			}else{
				mem.add(id);
				id = baseId;
			}
		}
	}
	
}
