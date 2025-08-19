import mftplus.model.entity.enums.EducationGrade;
import mftplus.model.entity.enums.Role;

public class App {
    public static void main(String[] args) {
        for (EducationGrade value : EducationGrade.values()) {
            System.out.println(value);
        }
    }
}
