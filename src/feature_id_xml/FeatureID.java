package feature_id_xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureID {

	private List<FeatureNode> srcNodes;

	// 源map节点{ID:FeatureNode, ...}
	private Map<String, FeatureNode> srcNodesMap;

	// 目标map节点{ID:FeatureNode, ...}
	private Map<String, FeatureNode> aimNodesMap;

	public FeatureID(List<FeatureNode> srcNodes) {
		this.srcNodes = srcNodes;
		init();
	}

	/*
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
	 * 
	 */
	public String getBaseCn(String id) {
		return aimNodesMap.get(id).getCn();
	}

	/*
	 * 
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
				System.out.println("Error: Failed to find baseId when initAimNodesMap!");
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
			if (mem.contains(id)) {
				System.out.println("Error: baseId cycle: ");
				for (String s : mem) {
					System.out.println(s);
				}
				return false;
			}

			FeatureNode baseNode = srcNodesMap.get(id);
			String baseId = baseNode.getBaseId();

			if ("".equals(baseId)) {
				rslt.append(baseNode.getId());
				return true;
			} else {
				mem.add(id);
				id = baseId;
			}
		}
	}

}
