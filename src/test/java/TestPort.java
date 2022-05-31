import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestPort {

    @Test
    public void testGetNumericalIndexes() {
        List<String> indexes = new ArrayList<>();
        indexes.add("1,3-5");
        indexes.add("2");
        indexes.add("3-4");

        Port port = new Port(indexes);

        List<List<Integer>> numericalIndexes = new ArrayList<>();
        numericalIndexes.add(Stream.of(1, 3, 4, 5).collect(Collectors.toList()));
        numericalIndexes.add(Stream.of(2).collect(Collectors.toList()));
        numericalIndexes.add(Stream.of(3, 4).collect(Collectors.toList()));

        assertEquals(numericalIndexes, port.getNumericalIndexes());

    }

    @Test
    public void testGetNumericalIndexesWithOneElement() {
        List<String> indexes = new ArrayList<>();
        indexes.add("2");

        Port port = new Port(indexes);

        List<List<Integer>> numericalIndexes = new ArrayList<>();
        numericalIndexes.add(Stream.of(2).collect(Collectors.toList()));

        assertEquals(numericalIndexes, port.getNumericalIndexes());
    }

    @Test
    public void testGetOrderedParesOfElements() {
        List<String> indexes = new ArrayList<>();
        indexes.add("1,3-5");
        indexes.add("2");
        indexes.add("3-4");

        Port port = new Port(indexes);

        List<List<Integer>> orderedParesOfElements = new ArrayList<>();
        orderedParesOfElements.add(Stream.of(1, 2, 3).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(1, 2, 4).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(3, 2, 3).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(3, 2, 4).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(4, 2, 3).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(4, 2, 4).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(5, 2, 3).collect(Collectors.toList()));
        orderedParesOfElements.add(Stream.of(5, 2, 4).collect(Collectors.toList()));

        assertEquals(orderedParesOfElements, port.getOrderedParesOfElements());
    }

}
