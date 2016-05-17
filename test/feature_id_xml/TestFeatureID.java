package feature_id_xml;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestFeatureID {

	@Test
	public void test01() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		nodes.add(new FeatureNode("1", "中", "en", ""));
		FeatureID fid = new FeatureID(nodes);
		String cn = fid.getBaseCn("1");
		assertTrue(cn == "中");
		assertTrue(fid.getBaseEn("1") == "en");
	}

	@Test
	public void test02() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		
		nodes.add(new FeatureNode("1", "中", "en", "2"));
		nodes.add(new FeatureNode("2", "中2", "en2", ""));
		
		FeatureID fid = new FeatureID(nodes);
		
		assertTrue(fid.getBaseCn("1") == "中2");
		assertTrue(fid.getBaseEn("1") == "en2");
	}
	
	@Test
	public void test03() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		
		nodes.add(new FeatureNode("1", "中", "en", "2"));
		nodes.add(new FeatureNode("2", "中2", "en2", "1"));
		
		FeatureID fid = new FeatureID(nodes);
		
//		assertTrue(fid.getBaseCn("1") == "");
//		assertTrue(fid.getBaseEn("1") == "");
	}
}
