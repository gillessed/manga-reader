package com.gillessed.daedalus.rest.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by gcole on 5/20/16.
 */
public class Languages {
    public static final String ENGLISH = "English";
    public static final String JAPANESE = "日本語";
    public static final String CHINESE = "中文";
    public static final String KOREAN = "한국어";

    public static final List<String> ALL = Lists.newArrayList(ENGLISH, JAPANESE, CHINESE, KOREAN);
}
