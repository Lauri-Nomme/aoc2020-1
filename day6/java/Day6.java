import java.io.FileInputStream;
import static java.lang.Integer.bitCount;

public class Day6 {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("./input.txt");
        
        int person = 0;
        int groupV2 = -1;
        int groupV1 = 0;
        long answer1 = person;
        long answer2 = person;

        while (true) {
            int nextByte = in.read();
            if ((nextByte & 0xe0) == 0x60) { // 0x60-0x7f (not EOF and covers [a-z])
                person |= 1 << (nextByte & 0x1f);  // turn on bit for this letter
            } else if (person == 0) { // must be EOF or '\n', if person == 0 then we are at the end of the group or at the end of the file.
                answer1 += bitCount(groupV1);
                answer2 += bitCount(groupV2);
                groupV1 = 0;
                groupV2 = -1; // short version of 0xFFFFFFFF;
                if (nextByte == -1) break; // end of stream in Java
            } else {
                groupV1 |= person;
                groupV2 &= person;
                person = 0;
            }
        }

        System.out.println("Answer1: " + answer1 + ", answer2: " + answer2);
    }
}