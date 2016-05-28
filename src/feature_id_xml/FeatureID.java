package feature_id_xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureID {

	// 原始Nodes
	private List<FeatureNode> srcNodes;

	// 原始map结构存放的Nodes，形如：{ID:FeatureNode, ...}
	private Map<String, FeatureNode> srcNodesMap;

	// 转换后map结构存放的Nodes，形如：{ID:FeatureNode, ...}
	private Map<String, FeatureNode> aimNodesMap;

	public FeatureID() {
		this.srcNodes = new ArrayList<FeatureNode>();
	}
	
	public FeatureID(List<FeatureNode> srcNodes) {
		this.srcNodes = srcNodes;
	}

	/*
	 * 设置原始节点
	 */
	public void setSrcNodes(List<FeatureNode> srcNodes) {
		this.srcNodes = srcNodes;
	}
	
	/*
	 * 初始化
	 * @return: 初始化是否成功
	 */
	public boolean init() {
		if (!initSrcNodesMap()) {
			return false;
		}

		if (!initAimNodesMap()) {
			return false;
		}

		return true;
	}

	/*
	 * 获取输入id所对应的最终中文
	 */
	public String getBaseCn(String id) {
		return aimNodesMap.get(id).getCn();
	}

	/*
	 * 获取输入id所对应的最终英文
	 */
	public String getBaseEn(String id) {
		return aimNodesMap.get(id).getEn();
	}

	private boolean initAimNodesMap() {
		aimNodesMap = new HashMap<String, FeatureNode>();

		for (FeatureNode node : srcNodes) {
			String id = node.getId();
			StringBuilder baseId = new StringBuilder();
			if (!findBaseID(id, baseId)) {
				System.out.println("Error: Failed to find baseId when initAimNodesMap()!");
				return false;
			}
			aimNodesMap.put(id, srcNodesMap.get(baseId.toString()));
		}

		return true;
	}

	private boolean initSrcNodesMap() {
		srcNodesMap = new HashMap<String, FeatureNode>();
		for (FeatureNode node : srcNodes) {
			String key = node.getId();
			if (srcNodesMap.containsKey(key)) {
				System.out.println("Warning: key duplicated: " + key);
				return false;
			}

			srcNodesMap.put(key, node);
		}
		return true;
	}

	/*
	 * 获取输入id的最baseId
	 * rslt: 输出参数
	 * @return: 是否find成功
	 */
	private boolean findBaseID(String id, StringBuilder rslt) {
		
		// 防死循环记忆器
		List<String> mem = new ArrayList<String>();

		while (true) {
			
			FeatureNode baseNode = srcNodesMap.get(id);
			String baseId = baseNode.getBaseId();

			if ("".equals(baseId)) {
				rslt.append(baseNode.getId());
				return true;
			}
			
			mem.add(id);
			id = baseId;
			if (mem.contains(id)) {
				System.out.println("Error: baseId cycle: ");
				for (String s : mem) {
					System.out.println(s);
				}
				return false;
			}
		}
	}

}
