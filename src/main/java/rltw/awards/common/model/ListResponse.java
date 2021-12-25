package rltw.awards.common.model;

import java.util.List;

public class ListResponse<T> {
    private List<T> data;
    private long size;

    public ListResponse(List<T> data, long size) {
        this.data = data;
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
