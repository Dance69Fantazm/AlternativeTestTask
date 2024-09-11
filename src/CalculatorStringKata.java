import java.util.Scanner;

public class CalculatorStringKata {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любую строку: ");
        String string = scanner.nextLine();
        try {
            System.out.println(calculateStringResult(string));
        } catch (NumberFormatException e) {
            System.err.println("Нельзя число делить и умножать на строку!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Строка не должна быть пустой");
        }
    }

    public static String calculateStringResult(String stringResult) throws NumberFormatException {
        StringBuilder modifiedStr = new StringBuilder();
        char symbol;
        char firstSymbol;
        char lastSymbol;
        String[] arrString;
        int elementsArrString;

        for (int i = 0; i < stringResult.length(); i++) {
            symbol = stringResult.charAt(i);
            firstSymbol = stringResult.charAt(0);
            lastSymbol = stringResult.charAt(stringResult.length() - 1);

            switch (symbol) {
                case '"':
                    i++;
                    while (stringResult.charAt(i) != '"') {
                        i++;
                    }
                    break;
                case '+':
                    arrString = stringResult.replaceAll("\"", "").split(" \\+ ");
                    if (arrString[0].length() > 10 || arrString[1].length() > 10) {
                        System.err.println("Одна из строк превышает допустимую длинну 10 символов");
                    } else if (Character.isDigit(firstSymbol) || Character.isDigit(lastSymbol)) {
                        System.err.println("Первый или последний аргумент не должен быть числом!");
                    } else {
                        modifiedStr.append(arrString[0].concat(arrString[1]))
                                .insert(0, "\"")
                                .insert(modifiedStr.length(), "\"");
                    }
                    break;
                case '-':
                    arrString = stringResult.trim().replaceAll("\"", "").split(" - ");
                    elementsArrString = arrString[0].indexOf(arrString[1]);
                    if (arrString[0].length() > 10 || arrString[1].length() > 10) {
                        System.err.println("Одна из строк превышает допустимую длинну 10 символов");
                    } else if (Character.isDigit(firstSymbol) || Character.isDigit(lastSymbol)) {
                        System.err.println("Оба аргумента должны быть строками!");
                    } else {
                        if (elementsArrString == -1) {
                            modifiedStr.append(arrString[0])
                                    .insert(0, "\"")
                                    .insert(modifiedStr.length(), "\"");
                        } else {
                            String oneStrMinusSecondStr = arrString[0].substring(0, elementsArrString);
                            oneStrMinusSecondStr += arrString[0].substring(elementsArrString + arrString[1].length());
                            modifiedStr.append(oneStrMinusSecondStr)
                                    .insert(0, "\"")
                                    .insert(modifiedStr.length(), "\"");
                        }
                    }
                    break;
                case '/':
                    arrString = stringResult.trim().replaceAll("\"", "").split(" / ");
                    elementsArrString = Integer.parseInt(arrString[1]);
                    if (arrString[0].length() > 10) {
                        System.err.println("Одна из строк превышает допустимую длинну 10 символов");
                    } else if (Character.isDigit(firstSymbol)) {
                        System.err.println("Первый аргумент не должен быть числом!");
                    } else if (elementsArrString < 0 || elementsArrString > 10) {
                        System.err.println("Число должно быть от 1 до 10");
                    } else {
                        for (int j = 0; j < arrString[0].length() / elementsArrString; j++) {
                            symbol = arrString[0].charAt(j);
                            modifiedStr.append(symbol);
                        }
                        modifiedStr.insert(0, "\"")
                                .insert(modifiedStr.length(), "\"");
                    }
                    break;
                case '*':
                    arrString = stringResult.trim().replaceAll("\"", "").split(" \\* ");
                    elementsArrString = Integer.parseInt(arrString[1]);

                    if (arrString[0].length() > 10) {
                        System.err.println("Одна из строк превышает допустимую длинну 10 символов");
                    } else if (elementsArrString < 0 || elementsArrString > 10) {
                        System.err.println("Число должно быть от 1 до 10");
                    } else if (Character.isDigit(firstSymbol) || !Character.isDigit(lastSymbol)) {
                        System.err.println("Первый аргумент не должен быть числом!");
                    } else {
                        modifiedStr = new StringBuilder(elementsArrString * arrString[0].length());
                        modifiedStr.append(arrString[0].repeat(elementsArrString))
                                .insert(0, "\"")
                                .insert(modifiedStr.length(), "\"");
                    }
                    break;
            }
        }
        if (modifiedStr.length() > 40) {
            String endStr = "..." + "\"";
            return modifiedStr.substring(0, 40).concat(endStr);
        } else {
            return modifiedStr.toString().trim();
        }
    }
}
