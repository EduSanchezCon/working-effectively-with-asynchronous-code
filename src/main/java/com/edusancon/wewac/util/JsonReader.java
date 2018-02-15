package com.edusancon.wewac.util;

import com.edusancon.wewac.students.model.Course;
import com.edusancon.wewac.students.model.Practice;
import com.edusancon.wewac.students.model.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public enum JsonReader {
    COURSES("courses.json", Course.class),
    PRACTICES("practices.json", Practice.class),
    STUDENTS("students.json", Student.class);

    private List elements;

    JsonReader(String fileName, Class myClass){
        ClassLoader classLoader = getClass().getClassLoader();
        final InputStream resource = classLoader.getResourceAsStream(fileName);
        final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            elements = mapper.readValue(resource, mapper.getTypeFactory().constructCollectionType(List.class, myClass));
        } catch (IOException e) {
            elements = Collections.EMPTY_LIST;
        }
    }

    public List get(){
        return  elements;
    }
}
