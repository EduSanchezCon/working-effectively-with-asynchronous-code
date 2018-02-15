package com.edusancon.wewac.util;

import com.edusancon.wewac.util.JsonSerializer;

import java.io.Serializable;

public abstract class JsonWritable implements Serializable{

    @Override
    public String toString() {
        return JsonSerializer.PRETTY.serialize(this);
    }
}
