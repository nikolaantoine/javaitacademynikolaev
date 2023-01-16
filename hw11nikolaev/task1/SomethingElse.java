package hw11nikolaev.task1;

public class SomethingElse {
    public void toNumber(String string1, String string2, String string3, String string4) throws
            MyNumberFormatException, MyOwnNullPointerException, MyExceptionInInitializerError {
        try {
            int i1 = Integer.parseInt(string1.trim());
            int i2 = Integer.parseInt(string2.trim());
            int i3 = Integer.parseInt(string3.trim());
            int i4 = Integer.parseInt(string4.trim());

            if (i2 < 0 || i3 < 0 || i4 < 0) {
                throw new MyNumberFormatException("Введено отрицательное число");
            }

            if (i1 < -9  || i1 > 9 || i2 < -9 || i2 > 9 || i3 < -9 || i3 > 9 || i4 < -9 || i4 > 9) {
                throw new MyExceptionInInitializerError("Должны быть числа");
            }

            System.out.print(i1);
            System.out.print(i2);
            System.out.print(i3);
            System.out.print(i4);

        } catch (NullPointerException nullPointerException) {
            throw new MyOwnNullPointerException("Незаполненный элемент");
        } catch (NumberFormatException numberFormatException) {
            throw new MyNumberFormatException("Введен неправильный символ");
        }
    }
}
