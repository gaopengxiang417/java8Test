package com.gao.annotaion;

/**
 * User: wangchen
 * Date: 14/12/6
 * Time: 16:14
 */
@CustomAnnotaionClass(author = "new wangchen", date = "2014-12-07")
public class AnnotatedClass {

    @CustomAnnotaionMethod(author = "first wangchen", date = "2014-12-03", description = "annotated method")
    public String annotatedMethod() {
        return "nothing doing";
    }
}
