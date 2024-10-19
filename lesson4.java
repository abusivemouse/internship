public class lesson4 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign(10, -11);
        printColor(10);
        compareNumbers(2,3);
        System.out.println(noTitle(5, 22));
        noTitle2(-2);
        System.out.println(noTitle3(-5));
        noTitle4("Беляева", 4);
        System.out.println(noTitle5(2000));
        massive();
        massive2();
        massive3();
        massive4();
        int[] array = massive5(10, 3);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
    public static void printThreeWords(){
        System.out.println("Orange\nBanana\nApple");
    }
    public static void checkSumSign(int a, int b) {
        System.out.println(a + b >= 0 ? "Сумма положительная" : "Сумма отрицательная" );
    }
    public static void printColor(int value) {
        if (value <= 0){
            System.out.println("Красный");
        } else if (value <= 100 ){
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }
    public static void compareNumbers(int a, int b) {
        System.out.println(a >= b ? "a >= b" : "a < b");
    }
    public static boolean noTitle(int a, int b) {
        return (10 <= a + b) && (a + b <= 20);
    }
    public static void noTitle2(int n) {
        System.out.println(n >= 0 ? "Положительное" : "Отрицательное");
    }
    public static boolean noTitle3(int n) {
        return n < 0;
    }
    public static void noTitle4(String string, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(string);
        }
    }
    public static boolean noTitle5(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
    public static void massive() {
        int[] arr = {1, 1, 0, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            System.out.print("old: " + arr[i]);
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
            System.out.print(" new: " + arr[i]);
            System.out.println();
        }
        System.out.println();
    }
    public static void massive2() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println();
    }
    public static void massive3() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            System.out.print("old: " + arr[i]);
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
            System.out.println(" new: " + arr[i]);
        }
        System.out.println();
    }
    public static void massive4() {
        int[] [] table = new int[9][9] ;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((i == j) || (i + j == 8)) {
                    table[i][j] = 1;
                }
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int[] massive5(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}
