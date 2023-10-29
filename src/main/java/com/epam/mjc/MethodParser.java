package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] parts = signatureString.split("\\s+");

        String accessModifier = null;
        String returnType = null;
        String methodName = parts[parts.length - 1];

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (parts.length >= 2) {
            returnType = parts[parts.length - 2];
        }

        if (parts.length >= 3) {
            accessModifier = parts[0];
        }

        String argsString = signatureString.substring(signatureString.indexOf('(') + 1, signatureString.indexOf(')'));

        String[] args = argsString.split(",\\s*");

        for (String arg : args) {
            String[] argParts = arg.split("\\s+");
            if (argParts.length == 2) {
                arguments.add(new MethodSignature.Argument(argParts[0], argParts[1]));
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
