package com.gao.lambda;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 20:16
 */
public class ConvertorMain {
    public static void main(String[] args) {
        Convertor<String, Integer> convertor = (from) -> Integer.valueOf(from);

        System.out.println(convertor.convert("232"));

        Convertor<String, Integer> convertor1 =  Integer::valueOf;
        System.out.println(convertor1.convert("111"));


        //convert
        Somthing somthing = new Somthing();
        Convertor<String, String> convertor2 = somthing::startWith;
        System.out.println(convertor2.convert("444"));


        PersonFactory<PersonTest> personPersonFactory = PersonTest::new;
        PersonTest personTest = personPersonFactory.create("first", "second");
        System.out.println(personTest);


    }

    static class Somthing {
        String startWith(String s){
            return String.valueOf(s.charAt(0));
        }
    }
}

interface PersonFactory<PersonTest>{

    PersonTest create(String firstname, String secondname);
}

class PersonTest{
    private String firstname;

    private String secondname;

    PersonTest() {
    }

    PersonTest(String firstname, String secondname) {
        this.firstname = firstname;
        this.secondname = secondname;
    }
}
