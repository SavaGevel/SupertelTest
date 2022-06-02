import java.util.*;

public class Port {
    
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    private final List<String> indexes;

    public Port(List<String> indexes) {
        this.indexes = indexes;
    }

    public List<String> getIndexes() {
        return indexes;
    }

    public List<List<Integer>> getNumericalIndexes() {

        List<List<Integer>> numericalSequences = new ArrayList<>();
        for(String index : indexes) {

            List<Integer> numericalSequence = new ArrayList<>();

            String[] numbers = index.split(COMMA);

            for (String number : numbers) {
                number = number.trim();
                if (number.length() == 1) {
                    numericalSequence.add(Integer.parseInt(number));
                } else {
                    String[] interval = number.split(HYPHEN);

                    int leftBorder = Integer.parseInt(interval[0]);
                    int rightBorder = Integer.parseInt(interval[1]);

                    while (leftBorder <= rightBorder) {
                        numericalSequence.add(leftBorder);
                        leftBorder++;
                    }
                }
            }
            numericalSequences.add(numericalSequence);
        }
        return numericalSequences;
    }

    public List<List<Integer>> getOrderedParesOfElements() {

        List<List<Integer>> numericalIndexes = getNumericalIndexes();

        List<List<Integer>> pares = new ArrayList<>();
        Map<Integer, Integer> pointerPositionsForIndexes = new HashMap<>();

        for(int i = 0; i < numericalIndexes.size(); i++) {
            pointerPositionsForIndexes.put(i, 0);
        }

        int numberOfPares = numericalIndexes.stream().mapToInt(List::size).reduce(1, (a, b) -> a * b);
        boolean overflow = false;

        while(pares.size() < numberOfPares) {

            List<Integer> pare = new ArrayList<>();
            for(int i = numericalIndexes.size() - 1; i >= 0; i--) {

                int position = pointerPositionsForIndexes.get(i);

                if(overflow) {
                    position++;
                    overflow = false;
                }
                if(position == numericalIndexes.get(i).size()) {
                    position = 0;
                    overflow = true;
                }
                pointerPositionsForIndexes.put(i, position);
                pare.add(numericalIndexes.get(i).get(position));
            }
            Collections.reverse(pare);
            pares.add(pare);
            overflow = true;
        }

        return pares;
    }

}
