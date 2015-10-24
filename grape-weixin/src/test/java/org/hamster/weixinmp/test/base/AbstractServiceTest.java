package org.hamster.weixinmp.test.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 测试基础类,加载配置文件信息
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext-test-weixinmp.xml" })
public abstract class AbstractServiceTest extends
        AbstractJUnit4SpringContextTests {

}
