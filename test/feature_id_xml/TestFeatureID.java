package feature_id_xml;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestFeatureID {

	/*
	 * 简单基本场景
	 */
	@Test
	public void test01() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		nodes.add(new FeatureNode("1", "中", "en", ""));
		FeatureID fid = new FeatureID(nodes);
		boolean ok = fid.init();
		assertTrue(ok);
		String cn = fid.getBaseCn("1");
		assertEquals(cn, "中");
		assertEquals(fid.getBaseEn("1"), "en");
	}

	/*
	 * 简单重定向场景
	 */
	@Test
	public void test02() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		
		nodes.add(new FeatureNode("1", "中", "en", "2"));
		nodes.add(new FeatureNode("2", "中2", "en2", ""));
		
		FeatureID fid = new FeatureID(nodes);
		boolean ok = fid.init();
		assertTrue(ok);
		
		assertEquals(fid.getBaseCn("1"), "中2");
		assertEquals(fid.getBaseEn("1"), "en2");
	}
	
	/*
	 * 循环
	 */
	@Test
	public void test03() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		
		nodes.add(new FeatureNode("1", "中", "en", "2"));
		nodes.add(new FeatureNode("2", "中2", "en2", "1"));
		
		FeatureID fid = new FeatureID(nodes);
		boolean ok = fid.init();
		assertFalse(ok);
	}
	
	/*
	 * 重复的node id
	 */
	@Test
	public void test04() {
		List<FeatureNode> nodes = new ArrayList<FeatureNode>();
		
		nodes.add(new FeatureNode("1", "中", "en", ""));
		nodes.add(new FeatureNode("1", "中2", "en2", ""));
		
		FeatureID fid = new FeatureID(nodes);
		boolean ok = fid.init();
		assertFalse(ok);
	}
}
