package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private int sum = 0;

    public int run(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        List <Integer> nums =parseInput(input);

        for(Integer num : nums){
            sum += num;
        }

        return sum;
    }

    public List<Integer> parseInput(String input) {
        List<Integer> result = new ArrayList<>();
        String[] tokens = input.split(",");
        for (String token : tokens) {
            result.add(Integer.parseInt(token));
        }
        return result;
    }
}
