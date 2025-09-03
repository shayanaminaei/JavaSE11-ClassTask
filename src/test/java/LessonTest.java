import mftplus.model.entity.Lesson;
import mftplus.model.repository.LessonRepository;
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


        try (LessonRepository lessonRepository = new LessonRepository()) {
            lessonRepository.save(lesson);
        }






        LessonService.getService().save(lesson);
    }
}
