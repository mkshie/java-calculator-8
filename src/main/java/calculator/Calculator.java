package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator {

    private String delimiter  = "[,:]";
    private static final String POSITIVE_INT = "^[1-9]\\d*$";

    public int run(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        if(input.isEmpty()){ //빈 값 0으로 예외 처리
            return 0;
        }


        String number;
        number = parseHeaderAndApplyDelimiter(input);
        if (number == null) return 0;

        List <Integer> numsList = parseNumbers(number);

        int sum = 0;
        for(Integer num : numsList){
            sum += num;
        }

        return sum;
    }

    private String parseHeaderAndApplyDelimiter(String input) {
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
                return null;
            }
        }
        else{
            number = input;
        }
        return number;
    }

    public List<Integer> parseNumbers(String number) {
        List<Integer> result = new ArrayList<>();
        String[] tokens = number.split(delimiter);
        for (String token : tokens) {
            token = token.trim();

            if (token.isEmpty()) {
                throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
            }

            if (!token.matches(POSITIVE_INT)) {
                throw new IllegalArgumentException("양의 정수만 입력하세요: " + token);
            }

            result.add(Integer.parseInt(token));
        }
        return result;
    }
}
