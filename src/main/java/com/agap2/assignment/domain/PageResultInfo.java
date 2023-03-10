package com.agap2.assignment.domain;

import java.io.Serializable;

public class PageResultInfo<V> implements Serializable {

    private static final long serialVersionUID = -1L;

    private V data = null;

    private boolean succ = false;

    private String msg;

    private int code;

    private int totalPages;

    private long totalRecords;

    private int status = Status.OK;

    public PageResultInfo() {

    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}


	public static <V> PageResultInfo<V> getInstance(V data, boolean isSucc, String msg, int code) {
        PageResultInfo<V> pageResultInfo = new PageResultInfo<V>();
        pageResultInfo.setSucc(isSucc);
        pageResultInfo.setMsg(msg);
        pageResultInfo.setStatus(Status.OK);
        pageResultInfo.setCode(code);
        pageResultInfo.setData(data);
        return pageResultInfo;
    }

	public static <V> PageResultInfo<V> getListInstance(V data, boolean isSucc, String msg, int code,long totalRecords,int totalPages) {
        PageResultInfo<V> pageResultInfo = new PageResultInfo<V>();
        pageResultInfo.setSucc(isSucc);
        pageResultInfo.setMsg(msg);
        pageResultInfo.setStatus(Status.OK);
        pageResultInfo.setCode(code);
        pageResultInfo.setData(data);
        pageResultInfo.setTotalRecords(totalRecords);
        pageResultInfo.setTotalPages(totalPages);
        return pageResultInfo;
    }

    public static <V> PageResultInfo<V> getSucceedInstance(V dataMod) {
        return getInstance(dataMod, true, "SUCCESS", Status.OK);
    }

    public static <V> PageResultInfo<V> getFailInstance() {
        return getInstance(null, false, "ERROR", 0);
    }

    public static <V> PageResultInfo<V> getFailInstance(String msg) {
        return getInstance(null, false, msg, 0);
    }

    public static <V> PageResultInfo<V> getFailInstance(int code, String msg) {
        return getInstance(null, false, msg, code);
    }

    public static <V> PageResultInfo<V> getSucceedInstanceForRegistration(V dataMod,String msg) {
        return getInstance(dataMod, true, msg, Status.OK);
    }

    public static <V> PageResultInfo<V> getSucceedInstanceForLeaseRate(V dataMod,String msg) {
        return getInstance(dataMod, true, msg, Status.OK);
    }

    public static <V> PageResultInfo<V> getSucceedInstanceForUpdate(V dataMod,String msg) {
        return getInstance(dataMod, true, msg, Status.OK);
    }

    public static <V> PageResultInfo<V> getSucceedInstanceForList(V dataMod,long totalRecords, int totalPages) {
        return getListInstance(dataMod, true, "SUCCESS", 0, totalRecords, totalPages);
    }

    public static class Status {

        public static final int INTERNAL_ERROR = 500;
        public static final int NOT_FOUND = 404;
        public static final int FORBIDDEN = 403;
        public static final int OK = 200;
        public static final int TEMP_USER_DISALLOW = 101;
        public static final int NOT_LOGIN = 100;

    }


}
