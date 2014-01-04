package com.gao.lambda;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午2:55
 */
public interface CheckPerson {

    /**
     * 检查某个人是否符合一定的标准
     * @param person
     * @return
     */
    boolean test(Person person);
}

class CheckPersonEligibleForSelectService implements CheckPerson{
    @Override
    public boolean test(Person person) {
        return person.getGender() == Person.Sex.FEMALE &&
                person.getBirthday() > 18 &&
                person.getBirthday() < 50;
    }
}


