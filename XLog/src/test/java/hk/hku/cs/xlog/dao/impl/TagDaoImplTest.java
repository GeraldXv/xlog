package hk.hku.cs.xlog.dao.impl;

import hk.hku.cs.xlog.entity.Tag;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml", "classpath:applicationContext-hibernate.xml" })
public class TagDaoImplTest {
	@Autowired
	TagDaoImpl tagDaoImpl;

	@Test
	public void test() {
		// Tag tag = new Tag();
		// tag.setTagName("happy");
		//
		// tagDaoImpl.saveOrUpdate(tag);

		List<Tag> tlist = tagDaoImpl.getMessagesByRank();

		for (Tag t : tlist) {
			System.out.println(t.getTagName());
		}
	}

}
