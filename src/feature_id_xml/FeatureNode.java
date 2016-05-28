package feature_id_xml;

public class FeatureNode {
	private String id;
	private String cn;
	private String en;
	private String baseId;
	public FeatureNode(String id, String cn, String en, String baseId) {
		super();
		this.id = id;
		this.cn = cn;
		this.en = en;
		this.baseId = baseId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
	}
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}


	
}
