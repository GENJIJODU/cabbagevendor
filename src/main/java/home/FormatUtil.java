package home;

public class FormatUtil {
    public static String goldFormat(Integer total) {
        String output = "";
        Integer gold = Math.floorDiv(total, 10000);
        Integer silver = Math.floorDiv((total-gold*10000), 100);
        Integer copper = total - (gold*10000) - (silver*100);
        if (gold > 0) {

        }
        if (silver > 0) {

        }
        if (copper > 0) {

        }

        return output;
    }
}
