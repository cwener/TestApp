package com.example.myannotation;

/**
 * Created by chengwen on 2019-07-02
 */
public interface TrackInfoProvide {
    /**
     * 通过类名查找足迹定义信息
     *
     * @param className
     * @return
     */
    public String getTrackNameByClass(String className);
}
