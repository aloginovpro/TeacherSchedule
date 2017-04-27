package com.vbelova.teachers.entity;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNotNull;
import static org.apache.commons.lang3.StringUtils.join;
import static org.junit.Assert.assertTrue;

//checks whether all entities have an empty constructor to avoid database deserialization issues
public class EntityEmptyConstructorTest {

    @Test
    public void test() {
        String[] fileNames = new File(System.getProperty("user.dir") + "/src/main/java/com/vbelova/teachers/entity").list();
        assertNotNull("Entity directory doesn't exist", fileNames);
        List<String> classes = Arrays.stream(fileNames).map(it -> it.replace(".java", ""))
                .map(this::getClass)
                .filter(it -> !it.isInterface())
                .filter(it -> !hasEmptyConstructor(it))
                .map(Class::getSimpleName)
                .collect(Collectors.toList());
        assertTrue("Classes don't have empty constructor: " + join(classes, ", "), classes.isEmpty());
        System.out.println();
    }

    private Class getClass(String className) {
        try {
            return Class.forName("com.vbelova.teachers.entity." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasEmptyConstructor(Class clazz) {
        return Arrays.stream(clazz.getConstructors())
                .anyMatch(it -> it.getParameterCount() == 0);
    }

}
