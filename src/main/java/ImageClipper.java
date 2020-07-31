import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImageClipper {

    private static final String SOURCE_FOLDER = "source";

    public static void main(String args[]) {
        List<String> imageList = getImagesFromSource();
        SeleniumUtils utils = new SeleniumUtils();
        for (String image : imageList) {
            utils.clipImage(image);
        }


    }

    private static List<String> getImagesFromSource() {
        File sourceDir = new File(SOURCE_FOLDER);
        File images[] = sourceDir.listFiles();
        return Arrays.stream(images).map(File::getAbsolutePath).collect(Collectors.toList());
    }

}
