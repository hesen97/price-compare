package com.hesen.crawler.task;

import com.hesen.crawler.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class testItemTask extends BaseTest {

    @Autowired
    private ItemTaskJD itemTaskJD;

    @Autowired
    private ItemTaskTM itemTaskTM;

    @Test
    public void testTaskJD() throws Exception {
        itemTaskJD.itemTask();
    }

    @Test
    public void testTaskTM() throws Exception {
        itemTaskTM.itemTask();
    }
}
