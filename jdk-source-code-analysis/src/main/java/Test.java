public class Test {

    static class Father {
        public int a = 1;
        public Father() {
            System.out.println("===");
            a = 2;
            print();
        }
        public void print() {
            System.out.println("father " + a);
        }
    }

    static class Son extends Father {
        public int a = 3;
        public Son() {
            System.out.println("----");
            a = 4;
            print();
        }
        public void print() {
            System.out.println("son " + a);
        }
    }

    public static void main(String[] args) {
        Son f = new Son();
        System.out.println(f.a);
    }
}
