package codestates.preproject.stackoverflow.post.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagMap {

    static String[] arr = {"JAVA", "REACT", "HTML", "JAVASCRIPT", "CONTROLLER"};

    public static List<String> sendTag(List<Integer> list) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            answer.add(arr[list.get(i)]);
        }
        return answer;
    }
}
