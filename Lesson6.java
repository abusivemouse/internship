public class Lesson6 {
    public static class Person {
        private String FIO;
        private String position;
        private String email;
        private String phone;
        private int salary;
        private int age;

        public Person(String FIO, String position, String email, String phone, int salary, int age) {
            this.FIO = FIO;
            this.position = position;
            this.email = email;
            this.phone = phone;
            this.salary = salary;
            this.age = age;
        }

        public void info() {
            System.out.println("ФИО: " + FIO);
            System.out.println("Должность: " + position);
            System.out.println("Email: " + email);
            System.out.println("Телефон: " + phone);
            System.out.println("Зарплата: " + salary);
            System.out.println("Возраст: " + age);
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Беляева Александра Сергеевна", "обучающийся", "asyabeliaevas@gmail.com", "+7(123)456-789", 300000, 20);
        person.info();
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Person("Butusov Vyacheslav", "Architect", "butusov@gmail.com", "892312323", 45000, 40);
        persArray[2] = new Person("Dirkin Veniamin", "Teacher", "veniamin@gmail.com", "895456644", 20000, 29);
        persArray[3] = new Person("Gurbanguli Berdimuhamedov", "President", "recGB@mail.ru", "84562343", 1000000, 40);
        persArray[4] = new Person("Kinchev Konstantin", "Builder", "alisa@rambler.ru", "89765432", 15000, 50);
        for (int i = 0; i < persArray.length; i++) {
            persArray[i].info();
        }
        Park.Attraction attraction = new Park().new Attraction("Kamikadze", "10 minutes", 100);
        System.out.println("Информация: " + attraction.info);
        System.out.println("Время: " + attraction.time);
        System.out.println("Ценв: " + attraction.price);
    }

    public static class Park {
        public class Attraction {
            private String info;
            private String time;
            private int price;

            public Attraction(String info, String time, int price) {
                this.info = info;
                this.time = time;
                this.price = price;
            }
        }
    }
}
