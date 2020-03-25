package com.flm.dto;

public class ExecutionResultDTO {
	
	private double inclusionInSeconds;
	
	private double batchInsertionInSeconds;

	public double getInclusionInSeconds() {
		return inclusionInSeconds;
	}

	public void setInclusionInSeconds(double inclusionInSeconds) {
		this.inclusionInSeconds = inclusionInSeconds;
	}

	public double getBatchInsertionInSeconds() {
		return batchInsertionInSeconds;
	}

	public void setBatchInsertionInSeconds(double batchInsertionInSeconds) {
		this.batchInsertionInSeconds = batchInsertionInSeconds;
	}
}
