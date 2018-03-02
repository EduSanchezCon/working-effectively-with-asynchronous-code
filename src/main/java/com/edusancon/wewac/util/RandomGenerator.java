package com.edusancon.wewac.util;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomGenerator {

    private static final int MIN_COLLECTION_SIZE = 1;
    private static final int MAX_COLLECTION_SIZE = 5;

    private volatile static EnhancedRandom defaultEnhancedRandom;

    private static EnhancedRandom getDefaultRandomizer() {

        if (defaultEnhancedRandom == null) {
            synchronized (RandomGenerator.class) {
                if (defaultEnhancedRandom == null) {
                    defaultEnhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().collectionSizeRange(1, 5)
                            .scanClasspathForConcreteTypes(true).build();
                }
            }
        }
        return defaultEnhancedRandom;

    }


    public static Object getRandomObject(Type type) {
        if (type instanceof ParameterizedType) {

            ParameterizedType pType = (ParameterizedType) type;
            Type rawType = pType.getRawType();
            Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

            if (TypeUtils.isAssignable(rawType, List.class)) {

                int size = new Random().nextInt((MAX_COLLECTION_SIZE - MIN_COLLECTION_SIZE) + 1) + MIN_COLLECTION_SIZE;
                return getDefaultRandomizer().objects((Class<?>) parameterType, size).collect(Collectors.toList());

            } else {
                return getDefaultRandomizer().nextObject((Class<?>) rawType);
            }
        }

        return getDefaultRandomizer().nextObject((Class<?>) type);
    }
}

