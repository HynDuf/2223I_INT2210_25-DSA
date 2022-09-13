1. `TenHelloWorlds.java`:

```java
package uet.Week1;

public class TenHelloWorlds {
    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Hello, World");
        }
    }
}
```

2.

-   `public`:

    -   Bỏ `public` ra khỏi hàm `main` thì chương trình vẫn compile nhưng khi chạy
        thì sẽ bị lỗi

        `Error: Main method not found in class uet.Week1.TenHelloWorlds, please define the main method as: public static void main(String[] args) or a JavaFX application class must extend javafx.application.Application`

    -   Bỏ `public` ra khỏi class thì chạy bình thường.

-   `static`: compile thì được nhưng chạy sẽ bị lỗi

    `Error: Main method is not static in class uet.Week1.TenHelloWorlds, please define the main method as: public static void main(String[] args)`

-   `void`: lỗi khi compile: `return type required`

-   `args`: compile được nhưng lúc chạy không được bị lỗi không tìm được hàm main.

3. Lỗi biên dịch

4.

-   `java Hi`: lỗi truy cập `args[0]`: `Index 0 out of bounds for length 0`
-   `java Hi @!&^%`: Chạy bình thường, in ra `Hi, @%&^%. How are you ?`
-   `java Hi 1234`: Chạy bình thường, in ra `Hi, 1234. How are you ?`
-   `java Hi.class Bob`: Lỗi
    `Error: Could not find or load main class Hi.class Caused by: java.lang.ClassNotFoundException: Hi.class`
-   `java Hi.java Bob`: Chạy bình thường, in ra `Hi, Bob. How are you?`
-   `java Hi Alice Bob`: Chạy bình thường, in ra `Hi, Alice. Hơ are you?`

5. `HiThree.java`

```java
public class HiThree {
    public static void main(String[] args) {
        System.out.print("Hi " + args[2] + ", " + args[1] + ", and " + args[0] + ".");
    }
}
```

6. `Initials.java`

```java
public class Initials {
    public static void main(String[] args) {
        System.out.println("**      **    ********   *******");
        System.out.println("**      **    ********   **     **");
        System.out.println("**      **       **      **      **");
        System.out.println("**********       **      **       **");
        System.out.println("**********       **      **        **");
        System.out.println("**********       **      **       **");
        System.out.println("**      **       **      **      **");
        System.out.println("**      **       **      **     **");
        System.out.println("**      **       **      *******");
    }
}
```

7. Compile được nhưng lúc chạy không được bị lỗi không tìm được hàm main (vì
   thiếu `String[] args`).
