/**
 * User: wangchen.gpx
 * Date: 13-7-8
 * Time: 下午1:32
 */
public class SizeTest {
    public static void main(String[] args) {
        int[] a = {3,5,6};
        System.out.println(a[0]);

        for (int i = a.length - 1; i >= 0; i--) {
            System.out.println(a[i]);
        }

        String s = "2345667";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            stringBuilder.append(s);
        }

        String s1 = stringBuilder.toString();
        System.out.println(s1.getBytes().length);
    }
}
