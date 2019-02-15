package com.feishuixiansheng.flyingwater.bean;

/**
 * @author dupengfei
 * @create 2019/2/14 0014
 * @Describe
 */
public class TestBean {

    Bean2 bean2;


    public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }



    public static class Bean2{
        String namename;
        String id;

        public String getNamename() {
            return namename;
        }

        public void setNamename(String namename) {
            this.namename = namename;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
