package com.truemega.dto.utils;

public class UploadStatus {
	boolean noActionTaken = false;
	boolean approved = false;
	boolean rejected = false;
	boolean tempData = false;
	boolean existRecord = true;
	int fileUploadId = -1;

	public boolean isNoActionTaken() {
		return noActionTaken;
	}

	public void setNoActionTaken(boolean noActionTaken) {
		this.noActionTaken = noActionTaken;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public boolean isTempData() {
		return tempData;
	}

	public void setTempData(boolean tempData) {
		this.tempData = tempData;
	}

	public int getFileUploadId() {
		return fileUploadId;
	}

	public void setFileUploadId(int fileUploadId) {
		this.fileUploadId = fileUploadId;
	}

	public boolean isExistRecord() {
		return existRecord;
	}

	public void setExistRecord(boolean existRecord) {
		this.existRecord = existRecord;
	}
}
