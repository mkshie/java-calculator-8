package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator {

    private int sum = 0;
    private String delimiter  = "[,:]";
    private final String marker = "\\n";
    private String number;

    public int run(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        if(input.isEmpty()){ //빈 값 0으로 예외 처리
            return 0;
        }


        String number;
        if(input.startsWith("//")){

            String marker = "\\n";
            int idx = input.indexOf(marker);
            if(idx < 0) throw new IllegalArgumentException("\\n 이 존재하지 않습니다.");

            String customDelimiter = input.substring(2 , idx);

            customDelimiter = Pattern.quote(customDelimiter);

            delimiter = delimiter + "|"  + customDelimiter;
            number = input.substring(idx + marker.length());

            if(number.isEmpty()){
                return 0;
            }
        }
        else{
            number = input;
        }

        List <Integer> numsList =parseInput(number);

        for(Integer num : numsList){
            if(num < 1) throw new IllegalArgumentException();
            sum += num;
        }

        return sum;
    }

    public List<Integer> parseInput(String number) {
        List<Integer> result = new ArrayList<>();
        String[] tokens = number.split(delimiter);
        for (String token : tokens) {
            result.add(Integer.parseInt(token));
        }
        return result;
    }
}
