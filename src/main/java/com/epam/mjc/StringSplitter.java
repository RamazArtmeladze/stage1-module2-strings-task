package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> substrings = new ArrayList<>();
        int startIndex = 0;

        while (startIndex < source.length()) {
            int nextDelimiterIndex = getNextDelimiterIndex(source, startIndex, delimiters);

            if (nextDelimiterIndex == -1) {
                substrings.add(source.substring(startIndex));
                break;
            }

            substrings.add(source.substring(startIndex, nextDelimiterIndex));
            startIndex = nextDelimiterIndex + 1;
        }

        return substrings;
    }

    private int getNextDelimiterIndex(String source, int startIndex, Collection<String> delimiters) {
        int minIndex = -1;

        for (String delimiter : delimiters) {
            int index = source.indexOf(delimiter, startIndex);

            if (index != -1 && (minIndex == -1 || index < minIndex)) {
                minIndex = index;
            }
        }

        return minIndex;

    }
}
