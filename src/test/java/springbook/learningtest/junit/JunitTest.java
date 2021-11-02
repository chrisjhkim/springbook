package springbook.learningtest.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.either;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//2-24
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "junit.xml")
public class JunitTest {
	@Autowired
	ApplicationContext context;
	
	static Set<JunitTest> testObjects = new HashSet<>();
	static ApplicationContext contextObject = null;
	
	@Test
	public void test1() {
		assertThat(testObjects,  not(org.junit.matchers.JUnitMatchers.hasItem((this))));
		testObjects.add(this);
		assertThat(contextObject == null || contextObject == this.context,  is(true));
	}
	
	@Test
	public void test3() {
		assertTrue(contextObject == null || contextObject == this.context);
		testObjects.add(this);
	}
	@Test
	public void test2() {
		assertThat(testObjects,  not(org.junit.matchers.JUnitMatchers.hasItem((this))));
		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		testObjects.add(this);
	}
}
