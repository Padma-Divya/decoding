import java.math.BigInteger;
import org.apache.commons.codec.binary.Base32;

public class Decoder {

    public static void main(String[] args) {
       /* String input = "2277114742311135167021343424004141432061264036716605455" +
                "35070012425145143366515462107042711115720106717127670062" +
                "71704657770433346073017047360217626325467150763006577133" +
                "54152655466766041402716542311111131505761476052650004524" +
                "21616177052165224543311447543654741617367042213645643631" +
                "33346575330621635642541636644326535501666004333326756424" +
                "47003252221104064117622317044717471111";*/

        String input = "31646541";
        String encoding = "octal";
        byte[] result = decodeString(input, encoding);

        // Print the result
        for (byte b : result) {
            System.out.print(b + " ");
        }
    }

    public static byte[] decodeString(String input, String encoding) {
        // Check if the input string is not empty
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        // Convert the input string based on the specified encoding
        BigInteger decimalValue;
        switch (encoding.toLowerCase()) {
            case "hexadecimal":
                decimalValue = new BigInteger(input, 16);
                break;
            case "octal":
                decimalValue = new BigInteger(input, 8);
                break;
            case "base32":
                // Assuming base32 is a custom encoding,
                decimalValue = decodeBase32(input);
                break;
            // Add more cases for other encodings as needed
            default:
                throw new IllegalArgumentException("Unsupported encoding: " + encoding);
        }

        // Convert the decimal value to a byte array
        return convertToByteArray(decimalValue);
    }

    private static byte[] convertToByteArray(BigInteger decimalValue) {
        // Convert the BigInteger to a byte array
        byte[] byteArray = decimalValue.toByteArray();

        // If the most significant bit is set, BigInteger.toByteArray() adds an extra byte
        // Remove the leading zero byte if present
        if (byteArray.length > 1 && byteArray[0] == 0) {
            byte[] trimmedArray = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, trimmedArray, 0, trimmedArray.length);
            return trimmedArray;
        }

        return byteArray;
    }

    private static BigInteger decodeBase32(String input) {
        Base32 base32 = new Base32();

        // Decode the string
        byte[] decodedBytes = base32.decode(input);

        // Convert the decoded bytes to a string (assuming it represents text)
        BigInteger decodedBigInteger = new BigInteger(decodedBytes);

        return decodedBigInteger;

    }
}
