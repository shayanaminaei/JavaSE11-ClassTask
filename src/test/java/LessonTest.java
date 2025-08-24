import mftplus.model.entity.Lesson;
import mftplus.model.service.LessonService;

import java.time.LocalDate;

public class LessonTest {
    public static void main(String[] args) throws Exception {
        Lesson lesson = Lesson
                .builder()
                .personId(1)
                .title("math")
                .code(123)
                .teacher("mrs.azizi")
                .unit("first")
                .startDateTime(LocalDate.of(2022,1,3))
                .build();


        LessonService.getService().save(lesson);
    }
}
