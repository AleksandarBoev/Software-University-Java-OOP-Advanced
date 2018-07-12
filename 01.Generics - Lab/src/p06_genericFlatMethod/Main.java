package p06_genericFlatMethod;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BaseClass> baseClassList = new ArrayList<>();

        List<SubClass1> subClass1List = new ArrayList<>(){{
            add(new SubClass1());
            add(new SubClass1());
            add(new SubClass2());
        }};

        List<SubClass2> subClass2List = new ArrayList<>(){{
           add(new SubClass2());
           add(new SubClass2());
           add(new SubClass2());
        }};

        List<List<? extends BaseClass>> listOfListsOfSubClasses = new ArrayList<>();
        listOfListsOfSubClasses.add(subClass1List);
        listOfListsOfSubClasses.add(subClass2List);

        ListUtils.flatten(baseClassList, listOfListsOfSubClasses);
        System.out.println(baseClassList.size());
    }
}
