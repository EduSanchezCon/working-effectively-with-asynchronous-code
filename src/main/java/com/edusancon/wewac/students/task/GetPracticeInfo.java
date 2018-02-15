package com.edusancon.wewac.students.task;

import com.edusancon.wewac.students.model.Practice;
import com.edusancon.wewac.util.JsonReader;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GetPracticeInfo implements Function<Integer, Optional<Practice>> {


    @Override
    public Optional<Practice> apply(final Integer practiceId) {

        List<Practice> practices = JsonReader.PRACTICES.get();
        return practices.stream().filter(x -> x.getId() == practiceId).findAny();
    }
}
