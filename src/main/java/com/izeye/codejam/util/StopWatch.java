package com.izeye.codejam.util;

import lombok.Data;

/**
 * Created by izeye on 15. 3. 13..
 */
@Data
public class StopWatch {

	private long startTime;
	private long endTime;

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public void stop() {
		endTime = System.currentTimeMillis();
	}

	public long getElapsedTime() {
		return endTime - startTime;
	}

}
